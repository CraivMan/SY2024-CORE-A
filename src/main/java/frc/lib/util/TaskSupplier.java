package frc.lib.util;

@FunctionalInterface
public interface TaskSupplier {
    
    /**
     * Gets a result.
     *
     * @return a result
     */
    Task getAsTask();
}
