package org.firstinspires.ftc.teamcode.Subsytem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;
public class Shooter extends SubsystemBase{

    // TODO check motorex vs motor
    private DcMotorEx leftShooter, rightShooter;
    // make ramp motor or servo

    public Shooter(HardwareMap hw){
        leftShooter = hw.get(DcMotorEx.class, "lShoot");
        rightShooter = hw.get(DcMotorEx.class, "rShoot");

        // Orientation for shooter
        leftShooter.setDirection(DcMotor.Direction.FORWARD);
        rightShooter.setDirection(DcMotor.Direction.REVERSE);
    } // init

    public void shooterPower(double p){
        leftShooter.setPower(p);
        rightShooter.setPower(p);
    } // shoot
} // Shooter
