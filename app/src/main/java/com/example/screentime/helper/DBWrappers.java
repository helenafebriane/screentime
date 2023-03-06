package com.example.screentime.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.screentime.R;
import com.example.screentime.database.dbAddUsageLimit.DBAddUsageLimitHandler;
import com.example.screentime.model.AppAddUsageLimit;

import java.util.Collections;
import java.util.List;

public class DBWrappers {

    public static final String NO_RESTRICTED_APPS = "Tidak Ada Aplikasi Yang Memiliki Pembatasan !";
    public static final String NO_LAUNCH_RESTRICTED_APPS = "Tidak Ada Aplikasi Yang Memiliki Pembatasan Pemakaian !";
    public static final String NO_TIME_RESTRICTED_APPS = "Tidak Ada Aplikasi Yang Memiliki Pembatasan Waktu Pemakaian !";
    public static final String NO_SPECIFIC_TIME_RESTRICTED_APPS = "Tidak Ada Aplikasi Yang Memiliki Pembatasan Waktu Spesifik !";

    public static final int LAUNCH_COUNT_INFINITY = 7651234;
    public static final int TIME_VALUE_INFINITY = 90000000;




    public static List<AppAddUsageLimit> getAppsWithAnyLimit(Context context) {

        List<AppAddUsageLimit> anyLimitAppsList = DBAddUsageLimitHandler.getAppsWithAnyLimit();
        if (anyLimitAppsList.size() == 0) {
            AppAddUsageLimit appAddUsageLimit = new AppAddUsageLimit();
            appAddUsageLimit.setAppName(NO_RESTRICTED_APPS);
            Drawable d = context.getResources().getDrawable(R.mipmap.nodata);
            appAddUsageLimit.setIcon(d);
            anyLimitAppsList.add(appAddUsageLimit);
        }
        Collections.sort(anyLimitAppsList, new CustomComparators.alphabetOrderAppAddUsageLimit());
        return anyLimitAppsList;
    }





    public static List<AppAddUsageLimit> getAppsWithLaunchLimit(Context context) {

        List<AppAddUsageLimit> launchLimitAppsList = DBAddUsageLimitHandler.getAppsWithLaunchLimit();
        if (launchLimitAppsList.size() == 0) {
            AppAddUsageLimit appAddUsageLimit = new AppAddUsageLimit();
            appAddUsageLimit.setAppName(NO_LAUNCH_RESTRICTED_APPS);
            Drawable d = context.getResources().getDrawable(R.mipmap.nodata);
            appAddUsageLimit.setIcon(d);
            launchLimitAppsList.add(appAddUsageLimit);
        }
        Collections.sort(launchLimitAppsList, new CustomComparators.alphabetOrderAppAddUsageLimit());
        return launchLimitAppsList;
    }





    public static List<AppAddUsageLimit> getAppsWithTimeLimit(Context context) {

        List<AppAddUsageLimit> timeLimitAppsList = DBAddUsageLimitHandler.getAppsWithTimeLimit();
        if (timeLimitAppsList.size() == 0) {
            AppAddUsageLimit appAddUsageLimit = new AppAddUsageLimit();
            appAddUsageLimit.setAppName(NO_TIME_RESTRICTED_APPS);
            Drawable d = context.getResources().getDrawable(R.mipmap.nodata);
            appAddUsageLimit.setIcon(d);
            timeLimitAppsList.add(appAddUsageLimit);
        }
        Collections.sort(timeLimitAppsList, new CustomComparators.alphabetOrderAppAddUsageLimit());
        return timeLimitAppsList;
    }





    public static List<AppAddUsageLimit> getAppsWithSpecificTimeLimit(Context context) {
        List<AppAddUsageLimit> specifTimeLimitAppsList = DBAddUsageLimitHandler.getAppsWithSpecificTimeLimit();
        if (specifTimeLimitAppsList.size() == 0) {
            AppAddUsageLimit appAddUsageLimit = new AppAddUsageLimit();
            appAddUsageLimit.setAppName(NO_SPECIFIC_TIME_RESTRICTED_APPS);
            Drawable d = context.getResources().getDrawable(R.mipmap.nodata);
            appAddUsageLimit.setIcon(d);
            specifTimeLimitAppsList.add(appAddUsageLimit);
        }
        Collections.sort(specifTimeLimitAppsList, new CustomComparators.alphabetOrderAppAddUsageLimit());
        return specifTimeLimitAppsList;
    }


}
