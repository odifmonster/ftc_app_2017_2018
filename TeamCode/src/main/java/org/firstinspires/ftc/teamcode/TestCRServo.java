package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;

/**
 * Created by lamanwyner on 2/23/18.
 */
@Disabled
@Autonomous
public class TestCRServo extends LinearOpMode {
    CRServo crServo1, crServo2, crServo3;

    public void runOpMode() throws InterruptedException {
        crServo1 = hardwareMap.get(CRServo.class, "CRServo1");
        crServo2 = hardwareMap.get(CRServo.class, "CRServo2");
        crServo3 = hardwareMap.get(CRServo.class, "CRServo3");

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addLine("Set motor power to 200");
            crServo1.setPower(200);
            telemetry.addLine("Sleeping...");
            sleep(500);

            telemetry.addLine("set motor power to 128");
            crServo1.setPower(128);

            telemetry.update();
        }

    }
}
