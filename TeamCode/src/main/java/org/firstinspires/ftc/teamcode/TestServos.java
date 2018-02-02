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

        double pos = 0;

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.b) {
                lift.setPosition(pos);
                telemetry.addData("pressed", "true");
                telemetry.update();
                if (pos < 0.97) {
                    pos += .05;
                }
                sleep(500);
            }
            telemetry.addData("pressed", "false");
            /*if (gamepad1.b) {
                if (liftState) {
                    lift.setPosition(.01);
                    liftState = !liftState;
                } else {
                    lift.setPosition(.02);
                    liftState = !liftState;
                }
                sleep(500);
            }

            if (gamepad1.x) {
                    if (clawState) {
                        claw.setPosition(.9);
                        clawState = !clawState;
                    } else {
                        claw.setPosition(.1);
                        clawState = !clawState;
                    }
                sleep(500);
            }*/
            telemetry.addData("lift state", liftState);
            telemetry.addData("pos", pos);
            telemetry.addData("lift pos", lift.getPosition());
            /*telemetry.addData("claw state", clawState);
            telemetry.addData("claw pos", claw.getPosition());*/
            telemetry.update();
        }
    }
}
