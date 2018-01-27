package org.firstinspires.ftc.libraries;

import android.graphics.Color;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.GlyphDetector;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.vuforia.VuMarkTarget;

import org.firstinspires.ftc.enums.Direction;
import org.firstinspires.ftc.enums.FTCAlliance;
import org.firstinspires.ftc.enums.FTCPosition;
import org.firstinspires.ftc.libraries.DrivingLibrary;
import org.firstinspires.ftc.libraries.VuMarkIdentifyLibrary;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by megankaye on 1/3/18.
 */

public class AutonModeLibrary {
    LinearOpMode opMode;
    Gamepad autonGamePad;

    DrivingLibrary drivingLibrary;
    VuMarkIdentifyLibrary vuMarkIdentify;
    GlyphArmLibrary glyphArm;

    FTCAlliance alliance;
    FTCPosition position;

    Servo colorArm;
    ColorSensor colorSensor;
    DistanceSensor distanceSensor;

    private GlyphDetector glyphDetector;

    boolean[][] cryptobox;

    public AutonModeLibrary(LinearOpMode opMode, FTCAlliance alliance, FTCPosition position) {
        autonGamePad = new Gamepad();
        this.alliance = alliance;
        this.position = position;
        drivingLibrary = new DrivingLibrary(opMode);
        this.drivingLibrary.setSpeed(0.75);
        glyphArm = new GlyphArmLibrary(opMode);
        this.colorArm = opMode.hardwareMap.get(Servo.class,"color_arm");
        colorSensor = opMode.hardwareMap.get(ColorSensor.class, "color_sensor");
        distanceSensor = opMode.hardwareMap.get(DistanceSensor.class, "color_sensor");
        this.opMode = opMode;
        this.vuMarkIdentify = new VuMarkIdentifyLibrary(opMode);
        this.cryptobox = new boolean[3][4];

        glyphDetector = new GlyphDetector();
        glyphDetector.init(opMode.hardwareMap.appContext, CameraViewDisplay.getInstance());
        glyphDetector.enable();
    }

    //UPDATE this code
    public Direction knockOffJewel() {
        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};
        final float values[] = hsvValues;
        final double SCALE_FACTOR = 255;

        //MOVE SERVO
        colorArm.setPosition(0.1);
        opMode.sleep(2000);

