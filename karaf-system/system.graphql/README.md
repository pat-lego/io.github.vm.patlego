# System GraphQL

The GraphQL module exposes 3 services and a servlet in which will allow developers to easily interact and deploy Graph services.

##  Services

- GlobalSchema 
    This service creates the global schema based off of all of the implementations of the SchemaEntry services that exist in the current runtime environment
- GlobalRuntimeWiring
    This service retrieves all of the DataFetcher classes that are in the current run time environment and returns them in a RuntimeWiring instance
- GraphQLExecutor
    This service has access to the Global schema and will execute the incoming queery against the global schema

## Servlet

The `/graphql` servlet exposes the main functionality of the GraphQL infrastructure and allows users to query the run time environment for data

