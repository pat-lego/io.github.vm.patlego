package io.github.vm.patlego.mocks.server.graphql;

import java.io.InputStream;

import org.osgi.service.component.annotations.Component;

import io.github.vm.patlego.graphql.schema.SchemaEntry;

@Component(immediate = true, service = SchemaEntry.class)
public class HelloSchemaEntry implements SchemaEntry {

    @Override
    public InputStream get() {
        return this.getClass().getResourceAsStream("/graphql/schemas/helloworld.graphql");
    }

    @Override
    public String name() {
        return "Hello Schema";
    }

    @Override
    public String description() {
        return "Simple Hello World Schema";
    }
    
}
