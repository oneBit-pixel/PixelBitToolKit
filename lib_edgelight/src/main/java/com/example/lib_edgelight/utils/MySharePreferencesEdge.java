package com.example.lib_edgelight.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharePreferencesEdge {
    public static String BACKGROUND = "background";
    public static String BACKGROUNDCOLOR = "backgroundcolor";
    public static String BACKGROUNDLINK = "backgroundlink";
    public static String CHECKNOTCH = "checknotch";
    public static String CHECKRUN = "checkrun";
    public static String CHECKSHAPE = "checkshape";
    public static String COLOR1 = "color1";
    public static String COLOR2 = "color2";
    public static String COLOR3 = "color3";
    public static String COLOR4 = "color5";
    public static String COLOR5 = "color6";
    public static String COLOR6 = "color4";
    public static String ControlWindowManager = "ChangeWindowManager";
    public static String FINISH_BACKGROUND = "finish_background";
    public static String FINISH_BACKGROUNDCOLOR = "finish_backgroundcolor";
    public static String FINISH_BACKGROUNDLINK = "finish_backgroundlink";
    public static String FINISH_CHECKNOTCH = "finish_checknotch";
    public static String FINISH_CHECKSHAPE = "finish_checkshape";
    public static String FINISH_COLOR1 = "finish_color1";
    public static String FINISH_COLOR2 = "finish_color2";
    public static String FINISH_COLOR3 = "finish_color3";
    public static String FINISH_COLOR4 = "finish_color5";
    public static String FINISH_COLOR5 = "finish_color6";
    public static String FINISH_COLOR6 = "finish_color4";
    public static String FINISH_HOLECORNER = "finish_holecorner";
    public static String FINISH_HOLERADIUS = "finish_holeradius";
    public static String FINISH_HOLERADIUSY = "finish_holeradiusy";
    public static String FINISH_HOLESHARP = "finish_holesharp";
    public static String FINISH_HOLEX = "finish_holex";
    public static String FINISH_HOLEY = "finish_holey";
    public static String FINISH_INFILITYHEIGHT = "finish_infilityheight";
    public static String FINISH_INFILITYRADIUS = "finish_infilityradius";
    public static String FINISH_INFILITYRADIUSB = "finish_infilityradiusb";
    public static String FINISH_INFILITYSHARP = "finish_infilitysharp";
    public static String FINISH_INFILITYWIDTH = "finish_infilitywidth";
    public static String FINISH_NOTCHBOTTOM = "finish_notchbottom";
    public static String FINISH_NOTCHHEIGHT = "finish_notchheight";
    public static String FINISH_NOTCHRADIUSBOTTOM = "finish_notchradiusbottom";
    public static String FINISH_NOTCHRADIUSTOP = "finish_notchradiustop";
    public static String FINISH_NOTCHTOP = "finish_notchtop";
    public static String FINISH_RADIUSBOTTOM = "finish_bottom";
    public static String FINISH_RADIUSTOP = "finish_top";
    public static String FINISH_SHAPE = "finish_shape";
    public static String FINISH_SIZE = "finish_size";
    public static String FINISH_SPEED = "finish_speed";
    public static String HEIGHT = "height";
    public static String HOLECORNER = "holecorner";
    public static String HOLERADIUS = "holeradius";
    public static String HOLERADIUSY = "holeradiusy";
    public static String HOLESHARP = "holesharp";
    public static String HOLEX = "holex";
    public static String HOLEY = "holey";
    public static String ID_THEME = "id_theme";
    public static String INFILITYHEIGHT = "infilityheight";
    public static String INFILITYRADIUS = "infilityradius";
    public static String INFILITYRADIUSB = "infilityradiusb";
    public static String INFILITYSHARP = "infilitysharp";
    public static String INFILITYWIDTH = "infilitywidth";
    public static String NAMETHEME = "nametheme";
    private static final String NEWS_ADS_PREFERENCES = "DUONGCV";
    public static String NOTCHBOTTOM = "notchbottom";
    public static String NOTCHHEIGHT = "notchheight";
    public static String NOTCHRADIUSBOTTOM = "notchradiusbottom";
    public static String NOTCHRADIUSTOP = "notchradiustop";
    public static String NOTCHTOP = "notchtop";
    public static String RADIUSBOTTOM = "bottom";
    public static String RADIUSTOP = "top";
    public static String SHAPE = "shape";
    public static String SIZE = "size";
    public static String SPEED = "speed";
    public static String START_DATABASE = "startdatabase";
    public static String TIME_COUNTER_RATE = "time_counter_rate";
    public static String WIDTH = "width";

    public static int getInt(String str, Context context) {
        return getIntValue(str, context);
    }

    public static void setInt(String str, int i, Context context) {
        putIntValue(str, i, context);
    }

    public static float getFloat(String str, Context context) {
        return getFloatValue(str, context).floatValue();
    }

    public static void setFloat(String str, float f, Context context) {
        putFloatValue(str, Float.valueOf(f), context);
    }

    public static String getString(String str, Context context) {
        return getStringValue(str, context);
    }

    public static void setString(Context context, String str, String str2) {
        putStringValue(str, str2, context);
    }

    public static void putStringValue(String str, String str2, Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(NEWS_ADS_PREFERENCES, 0).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public static void putBoolean(String str, boolean z, Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(NEWS_ADS_PREFERENCES, 0).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public static void putFloatValue(String str, Float f, Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(NEWS_ADS_PREFERENCES, 0).edit();
        edit.putFloat(str, f.floatValue());
        edit.commit();
    }

    public static void putLongValue(String str, long j, Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(NEWS_ADS_PREFERENCES, 0).edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public static Long getLongValue(String str, Context context) {
        return Long.valueOf(context.getSharedPreferences(NEWS_ADS_PREFERENCES, 0).getLong(str, 0));
    }

    public static void putIntValue(String str, int i, Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(NEWS_ADS_PREFERENCES, 0).edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public static void putBooleanValue(String str, Boolean bool, Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(NEWS_ADS_PREFERENCES, 0).edit();
        edit.putBoolean(str, bool.booleanValue());
        edit.commit();
    }

    public static boolean getBooleanValue(String str, Context context) {
        return context.getSharedPreferences(NEWS_ADS_PREFERENCES, 0).getBoolean(str, false);
    }

    public static String getStringValue(String str, Context context) {
        return context.getSharedPreferences(NEWS_ADS_PREFERENCES, 0).getString(str, null);
    }

    public static Float getFloatValue(String str, Context context) {
        return Float.valueOf(context.getSharedPreferences(NEWS_ADS_PREFERENCES, 0).getFloat(str, 0.0f));
    }

    public static int getIntValue(String str, Context context) {
        return context.getSharedPreferences(NEWS_ADS_PREFERENCES, 0).getInt(str, -1);
    }
}
