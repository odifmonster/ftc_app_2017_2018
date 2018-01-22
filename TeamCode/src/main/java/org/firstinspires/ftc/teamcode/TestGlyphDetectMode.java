package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.GlyphDetector;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by lamanwyner on 1/16/18.
 */

@TeleOp
@Disabled
public class TestGlyphDetectMode extends LinearOpMode {
    private GlyphDetector glyphDetector;

    public void runOpMode() {
        glyphDetector = new GlyphDetector();
        glyphDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        glyphDetector.enable();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Closest Glyph Pos", glyphDetector.getChosenGlyphPosition().toString());
            telemetry.addData("Closest Glyph Offset", glyphDetector.getChosenGlyphOffset());
            telemetry.update();
        }

        glyphDetector.disable();
    }
}