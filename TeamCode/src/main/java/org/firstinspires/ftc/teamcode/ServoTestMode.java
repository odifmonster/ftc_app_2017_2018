package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.libraries.GlyphArmLibrary;

/**
 * Created by lamanwyner on 1/12/18.
 */

@TeleOp
public class ServoTestMode extends LinearOpMode {
    GlyphArmLibrary glyphArmLibrary;

    public void runOpMode() {
        glyphArmLibrary = new GlyphArmLibrary(this);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            glyphArmLibrary.moveAllArms(gamepad1, gamepad2);
            glyphArmLibrary.movePulley(gamepad2);

            telemetry.addData("Left Top Pos", glyphArmLibrary.getServoPos(0));
            telemetry.addData("Left Bottom Pos", glyphArmLibrary.getServoPos(1));
            telemetry.addData("Right Top Pos", glyphArmLibrary.getServoPos(2));
            telemetry.addData("Right Bottom Pos", glyphArmLibrary.getServoPos(3));
            telemetry.update();
        }
    }
}
