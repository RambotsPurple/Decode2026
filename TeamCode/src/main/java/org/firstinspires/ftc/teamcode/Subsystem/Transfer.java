package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Transfer {
    private DcMotorEx transfer;
    public Transfer(HardwareMap hw) {
        transfer = hw.get(DcMotorEx.class, "transfer");


        // Orientation for shooter
        transfer.setDirection(DcMotor.Direction.FORWARD);
    } // init

    public void run(){
        transfer.setPower(1);
    }

    public void stop(){
        transfer.setPower(0);
    }
}
