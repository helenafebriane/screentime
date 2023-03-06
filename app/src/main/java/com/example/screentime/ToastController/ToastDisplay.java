package com.example.screentime.ToastController;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastDisplay {

    public static void makeLongDurationToast(Context context, String toastMsg) {
        Toast toast = Toast.makeText(context, toastMsg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public static void makeShortDurationToast(Context context, String toastMsg) {
        Toast toast = Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
