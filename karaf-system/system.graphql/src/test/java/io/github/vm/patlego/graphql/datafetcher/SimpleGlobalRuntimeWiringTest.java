package io.github.vm.patlego.graphql.datafetcher;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.StaticDataFetcher;
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
    public void setUpNonMock() {
        DataFetcherEntry entry_1 = new HeroDataFetcher();
        DataFetcherEntry entry_2 = new HelloWorldDataFetcher();

        SimpleGlobalRuntimeWiring runtimeWiring = new SimpleGlobalRuntimeWiring();
        runtimeWiring.bind(entry_1);
        runtimeWiring.bind(entry_2);
    }

    @Test
    public void setUpNull() throws Exception {
        SimpleGlobalRuntimeWiring runtimeWiring = new SimpleGlobalRuntimeWiring();
        runtimeWiring.build();
    }

    @Test
    public void testActivate() {
        SimpleGlobalRuntimeWiring runtimeWiring = new SimpleGlobalRuntimeWiring();
        runtimeWiring.activate();
    }

    @Test
    public void testDectivate() {
        SimpleGlobalRuntimeWiring runtimeWiring = new SimpleGlobalRuntimeWiring();
        runtimeWiring.deactivate();
    }

    @Test
    public void testBind() {
        DataFetcherEntry entry = Mockito.mock(DataFetcherEntry.class);
        SimpleGlobalRuntimeWiring runtimeWiring = new SimpleGlobalRuntimeWiring();
        runtimeWiring.bind(entry);
        runtimeWiring.unbind(entry);
    }

    public static class HelloWorldDataFetcher implements DataFetcherEntry {

        @Override
        public DataFetcher get() {
            return new StaticDataFetcher("world");
        }

        @Override
        public String typename() {
            return "Query";
        }

        @Override
        public String fieldname() {
            return "hello";
        }
    }

    public static class HeroDataFetcher implements DataFetcherEntry {

        @Override
        public DataFetcher get() {
            return new DataFetcher<Hero>() {
				@Override
				public Hero get(DataFetchingEnvironment environment) throws Exception {
					return new Hero();
				}
            };
        }

        @Override
        public String typename() {
            return "Query";
        }

        @Override
        public String fieldname() {
            return "hero";
        }
    }

    public static class Hero {
        public String name;

        public String getName() {
            return "Batman";
        }
    }
}
