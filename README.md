# Banking & Logging Systems

## Structure
This project is split in 2:

* banking-system - Provides API's for creating/retrieving accounts/clients as well as providing API's for account to account transfers and ability to view respective transactions
* logging-system - Listens on a rabbitMq queue for messages and persists them to DB. This also exposes an API to retrieve messages.


## Technical Specifications

### banking-system
This system depends on 2 things: RabbitMq & Postgres Db. On startup this application is coded to drop all existing tables and data and recreate with sample data in order for postman calls to work.

This system has:

* Basic HTTP Authentication
* Orika implementations to map internal Pojos to external API objects.
* DB locking to prevent account balance  overriding.
* Aspects in place to intercept on any business logic calls and publish logging data to rabbitMQ queue
* Some minor unit tests to test out the transfer logic
* Query Dsl to facilitate querying
* RabbitMq integration to publish messages
* Rest APIs


Basic flow is: Controller -> BusinessService (Intercepted by ActionLogger) -> DataService -> Repository -> DB



### logging-system

This system depends on 2 things: RabbitMq & Postgres Db. On startup this application is coded to drop all existing tables and data and recreate with sample data in order for postman calls to work.

This system has:
* RabbitMQ integration to listen for messages
* Orika implementations to map internal Pojos to external API objects.
* Rest APIs


Basic flow for Api is: Controller -> LoggingService -> Repository -> DB.

Basic flow for message listening is -> LogListener -> LoggingService -> Repository -> DB.


### Docker

Docker has been used to bring up a contained environemnt for both systems as well as rabbitMq and postgres.


## Setup

* In banking-system project directory run: *mvn clean package docker:build*
* In logging-system project directory run: *mvn clean package docker:build*
* In root directory run: *docker-compose up*


## Testing

Postman Collection can be found [here](https://www.getpostman.com/collections/8d6c38abbdd39bbb47d0)

Login credentials are: bank_user/password

## Possible Enhancements & Decisions taken
Unfortunately due to time constraints certain enhancements needed to be cut down. These include:

* Making RabbitMq integration more generic by introducing Spring Integration (DSL)
* Consolidating authenticating users with clients
* Eliminating SimpleDateFormatter in logging-system
* Encryption of client & account ids when caling APIs
* Account number generation
* Maven common module & parent module

