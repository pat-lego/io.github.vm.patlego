package io.github.vm.patlego.graphql.schema;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SchemaEntryTest {

    @Test
    public void testClass() throws Exception {
        SchemaEntry entry_1 = Mockito.mock(SchemaEntry.class);
        entry_1.name();
        entry_1.description();
        entry_1.get();
        Mockito.verify(entry_1, Mockito.times(1)).description();
        Mockito.verify(entry_1, Mockito.times(1)).name();
        Mockito.verify(entry_1, Mockito.times(1)).get();
    }
    
}
