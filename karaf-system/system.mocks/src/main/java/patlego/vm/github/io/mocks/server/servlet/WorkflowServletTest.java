package patlego.vm.github.io.mocks.server.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import patlego.vm.github.io.workflow.WorkflowExecutor;
import patlego.vm.github.io.workflow.WorkflowManager;
import patlego.vm.github.io.workflow.utils.WorkItemManagerResult;
import patlego.vm.github.io.workflow.utils.WorkflowManagerResult;
import patlego.vm.github.io.workflow.utils.WorkflowResult;

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
        WorkflowResult result = workflowExecutor.run("serverWorkflow1");
        String workflowId = result.getId();

        WorkflowManagerResult managerResult = this.workflowManager.getWorklowInstanceInformation(workflowId);
        List<WorkItemManagerResult> items =  managerResult.getWorkItems();

        this.workflowManager.removeWorkflow(workflowId);
    }
    
}