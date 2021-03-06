package org.firstinspires.ftc.libraries;

import android.graphics.Color;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.enums.Direction;
import org.firstinspires.ftc.enums.FTCAlliance;
import org.firstinspires.ftc.enums.FTCPosition;
import org.firstinspires.ftc.enums.JewelColor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by megankaye on 1/3/18.
 */

public class AutonModeLibrary {
    LinearOpMode opMode;

    DrivingLibrary drivingLibrary;
    GyroSensorLibrary gyroSensorLibrary;
    VuMarkIdentifyLibrary vuMarkIdentify;
    GlyphArmLibrary glyphArm;

    FTCAlliance alliance;
    FTCPosition position;

    Servo colorArm;
    ColorSensor jewelColorSensor;
    ColorSensor cryptoColorSensor;
    DistanceSensor cryptoDistanceSensor;

    boolean[][] cryptobox;

    public AutonModeLibrary(LinearOpMode opMode, FTCAlliance alliance, FTCPosition position, boolean glyphLib) {
        this.alliance = alliance;
        drivingLibrary = new DrivingLibrary(opMode);
        this.drivingLibrary.setSpeed(0.75);
        this.position = position;
        if (glyphLib) {
            glyphArm = new GlyphArmLibrary(opMode);

        }
        gyroSensorLibrary = new GyroSensorLibrary(opMode, BNO055IMU.AngleUnit.RADIANS,
                BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC, drivingLibrary);
        this.colorArm = opMode.hardwareMap.get(Servo.class,"color_arm");
        jewelColorSensor = opMode.hardwareMap.get(ColorSensor.class, "jewelColorSensor");
        cryptoColorSensor = opMode.hardwareMap.get(ColorSensor.class, "cryptoColorSensor");
        cryptoDistanceSensor = opMode.hardwareMap.get(DistanceSensor.class, "cryptoColorSensor");
        this.opMode = opMode;
        this.vuMarkIdentify = new VuMarkIdentifyLibrary(opMode);
        this.cryptobox = new boolean[3][4];
    }

    //internal methods
    private JewelColor senseColor() {
        //threshold variables
        int redBThreshold = 60;
        int redTThreshold = 320;
        int blueBThreshold = 120;
        int blueTThreshold = 260;
        int waitSenseColor = 500;

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};
        // convert the RGB values to HSV values.
        //SENSE COLOR
        Color.RGBToHSV(jewelColorSensor.red() * 8, jewelColorSensor.green() * 8, jewelColorSensor.blue() * 8, hsvValues);
        float hue = hsvValues[0];
        opMode.telemetry.addData("hue", hue);
        opMode.sleep(waitSenseColor);

        boolean seeingRedJewel = hue < redBThreshold || hue > redTThreshold;
        boolean seeingBlueJewel = hue > blueBThreshold && hue < blueTThreshold;

