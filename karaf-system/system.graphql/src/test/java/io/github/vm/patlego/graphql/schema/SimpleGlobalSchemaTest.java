package io.github.vm.patlego.graphql.schema;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.github.vm.patlego.graphql.datafetcher.SimpleGlobalRuntimeWiringTest;
import io.github.vm.patlego.graphql.datafetcher.impl.SimpleGlobalRuntimeWiring;
import io.github.vm.patlego.graphql.executor.impl.SimpleGraphQLExecutor;
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
    public void testStitchingHero() throws Exception {
        SimpleGlobalSchema globalSchema = new SimpleGlobalSchema();
        globalSchema.bind(new SimpleGlobalSchemaTest.HeroSchema());
        globalSchema.bind(new SimpleGlobalSchemaTest.HelloSchema());

        SimpleGlobalRuntimeWiring globalRuntimeWiring = new SimpleGlobalRuntimeWiring();
        globalRuntimeWiring.bind(new SimpleGlobalRuntimeWiringTest.HeroDataFetcher());
        globalRuntimeWiring.bind(new SimpleGlobalRuntimeWiringTest.HelloWorldDataFetcher());

        globalSchema.runtimeWiring = globalRuntimeWiring;

        SimpleGraphQLExecutor executor = new SimpleGraphQLExecutor();
        executor.schema = globalSchema;
        JsonObject result = executor.execute(this.getClass().getResourceAsStream("/queries/hero.graphql"));
        assertNotNull(result);
        assertTrue(result.toString().toLowerCase().contains("batman"));
    }

    @Test
    public void testStitchingHello() throws Exception {
        SimpleGlobalSchema globalSchema = new SimpleGlobalSchema();
        globalSchema.bind(new SimpleGlobalSchemaTest.HeroSchema());
        globalSchema.bind(new SimpleGlobalSchemaTest.HelloSchema());

        SimpleGlobalRuntimeWiring globalRuntimeWiring = new SimpleGlobalRuntimeWiring();
        globalRuntimeWiring.bind(new SimpleGlobalRuntimeWiringTest.HeroDataFetcher());
        globalRuntimeWiring.bind(new SimpleGlobalRuntimeWiringTest.HelloWorldDataFetcher());

        globalSchema.runtimeWiring = globalRuntimeWiring;

        SimpleGraphQLExecutor executor = new SimpleGraphQLExecutor();
        executor.schema = globalSchema;
        JsonObject result = executor.execute(this.getClass().getResourceAsStream("/queries/hello.graphql"));
        assertNotNull(result);
        assertTrue(result.toString().toLowerCase().contains("world"));
    }

    @Test
    public void testActivate() {
        SimpleGlobalSchema globalSchema = new SimpleGlobalSchema();
        globalSchema.activate();
    }

    @Test
    public void testDeactivate() {
        SimpleGlobalSchema globalSchema = new SimpleGlobalSchema();
        globalSchema.deactivate();
    }

    @Test
    public void testBind() {
        SchemaEntry entry = Mockito.mock(SchemaEntry.class);
        SimpleGlobalSchema globalSchema = new SimpleGlobalSchema();
        globalSchema.bind(entry);
        globalSchema.unbind(entry);
    }

    public static class HeroSchema implements SchemaEntry {

        @Override
        public InputStream get() {
            return this.getClass().getResourceAsStream("/schemas/hero.graphql");
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

    public static class HelloSchema implements SchemaEntry {

        @Override
        public InputStream get() {
            return this.getClass().getResourceAsStream("/schemas/hello.graphql");
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
