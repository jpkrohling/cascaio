Cascaio
===

Cascaio is an open source web-based Personal Finance Management software.

## Modules

Cascaio is composed of the following modules:

1. API
2. Backend
3. Admin
4. Frontend

### API

The API is a simple set of models that represents the contract between a consumer and the REST API. It shows the possible options for requests and responses to and from the backend. If you are developing an external client, you can use this as the guide for the REST backend endpoints.

### Backend

The Backend is a standard Java EE application meant to be deployed on Wildfly. It makes use of some technologies which might or might not be available in simple Java containers, like Tomcat and Jetty.  A non-exhaustive list of features from Java EE that are used by the backend:

- JPA 2.1
- JAX-RS 2.0
- Batch 1.0
- EJB 3.2
- CDI 1.1
- Bean Validation 1.1

The requests are authenticated and authorized via Keycloak. A special setup is required and the steps are detailed on the Backend's README file. Due to this dependency, it might not be possible to use the Backend in other Application Servers other than Wildfly 8.1 (or later/compatible).

### Frontend

The Frontend is meant to be used by end-users as the main interface for interacting with Cascaio. It's a single-page app bootstrapped with Yeoman, uses CoffeeScript as the language and AngularJS as the main framework. It indirectly uses Bootstrap through Patternfly. A Keycloak JavaScript adapter is also used, for getting bearer tokens from Keycloak to send to the Backend.

### Admin

Meant to be used by administrators of the application, provides an overview of the state of the Backend. For instance, it lists the last Batch executions for all registered jobs, as well as provides an user interface for managing reference data, like "Mutual funds", "Stock Markets" and so on. Similarly to the Frontend, it's a single-page app bootstrapped with Yeoman, uses CoffeeScript as the language and AngularJS as the main framework. It indirectly uses Bootstrap through Patternfly. A Keycloak JavaScript adapter is also used, for getting bearer tokens from Keycloak to send to the Backend.

## Developing

To setup a development environment, there's a helper script located at ``setup/development.sh`` which should prepare your environment, if you are on Fedora 20. If you are on a different system, you can use this script as a base for your own setup script.
The only requirement that is not on the script is to have a PostgreSQL database installed and running, with a database named ``cascaiobackend`` , user ``cascaiobackend`` and password ``cascaiobackend``. If you wish to use different credentials, change the Data Source for Wildfly (``setup/configuration/`` or ``setup/templates/``)



