package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.libraries.RelicArmLibrary;

/**
 * Created by megankaye on 2/25/18.
 */
@TeleOp
public class TestRelicArmServos extends LinearOpMode {
    RelicArmLibrary relicArmLibrary;


    public void runOpMode() throws InterruptedException {
        relicArmLibrary = new RelicArmLibrary(this);

        waitForStart();

        while (opModeIsActive()) {
            //pulley extension
            if (gamepad1.a) {
                telemetry.addData("a", "Pressed");
                relicArmLibrary.firstServoForward();
            } else if (gamepad1.b) {
                telemetry.addData("b", "Pressed");
                relicArmLibrary.secondServoForward();
            } else if (gamepad1.x) {
                telemetry.addData("x", "Pressed");
                relicArmLibrary.thirdServoForward();
            } else {
                relicArmLibrary.stopAll();
                telemetry.addData("nothing pressed", "tru");
            }

            if (gamepad1.left_bumper) {
                telemetry.addData("lbumper", "Pressed");
                //relicArmLibrary.liftClaw();
                relicArmLibrary.extendArm();
            } if (gamepad1.right_bumper) {
                telemetry.addData("rbumper", "Pressed");
                //relicArmLibrary.liftLift();
                relicArmLibrary.retractArm();
            }
            //relic lift presets
            relicArmLibrary.outputInfo();
            telemetry.update();
        }
    }
}
