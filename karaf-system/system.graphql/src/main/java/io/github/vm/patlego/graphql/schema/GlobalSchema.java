package io.github.vm.patlego.graphql.schema;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import graphql.schema.GraphQLSchema;

public interface GlobalSchema {

    /**
     * Builds a global schema by retrieving all of the SchemEntry components and merges them together
     * @return GraphQLSchema - can be null if there are no registered {@link io.github.vm.patlego.graphql.schema.SchemaEntry} within the OSGi runtime environment
     * otherwise the {@link graphql.schema.GraphQLSchema} is returned
     * @throws Exception - Can be a descendant from SchemaEntry or from within the implementation of the build function
     */
    public @CheckForNull GraphQLSchema build() throws Exception;
    
}
