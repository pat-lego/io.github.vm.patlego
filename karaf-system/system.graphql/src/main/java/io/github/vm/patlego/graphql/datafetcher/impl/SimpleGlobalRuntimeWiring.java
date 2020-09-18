package io.github.vm.patlego.graphql.datafetcher.impl;

import java.util.LinkedList;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import graphql.schema.DataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.RuntimeWiring.Builder;
import io.github.vm.patlego.graphql.datafetcher.DataFetcherEntry;
import io.github.vm.patlego.graphql.datafetcher.GlobalRuntimeWiring;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

@Component(immediate = true, service = GlobalRuntimeWiring.class)
public class SimpleGlobalRuntimeWiring implements GlobalRuntimeWiring {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<DataFetcherEntry> datafetchers;

	@Override
	public RuntimeWiring build() throws Exception {
		if (this.datafetchers == null) {
            return RuntimeWiring.newRuntimeWiring().build();
        }

        Builder bulderManager = newRuntimeWiring();
        for(DataFetcherEntry datafetcher : datafetchers) {
            DataFetcher e = datafetcher.get();
            bulderManager.type(datafetcher.typename(), builder -> builder.dataFetcher(datafetcher.fieldname(), e));
        }
        return bulderManager.build();
    }
    
    @Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    public void bind(DataFetcherEntry datafetcher) {
        if (datafetchers == null) {
            datafetchers = new LinkedList<DataFetcherEntry>();
        }

        datafetchers.add(datafetcher);
    }
    protected void unbind(DataFetcherEntry datafetcher) {
        datafetchers.remove(datafetcher);
    }
    
    @Activate
    protected void activate() {
        this.datafetchers = new LinkedList<DataFetcherEntry>();
        logger.info(String.format("The %s service is now active", this.getClass().getName()));
    }

    @Deactivate
    protected void deactivate() {
        this.datafetchers = null;
        logger.info(String.format("The %s service has been deactivated", this.getClass().getName()));
    }
    
}
