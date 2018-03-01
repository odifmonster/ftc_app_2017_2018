package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by megankaye on 12/8/17.
 */

@TeleOp
@Disabled

public class ControllerTeleOpNoPully extends LinearOpMode {
    //defining our two motors
    private DcMotor motorL;
    private DcMotor motorR;

    //defining our servo motors - pully
    private DcMotor motorP;

    //defining our servo motors - claw
    private Servo servoCL;
    private Servo servoCR;

    @Override
    public void runOpMode() {
        motorL = hardwareMap.get(DcMotor.class, "motorL");
        motorR = hardwareMap.get(DcMotor.class, "motorR");

        //motorP = hardwareMap.get(DcMotor.class, "motorP");

        servoCL = hardwareMap.get(Servo.class, "servoCL");
        servoCR = hardwareMap.get(Servo.class, "servoCR");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        double forwardPower;
        double sidePower;

        /*boolean motorPGo;
        boolean motorPReverse;*/

        boolean servoCGo;
        boolean servoCReverse;

        while (opModeIsActive()) {
            //sees if gamepad sticks are held and moves motors.
            forwardPower = this.gamepad1.right_stick_y;
            sidePower = -this.gamepad1.right_stick_x;

            if ((forwardPower > 0 || forwardPower < 0) && sidePower == 0) {
                motorL.setPower(forwardPower);
                motorR.setPower(-forwardPower);
            }

            if (forwardPower == 0 && (sidePower > 0 || sidePower < 0)) {
                motorL.setPower(sidePower);
                motorR.setPower(sidePower);
            } else {
                motorL.setPower(0);
                motorR.setPower(0);
            }

            //see if LT was held. run pully servo motors.
            //see if LB was held. reverse pully servo motors.
            /*motorPGo = this.gamepad1.left_trigger>0.5;
            motorPReverse = this.gamepad1.left_bumper;
            if (motorPGo || motorPReverse) {
                if (motorPGo && !motorPReverse) {
                    motorP.setPower(1.0);
                }
                if (motorPReverse && !motorPGo) {
                    motorP.setPower(-1.0);
                }
            } else {
                motorP.setPower(0);
            }*/

            //see if RT was held. run claw servo motors.
            servoCGo = gamepad1.right_trigger > 0.5;
            if (servoCGo) {
                servoCL.setPosition(servoCL.getPosition() - .25);
                servoCR.setPosition(servoCR.getPosition() + .25);
            }

            //see if RB was held. reverse claw servo motors.
            servoCReverse = gamepad1.right_bumper;
            if (servoCReverse) {
                servoCL.setPosition(servoCL.getPosition() + .25);
                servoCR.setPosition(servoCR.getPosition() - .25);
            }


            telemetry.addData("Target Power Forward", forwardPower);
            telemetry.addData("Motor PowerL", motorL.getPower());
            telemetry.addData("Motor PowerR", motorR.getPower());
            telemetry.addData("Target Power Side", sidePower);

            /*telemetry.addData("LT Pressed", motorPGo);
            telemetry.addData("LB Pressed", motorPReverse);

            telemetry.addData("RT Pressed", servoCGo);
            telemetry.addData("RB Pressed", servoCReverse);*/

            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
