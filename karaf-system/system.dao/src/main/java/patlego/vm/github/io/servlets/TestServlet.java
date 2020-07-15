package patlego.vm.github.io.servlets;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component(property = { "alias=/servlet-example", "servlet-name=Example Servlet" })
public class TestServlet extends HttpServlet implements Servlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter pw = response.getWriter()) {
            pw.write("Hello World from an SCR servlet! fg");
        }
    }
    
}