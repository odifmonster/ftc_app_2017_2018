package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by megankaye on 1/26/18.
 */
@TeleOp
public class TestRelicClaw extends LinearOpMode {
    Servo extendServo;
    Servo liftServo;
    Servo clawServo;


    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad2.right_stick_x > 0) {
                extendServo.setPosition(extendServo.getPosition() + .2);
            } if (gamepad2.right_stick_x < 0) {
                extendServo.setPosition(extendServo.getPosition() - .2);
            }

            if (gamepad2.right_stick_y > 0) {
                liftServo.setPosition(liftServo.getPosition() + .2);
            } if (gamepad2.right_stick_y < 0) {
                liftServo.setPosition(liftServo.getPosition() - .2);
            }

            if (gamepad2.a) {
                if (clawServo.getPosition() == 0) {
                    clawServo.setPosition(1);
                } else {
                    clawServo.setPosition(0);
                }
            }
            telemetry.addData("extendservo", extendServo.getPosition());
            telemetry.addData("liftservo", liftServo.getPosition());
            telemetry.addData("clawservo", clawServo.getPosition());
            telemetry.update();
        }
    }
}
