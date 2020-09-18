package io.github.vm.patlego.graphql.datafetcher;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DataFetcherEntryTest {
    @Test
    public void testClass() throws Exception {
        DataFetcherEntry entry_1 = Mockito.mock(DataFetcherEntry.class);
        entry_1.get();
        Mockito.verify(entry_1, Mockito.times(1)).get();
    }
}