        // convert the RGB values to HSV values.
        //SENSE COLOR
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);
        float hue = hsvValues[0];
        opMode.telemetry.addData("hue", hue);

        boolean seeingRedJewel = hue < 20 || hue > 320;
        boolean seeingBlueJewel = hue > 120 && hue < 260;

        //DELETE
        if (seeingBlueJewel && !seeingRedJewel) {
            opMode.telemetry.addData("Saw:", "Blue Jewel");
        }
        else if (!seeingBlueJewel && seeingRedJewel) {
            opMode.telemetry.addData("Saw:", "Red Jewel");
        }
        else {
            opMode.telemetry.addData("Saw:", "Unknown");
        }
        //DELETE
        opMode.telemetry.update();
        /*
        opMode.sleep(3000);
        Direction dir;
        if (alliance == FTCAlliance.RED) {
            if (seeingBlueJewel) {
                drivingLibrary.driveStraight(0, -.4f);
                dir = Direction.BACKWARD;
            }
            else if (seeingRedJewel) {
                drivingLibrary.driveStraight(0, .4f);
                dir = Direction.FORWARD;
            }
            else {
                colorArm.setPosition(1);
                opMode.sleep(250);
                drivingLibrary.driveStraight(0, .4f);
                dir = Direction.FORWARD;
            }
        } else {
            if (seeingRedJewel) {
                drivingLibrary.driveStraight(0, .4f);
                dir = Direction.FORWARD;
            }
            else if (seeingBlueJewel){
                drivingLibrary.driveStraight(0, -.4f);
                dir = Direction.BACKWARD;
            }
            else {
                colorArm.setPosition(1);
                opMode.sleep(250);
                drivingLibrary.driveStraight(0, .4f);
                dir = Direction.FORWARD;
            }
        }
        opMode.sleep(500);
        drivingLibrary.stopDrivingMotors();
        colorArm.setPosition(1);
        opMode.telemetry.update();
        return dir*/
        return Direction.FORWARD;
    }

    //UPDATE this code
    public void driveToSafeZone(Direction dir) {
        opMode.sleep(500);
        if (position == FTCPosition.LEFT) {
            if (dir == Direction.BACKWARD) {
                if (alliance == FTCAlliance.RED) {
                    //if red, on left side, and went backwards
                    drivingLibrary.driveStraight(0, .6f);
                    opMode.sleep(2500);
                    drivingLibrary.driveStraight(1f, 0);
                    opMode.sleep(250);
                } else {
                    //if blue, on left side, and went backwards
                    drivingLibrary.driveStraight(0, -.4f);
                    opMode.sleep(500);
                    drivingLibrary.driveStraight(-1f, 0);
                    opMode.sleep(1000);
                    drivingLibrary.driveStraight(0, -.4f);
                    opMode.sleep(500);
                }
            } else {
                if (alliance == FTCAlliance.RED) {
                    //if red, on left side, and went forwards
                    drivingLibrary.driveStraight(0, .4f);
                    opMode.sleep(750);
                    drivingLibrary.driveStraight(1f, 0);
                    opMode.sleep(250);
                }
                else {
                    //if blue, on left side, and went forwards
                    drivingLibrary.driveStraight(0, -.6f);
                    opMode.sleep(1250);
                    drivingLibrary.driveStraight(-1f, 0);
                    opMode.sleep(1000);
                    drivingLibrary.driveStraight(0, -.4f);
                    opMode.sleep(800);
                }
            }

        } else {
            if (dir == Direction.BACKWARD) {
                if (alliance == FTCAlliance.RED) {
                    //if red, on right side, and went backwards
                    drivingLibrary.driveStraight(0, .6f);
                    opMode.sleep(2000);
                    drivingLibrary.driveStraight(-1f, 0);
                    opMode.sleep(1000);
                    drivingLibrary.driveStraight(0, .4f);
                    opMode.sleep(500);
                } else {
                    //if blue, on right side, and went backwards
                    drivingLibrary.driveStraight(0, -.4f);
                    opMode.sleep(750);
                    drivingLibrary.driveStraight(1f, 0);
                    opMode.sleep(250);
                }
            } else {
                if (alliance == FTCAlliance.RED) {
                    //if red, on right side, and went forwards
                    drivingLibrary.driveStraight(0, .4f);
                    opMode.sleep(800);
                    drivingLibrary.driveStraight(-1f, 0);
                    opMode.sleep(1000);
                    drivingLibrary.driveStraight(0, .4f);
                    opMode.sleep(300);
                } else {
                    //if blue, on right side, and went forwards
                    drivingLibrary.driveStraight(0, -.6f);
                    opMode.sleep(2500);
                    drivingLibrary.driveStraight(1f, 0);
                    opMode.sleep(250);
                }
            }
        }
        opMode.telemetry.update();
        drivingLibrary.stopDrivingMotors();
    }

    //change vuforial lib to localizer
    public RelicRecoveryVuMark identifyPictograph() {
        //pick up
        return vuMarkIdentify.identifyPictograph(opMode);
    }

    public void pickUpGlyph() {
        //pick up
        glyphArm.closeArmsPreset(true);
        opMode.sleep(500);
        glyphArm.movePulley(true);
    }

    public void dropGlyph() {
        //put down
        glyphArm.movePulley(false);
        opMode.sleep(500);
        glyphArm.openArmsPreset(.6f);

    }

    //fix
    public void senseCryotobox(int count) {
        //strafe slowly
        drivingLibrary.driveStraight (-.4f,0);
        double dist = distanceSensor.getDistance(DistanceUnit.CM);
        opMode.telemetry.addData("Sensing", dist != java.lang.Double.NaN);
        opMode.telemetry.addData("Dist", dist);
        opMode.telemetry.update();
    }

    //this needs so much fixing
    public void glyptograph(Direction direction) {
        //***CHECKS DIRECTION***

        //based on dist!
        //IF backwards DIR- then go x.
        //drive forward and turn to face camera (90 deg TURN preset maybe??)
        drivingLibrary.driveStraight(0,.5f);
        opMode.sleep(800);
        drivingLibrary.driveStraight(-.5f,0);
        opMode.sleep(500);
        drivingLibrary.turn(.5f,0);
        opMode.sleep(700);
        drivingLibrary.driveStraight (.5f,0);
        opMode.sleep(600);
        drivingLibrary.turn(.5f,0);
        opMode.sleep(200);
        drivingLibrary.brakeStop();

        //use vuforia to identify
        RelicRecoveryVuMark vuMark = identifyPictograph();
        opMode.sleep(500);

        /*
        int count;
        if (vuMark == RelicRecoveryVuMark.RIGHT) {
            count = 1;
        } else if (vuMark == RelicRecoveryVuMark.CENTER) {
            count = 2;
        }
        else if (vuMark == RelicRecoveryVuMark.LEFT) {
            count = 3;
        } else {
            count = 0                                                                              ;
        }
        opMode.telemetry.addData("count", count);
        double distThreshold = distanceSensor.getDistance(DistanceUnit.CM);

        drivingLibrary.driveStraight(.2f,0);
        opMode.sleep(1000);
        while (count != 0) {
            double dist = distanceSensor.getDistance(DistanceUnit.CM);
            //or just use 10
            //margin of error of 3 cm
            if (dist < (distThreshold + 3))) {
            }
            opMode.telemetry.addData("Distance", dist);
            opMode.telemetry.update();
        }
        drivingLibrary.brakeStop();
        opMode.sleep(300);

        //put glyph in
        drivingLibrary.driveStraight(0,.5f);
        dropGlyph();
        opMode.sleep(300);
        drivingLibrary.brakeStop();

        //turns around 180 to face glyph pit
        drivingLibrary.turn(.5f,.5f);
        opMode.sleep(1000);
        drivingLibrary.brakeStop();

        EDIT cryptobox array
        */
    }

    //LAMAN this is YOU
    public void getGlyphs() {
        /*
        cryptobox looks like
        |   |   |   | (row 3) --> Accessed: [x][3]
        |   |   |   | (row 2)
        |   |   |   | (row 1)
        | 0 |1  |2  | (row 0) --> Accessed: [x][0]
         */
        //use dogecv
        //identify nearest glyph (and color?)
        //picks up glyph
        //strafe until offset is zero OR within a given padding
        double offset = glyphDetector.getChosenGlyphOffset();
        while (offset != 0) {
            offset = glyphDetector.getChosenGlyphOffset();
            opMode.telemetry.addData("offset", offset);
            opMode.telemetry.update();
        }
    }
}
