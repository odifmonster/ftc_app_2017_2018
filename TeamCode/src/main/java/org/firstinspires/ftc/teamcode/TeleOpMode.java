package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.enums.DrivingMode;
import org.firstinspires.ftc.enums.GlyphArmMode;
import org.firstinspires.ftc.libraries.DrivingLibrary;
import org.firstinspires.ftc.libraries.GlyphArmLibrary;

/**
 * Created by lamanwyner on 12/30/17.
 */

/**
 * GC1
 * rt: relic claw
 * rb: relic claw
 * lt: relic lift
 * lb: relic lift
 * dpad: relic extend (x)
 * rjoy: move (y) turn (x)
 * ljoy: move (y) strafe (x)
 * a: switch driving modes
 * b: relic lift preset
 * x: relic drop preset
 * y: relic preset

 * GC2
 * rt: glyph arm
 * rb: glyph arm
 * lt: glyph arm
 * lb: glyph arm
 * dpad: pully (x)
 * rjoy:
 * ljoy:
 * a: switch glyph arm modes
 * b: preset: ready for more glyphs
 * x: preset: stack glyphs
 * y: preset: quick drop glyphs
 */

@TeleOp
public class TeleOpMode extends LinearOpMode {
    DrivingLibrary drivingLibrary;
    GlyphArmLibrary glyphArmLibrary;
    int drivingMode;
    GlyphArmMode glyphArmMode;
    int glyphArmInt;

    public void runOpMode() throws InterruptedException {
        drivingLibrary = new DrivingLibrary(this);
        drivingLibrary.setSpeed(1);
        drivingMode = 0;
        drivingLibrary.setMode(drivingMode);

        glyphArmLibrary = new GlyphArmLibrary(this);
        glyphArmInt = 0;
        glyphArmMode = GlyphArmMode.values()[0];

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                drivingMode++;
                drivingMode %= DrivingMode.values().length;
                drivingLibrary.setMode(drivingMode);
            }

            drivingLibrary.driveStraight(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            drivingLibrary.turn(gamepad1.right_stick_x, -gamepad1.right_stick_y);

            if (gamepad2.a) {
                glyphArmInt++;
                glyphArmInt %= GlyphArmMode.values().length;
                glyphArmMode = GlyphArmMode.values()[glyphArmInt];
            }

            switch (glyphArmMode) {
                case ALL_MOVE:
                    glyphArmLibrary.allArmsPreset(gamepad2.left_bumper, gamepad2.left_trigger);
                    glyphArmLibrary.allArmsIncrement(gamepad2.right_bumper, gamepad2.right_trigger);
                    break;
                case TOP_BOTTOM_PRESET:
                    glyphArmLibrary.topArmsPreset(gamepad2.left_bumper, gamepad2.left_trigger);
                    glyphArmLibrary.bottomArmsPreset(gamepad2.right_bumper, gamepad2.right_trigger);
                    break;
                case TOP_BOTTOM_INCREMENT:
                    glyphArmLibrary.topArmsIncrement(gamepad2.left_bumper, gamepad2.left_trigger);
                    glyphArmLibrary.bottomArmsIncrement(gamepad2.right_bumper, gamepad2.right_trigger);
                    break;
            }

            glyphArmLibrary.movePulley(gamepad2);

            telemetry.addData("Status", "Running");
            telemetry.addData("Brake Mode", drivingLibrary.getMode());
            telemetry.addData("Glyph Arm Mode", glyphArmMode.getModeString());
            telemetry.update();
        }
    }
}
