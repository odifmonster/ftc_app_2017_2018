package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.libraries.RelicArmLibrary;

/**
 * Created by megankaye on 1/26/18.
 */
@TeleOp
public class TestRelicClaw extends LinearOpMode {
    RelicArmLibrary relicArmLibrary;


    public void runOpMode() throws InterruptedException {
        relicArmLibrary = new RelicArmLibrary(this);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad2.dpad_right) {
                relicArmLibrary.extendArm(true);
            } else if (gamepad2.dpad_left) {
                relicArmLibrary.extendArm(false);
            } else {
                relicArmLibrary.stopArm();
            }

            if (gamepad2.b) {
                relicArmLibrary.activateLift();
            }

            if (gamepad2.a) {
                relicArmLibrary.activateClaw();
            }
            relicArmLibrary.outputInfo();
        }
    }
}
