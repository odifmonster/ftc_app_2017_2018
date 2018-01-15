package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;



/**
 * Created by megankaye on 12/4/17.
 */

@Autonomous
@Disabled
public class BasicAutonRightSIde extends LinearOpMode {
    private DcMotor motorL;
    private DcMotor motorR;

    @Override
    public void runOpMode() {
        motorL = hardwareMap.get(DcMotor.class, "motorL");
        motorR = hardwareMap.get(DcMotor.class, "motorR");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        boolean completed = false;
        while (opModeIsActive()) {
            if (!completed) {

                //drives forward
                telemetry.addData("Stage", "One");
                motorL.setPower(-.5);
                motorR.setPower(.5);
                sleep(2000);

                //turns
                telemetry.addData("Stage", "Two");
                motorL.setPower(0);
                motorR.setPower(0);
                sleep(3000);

                //drives forward
                telemetry.addData("Stage", "Three");
                motorL.setPower(-.5);
                motorR.setPower(.5);
                sleep(2000);
                //zero

                telemetry.addData("Stage", "five");
                motorL.setPower(0);
                motorR.setPower(0);
                sleep(2000);

            }
            else {
                telemetry.addData("Status", "Auton Finished");
                telemetry.update();
            }
        }
    }
}
