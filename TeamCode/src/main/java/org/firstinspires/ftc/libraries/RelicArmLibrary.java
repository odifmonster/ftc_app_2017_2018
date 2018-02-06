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
    DcMotor extendArmL;
    DcMotor extendArmR;
    //DcMotor pullInArm;
    Servo liftArm;
    Servo clawArm;

    boolean clawState = true;
    boolean liftState = true;


    public RelicArmLibrary(LinearOpMode opMode) {
        this.opMode = opMode;
        extendArmL = opMode.hardwareMap.get(DcMotor.class,"extendArmL");
        extendArmR = opMode.hardwareMap.get(DcMotor.class,"extendArmR");
        //pullInArm = opMode.hardwareMap.get(DcMotor.class,"pullInArm");
        liftArm = opMode.hardwareMap.get(Servo.class, "liftArm");
        clawArm = opMode.hardwareMap.get(Servo.class, "clawArm");
    }

    public void extendArm(boolean direction) {
        if (direction) {
            extendArmL.setPower(.3);
            extendArmR.setPower(.3);
        } else {
            //pullInArm.setPower(-.3)
            extendArmL.setPower(-.3);
            extendArmR.setPower(-.3);
        }
    }

    public void stopArm() {
        extendArmL.setPower(0);
        extendArmR.setPower(0);
    }

    public void activateLift() {
        if (liftState) {
            liftArm.setPosition(.9);
            liftState = !liftState;
        } else {
            liftArm.setPosition(.1);
            liftState = !liftState;
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

    public void relicLiftPreset() {
        clawArm.setPosition(.1);
        liftArm.setPosition(.9);
    }

    public void relicDropPreset() {
        clawArm.setPosition(.9);
        liftArm.setPosition(.1);
    }

    public void outputInfo() {
        opMode.telemetry.addData("extendL", extendArmL.getPower());
        opMode.telemetry.addData("extendR", extendArmR.getPower());
        opMode.telemetry.addData("lift", liftArm.getPosition());
        opMode.telemetry.addData("claw", clawArm.getPosition());
        opMode.telemetry.update();
    }

}
