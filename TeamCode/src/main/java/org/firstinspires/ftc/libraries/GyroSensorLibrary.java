package org.firstinspires.ftc.libraries;

import android.view.Gravity;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

/**
 * Created by lamanwyner on 2/5/18.
 */

public class GyroSensorLibrary {
    private OpMode opMode;
    private DrivingLibrary drivingLibrary;

    private BNO055IMU imu;
    private AngleUnit angleUnit;

    private double currentYaw;
    private double currentRoll;
    private double currentPitch;
    private Orientation angles;
    private Gravity gravity;

    public GyroSensorLibrary(OpMode opMode, BNO055IMU.AngleUnit angleUnit,
                             BNO055IMU.AccelUnit accelUnit, DrivingLibrary drivingLibrary) {
        this.opMode = opMode;
        this.drivingLibrary = drivingLibrary;

        imu = opMode.hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = angleUnit;
        parameters.accelUnit = accelUnit;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        imu.initialize(parameters);
        imu.startAccelerationIntegration(new Position(), new Velocity(), 100);

        this.angleUnit = (angleUnit == BNO055IMU.AngleUnit.RADIANS) ? AngleUnit.RADIANS :
                AngleUnit.DEGREES;
    }

    public void leftToAngle(double targetYaw) {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, angleUnit);
        currentYaw = angles.firstAngle + Math.PI;
        targetYaw += (Math.PI);

        if (targetYaw < currentYaw) targetYaw += (2 * Math.PI);

        while (currentYaw < targetYaw) {
            drivingLibrary.turn(-0.5f, 0);
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, angleUnit);
            currentYaw = angles.firstAngle + Math.PI;
        }

        drivingLibrary.brakeStop();
    }

    public void rightToAngle(double targetYaw) {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, angleUnit);
        currentYaw = angles.firstAngle + Math.PI;
        targetYaw += (Math.PI);

        if (targetYaw > currentYaw) targetYaw -= (2 * Math.PI);

        while (currentYaw > targetYaw) {
            drivingLibrary.turn(0.5f, 0);
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, angleUnit);
            currentYaw = angles.firstAngle + Math.PI;
        }

        drivingLibrary.brakeStop();
    }

    public void turnLeft(double radians) {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, angleUnit);
        currentYaw = angles.firstAngle + Math.PI;
        double targetYaw = currentYaw + radians + Math.PI;
        double subtractYaw = 0;

        if (targetYaw > 2 * Math.PI) {
            targetYaw -= (2 * Math.PI);
            subtractYaw = 2 * Math.PI;
        }

        while (currentYaw - subtractYaw < targetYaw) {
            drivingLibrary.turn(-0.5f, 0);
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, angleUnit);
            currentYaw = angles.firstAngle + Math.PI;
        }

        drivingLibrary.brakeStop();
    }

    public void turnRight(double radians) {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, angleUnit);
        currentYaw = angles.firstAngle + Math.PI;
        double targetYaw = currentYaw + radians + Math.PI;
        double addYaw = 0;

        if (targetYaw < 0) {
            targetYaw += (2 * Math.PI);
            addYaw = 2 * Math.PI;
        }

        while (currentYaw + addYaw > targetYaw) {
            drivingLibrary.turn(0.5f, 0);
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, angleUnit);
            currentYaw = angles.firstAngle + Math.PI;
        }

        drivingLibrary.brakeStop();
    }
}
