package patlego.vm.github.io.utils;

import java.util.Map;

public interface WorkResult {

    /**
     * Returns true if no execeptions where caught during the execution of the WorkItem
     * Return false otherwise
     * @return True -> WorkItem completed successsfully, False -> An exception was caught
     */
    public Boolean haSucceeded();

    /**
     * If an exception has occured then it will be stored in here and can be referred to upon a later time
     * @return Exception
     */
    public Exception getException();

    /**
     * Returns the parameters that are part of the map upon completion of the WorkItem
     * @return Map
     */
    public Map<String, Object> getParameters();
    
}