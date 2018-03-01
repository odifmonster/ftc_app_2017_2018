package org.firstinspires.ftc.libraries;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by megankaye on 1/30/18.
 */

public class RelicArmLibrary {
    OpMode opMode;
    CRServo lift;
    Servo claw;
    boolean clawState = false;
    double clawBottom =  0.05;
    double clawTop = 0.45;

    public boolean cont12Locked = false;
    public boolean cont3locked = false;

    public RelicArmLibrary(LinearOpMode opMode) {
        this.opMode = opMode;
        //lift = opMode.hardwareMap.get(Servo.class, "lift");
        claw = opMode.hardwareMap.get(Servo.class, "claw");
        lift = opMode.hardwareMap.get(CRServo.class, "cont1");
    }

    public void extendArm() {
        lift.setPower(1);
    }

    public void retractArm() {
        lift.setPower(-1);
    }


    public void stopAll() {
        lift.setPower(0);
    }

    public void liftClaw() {
       if (clawState) {
            claw.setPosition(clawBottom);
        } else {
            claw.setPosition(clawTop);
        }
        clawState = !clawState;
    }

    public void outputInfo() {
        opMode.telemetry.addData("first servo power", lift.getPower());
        opMode.telemetry.addData("claw pos", claw.getPosition());
    }

    public void lockCont3() {
        if (cont3locked) {
            releaseLift();
        }
        cont3locked = !cont3locked;
    }


    public void releaseLift() {
        lift.setPower(0);
    }


    public void idle(boolean noButtonsPressed) {
        if (cont3locked || cont12Locked) {
            if (cont3locked) {
                lift.setPower(-.1);
            }
        }
        else if (noButtonsPressed){
            stopAll();
        }
    }

}
