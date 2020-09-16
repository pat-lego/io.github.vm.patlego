package io.github.vm.patlego.graphql.datafetcher;

import javax.annotation.Nonnull;

import graphql.schema.idl.RuntimeWiring;

public interface GlobalRuntimeWiring {
    
    /**
     * Retrieves all of the DataFetchers from the OSGi runtime and builds a global {@link graphql.schema.idl.RuntimeWiring} containing all of the given 
     * {@link io.github.vm.patlego.graphql.datafetcher.DataFetcherEntry} classes
     * @return RutimeWiring
     * @throws Exception - Failed to parse build the RuntimeWiring or the creation of one of the DataFetcher classes from the DataFetcherEntry failed
     */
    public @Nonnull RuntimeWiring build() throws Exception;
}
