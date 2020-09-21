package io.github.vm.patlego.graphql.datafetcher;

import javax.annotation.Nonnull;

import graphql.schema.DataFetcher;

public interface DataFetcherEntry<T> {

    /**
     * Creates a DataFetcher for a given object and can be incorporated within the{@link graphql.schema.idl.RuntimeWiring}
     * @return Returns a {@link graphql.schema.idl.TypeRuntimeWiring.Builder} and can be incorporated within the Runtime envrionment
     */
    public @Nonnull DataFetcher<T> get();

    /**
     * Returns the name of the object
     * @return java.lang.String
     */
    public @Nonnull String typename();

    /**
     * Returns the name of the field that the DataFetcher is associated to
     * @return java.lang.String
     */
    public @Nonnull String fieldname();
}
