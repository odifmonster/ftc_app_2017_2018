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
        wServo.getController().pwmDisable();
        wServo = hardwareMap.get(Servo.class, "wServo");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        double x = 0;

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad2.right_stick_x > 0) {
                wServo.setPosition(1);
                wServo.getController().pwmDisable();
                telemetry.addData("set pos?", "done");
                telemetry.update();
                sleep(3000);
            }
            else if (gamepad2.right_stick_x < 0) {
                wServo.setPosition(0);
                wServo.getController().pwmDisable();
                telemetry.addData("set pos?", "-done");
                telemetry.update();
                sleep(3000);
            } else {
                wServo.setPosition(.5);
                wServo.getController().pwmDisable();
                sleep(3000);
                telemetry.addData("stopped?", "hopeflly");
                telemetry.update();
            }

        }
    }
}
