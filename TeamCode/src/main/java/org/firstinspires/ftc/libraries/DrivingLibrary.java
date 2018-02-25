package org.firstinspires.ftc.libraries;

/**
 * Created by lamanwyner on 12/29/17.
 */

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.enums.DrivingMode;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
// git push origin beta

public class DrivingLibrary {
    // hardware variables
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftRear;
    private DcMotor rightRear;
    private DcMotor[] allMotors;
    private HardwareMap hardwareMap;
    private double[] strafeBias;

    // sensor variables
    private BNO055IMU imu;
    private Orientation angles;
    private Acceleration gravity;

    // other variables
    private double speedSetting;
    private DrivingMode drivingMode;
    private OpMode opMode;

    public DrivingLibrary(OpMode opMode) {
        this.opMode = opMode;
        hardwareMap = opMode.hardwareMap;

        leftFront = hardwareMap.get(DcMotor.class, "left_front");
        rightFront = hardwareMap.get(DcMotor.class, "right_front");
        leftRear = hardwareMap.get(DcMotor.class, "left_rear");
        rightRear = hardwareMap.get(DcMotor.class, "right_rear");

        rightRear.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu.initialize(parameters);

        allMotors = new DcMotor[] {leftFront, rightFront, leftRear, rightRear};
        strafeBias = new double[] {1, 1, 1, 1};
    }

    public void updateStrafeBias(int wheel, int multiplier) {
        strafeBias[wheel] += (.01 * multiplier);
    }
    public void printStrafeBias() {
        opMode.telemetry.addData("fl", strafeBias[0]);
        opMode.telemetry.addData("fr", strafeBias[1]);
        opMode.telemetry.addData("rr", strafeBias[2]);
        opMode.telemetry.addData("rl", strafeBias[3]);
    }

    public void driveStraight(float x, float y) {
        float maxSpeed = Math.max(y + x, y - x);
        double multiplier = 1;

        if (maxSpeed > 1) {
            multiplier = 1 / maxSpeed;
        }

        leftFront.setPower(multiplier * speedSetting * (y + x) * strafeBias[0]);
        rightFront.setPower(multiplier * speedSetting * (y - x) * strafeBias[1]);
        rightRear.setPower(multiplier * speedSetting * (y + x) * strafeBias[2]);
        leftRear.setPower(multiplier * speedSetting * (y - x) * strafeBias[3]);
    }

