package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.enums.DrivingMode;
import org.firstinspires.ftc.enums.GlyphArmMode;
import org.firstinspires.ftc.libraries.DrivingLibrary;
import org.firstinspires.ftc.libraries.GlyphArmLibrary;

/**
 * Created by megankaye on 2/3/18.
 */

@TeleOp

public class TestStrafeBias extends LinearOpMode {
    DrivingLibrary drivingLibrary;
    int drivingMode;
    GlyphArmMode glyphArmMode;

    public void runOpMode() throws InterruptedException {
        drivingLibrary = new DrivingLibrary(this);
        drivingLibrary.setSpeed(1);
        drivingMode = 0;
        drivingLibrary.setMode(drivingMode);

        int multiplier = -1;

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

            while (opModeIsActive()) {
                if (gamepad1.a) {
                    drivingLibrary.updateStrafeBias(0, multiplier);
                }
                if (gamepad1.b) {
                    drivingLibrary.updateStrafeBias(1, multiplier);
                }
                if (gamepad1.x) {
                    drivingLibrary.updateStrafeBias(2, multiplier);
                }
                if (gamepad1.y) {
                    drivingLibrary.updateStrafeBias(3, multiplier);
                }

                if (gamepad1.right_bumper) {
                    drivingMode++;
                    drivingMode %= DrivingMode.values().length;
                    drivingLibrary.setMode(drivingMode);
                }

                if (gamepad1.left_bumper) multiplier *= -1;

                drivingLibrary.driveStraight(gamepad1.left_stick_x, -gamepad1.left_stick_y);
                drivingLibrary.turn(gamepad1.right_stick_x, -gamepad1.right_stick_y);

                telemetry.addData("Status", "Running");
                telemetry.addData("Multiplier", "" + multiplier);
                drivingLibrary.printStrafeBias();
                telemetry.addData("Brake Mode", drivingLibrary.getMode());

                telemetry.update();
            }

        }
}
