package io.github.vm.patlego.graphql.schema;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.github.vm.patlego.graphql.datafetcher.SimpleGlobalRuntimeWiringTest;
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
        SimpleGlobalSchema globalSchema = new SimpleGlobalSchema();
        globalSchema.bind(new SimpleGlobalSchemaTest.HeroSchema());
        globalSchema.bind(new SimpleGlobalSchemaTest.HelloSchema());

        SimpleGlobalRuntimeWiring globalRuntimeWiring = new SimpleGlobalRuntimeWiring();
        globalRuntimeWiring.bind(new SimpleGlobalRuntimeWiringTest.HeroDataFetcher());
        globalRuntimeWiring.bind(new SimpleGlobalRuntimeWiringTest.HelloWorldDataFetcher());

        globalSchema.runtimeWiring = globalRuntimeWiring;

        GraphQLSchema schema = globalSchema.build();
        GraphQL graphQL = GraphQL.newGraphQL(schema).build();
        String query = IOUtils.toString(this.getClass().getResourceAsStream("/queries/hero.graphql"), "UTF-8");
        ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query).build();
        ExecutionResult executionResult = graphQL.execute(executionInput);
        assertTrue(executionResult.isDataPresent());
        System.out.println(executionResult.getData().toString());
        Gson gson = new Gson();
        System.out.println(gson.toJson(executionResult.toSpecification()));
    }

    public static class HeroSchema implements SchemaEntry {

        @Override
        public InputStream get() throws Exception {
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
        public InputStream get() throws Exception {
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
