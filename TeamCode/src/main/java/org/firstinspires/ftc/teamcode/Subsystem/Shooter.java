package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Shooter extends SubsystemBase{

    // TODO check motorex vs motor
    private DcMotorEx shooter1, shooter2;
    // make ramp motor or servo
    private Servo  launcher;

    private double Kp = 0.001, Ki = 0, Kd = 0; // tune these

    private double integralSum = 0;
    private double lastError = 0;
    private ElapsedTime timer = new ElapsedTime();

    private int lastPos = 0;

    public Shooter(HardwareMap hw) {
        shooter1 = hw.get(DcMotorEx.class, "lShoot");
//        shooter1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooter2 = hw.get(DcMotorEx.class, "rShoot");        //run without so we can utilize 100% of it's power
        // Orientation for shooter
        shooter1.setDirection(DcMotor.Direction.REVERSE);
        shooter2.setDirection(DcMotor.Direction.REVERSE);

        launcher = hw.get(Servo.class,"launcher");
        launcher.setDirection(Servo.Direction.REVERSE);
    } // init

    public void setPower(double p) {
        shooter1.setPower(p);
        shooter2.setPower(p);
    } // shoot



    // set RPM of motor
    public void setRPM(int RPM) {
        shooter1.setVelocity(RPM * 6, AngleUnit.DEGREES);
    }

    public double getRPM() {
        return shooter1.getVelocity(AngleUnit.DEGREES) / 6;
    }

    public double getTPS() {
        return shooter1.getVelocity();
    }

    public void setTargetPosition(int position) {
        shooter1.setTargetPosition(position);
    }

    public int getTargetPosition() {
        return shooter1.getTargetPosition();
    }

    public int getCurrentPosition() {
        return shooter1.getCurrentPosition();
    }

    // get RPM by comparing current encoder position to last encoder position
    public double getRPM(long currentTime, long lastTime) {
        int currentPos = getCurrentPosition();
        int deltaPos = currentPos - lastPos;

        lastPos = currentPos;

        return (double)deltaPos / (currentTime - lastTime);
    }

    public void setVelocityPID(int targetRpm){
        int PPR = 28; // pulses per revolution
        double targetTicksPerSec = (targetRpm * PPR) / 60.0;

        double currentVelocity = shooter1.getVelocity(); // in ticks/sec
        double error = targetTicksPerSec - currentVelocity;
        double deltaTime = timer.seconds();

        // integral and derivative
        integralSum += error * deltaTime;
        double derivative = (error - lastError) / deltaTime;

        // PID output
        double out = (Kp * error) + (Ki * integralSum) + (Kd * derivative);

        // clip output to motor power range
        out = Math.max(-1, Math.min(1, out));

        // apply power
        shooter1.setPower(out);

        // prepare for next cycle
        lastError = error;
        timer.reset();
    }//end of setVelocityPID

    public void servoUp(){
        launcher.setPosition(1.0);
    }public void servoDown(){
        launcher.setPosition(0.0);
    }

} // Shooter
