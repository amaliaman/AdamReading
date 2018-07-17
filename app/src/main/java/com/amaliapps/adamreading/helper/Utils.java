package com.amaliapps.adamreading.helper;

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
     * Set activity's subtitle according to the current {@link Letter}
     *
     * @param activity the parent activity
     * @param letter   the current letter displayed in the activity
     */
    public static void setActivitySubtitle(AppCompatActivity activity, Letter letter) {
        String subtitle = letter.getCharacter() + " - " + letter.getName();
        Objects.requireNonNull(activity.getSupportActionBar()).setSubtitle(subtitle);
    }

    /**
     * Change activity's colors according to the current {@link Letter}
     *
     * @param activity the parent activity
     * @param letter   the current letter displayed in the activity
     */
    public static void changeActivityTheme(AppCompatActivity activity, Letter letter) {
        Objects.requireNonNull(activity.getSupportActionBar()).setBackgroundDrawable(
                new ColorDrawable(activity.getResources().getColor(letter.getColorResourceId())));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(darkenColor(
                    ContextCompat.getColor(activity, letter.getColorResourceId())));
        }
    }

    /**
     * Darken the color supplied
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

    /**
     * Change the hue of the color supplied by reducing it by the supplied value
     *
     * @param color the color to change
     * @param f     the amount of hue to reduce (in HSL)
     * @return the new color
     */
    public static int changeColor(int color, float f) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        float hue = hsv[0];
        hue -= f;
        if (hue < 0) {
            hue += 360;
        }
        hsv[0] = hue;
        return Color.HSVToColor(hsv);
    }

}
