package io.github.vm.patlego.graphql.schema;

import java.io.InputStream;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

public interface SchemaEntry {
    /**
     * Retrieves a GraphQL to be injected into the system runtime
     * @return java.io.InputStream
     * @throws Exception - Failed to retrieve the GraphQL exception
     */
    public @Nonnull InputStream get() throws Exception;

    /**
     * Returns a representative name for the given schema
     * @return java.lang.String - Name for the schema
     */
    public @Nonnull String name();

    /**
     * Returns a description for the schema
     * @return  java.lang.String - Description for the schema
     */
    public @CheckForNull String description();
}
