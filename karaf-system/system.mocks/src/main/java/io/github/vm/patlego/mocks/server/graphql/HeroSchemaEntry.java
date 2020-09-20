package io.github.vm.patlego.mocks.server.graphql;

import java.io.InputStream;

import org.osgi.service.component.annotations.Component;

import io.github.vm.patlego.graphql.schema.SchemaEntry;

@Component(immediate = true, service = SchemaEntry.class)
public class HeroSchemaEntry implements SchemaEntry {

    @Override
    public InputStream get() {
        return this.getClass().getResourceAsStream("/graphql/schemas/hero.graphql");
    }

    @Override
    public String name() {
        return "Hero Schema";
    }

    @Override
    public String description() {
        return "Hero Description";
    }
    
}
