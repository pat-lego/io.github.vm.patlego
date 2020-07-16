package patlego.vm.github.io.workflow.utils;

import java.util.Map;

import javax.annotation.Nonnull;

public interface WorkObject {

    /**
     * Returns the parameters that are part of the map prior to the beginning of the WorkItem execution
     * @return Map
     */
    public @Nonnull Map<String, Object> getParameters();
}