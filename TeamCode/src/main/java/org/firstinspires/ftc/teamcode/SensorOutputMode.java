package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.enums.SensingMode;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

import java.util.Locale;

/**
 * Created by lamanwyner on 1/18/18.
 */

@Disabled
@TeleOp
public class SensorOutputMode extends LinearOpMode {
    private DcMotor leftFront, rightFront, leftRear, rightRear; // TODO: Add other motors
    private DcMotor pulley;
    private Servo leftTop, rightTop, leftBottom, rightBottom;
    private Servo jewelArm; // TODO: Add other servos

    private ColorSensor colorSensor;
    private DistanceSensor distanceSensor;

    private BNO055IMU imu;
    private Orientation angles;
    private Acceleration gravity;

    private SensingMode sensingMode;
    private int sensingModeInd;

    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotor.class, "left_front");
        rightFront = hardwareMap.get(DcMotor.class, "right_front");
        leftRear = hardwareMap.get(DcMotor.class, "left_rear");
        rightRear = hardwareMap.get(DcMotor.class, "right_rear");

        pulley = hardwareMap.get(DcMotor.class, "pulley");

        leftTop = hardwareMap.get(Servo.class, "left_top");
        rightTop = hardwareMap.get(Servo.class, "right_top");
        leftBottom = hardwareMap.get(Servo.class, "left_bottom");
        rightBottom = hardwareMap.get(Servo.class, "right_bottom");

        jewelArm = hardwareMap.get(Servo.class, "color_arm");

        colorSensor = hardwareMap.get(ColorSensor.class, "color_sensor");
        distanceSensor = hardwareMap.get(DistanceSensor.class, "color_sensor");

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu.initialize(parameters);

        sensingMode = SensingMode.ALL_SENSORS;
        sensingModeInd = 5;

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);

        while (opModeIsActive()) {
            if (gamepad1.dpad_up) {
                sensingModeInd++;
                sensingModeInd %= 7;
                sensingMode = SensingMode.values()[sensingModeInd];
            }

            if (sensingMode == SensingMode.MOTORS_AND_SERVOS) {
                telemetry.addLine()
                    .addData("Left Front", leftFront.getCurrentPosition())
                    .addData("Right Front", rightFront.getCurrentPosition())
                ;
                telemetry.addLine()
                    .addData("Left Rear", leftRear.getCurrentPosition())
                    .addData("Right Rear", rightRear.getCurrentPosition())
                ;
                telemetry.addLine()
                    .addData("Left Top", leftTop.getPosition())
                    .addData("Right Top", rightTop.getPosition())
                ;
                telemetry.addLine()
                    .addData("Left Bottom", leftBottom.getPosition())
                    .addData("Right Bottom", rightBottom.getPosition())
                ;
            } else if (sensingMode == SensingMode.COLOR_DISTANCE) {
                float[] hsvValues = new float[] {0f, 0f, 0f};
                double scaleFactor = 255;
                Color.RGBToHSV((int) (colorSensor.red() * scaleFactor),
                        (int) (colorSensor.green() * scaleFactor),
                        (int) (colorSensor.blue() * scaleFactor),
                        hsvValues);

                telemetry.addData("Distance (cm)",
                        String.format(Locale.US, "%.02f",
                                distanceSensor.getDistance(DistanceUnit.CM)));
                telemetry.addLine()
                    .addData("Red", colorSensor.red())
                    .addData("Green", colorSensor.green())
                    .addData("Blue", colorSensor.blue())
                    .addData("Alpha", colorSensor.alpha())
                ;
                telemetry.addData("Hue", hsvValues[0]);
            } else if (sensingMode == SensingMode.GYRO) {
                telemetry.addAction(new Runnable() {
                    @Override
                    public void run() {
                        angles = imu.getAngularOrientation(AxesReference.INTRINSIC,
                                AxesOrder.ZYX, AngleUnit.RADIANS);
                        gravity = imu.getGravity();
                    }
                });

                telemetry.addLine()
                    .addData("Yaw", new Func<String>() {
                        @Override
                        public String value() {
                            return formatAngle(angles.angleUnit, angles.firstAngle);
                        }
                    })
                    .addData("Pitch", new Func<String>() {
                        @Override
                        public String value() {
                            return formatAngle(angles.angleUnit, angles.secondAngle);
                        }
                    })
                    .addData("Roll", new Func<String>() {
                        @Override
                        public String value() {
                            return formatAngle(angles.angleUnit, angles.thirdAngle);
                        }
                    })
                ;
            }

            telemetry.addData("Sensing Mode", sensingModeInd);
            telemetry.update();
        }
    }

    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
}
