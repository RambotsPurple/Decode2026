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
    private DcMotorEx shooter;
    // make ramp motor or servo
    private Servo launcher;

    private int lastPos = 0;

    public Shooter(HardwareMap hw) {
        shooter = hw.get(DcMotorEx.class, "lShoot");
        shooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //run without so we can utilize 100% of it's power
        shooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // Orientation for shooter
        shooter.setDirection(DcMotor.Direction.REVERSE);

        launcher = hw.get(Servo.class,"launcher");
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

    public double getTPS() {
        return shooter.getVelocity();
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

    // get RPM by comparing current encoder position to last encoder position
    public double getRPM(long currentTime, long lastTime) {
        int currentPos = getCurrentPosition();
        int deltaPos = currentPos - lastPos;

        lastPos = currentPos;

        return (double)deltaPos / (currentTime - lastTime);
    }

    public double setVelocityPID(int targetRpm){
        //@TODO tune the PID values and test the pid it's self
        double Kp = 1;
        double Ki =1;
        double Kd = 1;
        int PPR = 28;
        //in ticks per second
        double targetTick = (targetRpm*PPR)/60;
        double error;
        double derivative;
        double integralSum = 0;
        double out = 0;
        double lastError = 0;

        ElapsedTime timer = new ElapsedTime();


        while(shooter.getVelocity() !=targetTick) {
            error = targetTick - shooter.getVelocity();

            // rate of change of the error
            derivative = (error - lastError) / timer.seconds();

            // sum of all error over time
            integralSum += (error * timer.seconds());

            out = (Kp * error) + (Ki * integralSum) + (Kd * derivative);
            
            lastError = error;

            // reset the timer for next time
            timer.reset();
        }
        return out;
    }//end of setVelocityPID

    public void servoUp(){
        launcher.setPosition(0);
    }public void servoDown(){
        launcher.setPosition(1);
    }

} // Shooter
