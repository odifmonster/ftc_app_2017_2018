package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.libraries.DrivingLibrary;

/**
 * Created by lamanwyner on 1/22/18.
 */

@Autonomous
public class TestDrivingLibraryAuton extends LinearOpMode {
    DrivingLibrary drivingLibrary;

    @Override
    public void runOpMode() {
        drivingLibrary = new DrivingLibrary(this);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            drivingLibrary.turnRight(Math.PI / 2);
        }
    }
}
