// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.lib.util;

import java.beans.Visibility;

import org.opencv.core.Mat.Tuple2;

import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Transform3d;
import frc.robot.Constants;
import frc.robot.subsystems.Limelight;
import frc.robot.Constants.Vision;

/** Add your docs here. */
public class AprilTag {

  private class TargetVector {

  }

  // private static double tx, ty, height;
  private static double angle;
  private static double height;

  // public AprilTag(double tx, double ty, double height) {
  //   this.tx = tx;
  //   this.ty = ty;
  //   this.height = height;
  // }

  // public static double[] targetToXYZVector() {
  //   double[] targetVector = new double[3];
  //   var currentTargetID = Limelight.entry("tid").getInteger(-1);
  //   if (currentTargetID != -1) {
  //    try {
  //     // height = Constants.APRILTAGS.get(currentTargetID).getZ();
  //     height = 0.3429;
  //    } catch (Exception systemException) {}
  //   } else {
  //     System.out.print("Unfortunately, no limelight is detected.");
  //     return null;
  //   }

  //   double z = height - Vision.kLimelightHeightMeters;
  //   double y = z / Math.tan(Math.toRadians(LimelightHelpers.getTY("") + Vision.kLimelightAngleDegrees)); // also need Math.abs()?
  //   // double y = z / Math.tan(Math.toRadians(Limelight.entry("ty").getDouble(-1) + Vision.kLimelightAngleDegrees)); // also need Math.abs()?

  //   double x = y * Math.tan(Math.toRadians(LimelightHelpers.getTX("")));
  //   // double x = y * Math.tan(Math.toRadians(Limelight.entry("tx").getDouble(-1)));

  //   targetVector[0] = x;
  //   targetVector[1] = y;
  //   targetVector[2] = z;

  //   return targetVector;
  // }

  public static double getDirectDistance() {
    Transform3d targetVector = Limelight.targetPos();

    if (Limelight.targetPos() != null) {
      return Math.sqrt(Math.pow(targetVector.getX(), 2) + Math.pow(targetVector.getY(), 2) + Math.pow(targetVector.getZ(), 2));
    } else {
      return -1;
    }
  }



  // public double[] targetToXYZVector() {
  // double[] targetVector = new double[3];

  // double d = getDirectDistance();

  // double x = d * Math.sin(LimelightHelpers.getTX(""));
  // targetVector[0] = x;

  // return targetVector;
  // }

  // public double getDirectDistance() {
  // return (/*placeholder*/3);
  // }

}