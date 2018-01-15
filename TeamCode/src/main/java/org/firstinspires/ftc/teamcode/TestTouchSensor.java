package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.libraries.AutonModeLibrary;
import org.firstinspires.ftc.enums.Direction;
import org.firstinspires.ftc.enums.FTCAlliance;
import org.firstinspires.ftc.enums.FTCPosition;

/**
 * Created by megankaye on 1/11/18.
 */
@Autonomous
public class TestTouchSensor extends LinearOpMode {
    AutonModeLibrary autonMode;
    public void runOpMode() throws InterruptedException {
        autonMode = new AutonModeLibrary(this, FTCAlliance.BLUE, FTCPosition.LEFT);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        boolean tapped;

        waitForStart();

        while (opModeIsActive()) {
            tapped = autonMode.testTouchSensor();
            if (tapped == false) {
// button is pressed.
                telemetry.addData("Button", "PRESSED");
            } else {
// button is not pressed.
                telemetry.addData("Button", "NOT PRESSED");
            }
            telemetry.update();
        }
    }
}
