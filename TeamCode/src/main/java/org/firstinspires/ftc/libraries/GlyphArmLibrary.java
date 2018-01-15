package org.firstinspires.ftc.libraries;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by lamanwyner on 1/11/18.
 */

public class GlyphArmLibrary {
    private OpMode opMode;
    private HardwareMap hardwareMap;
    private DcMotor pulley;
    private Servo leftTop, leftBottom, rightTop, rightBottom;

    private double[] closedBottomPosition, closedTopPosition, openBottomPosition,
        openTopPosition;
    private double[] maxClosed, maxOpen;
    private Servo[] servos;
    private double increment;

    double pulleySpeed;

    public GlyphArmLibrary(OpMode opMode) {
        this.opMode = opMode;
        hardwareMap = opMode.hardwareMap;
        pulley = hardwareMap.get(DcMotor.class, "pulley");
        leftTop = hardwareMap.get(Servo.class, "left_top");
        leftBottom = hardwareMap.get(Servo.class, "left_bottom");
        rightTop = hardwareMap.get(Servo.class, "right_top");
        rightBottom = hardwareMap.get(Servo.class, "right_bottom");

        closedBottomPosition = new double[] {0.64, 0.33};
        closedTopPosition = new double[] {0.86, 0.4};
        openBottomPosition = new double[] {0.1, 0.9};
        openTopPosition = new double[] {0, 1};
        servos = new Servo[] {leftTop, leftBottom, rightTop, rightBottom};
        increment = 0.002;

        pulleySpeed = 0.5;
    }

    public void closeArmsPreset(boolean lb) {
        if (lb) {
            leftTop.setPosition(closedTopPosition[0]);
            leftBottom.setPosition(closedBottomPosition[0]);
            rightTop.setPosition(closedTopPosition[1]);
            rightBottom.setPosition(closedBottomPosition[1]);
        }
    }

    public void openArmsPreset(float lt) {
        if (lt > 0.5) {
            leftTop.setPosition(openTopPosition[0]);
            leftBottom.setPosition(openBottomPosition[0]);
            rightTop.setPosition(openTopPosition[1]);
            rightBottom.setPosition(openBottomPosition[1]);
        }
    }

    public void closeArmsIncrement(boolean rb) {
        if (rb) {
            if (leftTop.getPosition() + increment <= closedTopPosition[0]) {
                leftTop.setPosition(leftTop.getPosition() + increment);
            }
            if (leftBottom.getPosition() + increment <= closedBottomPosition[0]) {
                leftBottom.setPosition(leftBottom.getPosition() + increment);
            }
            if (rightTop.getPosition() - increment >= closedTopPosition[1]) {
                rightTop.setPosition(rightTop.getPosition() - increment);
            }
            if (rightBottom.getPosition() - increment >= closedBottomPosition[1]) {
                rightBottom.setPosition(rightBottom.getPosition() - increment);
            }
        }
    }

    public void openArmsIncrement(float rt) {
        if (rt > 0.5) {
            if (leftTop.getPosition() - increment >= openTopPosition[0]) {
                leftTop.setPosition(leftTop.getPosition() - increment);
            }
            if (leftBottom.getPosition() - increment >= openBottomPosition[0]) {
                leftBottom.setPosition(leftBottom.getPosition() - increment);
            }
            if (rightTop.getPosition() + increment <= openTopPosition[1]) {
                rightTop.setPosition(rightTop.getPosition() + increment);
            }
            if (rightBottom.getPosition() + increment <= openBottomPosition[1]) {
                rightBottom.setPosition(rightBottom.getPosition() + increment);
            }
        }
    }

    public void moveAllArms(Gamepad g1, Gamepad g2) {
        if (g1.left_bumper) {
            leftTop.setPosition(leftTop.getPosition() - increment);
        }
        if (g1.left_trigger > 0.5) {
            leftBottom.setPosition(leftBottom.getPosition() - increment);
        }
        if (g1.right_bumper) {
            leftTop.setPosition(leftTop.getPosition() + increment);
        }
        if (g1.right_trigger > 0.5) {
            leftBottom.setPosition(leftBottom.getPosition() + increment);
        }
        if (g2.left_bumper) {
            rightTop.setPosition(rightTop.getPosition() + increment);
        }
        if (g2.left_trigger > 0.5) {
            rightBottom.setPosition(rightBottom.getPosition() + increment);
        }
        if (g2.right_bumper) {
            rightTop.setPosition(rightTop.getPosition() - increment);
        }
        if (g2.right_trigger > 0.5) {
            rightBottom.setPosition(rightBottom.getPosition() - increment);
        }
    }

    public double getServoPos(int servo) {
        return servos[servo].getPosition();
    }

    public void movePulley(Gamepad gamepad) {
        if (gamepad.dpad_up) {
            pulley.setPower(pulleySpeed);
        } else if (gamepad.dpad_down) {
            pulley.setPower(-pulleySpeed);
        } else {
            pulley.setPower(0);
        }
    }
}