#!/bin/bash
CASCAIO_CONFIGURE_LOG="/tmp/cascaio-configure.log"
CASCAIO_ROOT="$( cd "$( dirname "$0" )/../" && pwd )"
CASCAIO_CONFIG_DIR="$CASCAIO_ROOT/setup/configuration"

##### erase the log file
printf "Erasing log file... "
echo "" > $CASCAIO_CONFIGURE_LOG
if [ $? -eq 0 ]
then
    printf "Done!\r\n"
else
    printf "Failed!"
    exit -1
fi

# location for the temporary key files
CASCAIO_KEYCLOAK_REALM_PRIVATE_KEY_FILE="/tmp/cascaio-private.pem"
CASCAIO_KEYCLOAK_REALM_PUBLIC_KEY_FILE="/tmp/cascaio-public.pub"

# first, we copy the template over to a new file
if [ -d $CASCAIO_CONFIG_DIR ]
then
    rm -rf $CASCAIO_CONFIG_DIR
fi
mkdir -p $CASCAIO_CONFIG_DIR
cp $CASCAIO_ROOT/setup/templates/*json $CASCAIO_CONFIG_DIR/
cp $CASCAIO_ROOT/setup/templates/*cli $CASCAIO_CONFIG_DIR/
cp $CASCAIO_ROOT/setup/templates/*conf $CASCAIO_CONFIG_DIR/

# we generate some UUIDs
UUID_BIN=`which uuidgen 2>>$CASCAIO_CONFIGURE_LOG`
if [ "x$UUID_BIN" == "x" ]
then
    echo "'uuidgen' is required, to generate the application secrets."
    exit -1
fi

printf "Generating UUIDs for the applications... "
CASCAIO_KEYCLOAK_APPLICATIONS_FRONTEND_SECRET=`$UUID_BIN`
CASCAIO_KEYCLOAK_APPLICATIONS_ADMIN_SECRET=`$UUID_BIN`
CASCAIO_KEYCLOAK_APPLICATIONS_BACKEND_SECRET=`$UUID_BIN`
printf "Done!\r\n"

# we generate the private/public key pair
OPENSSL_BIN=`which openssl 2>>$CASCAIO_CONFIGURE_LOG`
if [ "x$OPENSSL_BIN" == "x" ]
then
    echo "'openssl' is required, to generate the application's public/private key pair."
    exit -1
fi

printf "Generating key pair for the realm... "
$OPENSSL_BIN genrsa -out $CASCAIO_KEYCLOAK_REALM_PRIVATE_KEY_FILE 1024 >> $CASCAIO_CONFIGURE_LOG 2>&1
$OPENSSL_BIN rsa -in $CASCAIO_KEYCLOAK_REALM_PRIVATE_KEY_FILE -pubout > $CASCAIO_KEYCLOAK_REALM_PUBLIC_KEY_FILE 2>>$CASCAIO_CONFIGURE_LOG
CASCAIO_KEYCLOAK_REALM_PRIVATE_KEY=$(cat $CASCAIO_KEYCLOAK_REALM_PRIVATE_KEY_FILE | grep -v "PRIVATE KEY" | tr -d '\n')
CASCAIO_KEYCLOAK_REALM_PUBLIC_KEY=$(cat $CASCAIO_KEYCLOAK_REALM_PUBLIC_KEY_FILE | grep -v "PUBLIC KEY" | tr -d '\n')
printf "Done!\r\n"

printf "Removing temporary key pair files... "
rm -f $CASCAIO_KEYCLOAK_REALM_PRIVATE_KEY_FILE
rm -f $CASCAIO_KEYCLOAK_REALM_PUBLIC_KEY_FILE
printf "Done!\r\n"

# we source the values from the configuration.properties... 
# perhaps the user wants a specific UUID already configured at other time
if [ -f $CASCAIO_ROOT/setup/configuration.properties ]
then
    printf "Retrieving configuration values from $CASCAIO_ROOT/setup/configuration.properties ... "
    source $CASCAIO_ROOT/setup/configuration.properties
    printf "Done!\r\n"
fi

# then, we override the ones from the user's home directory
if [ -f ~/.cascaiorc ]
then
    printf "Retrieving configuration values from ~/.cascaiorc ... "
    source ~/.cascaiorc
    printf "Done!\r\n"
else
    printf "ERROR: The file ~/.cascaiorc doesn't exists. Make sure you have the appropriate values there\r\n"
    exit -1
fi

# now, we check which vars we need to replace, all of them starting with CASCAIO_
VAR_LIST_REPLACE=$(set | grep -Eo '^CASCAIO_.*=' | sed 's/\=//gi')

printf "Starting replace of placeholders with the proper values ... "
for var in $VAR_LIST_REPLACE
do
    val=$(eval echo \$$var)
    if [ "x$val" == "x" ]
    then
        printf "ERROR: The property $var is empty. Make sure to set the appropriate values on ~/.cascaiorc\r\n"
        exit -1
    fi
    sed -i "s|${var}|${val}|gi" $CASCAIO_CONFIG_DIR/*
done
printf "Done!\r\n"

echo "Finished configuration. The configuration files are ready for review at $CASCAIO_CONFIG_DIR"
