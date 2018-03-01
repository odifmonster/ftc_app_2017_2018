package org.firstinspires.ftc.whatever;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by megankaye on 12/22/17.
 */

public class DrivingLib {
    //other things
    private OpMode opmode;
    private boolean color;
    private boolean pos;
    private double speedSetting = 0.75;

    //defining our four motors
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private HardwareMap hardwareMap;

    public DrivingLib(OpMode opmode, boolean color, boolean pos) {
        //tele
        this.opmode = opmode;
        this.color = color;
        this.pos = pos;
        this.hardwareMap = opmode.hardwareMap;
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

    }

    public void drivGay(int clicks, double y, double x) {
        resetEncoders();
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        if (clicks != 0) {
            leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftBack.setTargetPosition(clicks);
            leftFront.setTargetPosition(clicks);
            rightBack.setTargetPosition(clicks);
            rightFront.setTargetPosition(clicks);
        } else {
            leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        leftBack.setPower(y + x);
        leftFront.setPower(y + x);
        rightBack.setPower(y - x);
        rightFront.setPower(y - x);

        if (clicks != 0) {
            while(leftBack.getCurrentPosition() < clicks) {
                //yo
            }
            leftBack.setPower(0);
            leftFront.setPower(0);
            rightBack.setPower(0);
            rightFront.setPower(0);
        }
        resetEncoders();

    }


    public void drivStrafeAuton(int clicks, double power, int x, int y) {
        float pos_x = Math.abs(x);  //Positive of the X value from the stick
        float pos_y = Math.abs(y);  //Positive of the Y value from the stick

        resetEncoders();
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        if (clicks != 0) {
            leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftBack.setTargetPosition(clicks);
            leftFront.setTargetPosition(clicks);
            rightBack.setTargetPosition(clicks);
            rightFront.setTargetPosition(clicks);
        } else {
            leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        if ((x >= 0) && (y <= 0)) {                            //bottom left
            if (pos_y < pos_x) {
                leftBack.setPower(-(x + y) * (speedSetting));   //backwards drive
                leftFront.setPower((x) * (speedSetting));       //backwards drive
                rightBack.setPower((x) * (speedSetting));       //backwards drive
                rightFront.setPower(-(x + y) * (speedSetting));   //backwards drive

            } else {                                           //after crossing over 45 degrees
                leftBack.setPower(-(x + y) * (speedSetting));   //forward drive
                leftFront.setPower(-(y) * (speedSetting));        //backward drive
                rightBack.setPower(-(y) * (speedSetting));        //backward drive
                rightFront.setPower(-(x + y) * (speedSetting));   //forward drive
            }
        } else if ((x <= 0) && (y <= 0)) {                     //top left
            if (pos_y > pos_x) {
                leftBack.setPower(-(y) * (speedSetting));       //forward drive
                leftFront.setPower((x - y) * (speedSetting));    //backward drive
                rightBack.setPower((x - y) * (speedSetting));    //backward drive
                rightFront.setPower(-(y) * (speedSetting));       //forward drive
            } else {                                           //after crossing over 45 degrees
                leftBack.setPower(-(x) * (speedSetting));       //forward drive
                leftFront.setPower((x - y) * (speedSetting));    //forward drive
                rightBack.setPower((x - y) * (speedSetting));    //forward drive
                rightFront.setPower(-(x) * (speedSetting));       //forward drive
            }
        } else if ((x <= 0) && (y >= 0)) {                     //top right
            if (pos_y < pos_x) {
                leftBack.setPower(-(x + y) * (speedSetting));   //forward drive
                leftFront.setPower((x) * (speedSetting));        //forward drive
                rightBack.setPower((x) * (speedSetting));        //forward drive
                rightFront.setPower(-(x + y) * (speedSetting));   //forward drive
            } else {                                           //after crossing over 45 degrees
                leftBack.setPower(-(x + y) * (speedSetting));   //backward drive
                leftFront.setPower(-(y) * (speedSetting));       //forward drive
                rightBack.setPower(-(y) * (speedSetting));       //forward drive
                rightFront.setPower(-(x + y) * (speedSetting));   //backward drive
            }
        } else if ((x >= 0) && (y >= 0)) {                     //bottom right
            if (pos_y > pos_x) {
                leftBack.setPower(-(y) * (speedSetting));       //backward drive
                leftFront.setPower((x - y) * (speedSetting));    //forward drive
                rightBack.setPower((x - y) * (speedSetting));    //forward drive
                rightFront.setPower(-(y) * (speedSetting));       //backward drive
            } else {                                           //after crossing over 45 degrees
                leftBack.setPower(-(x) * (speedSetting));       //backward drive
                leftFront.setPower((x - y) * (speedSetting));    //backward drive
                rightBack.setPower((x - y) * (speedSetting));    //backward drive
                rightFront.setPower(-(x) * (speedSetting));       //backward drive
            }
        } else {                                               //No stick pushing, stop motors
            allStop();
        }

        if (clicks != 0) {
            while(leftBack.getCurrentPosition() < clicks) {
                //yo
            }
            leftBack.setPower(0);
            leftFront.setPower(0);
            rightBack.setPower(0);
            rightFront.setPower(0);
        }
        resetEncoders();
    }

    public void drivStrafe(float x, float y) {
        float pos_x = Math.abs(x);  //Positive of the X value from the stick
        float pos_y = Math.abs(y);  //Positive of the Y value from the stick

        if ((x >= 0) && (y <= 0)) {                            //bottom left
            if (pos_y < pos_x) {
                leftBack.setPower(-(x + y) * (speedSetting));   //backwards drive
                leftFront.setPower((x) * (speedSetting));       //backwards drive
                rightBack.setPower((x) * (speedSetting));       //backwards drive
                rightFront.setPower(-(x + y) * (speedSetting));   //backwards drive

            } else {                                           //after crossing over 45 degrees
                leftBack.setPower(-(x + y) * (speedSetting));   //forward drive
                leftFront.setPower(-(y) * (speedSetting));        //backward drive
                rightBack.setPower(-(y) * (speedSetting));        //backward drive
                rightFront.setPower(-(x + y) * (speedSetting));   //forward drive
            }
        } else if ((x <= 0) && (y <= 0)) {                     //top left
            if (pos_y > pos_x) {
                leftBack.setPower(-(y) * (speedSetting));       //forward drive
                leftFront.setPower((x - y) * (speedSetting));    //backward drive
                rightBack.setPower((x - y) * (speedSetting));    //backward drive
                rightFront.setPower(-(y) * (speedSetting));       //forward drive
            } else {                                           //after crossing over 45 degrees
                leftBack.setPower(-(x) * (speedSetting));       //forward drive
                leftFront.setPower((x - y) * (speedSetting));    //forward drive
                rightBack.setPower((x - y) * (speedSetting));    //forward drive
                rightFront.setPower(-(x) * (speedSetting));       //forward drive
            }
        } else if ((x <= 0) && (y >= 0)) {                     //top right
            if (pos_y < pos_x) {
                leftBack.setPower(-(x + y) * (speedSetting));   //forward drive
                leftFront.setPower((x) * (speedSetting));        //forward drive
                rightBack.setPower((x) * (speedSetting));        //forward drive
                rightFront.setPower(-(x + y) * (speedSetting));   //forward drive
            } else {                                           //after crossing over 45 degrees
                leftBack.setPower(-(x + y) * (speedSetting));   //backward drive
                leftFront.setPower(-(y) * (speedSetting));       //forward drive
                rightBack.setPower(-(y) * (speedSetting));       //forward drive
                rightFront.setPower(-(x + y) * (speedSetting));   //backward drive
            }
        } else if ((x >= 0) && (y >= 0)) {                     //bottom right
            if (pos_y > pos_x) {
                leftBack.setPower(-(y) * (speedSetting));       //backward drive
                leftFront.setPower((x - y) * (speedSetting));    //forward drive
                rightBack.setPower((x - y) * (speedSetting));    //forward drive
                rightFront.setPower(-(y) * (speedSetting));       //backward drive
            } else {                                           //after crossing over 45 degrees
                leftBack.setPower(-(x) * (speedSetting));       //backward drive
                leftFront.setPower((x - y) * (speedSetting));    //backward drive
                rightBack.setPower((x - y) * (speedSetting));    //backward drive
                rightFront.setPower(-(x) * (speedSetting));       //backward drive
            }
        } else {                                               //No stick pushing, stop motors
            allStop();
        }
    }

    public void allStop() {
        leftBack.setPower(0);
        leftFront.setPower(0);
        rightBack.setPower(0);
        rightFront.setPower(0);
    }

    private void resetEncoders() {
        // Reset the motor encoders on the drive wheels.
        if (leftBack != null) {
            leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        if (leftFront != null) {
            leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        if (rightBack != null) {
            rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        if (rightFront != null) {
            rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }


}
