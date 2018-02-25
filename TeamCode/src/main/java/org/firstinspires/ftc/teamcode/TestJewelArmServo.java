package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.enums.FTCAlliance;
import org.firstinspires.ftc.enums.FTCPosition;
import org.firstinspires.ftc.libraries.AutonModeLibrary;

/**
 * Created by megankaye on 2/25/18.
 */
@TeleOp
public class TestJewelArmServo extends LinearOpMode {
    AutonModeLibrary autonMode;

    public void runOpMode() throws InterruptedException {
        autonMode = new AutonModeLibrary(this, FTCAlliance.RED, FTCPosition.LEFT, true);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            autonMode.colorArm.setPosition(.1);
            sleep(1000);
            autonMode.colorArm.setPosition(1);
            sleep(1000);
        }
    }
}
