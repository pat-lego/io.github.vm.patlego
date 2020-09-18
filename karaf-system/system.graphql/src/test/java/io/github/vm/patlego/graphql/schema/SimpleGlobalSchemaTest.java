package io.github.vm.patlego.graphql.schema;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.github.vm.patlego.graphql.datafetcher.GlobalRuntimeWiring;
import io.github.vm.patlego.graphql.datafetcher.impl.SimpleGlobalRuntimeWiring;
import io.github.vm.patlego.graphql.schema.impl.SimpleGlobalSchema;

public class SimpleGlobalSchemaTest {

    @Test
    public void testSetup() {
        SchemaEntry entry_1 = Mockito.mock(SchemaEntry.class);
        SchemaEntry entry_2 = Mockito.mock(SchemaEntry.class);
        SimpleGlobalSchema globalSchema = new SimpleGlobalSchema();

        globalSchema.bind(entry_1);
        globalSchema.bind(entry_2);
    }

    @Test
    public void testStitching() throws Exception {
        SchemaEntry entry_1 = new SchemaEntry1();
        SchemaEntry entry_2 = new SchemaEntry2();
        SimpleGlobalSchema globalSchema = new SimpleGlobalSchema();

        globalSchema.runtimeWiring = new SimpleGlobalRuntimeWiring();

        globalSchema.bind(entry_2);
        globalSchema.bind(entry_1);

        globalSchema.build();
    }

    public class SchemaEntry1 implements SchemaEntry {

        @Override
        public InputStream get() throws Exception {
            return IOUtils.toInputStream(String.join("\n",
                "type hero {",
                  "name: String",
                  "appearsIn: String",
                "}"), StandardCharsets.UTF_8);
        }

        @Override
        public String name() {
            return this.getClass().getName();
        }

        @Override
        public String description() {
            return this.getClass().getName();
        }

    }

    public class SchemaEntry2 implements SchemaEntry {

        @Override
        public InputStream get() throws Exception {
            return IOUtils.toInputStream(String.join("\n",
                "type Query {",
                  "hello: String",
                "}"), StandardCharsets.UTF_8);
        }

        @Override
        public String name() {
            return this.getClass().getName();
        }

        @Override
        public String description() {
            return this.getClass().getName();
        }

    }
}
