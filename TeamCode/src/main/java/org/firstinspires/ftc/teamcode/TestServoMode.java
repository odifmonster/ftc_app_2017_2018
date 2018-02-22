package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.libraries.GlyphArmLibrary;

/**
 * Created by megankaye on 2/20/18.
 */
@TeleOp
public class TestServoMode extends LinearOpMode {
    GlyphArmLibrary glyphArmLibrary;
    Servo leftTop, leftBottom, rightTop, rightBottom;

    public void runOpMode() throws InterruptedException {
        leftTop = hardwareMap.get(Servo.class, "left_top");
        leftBottom = hardwareMap.get(Servo.class, "left_bottom");
        rightTop = hardwareMap.get(Servo.class, "right_top");
        rightBottom = hardwareMap.get(Servo.class, "right_bottom");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        double threshold = .01;
        boolean leftt, rightt, leftb, rightb;
        leftb = false;
        rightb = false;
        rightt = false;
        leftt = false;
        waitForStart();

        while (opModeIsActive()) {

            if (gamepad2.left_bumper) {
                //left top
                telemetry.addData("lefttpushed", true);
                if (leftt) {
                    leftTop.setPosition(1);
                } else {
                    leftTop.setPosition(0);
                }
                leftt = !leftt;
            }
            if (gamepad2.right_bumper) {
                //right top
                telemetry.addData("rightttpushed", true);
                if (rightt) {
                    rightTop.setPosition(1);
                } else {
                    rightTop.setPosition(0);
                }
                rightt = !rightt;

            }
            if (gamepad2.left_trigger > threshold) {
                //left bottom
                telemetry.addData("leftbpushed", true);
                if (leftb) {
                    leftBottom.setPosition(1);
                } else {
                    leftBottom.setPosition(0);
                }
                leftb = !leftb;

            }
            if (gamepad2.right_trigger > threshold) {
                //right bottom
                telemetry.addData("rightbpushedd", true);
                if (rightb) {
                    rightBottom.setPosition(1);
                } else {
                    rightBottom.setPosition(0);
                }
                rightb = !rightb;
            }

            telemetry.addData("leftbottom", leftBottom.getPosition());
            telemetry.addData("rightbottom", rightBottom.getPosition());
            telemetry.addData("righttop", rightTop.getPosition());
            telemetry.addData("lefttop", leftTop.getPosition());
            telemetry.update();
        }
    }
}
