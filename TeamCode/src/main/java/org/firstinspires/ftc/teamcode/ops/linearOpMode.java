package org.firstinspires.ftc.teamcode.ops;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystem.*;


@TeleOp(name = "RambotsPurpleTeleOp")
public class linearOpMode extends LinearOpMode {

    @Override
    public void runOpMode() {

        // initializing hardware
        DriveTrain driveTrain = new DriveTrain(hardwareMap);
        Shooter shooter = new Shooter(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        Transfer transfer = new Transfer(hardwareMap);
        boolean prevAStatus = false;
        boolean isShooterOn = false;
        boolean prevBStatus = false;
        boolean isIntakeOn = false;
        boolean prevXStatus = false;
        boolean isTransferOn = false;
        boolean correctPath = false;

        boolean intakeReleased = true;

        double direction = 0;
        double Turn  = 0;
        double targetDirection = 0;

        waitForStart();





        // for calculating motor speed of shooter
        int lastPos = 0;
        long lastTime = 0;
        long currentTime = 0;
        ElapsedTime timer = new ElapsedTime();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            /*
            GamePad Map
            GamePad 1 (Driver)
            Left JoyStick = lateral, diagonal, forwards and backwards movements
            Right JoyStick = Rotation of drive train
            right bumper turn on auto correction

            GamePad 2 (Operator)
            Button A = toggle shooter flywheel on/off
            Button B = toggle intake on/off
            Button X = toggle transfer on/off
            */
            direction = driveTrain.getAngle();

            if(gamepad1.right_stick_x != 0 || gamepad1.right_bumper){
                targetDirection = direction;
            }

            //@TODO might need to tuen the denom for less aggressive auto correct
            Turn = ((driveTrain.getAngle() - targetDirection) / 40) + gamepad1.right_stick_x;

            driveTrain.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, Turn, direction);


            // shooter
            shooter.setTargetPosition(20000);

            // toggle fo
            // r shooter power on gamepad2.a
            if (gamepad2.a != prevAStatus) {
                if (!gamepad2.a) {
                    isShooterOn = !isShooterOn;

                    if (isShooterOn) shooter.setPower(1);
                    else shooter.setPower(0);
                } // if
                prevAStatus = gamepad2.a;
            } // if

            // toggle for intake power on gamepad2.b
            if (gamepad2.b != prevBStatus) {
                if (!gamepad2.b) {
                    isIntakeOn = !isIntakeOn;

                    if (isIntakeOn){

                        intake.setPower(1);
                    }
                    else{

                        intake.setPower(0);
                    }
                } // if
                prevBStatus = gamepad2.b;
            } // if

            if (gamepad2.x != prevXStatus) {
                if (!gamepad2.x) {
                    isTransferOn = !isTransferOn;

                    if (isTransferOn){
                        transfer.run();
                    }
                    else{
                        transfer.stop();
                    }
                } // if
                prevXStatus = gamepad2.x;
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
            telemetry.addLine("Gamepad 2/Operator\n " +
                    "Button A = toggle shooter flywheel on/off\n" +
                    "            Button B = toggle intake on/off\n" +
                    "            Button X = toggle transfer on/off");
            telemetry.addLine("Gamepade 1/Driver\nLeft JoyStick = lateral, diagonal, forwards and backwards movements\n" +
                    "            Right JoyStick = Rotation of drive train\n" +
                    "            right bumper turn on auto correction");

            telemetry.update();

        } // while

    } // runOpMode



} // linearOpMode