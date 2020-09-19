package io.github.vm.patlego.graphql.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import io.github.vm.patlego.graphql.executor.GraphQLExecutor;

@Component(property = { "alias=/graphql", "servlet-name=GraphQL Servlet" })
public class GraphQLServlet extends HttpServlet implements Servlet {

    @Reference
    private GraphQLExecutor executor;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        InputStream query = request.getInputStream();
        JsonObject result = this.executor.execute(query);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(result.toString());
        response.getWriter().flush();
        response.getWriter().close();        
    }
    
}
