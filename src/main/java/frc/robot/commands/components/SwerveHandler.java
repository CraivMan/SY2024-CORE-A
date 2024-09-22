package frc.robot.commands.components;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.lib.util.Pose3dSupplier;
import frc.robot.Constants;
import frc.robot.subsystems.Swerve;

public class SwerveHandler extends Command {

    private BooleanSupplier booleanSupplier;
    private Pose3dSupplier pose3dSupplier;

    private Swerve s_Swerve;
    private DoubleSupplier translationSup;
    private DoubleSupplier strafeSup;
    private DoubleSupplier rotationSup;
    private BooleanSupplier robotCentricSup;

    private SlewRateLimiter translationLimiter = new SlewRateLimiter(3.0);
    private SlewRateLimiter strafeLimiter = new SlewRateLimiter(3.0);
    private SlewRateLimiter rotationLimiter = new SlewRateLimiter(3.0);

    public SwerveHandler(
      Swerve s_Swerve,
      DoubleSupplier translationSup,
      DoubleSupplier strafeSup,
      DoubleSupplier rotationSup,
      BooleanSupplier robotCentricSup,
      BooleanSupplier booleanSupplier,
      Pose3dSupplier poseSupplier) {
    this.s_Swerve = s_Swerve;
    addRequirements(s_Swerve);

    this.booleanSupplier = booleanSupplier;
    this.pose3dSupplier = poseSupplier;

    this.translationSup = translationSup;
    this.strafeSup = strafeSup;
    this.rotationSup = rotationSup;
    this.robotCentricSup = robotCentricSup;
  }

    @Override
    public void execute() {
        if (booleanSupplier.getAsBoolean()) {
            /* Get Values, Deadband*/
            double translationVal =
                translationLimiter.calculate(
                    MathUtil.applyDeadband(translationSup.getAsDouble(), Constants.Swerve.stickDeadband));
            double strafeVal =
                strafeLimiter.calculate(
                    MathUtil.applyDeadband(strafeSup.getAsDouble(), Constants.Swerve.stickDeadband));
            double rotationVal =
                rotationLimiter.calculate(
                    MathUtil.applyDeadband(rotationSup.getAsDouble(), Constants.Swerve.stickDeadband));

            /* Drive */
            s_Swerve.drive(
                new Translation2d(translationVal, strafeVal).times(Constants.Swerve.maxSpeed),
                rotationVal * Constants.Swerve.maxAngularVelocity,
                !robotCentricSup.getAsBoolean(),
                true);

            System.out.println("USER");
        } else {
            s_Swerve.advanceToTarget(pose3dSupplier.getAsPose3d());

            System.out.println("ROBOT");
        }
    }
}
