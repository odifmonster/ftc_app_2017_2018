package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.enums.Direction;
import org.firstinspires.ftc.enums.FTCAlliance;
import org.firstinspires.ftc.enums.FTCPosition;
import org.firstinspires.ftc.libraries.AutonModeLibrary;

/**
 * Created by megankaye on 1/12/18.
 */

@Autonomous
public class TestAutonTotal extends LinearOpMode {
    AutonModeLibrary autonMode;

    public void runOpMode() throws InterruptedException {
        autonMode = new AutonModeLibrary(this, FTCAlliance.BLUE, FTCPosition.LEFT);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        Direction direction = Direction.BACKWARD;
        int state = 0;

        while (opModeIsActive()) {
            //knock off jewel: 2 sec/1 sec
            switch (state) {
                case 0:
                    direction = autonMode.knockOffJewel();
                    state++;
                    break;
                case 1:
                    //pick up glyph: 1 sec
                    autonMode.pickUpGlyph();
                    state++;
                    break;
                case 2:
                    //place glyph: 10 sec
                    if (autonMode.identifyAndPlace(direction)) {
                        state++;
                    }
                    break;
                case 3:
                    //get more glyphs: 15 (7.5 and 7.5)
                    autonMode.driveGetGlyphPlace();
                    //CHECK if they've done it twice, etc.
                    state++;
                    break;
                case 4:
                    //drive to safe zone: 2 sec **NEEDS FIXING**
                    autonMode.driveToSafeZone(direction);
                    state++;
                    break;
                default:
                    sleep(30000);
                    break;
            }
        }
    }
}
