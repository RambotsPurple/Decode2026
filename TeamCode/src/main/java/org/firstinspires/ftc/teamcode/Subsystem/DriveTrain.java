package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;


import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class DriveTrain extends SubsystemBase{
    private DcMotorEx frontLeft, frontRight, rearLeft, rearRight;
    private IMU imu;
    private double direction;
    private double lastAngles;



    public DriveTrain (HardwareMap hw) {
        frontLeft = hw.get(DcMotorEx.class, "leftFront");
        frontRight = hw.get(DcMotorEx.class, "rightFront");
        rearLeft = hw.get(DcMotorEx.class, "leftBack");
        rearRight = hw.get(DcMotorEx.class, "rightBack");

        imu = hw.get(IMU.class, "imu");

        imu.initialize(
                new IMU.Parameters(new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.DOWN,
                        RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
                ))
        );

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Orientation for drivetrain
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        rearLeft.setDirection(DcMotor.Direction.FORWARD);
        rearRight.setDirection(DcMotor.Direction.REVERSE);

        resetAngle();
    } // init

    public void drive(double x, double y, double turn, double heading) {



        // Corrected field-centric math (if you use IMU)
        double rotatedX = x * Math.cos(heading) - y * Math.sin(heading);
        double rotatedY = x * Math.sin(heading) + y * Math.cos(heading);

        // Calculate raw motor powers
        double frontLeftPower = rotatedY + rotatedX + turn;
        double frontRightPower = rotatedY - rotatedX - turn;
        double rearLeftPower = rotatedY - rotatedX + turn;
        double rearRightPower = rotatedY + rotatedX - turn;

        // Normalize powers if any exceeds 1.0
        double max = Math.max(
                1.0,
                Math.max(
                        Math.abs(frontLeftPower),
                        Math.max(Math.abs(frontRightPower),
                                Math.max(Math.abs(rearLeftPower), Math.abs(rearRightPower)))
                )
        );

        frontLeftPower /= max;
        frontRightPower /= max;
        rearLeftPower /= max;
        rearRightPower /= max;

        // Send power to motors
        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        rearLeft.setPower(rearLeftPower);
        rearRight.setPower(rearRightPower);
    }


    public void resetAngle() {
        lastAngles = imu.getRobotYawPitchRollAngles().getYaw();
        imu.resetYaw();
        direction = 0;
    }



    public double getAngle() {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

         direction = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

//        double deltaAngle = angles - lastAngles;
//
//        if (deltaAngle < -180)
//            deltaAngle += 360;
//        else if (deltaAngle > 180)
//            deltaAngle -= 360;
//
//        direction += deltaAngle;
//
//        lastAngles = angles;

        return direction;
    }

    public void setFrontLeftPower(double power) {
        frontLeft.setPower(power);
    }

    public void setFrontRightPower(double power) {
        frontRight.setPower(power);
    }

    public void setRearLeftPower(double power) {
        rearLeft.setPower(power);
    }

    public void setRearRightPower(double power) {
        rearRight.setPower(power);
    }


} // DriveTrain
