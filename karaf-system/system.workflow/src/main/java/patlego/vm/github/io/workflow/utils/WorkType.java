package patlego.vm.github.io.workflow.utils;

import javax.annotation.Nonnull;

import patlego.vm.github.io.workflow.enums.ParamType;

public interface WorkType {
    /**
     * Defines the enum type that will be returned giving a high level explanation of
     * what the element is
     * @return ParamType
     */
    public @Nonnull ParamType getType();

    /**
     * Returns a string containing the java classpath of the object.
     * If it in an int then Integer will be returned
     * @return
     */
    public @Nonnull String getClassName();

    /**
     * Returns a string representation of this object
     * @return String representation of this object
     */
    public @Nonnull String toString();
}