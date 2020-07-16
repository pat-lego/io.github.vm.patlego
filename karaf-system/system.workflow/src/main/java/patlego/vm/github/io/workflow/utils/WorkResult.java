package patlego.vm.github.io.workflow.utils;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface WorkResult {

    /**
     * Returns true if no execeptions where caught during the execution of the WorkItem
     * Return false otherwise
     * @return True -> WorkItem completed successsfully, False -> An exception was caught
     */
    public @Nonnull Boolean haSucceeded();

    /**
     * If an exception has occured then it will be stored in here and can be referred to upon a later time
     * @return Exception
     */
    public @Nullable Exception getException();

    /**
     * Returns the parameters that are part of the map upon completion of the WorkItem
     * @return Map
     */
    public @Nonnull Map<String, Object> getParameters();
    
}