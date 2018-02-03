package org.firstinspires.ftc.libraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by lamanwyner on 1/11/18.
 */

public class GlyphArmLibrary {
    private LinearOpMode opMode;
    private HardwareMap hardwareMap;
    private DcMotor pulley;
    private Servo leftTop, leftBottom, rightTop, rightBottom;

    private double[] closedBottomPosition, closedTopPosition, openBottomPosition,
        openTopPosition;
    private Servo[] servos;
    private double increment;

    double pulleySpeed;

    public GlyphArmLibrary(LinearOpMode opMode) {
        this.opMode = opMode;
        hardwareMap = opMode.hardwareMap;
        pulley = hardwareMap.get(DcMotor.class, "pulley");
        leftTop = hardwareMap.get(Servo.class, "left_top");
        leftBottom = hardwareMap.get(Servo.class, "left_bottom");
        rightTop = hardwareMap.get(Servo.class, "right_top");
        rightBottom = hardwareMap.get(Servo.class, "right_bottom");

        closedBottomPosition = new double[] {1, 0};
        closedTopPosition = new double[] {1, 0};
        openBottomPosition = new double[] {0.1, 0.9};
        openTopPosition = new double[] {0, 1};
        servos = new Servo[] {leftTop, leftBottom, rightTop, rightBottom};
        increment = 0.1;

        pulleySpeed = 0.5;
    }

    public void allArmsPreset(boolean lb, float lt) {
        if (lb) {
            leftTop.setPosition(closedTopPosition[0]);
            leftBottom.setPosition(closedBottomPosition[0]);
            rightTop.setPosition(closedTopPosition[1]);
            rightBottom.setPosition(closedBottomPosition[1]);
        }
        if (lt > 0.5) {
            leftTop.setPosition(openTopPosition[0]);
            leftBottom.setPosition(openBottomPosition[0]);
            rightTop.setPosition(openTopPosition[1]);
            rightBottom.setPosition(openBottomPosition[1]);
        }
    }

    public void topArmsPreset(boolean lb, float lt) {
        if (lb) {
            leftTop.setPosition(closedTopPosition[0]);
            rightTop.setPosition(closedTopPosition[1]);
        }
        if (lt > 0.5) {
            leftTop.setPosition(openBottomPosition[0]);
            rightTop.setPosition(openBottomPosition[1]);
        }
    }

    public void bottomArmsPreset(boolean rb, float rt) {
        if (rb) {
            leftBottom.setPosition(closedBottomPosition[0]);
            rightBottom.setPosition(closedBottomPosition[1]);
        }
        if (rt > 0.5) {
            leftBottom.setPosition(openBottomPosition[0]);
            rightBottom.setPosition(openBottomPosition[1]);
        }
    }

    public void allArmsIncrement(boolean rb, float rt) {
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

    public void topArmsIncrement(boolean lb, float lt, boolean rb) {
        if (lb && !rb) {
            if (leftTop.getPosition() + increment <= closedTopPosition[0]) {
                leftTop.setPosition(leftTop.getPosition() + increment);
            }
            if (rightTop.getPosition() - increment >= closedTopPosition[1]) {
                rightTop.setPosition(rightTop.getPosition() - increment);
            }
        }
        if (lt > 0.5) {
            if (leftTop.getPosition() - increment >= openTopPosition[0]) {
                leftTop.setPosition(leftTop.getPosition() - increment);
            }
            if (rightTop.getPosition() + increment <= openTopPosition[1]) {
                rightTop.setPosition(rightTop.getPosition() + increment);
            }
        }
    }

    public void bottomArmsIncrement(boolean rb, float rt, boolean lb) {
        if (rb && !lb) {
            if (leftBottom.getPosition() + increment <= closedBottomPosition[0]) {
                leftBottom.setPosition(leftBottom.getPosition() + increment);
            }
            if (rightBottom.getPosition() - increment >= closedBottomPosition[1]) {
                rightBottom.setPosition(rightBottom.getPosition() - increment);
            }
        }
        if (rt > 0.5) {
            if (leftBottom.getPosition() - increment >= openBottomPosition[0]) {
                leftBottom.setPosition(leftBottom.getPosition() - increment);
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

    public void setPulleyBottom() {
        pulley.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pulley.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void movePulley(Gamepad gamepad) {
        if (gamepad.dpad_up) {
            pulley.setPower(pulleySpeed);
        } else if (gamepad.dpad_down && pulley.getCurrentPosition() > 0) {
            pulley.setPower(-pulleySpeed);
        } else {
            pulley.setPower(0);
        }
        opMode.telemetry.addData("Pulley Pos", pulley.getCurrentPosition());
    }

    public void movePulley(boolean direction) {
        if (direction) {
            pulley.setPower(pulleySpeed);
            opMode.sleep(500);
            pulley.setPower(0);
        } else {
            pulley.setPower(-pulleySpeed);
            opMode.sleep(500);
            pulley.setPower(0);
        }
    }

    public void resetArmPosition() {
        if (pulley.getCurrentPosition() > 0) {
            pulley.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            pulley.setTargetPosition(0);
            pulley.setPower(-pulleySpeed);

            while (pulley.getCurrentPosition() > 0) {
                // continue
            }

            pulley.setPower(0);
        }

        leftBottom.setPosition(openBottomPosition[0]);
        rightBottom.setPosition(openBottomPosition[1]);
        leftTop.setPosition(openTopPosition[0]);
        rightTop.setPosition(openTopPosition[1]);
    }

    public void liftTwoGlyphs() {
        leftBottom.setPosition(closedBottomPosition[0]);
        rightBottom.setPosition(closedBottomPosition[1]);
        leftTop.setPosition(closedTopPosition[0]);
        rightTop.setPosition(closedTopPosition[1]);

        pulley.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pulley.setTargetPosition(2000);
        pulley.setPower(pulleySpeed);

        while (pulley.getCurrentPosition() < 10) {
            // continue
        }

        pulley.setPower(0);
        pulley.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void dropGlyphs() {
        leftTop.setPosition(openTopPosition[0]);
        rightTop.setPosition(openTopPosition[1]);
        leftBottom.setPosition(openBottomPosition[0]);
        rightBottom.setPosition(openBottomPosition[1]);
    }
}