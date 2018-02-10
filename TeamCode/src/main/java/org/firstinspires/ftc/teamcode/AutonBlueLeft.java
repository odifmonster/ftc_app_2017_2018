package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.enums.Direction;
import org.firstinspires.ftc.enums.FTCAlliance;
import org.firstinspires.ftc.enums.FTCPosition;
import org.firstinspires.ftc.libraries.AutonModeLibrary;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
/**
 * Created by megankaye on 1/5/18.
 */

@Autonomous
public class AutonBlueLeft extends LinearOpMode {

    AutonModeLibrary autonMode;

    public void runOpMode() throws InterruptedException {
        autonMode = new AutonModeLibrary(this, FTCAlliance.BLUE, FTCPosition.LEFT);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            autonMode.pickUpGlyph();

            Direction dir = autonMode.knockOffJewel();
            int count = autonMode.glyptograph(dir);

            autonMode.placeGlyphs(count);

            //glyptograph
            //RelicRecoveryVuMark rvu = autonMode.glyptograph(dir);

            //int count = autonMode.glyptograph(dir);
            //autonMode.placeGlyphs(count);

            sleep(30000);
        }
    }
}
