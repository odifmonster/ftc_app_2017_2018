package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.enums.FTCAlliance;
import org.firstinspires.ftc.enums.FTCPosition;
import org.firstinspires.ftc.libraries.AutonModeLibrary;

/**
 * Created by megankaye on 1/25/18.
 */
@Autonomous
public class TestArmRollers extends LinearOpMode {
    AutonModeLibrary autonMode;
    CRServo servoArmL;
    CRServo servoArmR;

    public void runOpMode() throws InterruptedException {
        autonMode = new AutonModeLibrary(this, FTCAlliance.BLUE, FTCPosition.LEFT);
        servoArmL = hardwareMap.get(CRServo.class, "servoArmL");
        servoArmR = hardwareMap.get(CRServo.class, "servoArmR");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            servoArmR.setPower(.5);
            servoArmL.setPower(-.5);
        }
    }
}
