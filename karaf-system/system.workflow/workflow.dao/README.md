# System DAO

This module exposes various methods to communicate with the database.

The System DAO leverages JPA and Hibernate to manage the data being written/read to the database.

## Services

1. WorkflowMangerDS

        This interface allows users to read/write workflow transactions. This is not to be used by a developer directly as the WorkflowManager uses this 
        interface to communicate with the database.