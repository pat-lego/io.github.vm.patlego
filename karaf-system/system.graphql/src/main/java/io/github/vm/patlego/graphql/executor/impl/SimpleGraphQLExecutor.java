package io.github.vm.patlego.graphql.executor.impl;

import java.io.IOException;
import java.io.InputStream;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.errors.SchemaProblem;
import io.github.vm.patlego.graphql.executor.GraphQLExecutor;
import io.github.vm.patlego.graphql.schema.GlobalSchema;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component(service = GraphQLExecutor.class, immediate = true)
public class SimpleGraphQLExecutor implements GraphQLExecutor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference
    public GlobalSchema schema;

    @Override
    public JsonObject execute(InputStream query) throws IllegalArgumentException, IOException {
        if (query == null) {
            throw new IllegalArgumentException(String.format("InputStream cannot be null within the execute definition of the %s class", this.getClass().getName()));
        }
        Gson gson = new Gson();
        GraphQLSchema globalSchema = this.schema.build();
        GraphQL graphQL = GraphQL.newGraphQL(globalSchema).build();
        ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(IOUtils.toString(query, StandardCharsets.UTF_8)).build();
        ExecutionResult executionResult = graphQL.execute(executionInput);
        if (!executionResult.getErrors().isEmpty()) {
            this.logErrors(executionResult.getErrors());
        }
        return JsonParser.parseString(gson.toJson(executionResult.toSpecification())).getAsJsonObject();
    }

    public void logErrors(List<GraphQLError> graphErrors) {
        for (GraphQLError graphError : graphErrors) {
            logger.error(String.format("Caught GraphQL execution error with message %s", graphError.getMessage()));
        }
    }
    
}
