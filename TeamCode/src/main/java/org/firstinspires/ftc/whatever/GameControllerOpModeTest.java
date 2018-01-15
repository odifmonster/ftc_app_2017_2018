package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by megankaye on 12/1/17.
 */

@TeleOp
@Disabled

public class GameControllerOpModeTest extends LinearOpMode {
    private DcMotor motor1;
    private DcMotor motor2;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;
    private Servo servoTest;


    @Override
    public void runOpMode() {
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
        servoTest = hardwareMap.get(Servo.class, "servoTest");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        double tgtPower = 0;
        double tgtPowerR = 0;
        while (opModeIsActive()) {
            tgtPower = -this.gamepad1.left_stick_y;
            tgtPowerR = this.gamepad1.right_stick_y;
            motor1.setPower(tgtPower);
            motor2.setPower(tgtPowerR);
            telemetry.addData("Target PowerL", tgtPower);
            telemetry.addData("Motor PowerL", motor1.getPower());
            telemetry.addData("Target PowerR", tgtPowerR);
            telemetry.addData("Motor PowerR", motor2.getPower());
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