    public void driveStraightClicks(double theta, int clicks) {
        /**
         * This function takes in an angle and a number of clicks and moves the fastest motor that
         * many clicks
         **/

        // TODO: everything
        resetEncoders();

        double x = Math.cos(theta);
        double y = Math.sin(theta);
        double multiplier;           // accounts for values > 1

        startEncoders();

        // broken into quadrants for the motors that are turning the fastest and then negative values
        if (theta >= 0 && theta < Math.PI/2) {
            multiplier = 1 / (y + x);
            int clicksSmall = (int) (clicks * (y - x)/(y + x)); // fewer clicks for slower motor

            leftFront.setTargetPosition(clicks);
            rightRear.setTargetPosition(clicks);
            rightFront.setTargetPosition(clicksSmall);
            leftRear.setTargetPosition(clicksSmall);

            leftFront.setPower(multiplier * speedSetting * (y + x));
            rightRear.setPower(multiplier * speedSetting * (y + x));
            rightFront.setPower(multiplier * speedSetting * (y - x));
            leftRear.setPower(multiplier * speedSetting * (y - x));

            while (leftFront.getCurrentPosition() < clicks) {
                continue;
            }

            stopDrivingMotors();
        } else if (theta >= Math.PI / 2 && theta < Math.PI) {
            multiplier = 1 / (y - x);
            int clicksSmall = (int) (clicks * (y + x)/(y - x));

            rightFront.setTargetPosition(clicks);
            leftRear.setTargetPosition(clicks);
            leftFront.setTargetPosition(clicksSmall);
            rightRear.setTargetPosition(clicksSmall);

            rightFront.setPower(multiplier * speedSetting * (y - x));
            leftRear.setPower(multiplier * speedSetting * (y - x));
            leftFront.setPower(multiplier * speedSetting * (y + x));
            rightRear.setPower(multiplier * speedSetting * (y + x));

            while (rightFront.getCurrentPosition() < clicks) {
                continue;
            }

            stopDrivingMotors();
        } else if (theta >= Math.PI && theta < 3*Math.PI/2) {
            multiplier = -1 / (y + x); // keeps multiplier positive
            int clicksSmall = (int) (clicks * (y - x)/(y + x));

            leftFront.setTargetPosition(-clicks);
            rightRear.setTargetPosition(-clicks);
            rightFront.setTargetPosition(-clicksSmall);
            leftRear.setTargetPosition(-clicksSmall);

            leftFront.setPower(multiplier * speedSetting * (y + x));
            rightRear.setPower(multiplier * speedSetting * (y + x));
            rightFront.setPower(multiplier * speedSetting * (y - x));
            leftRear.setPower(multiplier * speedSetting * (y - x));

            while (leftFront.getCurrentPosition() > clicks) {
                continue;
            }

            stopDrivingMotors();
        } else if (theta >= 3*Math.PI/2 && theta < 2*Math.PI) {
            multiplier = -1 / (y - x);
            int clicksSmall = (int) (clicks * (y + x)/(y - x));

            rightFront.setTargetPosition(-clicks);
            leftRear.setTargetPosition(-clicks);
            leftFront.setTargetPosition(-clicksSmall);
            rightRear.setTargetPosition(-clicksSmall);

            rightFront.setPower(multiplier * speedSetting * (y - x));
            leftRear.setPower(multiplier * speedSetting * (y - x));
            leftFront.setPower(multiplier * speedSetting * (y + x));
            rightRear.setPower(multiplier * speedSetting * (y + x));

            while (rightFront.getCurrentPosition() > clicks) {
                continue;
            }

            stopDrivingMotors();
        }

        resetEncoders(); // this really stresses me out so I added another one just in case
    }

    public void turn(float x, float y) {
        float maxSpeed = Math.max(y + x, y - x);
        double multiplier = 1;

        if (maxSpeed > 1) {
            multiplier = 1 / maxSpeed;
        }

        leftFront.setPower((y + x) * speedSetting * multiplier * strafeBias[0]);
        leftRear.setPower((y + x) * speedSetting * multiplier * strafeBias[1]);
        rightFront.setPower((y - x) * speedSetting * multiplier * strafeBias[2]);
        rightRear.setPower((y - x) * speedSetting * multiplier * strafeBias[3]);
    }

    public void setSpeed(double speed) {
        speedSetting = speed;
    }

    public void setMode(int i) {
        DrivingMode[] values = DrivingMode.values();
        drivingMode = values[i];

        switch (drivingMode) {
            case FLOAT_STOP:
                for (DcMotor motor : allMotors) {
                    motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                }
                break;
            case BRAKE_STOP:
                for (DcMotor motor : allMotors) {
                    motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                }
                break;
        }
    }

    public void setMode(DrivingMode d) {
        drivingMode = d;

        switch (drivingMode) {
            case FLOAT_STOP:
                for (DcMotor motor : allMotors) {
                    motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                }
                break;
            case BRAKE_STOP:
                for (DcMotor motor : allMotors) {
                    motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                }
                break;
        }
    }

    public String getMode() {
        return drivingMode.getStringValue();
    }

    public void setMotorSpeed(double speed) {
        for (DcMotor motor : allMotors) {
            motor.setPower(speed);
        }
    }

    public void stopDrivingMotors() {
        for (DcMotor motor : allMotors) {
            motor.setPower(0);
        }
    }

    public void floatStop() {
        for (DcMotor motor : allMotors) {
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            motor.setPower(0);
        }
    }

    public void brakeStop() {
        for (DcMotor motor : allMotors) {
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor.setPower(0);
        }
    }

    private void resetEncoders() {
        for (DcMotor motor : allMotors) {
            if (motor != null) motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }

    private void startEncoders() {
        for (DcMotor motor : allMotors) {
            if (motor != null) motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }
}
