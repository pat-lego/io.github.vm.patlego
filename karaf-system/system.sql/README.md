# System SQL

This module is used to manage the relational database and will perform SQL migrations on the system. 

The SQL tool used to manage the RDBMS is [liquibase](https://www.liquibase.org/), compared to its competitors liquibase uses an abstraction layer to perform migrations allowing for them to be portable.

Get started documentation for liquibase can be found [here](https://www.liquibase.org/get-started)

## Lambda

To generate the lambda function that will manage the RDS instance run the following command `mvn clean install -Pecs-build -Dpostgres.ecs.password=....` this will generate a ZIP file in the target/ folder called `system.sql.zip`.