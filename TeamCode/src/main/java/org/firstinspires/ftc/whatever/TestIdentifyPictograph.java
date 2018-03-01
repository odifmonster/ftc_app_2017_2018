package org.firstinspires.ftc.whatever;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.enums.Direction;
import org.firstinspires.ftc.enums.FTCAlliance;
import org.firstinspires.ftc.enums.FTCPosition;
import org.firstinspires.ftc.libraries.AutonModeLibrary;

/**
 * Created by megankaye on 1/10/18.
 */
@Disabled
@Autonomous
public class TestIdentifyPictograph extends LinearOpMode {
    AutonModeLibrary autonMode;

    public void runOpMode() throws InterruptedException {
        autonMode = new AutonModeLibrary(this, FTCAlliance.BLUE, FTCPosition.LEFT, true);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        boolean done = false;

        while (opModeIsActive()) {
            if (!done) {
                autonMode.pickUpGlyph();
                //autonMode.glyptograph(Direction.BACKWARD);
                done = true;
            }
            telemetry.update();
        }
    }
}
