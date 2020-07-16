package patlego.vm.github.io.utils;

import java.util.Map;

public interface WorkObject {

    /**
     * Returns the parameters that are part of the map prior to the beginning of the WorkItem execution
     * @return Map
     */
    public Map<String, Object> getParameters();
}