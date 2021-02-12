# System Workflow

The Workflow module of the server allows users to create workflows and execute them within the JVM.

## Services

Currently this module offers two services with respect to Workflows

1. `WorkflowExecutor`

        The workflow executor allows users to execute a workflow based off of the name of a Workflow.

        Workflows are a logical chaining of WorkItems, a WorkItem is a single unit of code that performs a given task. The WorkItem contains two properties.
            1. SEQUENCE_NUMBER
            2. WORKFLOW_NAME
        
        The sequence number tells the WorkflowExecutor which WorkItem to execute first then second and so on and so forth. 

        The workflow name is used to associate all of the given WorkItems together. For instance all of the given WorkItems who have the same WORKFLOW_NAME will be executed together once the WorkflowExecutor is called to execute a given workflow with the given name.

2. `WorkflowManager`

        The WorkflowManager tracks the execution of Workflows that have been executed within the JVM and writes them to Postgres. 

        This can be used to retrieve previous workflow executions, start time, end time, success of the workflow.

## WorkItems

WorkItems are individual pieces of work that are used to perform a very specific task. 

### WorkItem Interface

The WorkItem exposes the following methods that need to be overriden 

1. execute - Instructions to be executed in the JVM
2. getInputParameters - Parameters exepected by the WorkItem
3. getOutputParameters - Results provided by the WorkItem
4. getWorkItemName - The name of the WorkItem
5. getWorkItemDescription - The description of the WorkItem
6. getWorkItemVersion - The version number of the WorkItem, purely for aesthetics 
7. getSequenceNumber - The index of which the WorkItem will be executed
