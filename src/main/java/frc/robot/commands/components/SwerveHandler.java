package frc.robot.commands.components;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.lib.util.Pose3dSupplier;
import frc.lib.util.TaskSupplier;
import frc.robot.Constants;
import frc.robot.subsystems.Swerve;

public class SwerveHandler extends Command {

    private boolean robotControl;
    private BooleanSupplier operatorOverride;

    private Pose3dSupplier targetPose;
    private TaskSupplier targetTask;

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
      BooleanSupplier operatorOverride,
      Pose3dSupplier poseSupplier,
      TaskSupplier targetTaskSupplier) {
    this.s_Swerve = s_Swerve;
    addRequirements(s_Swerve);

    this.operatorOverride = operatorOverride;

    this.targetPose = poseSupplier;
    targetTask = targetTaskSupplier;

    this.translationSup = translationSup;
    this.strafeSup = strafeSup;
    this.rotationSup = rotationSup;
    this.robotCentricSup = robotCentricSup;
    }


    @Override
    public void execute() {
        if (operatorOverride.getAsBoolean()) {
            robotControl = false;
        } else if (targetTask.getAsTask().getStage() != 0) {
            robotControl = true;
        }


        if (!robotControl) {
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

            // System.out.println("USER");
            return;
        }
        
        if (targetTask.getAsTask().getStage() == 1) {
            s_Swerve.advanceToTarget(targetPose.getAsPose3d()); // Possibly change to an Optional in the future; missing Poses may cause errors

            targetTask.getAsTask().advanceStage();
        } else if (targetTask.getAsTask().getStage() == 2) {
            // Add functionality to check whether the trajectory has been completed
        } else if (targetTask.getAsTask().getStage() == 3) {
            // Add functionality to inform another Subsystem or Command that the trajectory has been completed
        }
    }
}
