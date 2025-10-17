package org.firstinspires.ftc.teamcode.ops;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystem.*;


@TeleOp(name = "RambotsPurpleTeleOp")
public class linearOpMode extends LinearOpMode {

    @Override
    public void runOpMode() {

        // initializing hardware
        DriveTrain driveTrain = new DriveTrain(hardwareMap);
        Shooter shooter = new Shooter(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        boolean prevAStatus = false;

        waitForStart();

        boolean intakeReleased = true;
        boolean correctPath = false;
        boolean fieldCentric = false;

        double direction = 0;

        // for calculating motor speed of shooter
        int lastPos = 0;
        long lastTime = 0;
        long currentTime = 0;

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            /*
            GamePad Map
            GamePad 1 (Driver)
            Left JoyStick = lateral, diagonal, forwards and backwards movements
            Right JoyStick = Rotation of drive train

            GamePad 2 (Operator)
            Button A = toggle position of claw to open or closed (We start closed)
            left stick x = slide extension
            right stick y = slide abduction
            */

            direction = driveTrain.getAngle();
            driveTrain.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, direction);

            // shooter
            shooter.setTargetPosition(20000);

            if (gamepad1.a != prevAStatus) {
                if (gamepad1.a) shooter.setRPM(6000);
                else shooter.setRPM(0);

                prevAStatus = gamepad1.a;
            } // if

            if (gamepad1.b) {
                shooter.setRPM(6000);
            }

            if(gamepad2.a){
                intake.intakePower(1.0);
            }else if(gamepad2.b){
                intake.intakePower(0.0);
            }

//            currentTime = System.nanoTime();
//            lastTime = currentTime;

            telemetry.addData("hello world", 0);
            telemetry.addData("gamepad1 left x", gamepad1.left_stick_x);
            telemetry.addData("gamepad1 left y", gamepad1.left_stick_y);
            telemetry.addData("gamepad1 right x", gamepad1.right_stick_x);
            telemetry.addData("direction", direction);
            telemetry.addData("shooter RPM", shooter.getRPM());
            telemetry.addData("shooter position:", shooter.getCurrentPosition());
            telemetry.addData("shooter target pos: ", shooter.getTargetPosition());
            telemetry.update();

        } // while

    } // runOpMode



} // linearOpMode