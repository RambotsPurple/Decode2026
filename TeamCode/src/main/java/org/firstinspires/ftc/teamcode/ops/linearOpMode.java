package org.firstinspires.ftc.teamcode.ops;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subSystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subSystems.RobotConfig;


@TeleOp(name = "RambotsPurpleTeleOp")
public class linearOpMode extends LinearOpMode {

    @Override
    public void runOpMode() {

        // initializing hardware
        RobotConfig.initialize(hardwareMap);
        DriveTrain.init(hardwareMap);

        waitForStart();

        boolean intakeReleased = true;
        boolean correctPath = false;
        boolean fieldCentric = false;

        double direction = 0;

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

            direction = DriveTrain.getAngle();
            DriveTrain.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, direction);

            telemetry.addData("hello world", 0);
            telemetry.addData("gamepad1 left x", gamepad1.left_stick_x);
            telemetry.addData("gamepad1 left y", gamepad1.left_stick_y);
            telemetry.addData("gamepad1 right x", gamepad1.right_stick_x);
            telemetry.addData("direction", direction);
            telemetry.update();

        } // while

    } // runOpMode

} // linearOpMode