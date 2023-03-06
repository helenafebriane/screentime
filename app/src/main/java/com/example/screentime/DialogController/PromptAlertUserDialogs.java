package com.example.screentime.DialogController;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.screentime.ToastController.ToastDisplay;
import com.example.screentime.ToastController.ToastMessages;
import com.example.screentime.helper.DateAndTimeManip;
import com.example.screentime.helper.NavDrawerItemsIntentResolver;
import com.example.screentime.helper.SpecialIntents;
import com.example.screentime.model.AppAddUsageLimit;
import com.example.screentime.sharedPreferences.MySharedPreferences;
import com.example.screentime.sharedPreferences.SPNames;


public class PromptAlertUserDialogs {


    private final static long TEN_MINUTES = 10 * 60 * 1000;


    // prompt user for usage access required
    public static void showDialogUsageAccessRequired(final Context context) {

        AlertDialog usageAccessRequiredDialog;

        String title = "AKSES PEMAKAIAN DIPERLUKAN !!";
        String text = "Aplikasi ini membutuhkan AKSES PEMAKAIAN untuk bekerja. Berikan izin untuk terus menggunakan aplikasi ini.";
        String OKButton = "BERIKAN AKSES PEMAKAIAN";
        String CancelButton = "TIDAK";

        usageAccessRequiredDialog = new AlertDialog.Builder(context).create();
        usageAccessRequiredDialog.setTitle(title);
        usageAccessRequiredDialog.setMessage(text);
        usageAccessRequiredDialog.setCancelable(false);
        usageAccessRequiredDialog.setCanceledOnTouchOutside(false);

        usageAccessRequiredDialog.setButton(AlertDialog.BUTTON_POSITIVE, OKButton,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SpecialIntents.openUsageAccessSettingsPage(context);
                    }
                });

        usageAccessRequiredDialog.setButton(AlertDialog.BUTTON_NEGATIVE, CancelButton,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SpecialIntents.showHomeScreen(context);
                    }
                });


        usageAccessRequiredDialog.show();

    }


    // prompt user for showing help page
    public static void showDialogOpenHelpPage(final Context context) {

        AlertDialog openHelpPageDialog;

        String title = "Silakan Baca Bagian Bantuan !";
        String text = "Anda diminta untuk membaca Bagian Bantuan setidaknya sekali " +
                "untuk mendapatkan gambaran tentang cara kerja Aplikasi karena Aplikasi ini mungkin agak rumit " +
                "untuk dipahami oleh Pengguna Pertama Kali.";
        String OKButton = "TUNJUKKAN BANTUAN";
        String CancelButton = "TIDAK";

        openHelpPageDialog = new AlertDialog.Builder(context).create();
        openHelpPageDialog.setTitle(title);
        openHelpPageDialog.setMessage(text);
        openHelpPageDialog.setCancelable(false);
        openHelpPageDialog.setCanceledOnTouchOutside(false);

        openHelpPageDialog.setButton(AlertDialog.BUTTON_POSITIVE, OKButton,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        NavDrawerItemsIntentResolver.onHelpSelected(context);
                    }
                });

        openHelpPageDialog.setButton(AlertDialog.BUTTON_NEGATIVE, CancelButton,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });


        openHelpPageDialog.show();
    }


    // prompt user when app limit is not removed
    public static void showDialogAppLimitNotRemoved(final Context context, AppAddUsageLimit appAddUsageLimit) {

        AlertDialog appLimitNotRemovedDialog = new AlertDialog.Builder(context).create();

        String title = "Pembatasan Pemakaian Tidak Dihapus";
        String text = "Pembatasan Pemakaian pada " + appAddUsageLimit.getAppName() +
                " tidak dapat dihapus sekarang karena Anda telah mencapai batas jam/harian/waktu spesifik" +
                " untuk mengaksesnya. Menghapus pembatasan sekarang akan memberi Anda akses ke " +
                appAddUsageLimit.getAppName() + ". Coba lagi nanti !";
        String OKButton = "OK";

        appLimitNotRemovedDialog.setTitle(title);
        appLimitNotRemovedDialog.setMessage(text);
        appLimitNotRemovedDialog.setIcon(appAddUsageLimit.getIcon());
        appLimitNotRemovedDialog.setCancelable(false);
        appLimitNotRemovedDialog.setCanceledOnTouchOutside(false);

        appLimitNotRemovedDialog.setButton(AlertDialog.BUTTON_POSITIVE, OKButton,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        appLimitNotRemovedDialog.show();
    }


    public static void showDisableAppDialog(final Context context) {

        String title = " Nonaktifkan Screentime ? ";
        String text = "Anda yakin ingin menonaktifkan aplikasi? Anda hanya dapat melakukannya sekali sehari.";

        AlertDialog confirmAppDisableDialog = new AlertDialog.Builder(context).create();
        confirmAppDisableDialog.setTitle(title);
        confirmAppDisableDialog.setMessage(text);
        confirmAppDisableDialog.setCanceledOnTouchOutside(false);


        // YES button
        confirmAppDisableDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YA",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        // set app-disabled-once-a-day option to true
                        MySharedPreferences.storeBooleanValue(context,
                                SPNames.SP_APP_DISABLED, SPNames.SP_KEY_WAS_APP_DISABLED_ONCE_TODAY, true);

                        // set app-enabled-currently option to false
                        MySharedPreferences.storeBooleanValue(context,
                                SPNames.SP_APP_DISABLED, SPNames.SP_KEY_IS_APP_CURRENTLY_ENABLED, false);

                        // set app-enabled-time
                        MySharedPreferences.storeLongIntValue(context,
                                SPNames.SP_APP_DISABLED, SPNames.SP_KEY_APP_ENABLE_TIME, DateAndTimeManip.getTimeAfterTenMin());

                        ToastDisplay.makeLongDurationToast(context, ToastMessages.APP_DISABLED_SUCCESS);
                    }
                });

        // NO button
        confirmAppDisableDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "TIDAK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        confirmAppDisableDialog.show();
    }

}
