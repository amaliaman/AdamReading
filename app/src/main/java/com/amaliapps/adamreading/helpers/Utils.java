package com.amaliapps.adamreading.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import com.amaliapps.adamreading.R;

import java.util.Locale;

/**
 * General helper methods
 */
public class Utils {

    /**
     * Force Hebrew app locale
     */
    public static void setHebrewLocale(Context context) {
        // Create a new Locale object
        Locale locale = new Locale(context.getString(R.string.hebrew_locale_code));
        Locale.setDefault(locale);
        // Create a new configuration object
        Configuration config = new Configuration();
        // Set the locale of the new configuration
        config.locale = locale;
        // Update the configuration of the Application context
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

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