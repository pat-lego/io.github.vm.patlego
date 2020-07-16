package patlego.vm.github.io.datasource;

import javax.annotation.Nonnull;

import patlego.vm.github.io.datasource.utils.DataResource;

/**
 * This class allows users to retrieve a data resource which can be used to retrieve data.
 * This interface when implemented in an OSGi framework requires the following properties to be added
 * 
 * 1. DATASOURCE_NAME Which defines the name of the datasource to be retrieved
 */
public interface KarafDB_DAO {

    /**
     * Used to retrieve a data resource which provides access to the data source in question
     * @return DataResource
     */
    public @Nonnull DataResource getDataResource();
    
}