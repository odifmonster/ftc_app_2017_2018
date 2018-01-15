package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by megankaye on 12/8/17.
 */

@TeleOp
@Disabled
public class ClawTest extends LinearOpMode {
    //defining our servo motors - claw
    private Servo servoCL;
    private Servo servoCR;

    @Override
    public void runOpMode() {
        servoCL = hardwareMap.get(Servo.class, "servoCL");
        servoCR = hardwareMap.get(Servo.class, "servoCR");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        boolean servoCGo;
        boolean servoCReverse;

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();

            //see if RT was held. run claw servo motors.
            servoCGo = gamepad1.right_trigger>0.5;
            if (servoCGo) {
                servoCL.setPosition(servoCL.getPosition() - .25);
                servoCR.setPosition(servoCR.getPosition() + .25);
            }

            //see if RB was held. reverse claw servo motors.
            servoCReverse = gamepad1.right_bumper;
            if (servoCReverse) {
                servoCL.setPosition(servoCL.getPosition() + .25);
                servoCR.setPosition(servoCR.getPosition() - .25);
            }

        }
    }
}