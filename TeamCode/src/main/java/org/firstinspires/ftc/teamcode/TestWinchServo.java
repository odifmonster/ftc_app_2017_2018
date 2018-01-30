package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by megankaye on 1/27/18.
 */
@TeleOp
public class TestWinchServo extends LinearOpMode {
    Servo wServo;

    public void runOpMode() throws InterruptedException {
        wServo = hardwareMap.get(Servo.class, "wServo");
        wServo.getController().pwmDisable();
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        double x = 0;

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad2.right_stick_x > 0) {
                wServo.setPosition(1);
               // wServo.getController().pwmDisable();
                telemetry.addData("set pos?", "done");
                telemetry.update();
            }
            else if (gamepad2.right_stick_x < 0) {
                wServo.setPosition(0);
               // wServo.getController().pwmDisable();
                telemetry.addData("set pos?", "-done");
                telemetry.update();
            } else {
                wServo.setPosition(.5);
                //wServo.getController().pwmDisable();
                telemetry.addData("stopped?", "hopeflly");
                telemetry.update();
            }

        }
    }
}
