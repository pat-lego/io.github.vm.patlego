package io.github.vm.patlego.graphql.datafetcher;

import javax.annotation.Nonnull;

import graphql.schema.idl.RuntimeWiring;

public interface GlobalRuntimeWiring {
    
    /**
     * Retrieves all of the DataFetchers from the OSGi runtime and builds a global {@link graphql.schema.idl.RuntimeWiring} containing all of the given 
     * {@link io.github.vm.patlego.graphql.datafetcher.DataFetcherEntryTest} classes
     * @return RutimeWiring - the {@link graphql.schema.idl.RuntimeWiring} is returned
     */
    public @Nonnull RuntimeWiring build();
}
