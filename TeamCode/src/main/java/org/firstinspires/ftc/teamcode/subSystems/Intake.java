package org.firstinspires.ftc.teamcode.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    // TODO check motorex vs motor
    private static DcMotorEx leftIntake, rightIntake;

    public static void init(HardwareMap hw){
        leftIntake = hw.get(DcMotorEx.class, "lIntake");
        rightIntake = hw.get(DcMotorEx.class, "rIntake");

        // Orientation for intake
        leftIntake.setDirection(DcMotor.Direction.FORWARD);
        rightIntake.setDirection(DcMotor.Direction.REVERSE);
    } // init

    public static void intakePower(double p){
        leftIntake.setPower(p);
        rightIntake.setPower(p);
    } // intakePower
} // Intake
