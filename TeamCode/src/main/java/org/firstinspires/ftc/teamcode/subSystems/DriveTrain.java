package org.firstinspires.ftc.teamcode.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain {
    private static DcMotorEx frontLeft, frontRight, rearLeft, rearRight ;

    public static void init(HardwareMap hw){
        frontLeft = hw.get(DcMotorEx.class, "leftFront");
        frontRight = hw.get(DcMotorEx.class, "rightFront");
        rearLeft = hw.get(DcMotorEx.class, "leftBack");
        rearRight = hw.get(DcMotorEx.class, "rightBack");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Orientation for drivetrain
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        rearLeft.setDirection(DcMotor.Direction.FORWARD);
        rearRight.setDirection(DcMotor.Direction.REVERSE);
    } // init

    public static void drive(double x, double y, double turn, double direction){
        // input: theta and power
        // theta is where we want the direction the robot to go
        // power is (-1) to 1 scale where increasing power will cause the engines to go faster
        double theta = Math.atan2(y, x) - Math.toRadians(direction);
        double power = Math.hypot(x, y);
        double sin = Math.sin(theta - Math.PI / 4);
        double cos = Math.cos(theta - Math.PI / 4);
        // max variable allows to use the motors at its max power with out disabling it
        double max = Math.abs(Math.max(Math.abs(sin) + turn, Math.abs(cos) + turn));
        max = Math.max(max, 1);

        double frontLeftPower = power * cos / max + turn;
        double frontRightPower = power * sin / max - turn;
        double rearLeftPower = power * sin / max + turn;
        double rearRightPower = power * cos / max - turn;

        // Prevents the motors exceeding max power thus motors will not seize and act sporadically
        if ((power + Math.abs(turn)) > 1) {
            frontLeftPower /= power + turn;
            frontRightPower /= power - turn;
            rearLeftPower /= power + turn;
            rearRightPower /= power - turn;
        } // if

        // Power to the wheels
        frontLeft.setPower(frontLeftPower);
        rearLeft.setPower(rearLeftPower);
        frontRight.setPower(frontRightPower);
        rearRight.setPower(rearRightPower);

    } // drive
} // DriveTrain
