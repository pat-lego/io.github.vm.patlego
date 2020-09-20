package io.github.vm.patlego.mocks.server.graphql;

import javax.xml.crypto.Data;

import org.osgi.service.component.annotations.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import io.github.vm.patlego.graphql.datafetcher.DataFetcherEntry;

@Component(service = DataFetcherEntry.class, immediate = true)
public class HeroDataFetcherEntry implements DataFetcherEntry {

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

    public static class Hero {
        public String name;

        public String getName() {
            return "Batman";
        }
    }

}
