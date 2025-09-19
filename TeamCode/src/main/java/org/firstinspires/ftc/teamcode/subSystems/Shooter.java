package org.firstinspires.ftc.teamcode.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {
    private static DcMotorEx leftShooter, rightShooter;
    // make ramp motor or servo

    public static void init(HardwareMap hw){
        leftShooter = hw.get(DcMotorEx.class, "lShoot");
        rightShooter = hw.get(DcMotorEx.class, "rShoot");

        // Orientation for shooter
        leftShooter.setDirection(DcMotor.Direction.FORWARD);
        rightShooter.setDirection(DcMotor.Direction.REVERSE);
    } // init

    public static void shooterPower(double p){
        leftShooter.setPower(p);
        rightShooter.setPower(p);
    } // shoot
} // Shooter
