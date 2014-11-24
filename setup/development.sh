#!/bin/bash

if [ -f ~/.cascaiorc ]
then
    source ~/.cascaiorc
fi

## configure these variables, if not defined on ~/.cascaiorc
KEYCLOAK_APPLIANCE_ARCHIVE="${KEYCLOAK_APPLIANCE_ARCHIVE:-/home/jpkroehling/Projects/keycloak/distribution/appliance-dist/target/keycloak-appliance-dist-all-1.1.0.Beta2-SNAPSHOT.zip}"
DEST_DIR="${DEST_DIR:-/usr/local/data/tools/servers/cascaio/cascaio-with-keycloak-server}"
WILDFLY_HOME="${WILDFLY_HOME:-$DEST_DIR/keycloak-appliance-dist-all-1.1.0.Beta2-SNAPSHOT/keycloak}"

## no change is needed from here and on

# setup some other variables
CASCAIO_LOG="/tmp/cascaio-setup.log"
CASCAIO_ROOT="$( cd "$( dirname "$0" )/../" && pwd )"
CASCAIO_CONFIG_DIR="$CASCAIO_ROOT/setup/configuration"
CASCAIO_REALM_FILE=$CASCAIO_CONFIG_DIR/keycloak-realm-template.json
echo "" > $CASCAIO_LOG

printf "Configuring the templates... "
$CASCAIO_ROOT/setup/configure-templates.sh >> $CASCAIO_LOG 2>&1
if [ $? -eq 0 ]
then
    printf "Done!\r\n"
else
    printf "Failed! Check $CASCAIO_LOG for details\r\n"
    exit -1
fi

printf "Copying the keycloak.json for admin... "
cp $CASCAIO_CONFIG_DIR/keycloak-admin.json $CASCAIO_ROOT/admin/app/keycloak.json >> $CASCAIO_LOG 2>&1
if [ $? -eq 0 ]
then
    printf "Done!\r\n"
else
    printf "Failed! Check $CASCAIO_LOG for details\r\n"
    exit -1
fi

printf "Copying the keycloak.json for frontend... "
cp $CASCAIO_CONFIG_DIR/keycloak-frontend.json $CASCAIO_ROOT/frontend/app/keycloak.json >> $CASCAIO_LOG 2>&1
if [ $? -eq 0 ]
then
    printf "Done!\r\n"
else
    printf "Failed! Check $CASCAIO_LOG for details\r\n"
    exit -1
fi

printf "Installing PostgreSQL JDBC Driver..."
sudo yum install -y postgresql-jdbc >> $CASCAIO_LOG 2>&1
if [ $? -eq 0 ]
then
    printf "Done!\r\n"
else
    printf "Failed! Check $CASCAIO_LOG for details\r\n"
    exit -1
fi

printf "Removing (if exists) and re-creating $DEST_DIR ..."
if [ -d $DEST_DIR ]
then
    rm -rf $DEST_DIR
fi
mkdir -p $DEST_DIR >> $CASCAIO_LOG 2>&1
if [ $? -eq 0 ]
then
    printf "Done!\r\n"
else
    printf "Failed! Check $CASCAIO_LOG for details\r\n"
    exit -1
fi

printf "Installing Keycloak appliance at $DEST_DIR ..."
cd $DEST_DIR
unzip -q $KEYCLOAK_APPLIANCE_ARCHIVE >> $CASCAIO_LOG 2>&1
if [ $? -eq 0 ]
then
    printf "Done!\r\n"
else
    printf "Failed! Check $CASCAIO_LOG for details\r\n"
    exit -1
fi

printf "Installing PostgreSQL JDBC Driver into the Application Server..."
cp /usr/share/java/postgresql-jdbc.jar $WILDFLY_HOME/standalone/deployments/ >> $CASCAIO_LOG 2>&1
if [ $? -eq 0 ]
then
    printf "Done!\r\n"
else
    printf "Failed! Check $CASCAIO_LOG for details\r\n"
    exit -1
fi

printf "Starting the Application Server in background..."
export JBOSS_PIDFILE="/tmp/cascaio-wildfly.pid"
export LAUNCH_JBOSS_IN_BACKGROUND=true
$WILDFLY_HOME/bin/standalone.sh -Dkeycloak.import=$CASCAIO_REALM_FILE > /dev/null 2>&1 & >> $CASCAIO_LOG 2>&1

while [ "x$SERVER_STARTED" == "x" ]
do
    grep "JBAS015874" $WILDFLY_HOME/standalone/log/server.log > /dev/null 2>&1
    if [ $? -eq 0 ]
    then
        SERVER_STARTED=true
        printf " Done!\r\n"
    else
        grep "JBAS015875" $WILDFLY_HOME/standalone/log/server.log > /dev/null 2>&1
        if [ $? -eq 0 ]
        then
            echo "Server started with errors. Please, check the server log at $WILDFLY_HOME/standalone/logs/server.log"
            exit -1
        fi
        sleep 0.5
        printf "."
    fi
done
WILDFLY_PID=`cat $JBOSS_PIDFILE`

printf "Setting up the application server... "
$WILDFLY_HOME/bin/jboss-cli.sh --connect --file=$CASCAIO_CONFIG_DIR/cascaio-wildfly.cli >> $CASCAIO_LOG 2>&1
if [ $? -eq 0 ]
then
    printf "Done!\r\n"
else
    printf "Failed! Check $CASCAIO_LOG for details\r\n"
    exit -1
fi

