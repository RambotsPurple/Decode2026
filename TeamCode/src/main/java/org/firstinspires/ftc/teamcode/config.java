package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class config {







    public static HardwareMap hardwareMap;
    public static final double COUNTS_PER_MOTOR_REV = 537.6; // eg: TETRIX Motor Encoder
    public static IMU imu;
    public static double direction;
    public static double lastAngles;

    // ARM POSITIONS



    public static void initialize(HardwareMap hw) {
        hardwareMap = hw;

        imu = hardwareMap.get(IMU.class, "imu");

        imu.initialize(
                new IMU.Parameters(new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                        RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
                ))
        );

        // Drive Train motor


        // intake

        // ENCODERS



        resetAngle();
    }

    public static void resetAngle()
    {
        lastAngles = imu.getRobotYawPitchRollAngles().getYaw();

        direction = 0;
    }

    public static double getAngle()
    {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        double angles = imu.getRobotYawPitchRollAngles().getYaw();

        double deltaAngle = angles - lastAngles;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        direction += deltaAngle;

        lastAngles = angles;

        return direction;
    }




}