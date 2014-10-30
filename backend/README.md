Backend
===

The following needs to be installed/configured to get a fully working backend:

- PostgreSQL server on localhost with username ``cascaiobackend`` and password ``cascaiobackend``. If you want to use different credentials, change the ``cascaio-wildfly.cli`` file.
- Wildfly 8.1.0.Final with Keycloak adapter
- PostgreSQL JDBC driver
- Run ``cascaio-wildfly.cli``
- Deploy ``backend.war``
- Login into Keycloak as admin
- Import the ``keycloak-realm.json``

Those steps (except the last ones) can be accomplished with those commands, on a Fedora 20 host:

    $ sudo yum install -y postgresql-jdbc
    $ tar xzf /path/to/wildfly-8.1.0.Final.tar.gz
    $ cd $WILDFLY_HOME
    $ unzip -q /path/to/keycloak-wildfly-adapter-dist*.zip
    $ cp -R /path/to/keycloak-dist/deployments/* standalone/deployments/
    $ cp /usr/share/java/postgresql-jdbc.jar $WILDFLY_HOME/standalone/deployments/
    $ $WILDFLY_HOME/bin/standalone.sh
    $ $WILDFLY_HOME/bin/jboss-cli.sh \
      --connect \
      --file=$CASCAIO_ROOT/backend/src/main/resources/cascaio-wildfly.cli
    $ cp $CASCAIO_ROOT/target/backend.war $WILDFLY_HOME/standalone/deployments/

