package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;

public class Intake extends SubsystemBase{

    // TODO check motorex vs motor
    private DcMotorEx intake;

    public Intake (HardwareMap hw){
        intake = hw.get(DcMotorEx.class, "Intake");


        // Orientation for intake
        intake.setDirection(DcMotor.Direction.FORWARD);

    } // init

    public void setPower(double p) {
        intake.setPower(p);
    } // intakePower

} // Intake