        if (seeingBlueJewel) {
            opMode.telemetry.addData("Saw:", "Blue Jewel");
            opMode.telemetry.update();
            return JewelColor.BLUE;
        }
        else if (seeingRedJewel) {
            opMode.telemetry.addData("Saw:", "Red Jewel");
            opMode.telemetry.update();
            return JewelColor.RED;
        }
        else {
            opMode.telemetry.addData("Saw:", "Unknown");
            opMode.telemetry.update();
            return JewelColor.UNKNOWN;
        }
    }

    public void turnLeft() {
        gyroSensorLibrary.turnLeft(Math.PI / 2);
    }

    private RelicRecoveryVuMark identifyPictograph() {
        //pick up
        return vuMarkIdentify.identifyPictograph(opMode);
    }

    //external methods
    public void pickUpGlyph() {
        glyphArm.allArmsPreset(true, 0);
        opMode.sleep(1500);
        glyphArm.movePulley(true);
    }

    public Direction knockOffJewelSleep() {
        //test variables
        int waitMoveArm = 1000;
        int waitDriveTime = 150;
        int colorArmResetPos = 1;
        double colorArmDownPos = 0.1;
        float driveSpeed = .4f;

        //other variables
        Direction dir;

        //MOVE SERVO
        colorArm.setPosition(colorArmDownPos);
        opMode.telemetry.addData("pos", colorArm.getPosition());
        opMode.telemetry.update();
        opMode.sleep(waitMoveArm);
        JewelColor color = senseColor();

        if (alliance == FTCAlliance.RED) {
            if (color == JewelColor.BLUE) {
                drivingLibrary.driveStraight(0, -driveSpeed);
                dir = Direction.BACKWARD;
            }
            else if (color == JewelColor.RED) {
                drivingLibrary.driveStraight(0, driveSpeed);
                dir = Direction.FORWARD;
            }
            else {
                colorArm.setPosition(colorArmResetPos);
                opMode.sleep(waitMoveArm);
                drivingLibrary.driveStraight(0, driveSpeed);
                dir = Direction.FORWARD;
            }
        } else {
            if (color == JewelColor.RED) {
                drivingLibrary.driveStraight(0, -driveSpeed);
                dir = Direction.BACKWARD;
            }
            else if (color == JewelColor.BLUE){
                drivingLibrary.driveStraight(0, driveSpeed);
                dir = Direction.FORWARD;
            }
            else {
                colorArm.setPosition(colorArmResetPos);
                opMode.sleep(waitMoveArm);
                drivingLibrary.driveStraight(0, driveSpeed);
                dir = Direction.FORWARD;
            }
        }
        opMode.sleep(waitDriveTime);
        colorArm.setPosition(colorArmResetPos);
        drivingLibrary.brakeStop();
        opMode.sleep(1000);
        opMode.telemetry.update();
        return dir;
    }

    public int glyptographSleep(Direction dir) {
        float driveSpeed = .4f;

        int waitSensePictograph = 500;
        int drive1, drive2, drive3, drive4;

        if (position == FTCPosition.LEFT) {
            if (dir == Direction.BACKWARD) {
                if (alliance == FTCAlliance.RED) {
                    //if red, on left side, and went backwards
                    //DONE
                    drive1 = 1150 / 2;
                    drive2 = 500 / 2;
                    drive3 = 1200 / 2;
                    drive4 = 500 / 2;
                    drivingLibrary.driveStraight(0,driveSpeed);
                    opMode.sleep(drive1);
                    drivingLibrary.driveStraight(-driveSpeed,0);
                    opMode.sleep(drive2);
                    gyroSensorLibrary.rightToAngle(-Math.PI / 2);
                    drivingLibrary.driveStraight(driveSpeed,0);
                    opMode.sleep(drive3);
                    drivingLibrary.driveStraight(0,-driveSpeed);
                    opMode.sleep(drive4);
                    drivingLibrary.brakeStop();
                    gyroSensorLibrary.turnLeft(Math.PI/6);

                } else {
                    //if blue, on left side, and went backwards
                    drive1 = 950 / 2;
                    drive2 = 500 / 2;
                    drivingLibrary.driveStraight(0,driveSpeed);
                    opMode.sleep(drive1);
                    drivingLibrary.driveStraight(-driveSpeed,0);
                    opMode.sleep(drive2);
                    gyroSensorLibrary.rightToAngle(-Math.PI / 2);
                    drivingLibrary.driveStraight(driveSpeed,0);
                    opMode.sleep(500);
                    drivingLibrary.brakeStop();
                }
            } else {
                if (alliance == FTCAlliance.RED) {
                    //if red, on left side, and went forwards
                    drive1 = 950/ 2;
                    drive2 = 500 / 2;
                    drive3 = 1300/2;
                    drive4 = 500/2;
                    drivingLibrary.driveStraight(0,driveSpeed);
                    opMode.sleep(drive1);
                    drivingLibrary.driveStraight(-driveSpeed,0);
                    opMode.sleep(drive2);
                    gyroSensorLibrary.rightToAngle(-Math.PI / 2);
                    drivingLibrary.driveStraight(driveSpeed,0);
                    opMode.sleep(drive3);
                    drivingLibrary.driveStraight(0,-driveSpeed);
                    opMode.sleep(drive4);
                    drivingLibrary.brakeStop();
                    gyroSensorLibrary.turnLeft(Math.PI/6);
                }
                else {
                    //if blue, on left side, and went forwards
                    drive1 = 950/2;
                    drive2 = 500/2;
                    drivingLibrary.driveStraight(0,driveSpeed);
                    opMode.sleep(drive1);
                    drivingLibrary.driveStraight(-driveSpeed,0);
                    opMode.sleep(drive2);
                    gyroSensorLibrary.rightToAngle(-Math.PI / 2);
                    drivingLibrary.driveStraight(driveSpeed,0);
                    opMode.sleep(500);
                    drivingLibrary.brakeStop();
                }
            }

        } else {
            if (dir == Direction.BACKWARD) {
                drive1 = 1150/2;
                drive2 = 500/2;
                drive3 = 1200/2;
                drive4 = 500/2;
                if (alliance == FTCAlliance.RED) {
                    //if red, on right side, and went backwards
                    drivingLibrary.driveStraight(0,driveSpeed);
                    opMode.sleep(drive1);
                    drivingLibrary.driveStraight(-driveSpeed,0);
                    opMode.sleep(drive2);
                    gyroSensorLibrary.rightToAngle(-Math.PI / 2);
                    drivingLibrary.driveStraight(driveSpeed,0);
                    opMode.sleep(drive3);
                    drivingLibrary.driveStraight(0,-driveSpeed);
                    opMode.sleep(drive4);
                    drivingLibrary.brakeStop();
                    gyroSensorLibrary.turnLeft(Math.PI/6);
                } else {
                    //if blue, on right side, and went backwards
                    drivingLibrary.driveStraight(0,-driveSpeed);
                    opMode.sleep(drive1);
                    drivingLibrary.driveStraight(driveSpeed,0);
                    opMode.sleep(drive2);
                    gyroSensorLibrary.rightToAngle(-Math.PI / 2);
                    drivingLibrary.driveStraight(-driveSpeed,0);
                    opMode.sleep(drive3);
                    drivingLibrary.driveStraight(0,-driveSpeed);
                    opMode.sleep(drive4);
                    drivingLibrary.brakeStop();
                }
            } else {
                drive1 = 950/2;
                drive2 = 500/2;
                drive3 = 1300/2;
                drive4 = 500/2;
                if (alliance == FTCAlliance.RED) {
                    //if red, on right side, and went forwards
                    drivingLibrary.driveStraight(0,driveSpeed);
                    opMode.sleep(drive1);
                    drivingLibrary.driveStraight(-driveSpeed,0);
                    opMode.sleep(drive2);
                    gyroSensorLibrary.rightToAngle(-Math.PI / 2);
                    drivingLibrary.driveStraight(driveSpeed,0);
                    opMode.sleep(drive3);
                    drivingLibrary.driveStraight(0,-driveSpeed);
                    opMode.sleep(drive4);
                    drivingLibrary.brakeStop();
                    gyroSensorLibrary.turnLeft(Math.PI/6);
                } else {
                    //if blue, on right side, and went forwards
                    drivingLibrary.driveStraight(0,-driveSpeed);
                    opMode.sleep(drive1);
                    drivingLibrary.driveStraight(driveSpeed,0);
                    opMode.sleep(drive2);
                    gyroSensorLibrary.rightToAngle(-Math.PI / 2);
                    drivingLibrary.driveStraight(-driveSpeed,0);
                    opMode.sleep(drive3);
                    drivingLibrary.driveStraight(0,-driveSpeed);
                    opMode.sleep(drive4);
                    drivingLibrary.brakeStop();
                }
            }
        }

        //use vuforia to identify
        opMode.sleep(waitSensePictograph);
        int tries = 0;
        int count = 0;
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.UNKNOWN;
        while (tries < 3) {
            vuMark = identifyPictograph();
            if (vuMark == RelicRecoveryVuMark.LEFT) {
                count = 3;
                tries += 3;
            } else if (vuMark == RelicRecoveryVuMark.CENTER) {
                count = 2;
                tries += 3;
            }
            else if (vuMark == RelicRecoveryVuMark.RIGHT) {
                count = 1;
                tries += 3;
            } else { count = 0; }
            tries += 1;
            opMode.sleep(100);
        }
        if (count == 0)  { count = 2; }
        opMode.telemetry.addData("count", count);
        return count;
    }

    public void placeGlyphsSleep(int count) {
        int strafeTime = 590/2;

        drivingLibrary.driveStraight(0,.5f);
        opMode.sleep(strafeTime);

        if (alliance == FTCAlliance.RED) {
            if (position == FTCPosition.LEFT) {
                drivingLibrary.driveStraight(-.5f,0);
                while (count != 0) {
                    double dist = cryptoDistanceSensor.getDistance(DistanceUnit.CM);
                    if (dist != java.lang.Double.NaN) {
                        count -= 1;
                        drivingLibrary.driveStraight(-.5f,0);
                        opMode.sleep(strafeTime);
                    }
                }
            }
            else {
                drivingLibrary.driveStraight(0,.5f);
                opMode.sleep(300/2);

                drivingLibrary.driveStraight(-.5f,0);
                opMode.sleep(600/2);

                gyroSensorLibrary.turnLeft(Math.PI / 2);
                drivingLibrary.driveStraight(0,-.5f);
                opMode.sleep(500/2);

                drivingLibrary.driveStraight(-.5f,0);
                while (count != 0) {
                    double dist = cryptoDistanceSensor.getDistance(DistanceUnit.CM);
                    if (dist != java.lang.Double.NaN) {
                        count -= 1;
                        drivingLibrary.driveStraight(-.5f,0);
                        opMode.sleep(strafeTime);
                    }
                }
            }
        } else {
            if (position == FTCPosition.LEFT) {
                float driveSpeed = .4f;
                drivingLibrary.driveStraight(0,-driveSpeed);
                opMode.sleep(700/2);
                gyroSensorLibrary.rightToAngle(-Math.PI);
                drivingLibrary.driveStraight(0,driveSpeed);
                opMode.sleep(1200/2);
                drivingLibrary.driveStraight(driveSpeed,0);
                opMode.sleep(700/2);
                drivingLibrary.brakeStop();
                while (count != 0) {
                    double dist = cryptoDistanceSensor.getDistance(DistanceUnit.CM);
                    if (dist != java.lang.Double.NaN) {
                        count -= 1;
                        drivingLibrary.driveStraight(-.5f,0);
                        opMode.sleep(strafeTime);
                    }
                }

            } else {
                /*drivingLibrary.driveStraight(.5f,0);
                while (count != 0) {
                    double dist = cryptoDistanceSensor.getDistance(DistanceUnit.CM);
                    if (dist != java.lang.Double.NaN) {
                        count -= 1;
                        drivingLibrary.driveStraight(.5f,0);
                        opMode.sleep(strafeTime);
                    }
                }*/
            }
        }
        drivingLibrary.brakeStop();
        drivingLibrary.driveStraight(0,.5f);
        opMode.sleep(500/2);
        drivingLibrary.brakeStop();
        glyphArm.dropGlyphs();
        drivingLibrary.driveStraight(0,-.5f);
        opMode.sleep(200/2);
        drivingLibrary.driveStraight(0,.5f);
        opMode.sleep(1500/2);
        drivingLibrary.driveStraight(0,-.5f);
        opMode.sleep(100/2);

        if (strafeTime > 0) {
            drivingLibrary.driveStraight(.5f, 0);
            opMode.sleep(strafeTime);
        } else if (strafeTime < 0) {
            strafeTime = -strafeTime;
            drivingLibrary.driveStraight(-.5f, 0);
            opMode.sleep(strafeTime);
        }
        //turns around 180 to face glyph pit
        gyroSensorLibrary.turnRight(Math.PI);
        drivingLibrary.brakeStop();
    }

    //EXCLUSIVELY for Jewel Only Run
    public void driveToSafeZone(Direction dir) {
        float driveSpeedStone = .6f;
        float driveSpeedStrafe = 1f;
        float driveSpeed = .4f;


        if (position == FTCPosition.LEFT) {
            if (dir == Direction.BACKWARD) {
                if (alliance == FTCAlliance.RED) {
                    //if red, on left side, and went backwards
                    drivingLibrary.driveStraight(0, driveSpeedStone);
                    opMode.sleep(2500);
                    drivingLibrary.driveStraight(driveSpeedStrafe, 0);
                    opMode.sleep(250);
                } else {
                    //if blue, on left side, and went backwards
                    drivingLibrary.driveStraight(0, -driveSpeed);
                    opMode.sleep(500);
                    drivingLibrary.driveStraight(-driveSpeedStrafe, 0);
                    opMode.sleep(1000);
                    drivingLibrary.driveStraight(0, -driveSpeed);
                    opMode.sleep(500);
                }
            } else {
                if (alliance == FTCAlliance.RED) {
                    //if red, on left side, and went forwards
                    drivingLibrary.driveStraight(0, driveSpeed);
                    opMode.sleep(750);
                    drivingLibrary.driveStraight(driveSpeedStrafe, 0);
                    opMode.sleep(250);
                }
                else {
                    //if blue, on left side, and went forwards
                    drivingLibrary.driveStraight(0, -driveSpeedStone);
                    opMode.sleep(1250);
                    drivingLibrary.driveStraight(-driveSpeedStrafe, 0);
                    opMode.sleep(1000);
                    drivingLibrary.driveStraight(0, -driveSpeed);
                    opMode.sleep(800);
                }
            }

        } else {
            if (dir == Direction.BACKWARD) {
                if (alliance == FTCAlliance.RED) {
                    //if red, on right side, and went backwards
                    drivingLibrary.driveStraight(0, driveSpeedStone);
                    opMode.sleep(2000);
                    drivingLibrary.driveStraight(-driveSpeedStrafe, 0);
                    opMode.sleep(1000);
                    drivingLibrary.driveStraight(0, driveSpeed);
                    opMode.sleep(500);
                } else {
                    //if blue, on right side, and went backwards
                    drivingLibrary.driveStraight(0, -driveSpeed);
                    opMode.sleep(750);
                    drivingLibrary.driveStraight(driveSpeedStrafe, 0);
                    opMode.sleep(250);
                }
            } else {
                if (alliance == FTCAlliance.RED) {
                    //if red, on right side, and went forwards
                    drivingLibrary.driveStraight(0, driveSpeed);
                    opMode.sleep(800);
                    drivingLibrary.driveStraight(-driveSpeedStrafe, 0);
                    opMode.sleep(1000);
                    drivingLibrary.driveStraight(0, driveSpeed);
                    opMode.sleep(300);
                } else {
                    //if blue, on right side, and went forwards
                    drivingLibrary.driveStraight(0, -driveSpeedStone);
                    opMode.sleep(2500);
                    drivingLibrary.driveStraight(driveSpeedStrafe, 0);
                    opMode.sleep(250);
                }
            }
        }

        opMode.telemetry.update();
        drivingLibrary.stopDrivingMotors();
    }

}
