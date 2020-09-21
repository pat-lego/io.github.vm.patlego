package io.github.vm.patlego.mocks.server.graphql;

import org.osgi.service.component.annotations.Component;

import graphql.schema.DataFetcher;
import graphql.schema.StaticDataFetcher;
import io.github.vm.patlego.graphql.datafetcher.DataFetcherEntry;

@Component(immediate = true, service = DataFetcherEntry.class)
public class HelloDataFetcherEntry implements DataFetcherEntry {

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
