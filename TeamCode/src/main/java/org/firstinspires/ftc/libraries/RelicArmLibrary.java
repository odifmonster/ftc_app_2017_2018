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
    CRServo cont1;
    CRServo cont2;
    CRServo cont3;
    //Servo lift;
    //Servo claw;
    //boolean clawState = false;
    //boolean liftState = false;

    double liftBottom =  0.585;
    double liftTop =    0.66;
    double clawBottom =  0.05;
    double clawTop = 0.45;

    boolean cont12Locked = false;
    boolean cont3locked = false;

    public RelicArmLibrary(LinearOpMode opMode) {
        this.opMode = opMode;
        //lift = opMode.hardwareMap.get(Servo.class, "lift");
        //lift.setDirection(Servo.Direction.REVERSE);
        //claw = opMode.hardwareMap.get(Servo.class, "claw");
        cont1 = opMode.hardwareMap.get(CRServo.class, "cont1");
        cont2 = opMode.hardwareMap.get(CRServo.class, "cont2");
        cont3 = opMode.hardwareMap.get(CRServo.class, "cont3");
    }

    public void extendArm() {
        cont1.setPower(1);
        cont2.setPower(-1);
        cont3.setPower(1);
    }

    public void retractArm() {
        cont1.setPower(-1);
        cont2.setPower(1);
        cont3.setPower(-1);
    }

    public void firstServoForward() {
        cont1.setPower(1);
    }

    public void firstServoBackward() {
        cont1.setPower(-1);
    }

    public void secondServoForward() {
        cont2.setPower(1);
    }

    public void secondServoBackward() {
        cont2.setPower(-1);
    }

    public void thirdServoForward() {
        cont3.setPower(1);
    }

    public void thirdServoBackward() {
        cont3.setPower(-1);
    }

    public void stopAll() {
        cont1.setPower(0);
        cont2.setPower(0);
        cont3.setPower(0);
    }

    public void liftClaw() {
       /*if (clawState) {
            claw.setPosition(clawBottom);
        } else {
            claw.setPosition(clawTop);
        }
        clawState = !clawState;*/
    }

    public void liftLift() {
        /*if (liftState) {
            lift.setPosition(liftBottom);
        } else {
            lift.setPosition(liftTop);
        }
        liftState = !liftState;*/
    }

    public void outputInfo() {
        opMode.telemetry.addData("first servo power", cont1.getPower());
        opMode.telemetry.addData("second servo power", cont2.getPower());
        opMode.telemetry.addData("third servo power", cont3.getPower());
    }

}
