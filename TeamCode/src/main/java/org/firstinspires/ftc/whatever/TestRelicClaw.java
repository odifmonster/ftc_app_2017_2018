package org.firstinspires.ftc.whatever;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.libraries.RelicArmLibrary;

/**
 * Created by megankaye on 1/26/18.
 */
@Disabled
@TeleOp
public class TestRelicClaw extends LinearOpMode {
    RelicArmLibrary relicArmLibrary;


    public void runOpMode() throws InterruptedException {
        relicArmLibrary = new RelicArmLibrary(this);
        int sleeptime = 300;

        waitForStart();

        while (opModeIsActive()) {
            //pulley extension
            if (gamepad1.dpad_right) {
                telemetry.addData("dPadRight", "right");
                //relicArmLibrary.extendArm(true);
                sleep(sleeptime);
            } else if (gamepad1.dpad_left) {
                //relicArmLibrary.extendArm(false);
                telemetry.addData("dPadRight", "Pressed");
                sleep(sleeptime);
            } else {
                //relicArmLibrary.stopArm();
            }

            /*if (gamepad1.right_bumper) {
                relicArmLibrary.activatePWM();
            } else {
                relicArmLibrary.disablePWM();
            }*/

            if (gamepad1.left_bumper) {
                //relicArmLibrary.activateClaw();
                telemetry.addData("left bumper", "pressed");
                sleep(sleeptime);
            }

            //relic lift presets
            relicArmLibrary.outputInfo();
            telemetry.update();
        }
    }
}
