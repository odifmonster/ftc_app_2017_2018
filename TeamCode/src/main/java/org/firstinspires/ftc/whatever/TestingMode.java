package org.firstinspires.ftc.whatever;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@TeleOp
public class TestingMode extends LinearOpMode {
    public void runOpMode() {
        Servo servo_test = hardwareMap.get(Servo.class, "servo_test");
        int servo_dir = 1;

        telemetry.addData("Status", "Initialized");

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.left_bumper) {
                if (servo_test.getPosition() >= 1.0) {
                    telemetry.addData("Status", "Running");
                    servo_dir = -1;
                } else if (servo_test.getPosition() <= 0) {
                    servo_dir = 1;
                } else {
                    servo_test.setPosition(servo_test.getPosition() + 0.01 * servo_dir);

                    telemetry.addData("Servo Pos", servo_test.getPosition());
                    telemetry.update();
                }
            }
        }
    }
}
