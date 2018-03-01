package org.firstinspires.ftc.whatever;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by megankaye on 12/8/17.
 */

@Disabled
@TeleOp
public class ControllerTeleOp extends LinearOpMode {
    /*
    CONTROLS:
        Right joystick: Moving ^ v < >
        Left joystick: Turning < > (wheel power based on turning power)
        Right Bumper: Down
        Right trigger: Up
        Left Bumper: Open
        Left trigger: Close

        a:
     */
    //defining our four motors
    private DrivingLib dlib;

    //defining our servo motors - pully
    //private DcMotor motorP1;

    //defining our servo motors - claw
    /*private Servo servoCL;
    private Servo servoCR;*/

    @Override
    public void runOpMode() {
        dlib = new DrivingLib(this, true, true);

        //motorP1 = hardwareMap.get(DcMotor.class, "motorP1");

        //servoCL = hardwareMap.get(Servo.class, "servoCL");
        //servoCR = hardwareMap.get(Servo.class, "servoCR");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)

        boolean motorPGo;
        boolean motorPReverse;

        boolean servoCGo;
        boolean servoCReverse;

        while (opModeIsActive()) {
            //sees if gamepad sticks are held and moves motors.
                dlib.drivGay(0, -gamepad1.right_stick_y, gamepad1.right_stick_x);
                //dlib.drivStrafe(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            //see if LT was held. run pully servo motors.
            //see if LB wa6dfs held. reverse pully servo motors.
            /*motorPGo = this.gamepad1.left_trigger>0.5;
            motorPReverse = this.gamepad1.left_bumper;
            if (motorPGo || motorPReverse) {
                if (motorPGo && !motorPReverse) {
                    motorP1.setPower(1.0);
                }
                if (motorPReverse && !motorPGo) {
                    motorP1.setPower(-1.0);
                }
            } else {
                motorP1.setPower(0);
            }*/

            //see if RT was held. run claw servo motors.
            /*servoCGo = gamepad1.right_trigger > 0.5;
            if (servoCGo) {
                servoCL.setPosition(servoCL.getPosition() - .25);
                servoCR.setPosition(servoCR.getPosition() + .25);
            }

            //see if RB was held. reverse claw servo motors.
            servoCReverse = gamepad1.right_bumper;
            if (servoCReverse) {
                servoCL.setPosition(servoCL.getPosition() + .25);
                servoCR.setPosition(servoCR.getPosition() - .25);
            }*/

            /*telemetry.addData("LT Pressed", motorPGo);
            telemetry.addData("LB Pressed", motorPReverse);

            telemetry.addData("RT Pressed", servoCGo);
            telemetry.addData("RB Pressed", servoCReverse);*/

            /*telemetry.addData("Motor Power LeftFront", dlib.leftFront.getPower());
            telemetry.addData("Motor Power RightFront", rightFront.getPower());
            telemetry.addData("Motor Power LeftBack", leftBack.getPower());
            telemetry.addData("Motor Power RightBack", rightBack.getPower());*/

            telemetry.addData("left stick", gamepad1.left_stick_y);
            telemetry.addData("right stick", gamepad1.right_stick_y);

            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }

}