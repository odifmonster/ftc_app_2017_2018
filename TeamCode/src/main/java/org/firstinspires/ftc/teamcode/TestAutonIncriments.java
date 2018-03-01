package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.enums.Direction;
import org.firstinspires.ftc.enums.FTCAlliance;
import org.firstinspires.ftc.enums.FTCPosition;
import org.firstinspires.ftc.libraries.AutonModeLibrary;

/**
 * Created by megankaye on 2/28/18.
 */
@Autonomous
public class TestAutonIncriments extends LinearOpMode {
    AutonModeLibrary autonMode;

    public void runOpMode() throws InterruptedException {
        autonMode = new AutonModeLibrary(this, FTCAlliance.RED, FTCPosition.LEFT, true);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            autonMode.pickUpGlyph();
            Direction dir = Direction.FORWARD;

            //Direction dir = autonMode.knockOffJewelSleep();
            //int count = autonMode.glyptographSleep(dir);

            autonMode.placeGlyphsSleep(2);

            sleep(30000);
        }
    }
}
