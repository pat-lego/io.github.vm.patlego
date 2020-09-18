package io.github.vm.patlego.graphql.datafetcher;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.github.vm.patlego.graphql.datafetcher.impl.SimpleGlobalRuntimeWiring;

public class SimpleGlobalRuntimeWiringTest {

    @Test
    public void setUp() {
        DataFetcherEntry entry_1 = Mockito.mock(DataFetcherEntry.class);
        DataFetcherEntry entry_2 = Mockito.mock(DataFetcherEntry.class);

        SimpleGlobalRuntimeWiring runtimeWiring = new SimpleGlobalRuntimeWiring();
        runtimeWiring.bind(entry_1);
        runtimeWiring.bind(entry_2);
    }

    @Test
    public void setUpNull() throws Exception {
        SimpleGlobalRuntimeWiring runtimeWiring = new SimpleGlobalRuntimeWiring();
        runtimeWiring.build();
    }
}
