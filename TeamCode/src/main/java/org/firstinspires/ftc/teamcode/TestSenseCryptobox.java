package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.enums.Direction;
import org.firstinspires.ftc.enums.FTCAlliance;
import org.firstinspires.ftc.enums.FTCPosition;
import org.firstinspires.ftc.libraries.AutonModeLibrary;

/**
 * Created by megankaye on 2/8/18.
 */
@Autonomous
public class TestSenseCryptobox extends LinearOpMode {
    AutonModeLibrary autonMode;

    public void runOpMode() throws InterruptedException {
        autonMode = new AutonModeLibrary(this, FTCAlliance.RED, FTCPosition.LEFT);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            autonMode.pickUpGlyph();
            sleep(1000);

           //autonMode.senseCryptoboxFar();
            autonMode.goToBlue();

            sleep(30000);

        }
    }
}
