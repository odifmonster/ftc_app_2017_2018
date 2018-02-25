package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.enums.Direction;
import org.firstinspires.ftc.enums.FTCAlliance;
import org.firstinspires.ftc.enums.FTCPosition;
import org.firstinspires.ftc.libraries.AutonModeLibrary;

/**
 * Created by megankaye on 2/25/18.
 */
@Autonomous
public class TestAutonModeTwo extends LinearOpMode {
    AutonModeLibrary autonMode;

    public void runOpMode() throws InterruptedException {
        autonMode = new AutonModeLibrary(this, FTCAlliance.RED, FTCPosition.LEFT, true);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            autonMode.pickUpGlyph();

            Direction dir = autonMode.knockOffJewel();
            //int count = autonMode.glyptograph(dir);

            //autonMode.placeGlyphs(count);

            //glyptograph
            //RelicRecoveryVuMark rvu = autonMode.glyptograph(dir);

            //int count = autonMode.glyptograph(dir);
            //autonMode.placeGlyphs(count);

            sleep(30000);
        }
    }
}
