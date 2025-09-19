package org.firstinspires.ftc.teamcode.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Hopper {

    // TODO check motorex vs motor
    private static DcMotorEx leftRotation, rightRotation;

    public static void init(HardwareMap hw){
        leftRotation = hw.get(DcMotorEx.class, "lHopper");
        rightRotation = hw.get(DcMotorEx.class, "rHopper");

        // Orientation for hopper
        leftRotation.setDirection(DcMotor.Direction.FORWARD);
        rightRotation.setDirection(DcMotor.Direction.REVERSE);
    } // init

    public static void hopperPower(double p){
        leftRotation.setPower(p);
        rightRotation.setPower(p);
    } // hopperPower
} // Hopper
