package com.amaliapps.adamreading;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;

public class Utils {

    /**
     * Change activity's colors, from http://www.gadgetsaint.com/tips/change-statusbar-color-android/
     *
     * @param activity the activity
     * @param color    the desired primary color
     */
    public static void darkenStatusBar(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(darkenColor(
                    ContextCompat.getColor(activity, color)));
        }
    }

    /**
     * darken the color supplied
     *
     * @param color the desired primary color
     * @return the resulting primary dark color
     */
    private static int darkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;
        return Color.HSVToColor(hsv);
    }
}
