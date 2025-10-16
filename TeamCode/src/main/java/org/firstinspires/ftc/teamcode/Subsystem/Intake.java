package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;

public class Intake extends SubsystemBase{

    // TODO check motorex vs motor
    private DcMotorEx leftIntake;

    public Intake (HardwareMap hw){
        leftIntake = hw.get(DcMotorEx.class, "Intake");


        // Orientation for intake
        leftIntake.setDirection(DcMotor.Direction.FORWARD);
    } // init

    public void intakePower(double p){
        leftIntake.setPower(p);
    } // intakePower
} // Intake
