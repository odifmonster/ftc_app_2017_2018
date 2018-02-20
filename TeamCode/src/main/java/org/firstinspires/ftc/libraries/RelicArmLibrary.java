package org.firstinspires.ftc.libraries;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by megankaye on 1/30/18.
 */

public class RelicArmLibrary {
    OpMode opMode;
    DcMotor extendArm;
    //DcMotor pullInArm;
    Servo liftArm;
    Servo clawArm;

    boolean clawState = true;
    boolean hoverState = true;
    boolean manualSwitch = false;


    public RelicArmLibrary(LinearOpMode opMode) {
        this.opMode = opMode;
        extendArm = opMode.hardwareMap.get(DcMotor.class,"extendArm");
        liftArm = opMode.hardwareMap.get(Servo.class, "liftArm");
        clawArm = opMode.hardwareMap.get(Servo.class, "clawArm");
        //liftArm.getController().pwmDisable();
    }

    public void extendArm(boolean direction) {
        if (direction) {
            extendArm.setPower(.5);
        } else {
            extendArm.setPower(-.5);
        }
    }

    public void stopArm() {
        extendArm.setPower(0);
    }

    /*public void activatePWM() {
        liftArm.getController().pwmEnable();
    }
    public void disablePWM() {
        liftArm.getController().pwmDisable();
    }*/


    public void activateClaw() {
        if (clawState) {
            clawArm.setPosition(.5);
            clawState = !clawState;
        } else {
            clawArm.setPosition(.1);
            clawState = !clawState;
        }
    }

    public void outputInfo() {
        opMode.telemetry.addData("extend", extendArm.getPower());
        //opMode.telemetry.addData("lift", liftArm.getController().getPwmStatus());
        opMode.telemetry.addData("claw", clawArm.getPosition());
        //opMode.telemetry.update();
    }

}
