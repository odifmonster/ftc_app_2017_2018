package org.firstinspires.ftc.whatever;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.libraries.RelicArmLibrary;

/**
 * Created by megankaye on 2/25/18.
 */
@Disabled
@TeleOp
public class TestRelicArmServos extends LinearOpMode {
    RelicArmLibrary relicArmLibrary;

    boolean noButtonsPressed;

    public void runOpMode() throws InterruptedException {
        relicArmLibrary = new RelicArmLibrary(this);


        waitForStart();

        while (opModeIsActive()) {
            noButtonsPressed = true;
            //pulley extension
            /*if (gamepad1.a) {
                telemetry.addData("a", "Pressed");
                relicArmLibrary.firstServoForward();
                noButtonsPressed = false;
            } else if (gamepad1.b) {
                telemetry.addData("b", "Pressed");
                relicArmLibrary.secondServoForward();
                noButtonsPressed = false;
            } else if (gamepad1.x) {
                telemetry.addData("x", "Pressed");
                relicArmLibrary.thirdServoForward();
                noButtonsPressed = false;
            }
            if (gamepad1.left_bumper) {
                telemetry.addData("lbumper", "Pressed");
                relicArmLibrary.extendArm();
                noButtonsPressed = false;
            } if (gamepad1.right_bumper) {
                telemetry.addData("rbumper", "Pressed");
                noButtonsPressed = false;
                relicArmLibrary.retractArm();
            }
            if (gamepad1.left_trigger > .5) {
                relicArmLibrary.lockCont3();
                sleep(300);
            }
            if (gamepad1.right_trigger > .5) {
                relicArmLibrary.lockCont12();
                sleep(300);
            }
            if (gamepad2.right_bumper) {
                relicArmLibrary.liftClaw();
                sleep(300);
            }*/

            //relic lift presets
            relicArmLibrary.outputInfo();
            relicArmLibrary.idle(noButtonsPressed);
            telemetry.update();
        }
    }
}
