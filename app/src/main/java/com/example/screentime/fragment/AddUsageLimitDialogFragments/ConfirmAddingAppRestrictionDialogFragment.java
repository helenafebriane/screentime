package com.example.screentime.fragment.AddUsageLimitDialogFragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class ConfirmAddingAppRestrictionDialogFragment extends DialogFragment {

    private ConfirmAddingAppRestrictionDialogFragment.FinalDialogInterface mFinalDialogInterface;

    public ConfirmAddingAppRestrictionDialogFragment() {
        // Required empty public constructor
    }

    public interface FinalDialogInterface {
        void onYesFinalAlertSelected();

        void onExitFinalAlertSelected();
    }

    public static ConfirmAddingAppRestrictionDialogFragment newInstance() {
        return new ConfirmAddingAppRestrictionDialogFragment();
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mFinalDialogInterface = (ConfirmAddingAppRestrictionDialogFragment.FinalDialogInterface) getActivity();
        builder.setMessage("Apakah Anda yakin ingin menambahkan Pembatasan pada aplikasi Anda ?")
                .setTitle("Atur Pembatasan")
                .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mFinalDialogInterface.onYesFinalAlertSelected();
                    }
                })
                .setNeutralButton("KELUAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mFinalDialogInterface.onExitFinalAlertSelected();

                    }
                })
                .setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }


}
