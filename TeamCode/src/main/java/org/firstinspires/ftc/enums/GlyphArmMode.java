package org.firstinspires.ftc.enums;

/**
 * Created by lamanwyner on 1/27/18.
 */

public enum GlyphArmMode {
    ALL_MOVE("All move"),
    TOP_BOTTOM_PRESET("Top and bottom presets"),
    TOP_BOTTOM_INCREMENT("Top and bottom increments");

    String modeString;

    GlyphArmMode(String s) {
        modeString = s;
    }

    public String getModeString() {
        return modeString;
    }
}
