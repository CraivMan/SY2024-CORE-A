package frc.lib.util;

import java.lang.Object;

@FunctionalInterface
public interface ObjectSupplier {

    /**
     * Gets a result.
     *
     * @return a result
     */
    Object getAsObject();
}
