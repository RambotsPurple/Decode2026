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
        boolean isShooterOn = false;
        boolean prevBStatus = false;
        boolean isIntakeOn = false;

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
            Button A = toggle shooter flywheel on/off
            Button B = toggle intake on/off
            */

            direction = driveTrain.getAngle();
            driveTrain.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, direction);

            // shooter
            shooter.setTargetPosition(20000);

            // toggle for shooter power on gamepad2.a
            if (gamepad2.a != prevAStatus) {
                if (!gamepad2.a) {
                    isShooterOn = !isShooterOn;

                    if (isShooterOn) shooter.setRPM(2000);
                    else shooter.setRPM(0);
                } // if
                prevAStatus = gamepad2.a;
            } // if

            // toggle for intake power on gamepad2.b
            if (gamepad2.b != prevBStatus) {
                if (!gamepad2.b) {
                    isIntakeOn = !isIntakeOn;

                    if (isIntakeOn) intake.setPower(1.0);
                    else intake.setPower(0);
                } // if
                prevBStatus = gamepad2.b;
            } // if

//          currentTime = System.nanoTime();
//          lastTime = currentTime;

            telemetry.addData("hello world", 0);
            telemetry.addData("gamepad1 left x", gamepad1.left_stick_x);
            telemetry.addData("gamepad1 left y", gamepad1.left_stick_y);
            telemetry.addData("gamepad1 right x", gamepad1.right_stick_x);
            telemetry.addData("direction", direction);
            telemetry.addData("shooter RPM", shooter.getRPM());
            telemetry.addData("shooter TPS", shooter.getTPS());
            telemetry.addData("shooter TPS to RPM", shooter.getTPS() / 28.0 * 6.0);
            telemetry.addData("shooter position:", shooter.getCurrentPosition());
            telemetry.addData("shooter target pos: ", shooter.getTargetPosition());
            telemetry.update();

        } // while

    } // runOpMode



} // linearOpMode