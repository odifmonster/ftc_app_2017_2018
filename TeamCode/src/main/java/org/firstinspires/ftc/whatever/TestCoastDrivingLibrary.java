package org.firstinspires.ftc.whatever;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.libraries.DrivingLibrary;

/**
 * Created by megankaye on 1/25/18.
 */
@Autonomous
public class TestCoastDrivingLibrary extends LinearOpMode {
    DrivingLibrary drivingLibrary;

    public void runOpMode() throws InterruptedException {
        drivingLibrary = new DrivingLibrary(this);
        drivingLibrary.setSpeed(1);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            drivingLibrary.driveStraight(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            drivingLibrary.turn(gamepad1.right_stick_x, -gamepad1.right_stick_y);

            if(gamepad1.a) {
                drivingLibrary.setMode(0);
            }

            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
