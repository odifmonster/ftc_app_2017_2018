package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.enums.DrivingMode;
import org.firstinspires.ftc.libraries.DrivingLibrary;
import org.firstinspires.ftc.libraries.GlyphArmLibrary;

/**
 * Created by lamanwyner on 12/30/17.
 */

@TeleOp
public class TeleOpMode extends LinearOpMode {
    DrivingLibrary drivingLibrary;
    GlyphArmLibrary glyphArmLibrary;
    int drivingMode;

    public void runOpMode() throws InterruptedException {
        drivingLibrary = new DrivingLibrary(this);
        drivingLibrary.setSpeed(1);
        drivingMode = 0;
        drivingLibrary.setMode(drivingMode);

        glyphArmLibrary = new GlyphArmLibrary(this);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            drivingLibrary.driveStraight(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            drivingLibrary.turn(gamepad1.right_stick_x, -gamepad1.right_stick_y);

            if (gamepad1.a) {
                drivingMode++;
                drivingMode %= DrivingMode.values().length;
                drivingLibrary.setMode(drivingMode);
            }

            glyphArmLibrary.closeArmsPreset(gamepad2.left_bumper);
            glyphArmLibrary.openArmsPreset(gamepad2.left_trigger);
            glyphArmLibrary.closeArmsIncrement(gamepad2.right_bumper);
            glyphArmLibrary.openArmsIncrement(gamepad2.right_trigger);
            glyphArmLibrary.movePulley(gamepad2);

            telemetry.addData("Status", "Running");
            telemetry.addData("Brake Mode", drivingLibrary.getMode());
            telemetry.update();
        }
    }
}
