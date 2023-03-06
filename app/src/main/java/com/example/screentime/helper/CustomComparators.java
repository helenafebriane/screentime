package com.example.screentime.helper;

import com.example.screentime.model.AppAddUsageLimit;
import com.example.screentime.model.AppViewUsageStats;

import java.util.Comparator;

public class CustomComparators {


    public static class alphabetOrderAppAddUsageLimit implements Comparator<AppAddUsageLimit> {
        @Override
        public int compare(AppAddUsageLimit left, AppAddUsageLimit right) {
            return left.getAppName().compareToIgnoreCase(right.getAppName());
        }
    }



    public static class alphabetOrderAppViewUsageStats implements Comparator<AppViewUsageStats> {

        @Override
        public int compare(AppViewUsageStats left, AppViewUsageStats right) {
            return left.getAppName().compareToIgnoreCase(right.getAppName());
        }
    }
}
