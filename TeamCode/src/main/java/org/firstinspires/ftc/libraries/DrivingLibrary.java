package org.firstinspires.ftc.libraries;

/**
 * Created by lamanwyner on 12/29/17.
 */

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class DrivingLibrary {
    // hardware variables
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftRear;
    private DcMotor rightRear;
    private DcMotor[] allMotors;
    private HardwareMap hardwareMap;

    // other variables
    private double speedSetting;

    public DrivingLibrary(OpMode opMode) {
        hardwareMap = opMode.hardwareMap;

        leftFront = hardwareMap.get(DcMotor.class, "left_front");
        rightFront = hardwareMap.get(DcMotor.class, "right_front");
        leftRear = hardwareMap.get(DcMotor.class, "left_rear");
        rightRear = hardwareMap.get(DcMotor.class, "right_rear");

        leftRear.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setDirection(DcMotor.Direction.REVERSE);

        allMotors = new DcMotor[] {leftFront, rightFront, leftRear, rightRear};
    }

    public void driveStraight(float x, float y) {
        double multiplier = Math.sqrt(2.0) / 2.0;

        leftFront.setPower(multiplier * speedSetting * (y + x));
        rightFront.setPower(multiplier * speedSetting * (y - x));
        leftRear.setPower(multiplier * speedSetting * (y - x));
        rightRear.setPower(multiplier * speedSetting * (y + x));
    }

    public void turn(float x, float y) {
        leftFront.setPower((y + x) * speedSetting);
        leftRear.setPower((y + x) * speedSetting);
        rightFront.setPower((y - x) * speedSetting);
        rightRear.setPower((y - x) * speedSetting);
    }

    public void setSpeed(double speed) {
        speedSetting = speed;
    }

    public void setAllMotors(double speed) {
        for (DcMotor motor : allMotors) {
            motor.setPower(speed);
        }
    }

    public void stopDrivingMotors() {
        for (DcMotor motor : allMotors) {
            motor.setPower(0);
        }
    }

    public void resetEncoders() {
        for (DcMotor motor : allMotors) {
            if (motor != null) motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }
}