printf "Stopping the Application Server..."
while [ "x$SERVER_STOPPED" == "x" ]
do
    kill $WILDFLY_PID > /dev/null 2>&1
    grep JBAS015950 $WILDFLY_HOME/standalone/log/server.log > /dev/null 2>&1
    if [ $? -eq 0 ]
    then
        SERVER_STOPPED=true
        printf " Done!\r\n"
    else
        sleep 0.1
        printf "."
    fi
done

printf "Building Cascaio. This might take a while if it's the first build..."
cd $CASCAIO_ROOT
mvn clean install -DskipTests >> $CASCAIO_LOG 2>&1
if [ $? -eq 0 ]
then
    printf "Done!\r\n"
else
    printf "Failed! Check $CASCAIO_LOG for details\r\n"
    exit -1
fi

printf "Deploying backend.war into the Application Server... "
cp $CASCAIO_ROOT/backend/target/backend.war $WILDFLY_HOME/standalone/deployments/
if [ $? -eq 0 ]
then
    printf "Done!\r\n"
else
    printf "Failed! Check $CASCAIO_LOG for details\r\n"
    exit -1
fi

printf "Deploying the frontend to the staging environment... "
cd $CASCAIO_ROOT/frontend
node_modules/flightplan/bin/fly.js staging >> $CASCAIO_LOG 2>&1
if [ $? -eq 0 ]
then
    printf "Done!\r\n"
else
    printf "Failed! Check $CASCAIO_LOG for details\r\n"
    exit -1
fi

printf "Deploying the admin to the staging environment... "
cd $CASCAIO_ROOT/admin
node_modules/flightplan/bin/fly.js staging >> $CASCAIO_LOG 2>&1
if [ $? -eq 0 ]
then
    printf "Done!\r\n"
else
    printf "Failed! Check $CASCAIO_LOG for details\r\n"
    exit -1
fi

if [ -d /etc/nginx/conf.d ]
then
    printf "Copying admin.conf to nginx configuration directory... "
    sudo cp $CASCAIO_CONFIG_DIR/admin.conf /etc/nginx/conf.d/ >> $CASCAIO_LOG 2>&1
    if [ $? -eq 0 ]
    then
        printf "Done!\r\n"
    else
        printf "Failed! Check $CASCAIO_LOG for details\r\n"
        exit -1
    fi

    printf "Copying app.conf to nginx configuration directory... "
    sudo cp $CASCAIO_CONFIG_DIR/app.conf /etc/nginx/conf.d/ >> $CASCAIO_LOG 2>&1
    if [ $? -eq 0 ]
    then
        printf "Done!\r\n"
    else
        printf "Failed! Check $CASCAIO_LOG for details\r\n"
        exit -1
    fi
else
    echo "nginx wasn't detected on this system, therefore, it's not automatically configured. Install nginx and copy the configuration files from $CASCAIO_CONFIG_DIR"
fi

printf "Checking that dev.app.cascaio.com exists on /etc/hosts..."
grep dev.app.cascaio.com /etc/hosts > /dev/null 2>>$CASCAIO_LOG
if [ $? -eq 0 ]
then
    printf "Done!\r\n"
else
    sudo echo "127.0.0.1	dev.app.cascaio.com" >> /etc/hosts 2>>$CASCAIO_LOG
    if [ $? -eq 0 ]
    then
        printf "Added!\r\n"
    else
        printf "Failed! Check $CASCAIO_LOG for details\r\n"
        exit -1
    fi
    exit -1
fi

printf "Checking that dev.admin.cascaio.com exists on /etc/hosts..."
grep dev.admin.cascaio.com /etc/hosts > /dev/null 2>>$CASCAIO_LOG
if [ $? -eq 0 ]
then
    printf "Done!\r\n"
else
    sudo echo "127.0.0.1	dev.admin.cascaio.com" >> /etc/hosts 2>>$CASCAIO_LOG
    if [ $? -eq 0 ]
    then
        printf "Added!\r\n"
    else
        printf "Failed! Check $CASCAIO_LOG for details\r\n"
        exit -1
    fi
    exit -1
fi

printf "Checking that dev.api.cascaio.com exists on /etc/hosts..."
grep dev.api.cascaio.com /etc/hosts > /dev/null 2>>$CASCAIO_LOG
if [ $? -eq 0 ]
then
    printf "Done!\r\n"
else
    sudo echo "127.0.0.1	dev.api.cascaio.com" >> /etc/hosts 2>>$CASCAIO_LOG
    if [ $? -eq 0 ]
    then
        printf "Added!\r\n"
    else
        printf "Failed! Check $CASCAIO_LOG for details\r\n"
        exit -1
    fi
    exit -1
fi

printf "Checking that dev.auth.cascaio.com exists on /etc/hosts..."
grep dev.auth.cascaio.com /etc/hosts > /dev/null 2>>$CASCAIO_LOG
if [ $? -eq 0 ]
then
    printf "Done!\r\n"
else
    sudo echo "127.0.0.1	dev.auth.cascaio.com" >> /etc/hosts 2>>$CASCAIO_LOG
    if [ $? -eq 0 ]
    then
        printf "Added!\r\n"
    else
        printf "Failed! Check $CASCAIO_LOG for details\r\n"
        exit -1
    fi
    exit -1
fi

echo ""
echo "All done!"
echo "Start nginx (if not started) Once it's started, "
echo "you can browse the applications on these URLs:"
echo "Frontend: http://dev.app.cascaio.com"
echo "Admin: http://dev.admin.cascaio.com"
echo "Start also the application server, with this command:"
echo "$WILDFLY_HOME/bin/standalone.sh"
