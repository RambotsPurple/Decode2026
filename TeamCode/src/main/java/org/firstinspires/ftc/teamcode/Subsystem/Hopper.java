package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;

public class Hopper extends SubsystemBase{

    // TODO check motorex vs motor
    private DcMotorEx leftRotation, rightRotation;

    public Hopper(HardwareMap hw){
        leftRotation = hw.get(DcMotorEx.class, "lHopper");
        rightRotation = hw.get(DcMotorEx.class, "rHopper");

        // Orientation for hopper
        leftRotation.setDirection(DcMotor.Direction.FORWARD);
        rightRotation.setDirection(DcMotor.Direction.REVERSE);
    } // init

    public void hopperPower(double p){
        leftRotation.setPower(p);
        rightRotation.setPower(p);
    } // hopperPower
} // Hopper
