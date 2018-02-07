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
import org.opencv.core.Mat;

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
        currentYaw = angles.firstAngle;

        while (counterClockwiseDist(currentYaw, targetYaw) > 0) {
            drivingLibrary.turn(-0.5f, 0);
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, angleUnit);
            currentYaw = angles.firstAngle;
        }

        drivingLibrary.brakeStop();
    }

    public void rightToAngle(double targetYaw) {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, angleUnit);
        currentYaw = angles.firstAngle;

        while (clockwiseDist(currentYaw, targetYaw) > 0) {
            drivingLibrary.turn(0.5f, 0);
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, angleUnit);
            currentYaw = angles.firstAngle;
        }

        drivingLibrary.brakeStop();
    }

    public void turnLeft(double radians) {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, angleUnit);
        currentYaw = angles.firstAngle;
        double targetYaw = currentYaw + radians;

        if (targetYaw > Math.PI) {
            targetYaw -= (2 * Math.PI);
        }

        while (counterClockwiseDist(currentYaw, targetYaw) > 0) {
            drivingLibrary.turn(-0.5f, 0);
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, angleUnit);
            currentYaw = angles.firstAngle;
        }

        drivingLibrary.brakeStop();
    }

    public void turnRight(double radians) {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, angleUnit);
        currentYaw = angles.firstAngle;
        double targetYaw = currentYaw - radians;

        if (targetYaw < -Math.PI) {
            targetYaw += (2 * Math.PI);
        }

        while (clockwiseDist(currentYaw, targetYaw) > 0) {
            drivingLibrary.turn(0.5f, 0);
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, angleUnit);
            currentYaw = angles.firstAngle;
        }

        drivingLibrary.brakeStop();
    }

    private double clockwiseDist(double startAngle, double endAngle) {
        if (startAngle > endAngle) {
            return startAngle - endAngle;
        }
        return Math.PI + startAngle + Math.PI - endAngle;
    }

    private double counterClockwiseDist(double startAngle, double endAngle) {
        if (endAngle >= startAngle) {
            return endAngle - startAngle;
        }
        return Math.PI - startAngle + Math.PI + endAngle;
    }
}
