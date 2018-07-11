package com.amaliapps.adamreading.helper;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.amaliapps.adamreading.R;
import com.amaliapps.adamreading.model.Letter;

import java.util.Locale;
import java.util.Objects;

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
    private static void darkenStatusBar(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(darkenColor(ContextCompat.getColor(activity, color)));
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


    public static void changeActivityTheme(AppCompatActivity activity, Letter letter) {
        //        // Set activity's colors
        Objects.requireNonNull(activity.getSupportActionBar()).setBackgroundDrawable(
                new ColorDrawable(activity.getResources().getColor(letter.getColorResourceId())));
        darkenStatusBar(activity, letter.getColorResourceId());
    }
}
