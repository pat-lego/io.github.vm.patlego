package io.github.vm.patlego.graphql.executor;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import graphql.GraphQLError;
import io.github.vm.patlego.graphql.executor.impl.SimpleGraphQLExecutor;

public class SimpleGraphQLExecutorTest {

    @Test
    public void testNull() {
        SimpleGraphQLExecutor executor = new SimpleGraphQLExecutor();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            executor.execute(null);
        });
    }

    @Test
    public void testLogErrors() {
        GraphQLError error_1 = Mockito.mock(GraphQLError.class);
        GraphQLError error_2 = Mockito.mock(GraphQLError.class);
        Mockito.when(error_1.getMessage()).thenReturn("That is not good!");
        Mockito.when(error_2.getMessage()).thenReturn("That is really not good!");

        List<GraphQLError> list = new LinkedList<GraphQLError>();
        list.add(error_1);
        list.add(error_2);

        SimpleGraphQLExecutor graphql = new SimpleGraphQLExecutor();
        graphql.logErrors(list);


    }
    
}
