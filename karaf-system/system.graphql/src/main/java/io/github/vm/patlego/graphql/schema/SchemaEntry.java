package io.github.vm.patlego.graphql.schema;

import javax.annotation.Nonnegative;

import graphql.schema.GraphQLSchema;

public interface SchemaEntry {
    /**
     * Retrieves a GraphQL to be injected into the system runtime
     * @return GraphQLSchema
     * @throws Exception - Failed to retrieve the GraphQL exception
     */
    public @Nonnegative GraphQLSchema get() throws Exception;
}
