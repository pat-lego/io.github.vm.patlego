package io.github.vm.patlego.graphql.executor;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;

public interface GraphQLExecutor {

    /**
     * Executes the runtime query based off of the current schemas and datafetchers deployed within the runtime environment
     * @param query - InputStream representing the post
     * @return JsonObject 
     * @throws IOException
     * @throws IllegalArgumentException - null was passed into the execute function
     */
    public @Nonnull JsonObject execute(@Nonnull InputStream query) throws IllegalArgumentException, IOException;
    
}
