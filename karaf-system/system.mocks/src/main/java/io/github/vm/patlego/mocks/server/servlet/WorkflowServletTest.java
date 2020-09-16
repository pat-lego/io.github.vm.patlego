package io.github.vm.patlego.mocks.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import io.github.vm.patlego.workflow.WorkflowExecutor;
import io.github.vm.patlego.workflow.WorkflowManager;
import io.github.vm.patlego.workflow.utils.WorkItemManagerResult;
import io.github.vm.patlego.workflow.utils.WorkflowManagerResult;
import io.github.vm.patlego.workflow.utils.WorkflowResult;

@Component(property = {
    "alias=/mocks/linear-workflow", "servlet-name=Linear Workflow Servlet"
})
public class WorkflowServletTest extends HttpServlet implements Servlet {

    /**
     *
     */
    private static final long serialVersionUID = 4912057057727630514L;


    @Reference(target = "(&(EXECUTION_TYPE=LINEAR)(SYNCHRONOUS=TRUE))")
    private WorkflowExecutor workflowExecutor;

    @Reference
    private WorkflowManager workflowManager;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(PrintWriter writer = response.getWriter()) {
            WorkflowResult result = workflowExecutor.run("serverWorkflow1");
            String workflowId = result.getId();
            writer.write(String.format("Created workflow with ID %s \n", workflowId));
           
            WorkflowManagerResult managerResult = this.workflowManager.getWorklowInstanceInformation(workflowId);
            writer.write(String.format("Workflow started at %s \n", managerResult.getStartTime().toString()));
            writer.write(String.format("Workflow ended at %s \n", managerResult.getEndTime().toString()));

            writer.write(String.format("Workflow status is set to at %b \n", managerResult.getWorkflowSucceddedStatus()));
            List<WorkItemManagerResult> items =  managerResult.getWorkItems();

            writer.write(String.format("Workflow has %d work items within it \n", items.size()));
    
            this.workflowManager.removeWorkflow(workflowId);   

            writer.write(String.format("Deleted workflow and items from the DB with ID %s \n", workflowId));
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    
}