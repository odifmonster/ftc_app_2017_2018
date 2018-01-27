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
        extendServo = hardwareMap.get(Servo.class, "extendServo");
        liftServo = hardwareMap.get(Servo.class, "liftServo");
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        double x = 0;

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad2.right_stick_x > 0) {
                extendServo.setPosition(x + .02);
                x += .02;
            } if (gamepad2.right_stick_x < 0) {
                extendServo.setPosition(x - .02);
                x -= .02;
            }

            if (gamepad2.right_stick_y > 0) {
                liftServo.setPosition(x + .02);
                x += .02;
            } if (gamepad2.right_stick_y < 0) {
                liftServo.setPosition(x - .02);
                x -= .02;
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
