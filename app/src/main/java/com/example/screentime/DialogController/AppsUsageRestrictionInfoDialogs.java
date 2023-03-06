package com.example.screentime.DialogController;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.screentime.R;
import com.example.screentime.adapter.TimeSlotAdapter;
import com.example.screentime.helper.DBWrappers;
import com.example.screentime.helper.DateAndTimeManip;
import com.example.screentime.model.AppAddUsageLimit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AppsUsageRestrictionInfoDialogs {


    // any restriction limit info
    public static void showDialogAppsInfoWithAnyLimit(Activity activity, AppAddUsageLimit appAddUsageLimit) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.fragment_view_restriction_restrictions_type_info, null);
        dialogBuilder.setView(dialogView);

        TextView launchRestrictionSet, usageTimeRestrictionSet, specificTimeResrtrictionSet;

        launchRestrictionSet = (TextView) dialogView.findViewById(R.id.launchRestrictionSet);
        usageTimeRestrictionSet = (TextView) dialogView.findViewById(R.id.usageTimeRestrictionSet);
        specificTimeResrtrictionSet = (TextView) dialogView.findViewById(R.id.specificTimeRestrictionSet);

        if (appAddUsageLimit.getIsLaunchRestrictionSet()) {
            launchRestrictionSet.setText("Ya");
        } else {
            launchRestrictionSet.setText("Tidak");
        }

        if (appAddUsageLimit.getIsUsageTimeRestrictionSet()) {
            usageTimeRestrictionSet.setText("Ya");
        } else {
            usageTimeRestrictionSet.setText("Tidak");
        }

        if (appAddUsageLimit.getIsSpecifiTimeRestrictionSet()) {
            specificTimeResrtrictionSet.setText("Ya");
        } else {
            specificTimeResrtrictionSet.setText("Tidak");
        }

        String title = appAddUsageLimit.getAppName() + " Informasi Pembatasan Pemakaian ";

        dialogBuilder.setTitle(title);
        dialogBuilder.setIcon(appAddUsageLimit.getIcon());

        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        AlertDialog dialogAppsWithAnyLimitInfo = dialogBuilder.create();
        dialogAppsWithAnyLimitInfo.show();
    }


    // launch limit info
    public static void showDialogAppsInfoWithLaunchLimit(Activity activity, AppAddUsageLimit appAddUsageLimit) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.fragment_view_restriction_launch_restriction_info, null);
        dialogBuilder.setView(dialogView);

        TextView launchCounrPerHourTextView, launchCountPerDayTextView;

        launchCounrPerHourTextView = (TextView) dialogView.findViewById(R.id.perHourTime);
        launchCountPerDayTextView = (TextView) dialogView.findViewById(R.id.perDayTime);

        if (appAddUsageLimit.getLaunchAllowedPerHour() == 1) {
            launchCounrPerHourTextView.setText(Integer.toString(appAddUsageLimit.getLaunchAllowedPerHour()) + "  Kali");
        } else if (appAddUsageLimit.getLaunchAllowedPerHour() == DBWrappers.LAUNCH_COUNT_INFINITY) {
            launchCounrPerHourTextView.setText(" Tidak Ada Batas ");
        } else {
            launchCounrPerHourTextView.setText(Integer.toString(appAddUsageLimit.getLaunchAllowedPerHour()) + "  Kali");
        }

        if (appAddUsageLimit.getLaunchAllowedPerDay() == 1) {
            launchCountPerDayTextView.setText(Integer.toString(appAddUsageLimit.getLaunchAllowedPerDay()) + "  Kali");
        } else if (appAddUsageLimit.getLaunchAllowedPerDay() == DBWrappers.LAUNCH_COUNT_INFINITY) {
            launchCountPerDayTextView.setText(" Tidak Ada Batas ");
        } else {
            launchCountPerDayTextView.setText(Integer.toString(appAddUsageLimit.getLaunchAllowedPerDay()) + "  Kali");
        }

        String title = appAddUsageLimit.getAppName() + " Menggunakan Pembatasan Pemakaian ";

        dialogBuilder.setTitle(title);
        dialogBuilder.setIcon(appAddUsageLimit.getIcon());

        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        AlertDialog dialogAppsWithLaunchLimitInfo = dialogBuilder.create();
        dialogAppsWithLaunchLimitInfo.show();
    }


    // usage time limit info
    public static void showDialogAppsInfoWithTimeLimit(Activity activity, AppAddUsageLimit appAddUsageLimit) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.fragment_view_restriction_usage_time_restriction_info, null);
        dialogBuilder.setView(dialogView);

        TextView perHourTime, perDayTime;

        perHourTime = (TextView) dialogView.findViewById(R.id.perHourTime);
        perDayTime = (TextView) dialogView.findViewById(R.id.perDayTime);

        if (appAddUsageLimit.getTimeAllowedPerHour() != DBWrappers.TIME_VALUE_INFINITY) {
            perHourTime.setText(Integer.toString(appAddUsageLimit.getTimeAllowedPerHour() / 60000) + "  Menit");
        } else {
            perHourTime.setText("Tidak Ada Batas");
        }

        if (appAddUsageLimit.getTimeAllowedPerDay() != DBWrappers.TIME_VALUE_INFINITY) {
            perDayTime.setText(DateAndTimeManip.getTimeInHoursAndMin(appAddUsageLimit.getTimeAllowedPerDay()));
        } else {
            perDayTime.setText("Tidak Ada Batas");
        }

        String title = appAddUsageLimit.getAppName() + " Informasi Pembatasan Waktu Pemakaian ";

        dialogBuilder.setTitle(title);
        dialogBuilder.setIcon(appAddUsageLimit.getIcon());

        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        AlertDialog dialogAppsWithTimeLimitInfo = dialogBuilder.create();
        dialogAppsWithTimeLimitInfo.show();
    }


    // specific time limit info
    public static void showDialogAppsInfoWithSpecificTimeLimit(Activity activity, AppAddUsageLimit appAddUsageLimit) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.fragment_view_restriction_specific_time_restriction_info, null);
        dialogBuilder.setView(dialogView);

        ListView timeSlotsListView;
        timeSlotsListView = (ListView) dialogView.findViewById(R.id.timeSlotListView);

        String beginTime = appAddUsageLimit.getSpecificTimeBegin();
        String endTime = appAddUsageLimit.getSpecificTimeEnd();

        ArrayList<String> timeSlotsList = new ArrayList<>();

        String[] beginSlots = beginTime.split("-");
        String[] endSlots = endTime.split("-");

        for (int i = 0; i < beginSlots.length; i++) {
            timeSlotsList.add(beginSlots[i] + "  -  " + endSlots[i]);
        }

        Collections.sort(timeSlotsList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });

        TimeSlotAdapter timeSlotAdapter = new TimeSlotAdapter(activity, timeSlotsList);
        timeSlotsListView.setAdapter(timeSlotAdapter);

        String title = appAddUsageLimit.getAppName() + " Informasi Pembatasan Waktu Spesifik ";

        dialogBuilder.setTitle(title);
        dialogBuilder.setIcon(appAddUsageLimit.getIcon());
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        AlertDialog dialogAppsWithSpecificTimeLimitInfo = dialogBuilder.create();
        dialogAppsWithSpecificTimeLimitInfo.show();
    }


}