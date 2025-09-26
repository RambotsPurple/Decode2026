package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;

public class Intake extends SubsystemBase{

    // TODO check motorex vs motor
    private DcMotorEx leftIntake, rightIntake;

    public Intake (HardwareMap hw){
        leftIntake = hw.get(DcMotorEx.class, "lIntake");
        rightIntake = hw.get(DcMotorEx.class, "rIntake");

        // Orientation for intake
        leftIntake.setDirection(DcMotor.Direction.FORWARD);
        rightIntake.setDirection(DcMotor.Direction.REVERSE);
    } // init

    public void intakePower(double p){
        leftIntake.setPower(p);
        rightIntake.setPower(p);
    } // intakePower
} // Intake
