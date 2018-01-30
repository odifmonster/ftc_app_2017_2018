package org.firstinspires.ftc.libraries;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by megankaye on 1/30/18.
 */

public class RelicArmLibrary {
    OpMode opMode;
    DcMotor extendArm;
    Servo liftArm;
    Servo clawArm;

    boolean clawState = true;
    boolean liftState = true;


    public RelicArmLibrary(LinearOpMode opMode) {
        this.opMode = opMode;
        extendArm = opMode.hardwareMap.get(DcMotor.class,"extendArm");
        liftArm = opMode.hardwareMap.get(Servo.class, "liftArm");
        clawArm = opMode.hardwareMap.get(Servo.class, "clawArm");
    }

    public void extendArm(boolean direction) {
        if (direction) {
            extendArm.setPower(.3);
        } else {
            extendArm.setPower(-.3);
        }
    }

    public void stopArm() {
        extendArm.setPower(0);
    }

    public void activateLift() {
        if (liftState) {
            liftArm.setPosition(.9);
            liftState = !liftState;
        } else {
            liftArm.setPosition(.1);
        }
    }

    public void activateClaw() {
        if (clawState) {
            clawArm.setPosition(.9);
            clawState = !clawState;
        } else {
            clawArm.setPosition(.1);
            clawState = !clawState;
        }
    }

    public void outputInfo() {
        opMode.telemetry.addData("extend", extendArm.getPower());
        opMode.telemetry.addData("lift", liftArm.getPosition());
        opMode.telemetry.addData("claw", clawArm.getPosition());
        opMode.telemetry.update();
    }

}
