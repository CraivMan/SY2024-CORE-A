package frc.robot.subsystems;

import java.util.Optional;
import java.util.OptionalDouble;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;
import frc.robot.Constants.Vision;

public class BackLimelight extends SubsystemBase {

    private OptionalDouble tx = OptionalDouble.empty();
    private OptionalDouble ty = OptionalDouble.empty();

    public boolean hasTarget() {
        return (Optional.of(LimelightHelpers.getBotPose2d(Vision.BackLimelight.Name)).isPresent());
    }

    public OptionalDouble getLateralAngleToTarget() {
        return tx;
    }

    public OptionalDouble getVerticalAngleToTarget() {
        return ty;
    }


    @Override
    public void periodic() {
        if (hasTarget()) {
            tx = OptionalDouble.of(LimelightHelpers.getTX(Vision.BackLimelight.Name));
            ty = OptionalDouble.of(LimelightHelpers.getTY(Vision.BackLimelight.Name));
        } else {
            tx = OptionalDouble.empty();
            ty = OptionalDouble.empty();
        }
    }

}
