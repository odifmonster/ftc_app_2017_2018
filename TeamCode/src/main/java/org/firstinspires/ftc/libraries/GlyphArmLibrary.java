package org.firstinspires.ftc.libraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.enums.Direction;

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
    boolean pulleyStopped;
    Direction lastDir;

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
        increment = 0.05;

        pulleySpeed = 1;
        pulleyStopped = true;
        lastDir = null;
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

    public void topArmsIncrement(boolean lb, float lt) {
        if (lb) {
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

    public void bottomArmsIncrement(boolean rb, float rt) {
        if (rb) {
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

    public void alignArms(Gamepad gamepad) {
        if (gamepad.right_stick_x < 0 && gamepad.right_stick_y < 0.1 &&
                gamepad.right_stick_y > -0.1) {
            leftTop.setPosition(leftTop.getPosition() - increment);
            rightTop.setPosition(rightTop.getPosition() - increment);
        }
        if (gamepad.right_stick_x > 0 && gamepad.right_stick_y < 0.1 &&
                gamepad.right_stick_y > -0.1) {
            leftTop.setPosition(leftTop.getPosition() + increment);
            rightTop.setPosition(rightTop.getPosition() + increment);
        }
    }

    public void presetPos(Gamepad gamepad) {

    }

    public void setPulleyBottom() {
        pulley.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pulley.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void movePulley(Gamepad gamepad) {
        if (gamepad.dpad_up && pulley.getCurrentPosition() < 3500) {
            pulley.setPower(pulleySpeed);
            pulleyStopped = false;
            lastDir = Direction.FORWARD;
        } else if (gamepad.dpad_down && pulley.getCurrentPosition() > 100) {
            pulley.setPower(-pulleySpeed);
            pulleyStopped = false;
            lastDir = Direction.BACKWARD;
        } else if (gamepad.right_stick_y > 0 && gamepad.right_stick_x < 0.1 &&
                gamepad.right_stick_x > -0.1) {
            pulley.setPower(-pulleySpeed);
            pulleyStopped = false;
            lastDir = Direction.BACKWARD;
        } else if (gamepad.right_stick_y < 0 && gamepad.right_stick_x < 0.1 &&
                gamepad.right_stick_x > -0.1) {
            pulley.setPower(pulleySpeed);
            pulleyStopped = false;
            lastDir = Direction.FORWARD;
        } else if (!pulleyStopped) {
            if (lastDir == Direction.BACKWARD) {
                pulley.setPower(pulleySpeed);
                opMode.sleep(10);
            }
            pulley.setPower(0);
            pulleyStopped = true;
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

    public void dropGlyphs() {
        leftTop.setPosition(openTopPosition[0]);
        rightTop.setPosition(openTopPosition[1]);
        leftBottom.setPosition(openBottomPosition[0]);
        rightBottom.setPosition(openBottomPosition[1]);
    }
}