package kr.saintdev.dcalarmapp.modules.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingFunctions {
    static final String SHARED_PREP_NAME = "dcalarm.settings";
    static final String PARSEING_SERVICE_DELAY_NAME = "dcalarm.settings.pdelay";
    static final String WORK_ON_DATA_MODE_NAME = "dcalarm.settings.usedata";


    public static int getParseingServiceDelay(Context context) {
        SharedPreferences sharedPrep = context.getSharedPreferences(SHARED_PREP_NAME, Context.MODE_PRIVATE);
        return sharedPrep.getInt(PARSEING_SERVICE_DELAY_NAME, 10);
    }

    public static void setParseingServiceDelay(Context context, int a) {
        SharedPreferences.Editor sharedPrep = context.getSharedPreferences(SHARED_PREP_NAME, Context.MODE_PRIVATE).edit();
        sharedPrep.putInt(PARSEING_SERVICE_DELAY_NAME, a);
        sharedPrep.apply();
    }

    public static boolean isWorkOnDataMode(Context context) {
        SharedPreferences sharedPrep = context.getSharedPreferences(SHARED_PREP_NAME, Context.MODE_PRIVATE);
        return sharedPrep.getBoolean(WORK_ON_DATA_MODE_NAME, false);
    }

    public static void setWorkOnDataMode(Context context, boolean bool) {
        SharedPreferences.Editor sharedPrep = context.getSharedPreferences(SHARED_PREP_NAME, Context.MODE_PRIVATE).edit();
        sharedPrep.putBoolean(WORK_ON_DATA_MODE_NAME, bool);
        sharedPrep.apply();
    }
}
