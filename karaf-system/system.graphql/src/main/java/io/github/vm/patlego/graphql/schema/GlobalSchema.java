package io.github.vm.patlego.graphql.schema;

import javax.annotation.Nonnull;

import graphql.schema.GraphQLSchema;

public interface GlobalSchema {

    /**
     * Builds a global schema by retrieving all of the SchemEntry components and merges them together
     * @return GraphQLSchema
     * @throws Exception - Can be a descendant from SchemaEntry or from within the implementation of the build function
     */
    public @Nonnull GraphQLSchema build() throws Exception;
    
}
