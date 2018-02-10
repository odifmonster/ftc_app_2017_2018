package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.enums.DrivingMode;
import org.firstinspires.ftc.enums.GlyphArmMode;
import org.firstinspires.ftc.libraries.DrivingLibrary;
import org.firstinspires.ftc.libraries.GlyphArmLibrary;
import org.firstinspires.ftc.libraries.RelicArmLibrary;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by megankaye on 2/9/18.
 */
@TeleOp
public class TeleOpMode2 extends LinearOpMode {
    DrivingLibrary drivingLibrary;
    GlyphArmLibrary glyphArmLibrary;
    RelicArmLibrary relicArmLibrary;
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

        relicArmLibrary = new RelicArmLibrary(this);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.dpad_right) {
                telemetry.addData("dPadRight", "right");
                relicArmLibrary.extendArm(true);
                sleep(300);
            } else if (gamepad1.dpad_left) {
                relicArmLibrary.extendArm(false);
                telemetry.addData("dPadRight", "Pressed");
                sleep(300);
            } else {
                relicArmLibrary.stopArm();
            }

            if (gamepad1.right_bumper) {
                relicArmLibrary.activatePWM();
            } else {
                relicArmLibrary.disablePWM();
            }

            if (gamepad1.left_bumper) {
                relicArmLibrary.activateClaw();
                telemetry.addData("left bumper", "pressed");
                sleep(300);
            }

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
            if (gamepad2.b) glyphArmLibrary.resetArmPosition();
            if (gamepad2.x) glyphArmLibrary.liftTwoGlyphs();
            if (gamepad2.y) glyphArmLibrary.dropGlyphs();

            if (gamepad2.left_stick_y < 0) {
                glyphArmLibrary.setPulleyBottom();
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
                    glyphArmLibrary.bottomArmsIncrement(gamepad2.right_bumper,
                            gamepad2.right_trigger);
                    break;
            }

            glyphArmLibrary.movePulley(gamepad2);

            relicArmLibrary.outputInfo();
            telemetry.addData("Status", "Running");
            telemetry.addData("Brake Mode", drivingLibrary.getMode());
            telemetry.addData("Glyph Arm Mode", glyphArmMode.getModeString());

            telemetry.update();
        }
    }
}
