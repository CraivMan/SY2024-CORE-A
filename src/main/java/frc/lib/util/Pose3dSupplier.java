package frc.lib.util;

import edu.wpi.first.math.geometry.Pose3d;

/**
 * Wrote this on a whim as a test. If this gets in the actual code, whoo!
 */
@FunctionalInterface
public interface Pose3dSupplier {

    /**
     * Gets a result.
     *
     * @return a result
     */
    Pose3d getAsPose3d();
}