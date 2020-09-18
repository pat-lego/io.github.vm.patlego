package io.github.vm.patlego.graphql.schema.impl;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import io.github.vm.patlego.graphql.datafetcher.GlobalRuntimeWiring;
import io.github.vm.patlego.graphql.schema.GlobalSchema;
import io.github.vm.patlego.graphql.schema.SchemaEntry;

@Component(immediate = true, service = GlobalSchema.class)
public class SimpleGlobalSchema implements GlobalSchema {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<SchemaEntry> graphQLSchemas;

    @Reference
    public GlobalRuntimeWiring runtimeWiring;

    @Override
    public GraphQLSchema build() throws Exception {
       if (this.graphQLSchemas == null) {
           return null;
       }

       final SchemaParser schemaParser = new SchemaParser();
       final SchemaGenerator schemaGenerator = new SchemaGenerator();
       final TypeDefinitionRegistry typeDefinitionRegistry = new TypeDefinitionRegistry();
       for(SchemaEntry entry : this.graphQLSchemas) {
        typeDefinitionRegistry.merge(schemaParser.parse(entry.get()));
       }

       return schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, this.runtimeWiring.build());
    }

    @Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    public void bind(SchemaEntry schema) {
        if (graphQLSchemas == null) {
            graphQLSchemas = new LinkedList<SchemaEntry>();
        }

        graphQLSchemas.add(schema);
    }
    protected void unbind(SchemaEntry schema) {
        graphQLSchemas.remove(schema);
    }
    
    @Activate
    protected void activate() {
        this.graphQLSchemas = new LinkedList<SchemaEntry>();
        logger.info(String.format("The %s service is now active", this.getClass().getName()));
    }

    @Deactivate
    protected void deactivate() {
        this.graphQLSchemas = null;
        logger.info(String.format("The %s service has been deactivated", this.getClass().getName()));
    }
}
