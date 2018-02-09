package org.firstinspires.ftc.whatever;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.enums.Direction;
import org.firstinspires.ftc.enums.FTCAlliance;
import org.firstinspires.ftc.enums.FTCPosition;
import org.firstinspires.ftc.libraries.AutonModeLibrary;
import org.firstinspires.ftc.libraries.DrivingLibrary;

/**
 * Created by megankaye on 1/12/18.
 */

@Disabled
@Autonomous
public class TestAutonTotal extends LinearOpMode {
    AutonModeLibrary autonMode;
    DrivingLibrary drivingLibrary;

    public void runOpMode() throws InterruptedException {
        autonMode = new AutonModeLibrary(this, FTCAlliance.RED, FTCPosition.LEFT);
        drivingLibrary = new DrivingLibrary(this);
        drivingLibrary.setSpeed(1);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        Direction direction;

        while (opModeIsActive()) {
            //pick up glyph
            autonMode.pickUpGlyph();

            //knock off jewel: 2 sec/1 sec
            //direction = autonMode.knockOffJewel();

            //glyptograph
            //int count = autonMode.glyptograph(direction);

            //autonMode.placeGlyphs(count);

            /*
            //drive to safe zone: 2 sec **NEEDS FIXING**
            autonMode.driveToSafeZone(direction);
            drivingLibrary.turnRight(Math.PI / 2);
            drivingLibrary.turnLeft(Math.PI / 2);*/

            sleep(30000);
        }
    }
}