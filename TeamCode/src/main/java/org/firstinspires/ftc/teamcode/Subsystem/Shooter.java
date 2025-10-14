package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Shooter extends SubsystemBase{

    // TODO check motorex vs motor
    private DcMotorEx shooter;
    // make ramp motor or servo

    private int lastPos = 0;

    public Shooter(HardwareMap hw) {
        shooter = hw.get(DcMotorEx.class, "lShoot");
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Orientation for shooter
        shooter.setDirection(DcMotor.Direction.FORWARD);
    } // init

    public void setPower(double p) {
        shooter.setPower(p);
    } // shoot

    // set RPM of motor
    public void setRPM(int RPM) {
        shooter.setVelocity(RPM * 6, AngleUnit.DEGREES);
    }

    public double getRPM() {
        return shooter.getVelocity(AngleUnit.DEGREES) / 6;
    }

    public void setTargetPosition(int position) {
        shooter.setTargetPosition(position);
    }

    public int getTargetPosition() {
        return shooter.getTargetPosition();
    }

    public int getCurrentPosition() {
        return shooter.getCurrentPosition();
    }


} // Shooter
