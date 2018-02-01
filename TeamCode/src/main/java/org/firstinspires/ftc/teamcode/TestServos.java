package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by megankaye on 1/31/18.
 */
@TeleOp
public class TestServos extends LinearOpMode {
    Servo claw;
    Servo lift;
    boolean clawState = true;
    boolean liftState = true;
    public void runOpMode() throws InterruptedException {
        claw = hardwareMap.get(Servo.class, "claw");
        lift = hardwareMap.get(Servo.class, "lift");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.b) {
                if (liftState) {
                    lift.setPosition(.9);
                    liftState = !liftState;
                } else {
                    lift.setPosition(.1);
                }
            }

            if (gamepad1.x) {
                    if (clawState) {
                        claw.setPosition(.9);
                        clawState = !clawState;
                    } else {
                        claw.setPosition(.1);
                        clawState = !clawState;
                    }
            }
        }
    }
}
