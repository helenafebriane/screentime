package com.example.screentime.fragment.AddUsageLimitDialogFragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.example.screentime.ToastController.ToastDisplay;
import com.example.screentime.ToastController.ToastMessages;

public class SpecificTimeRestrictionFinalTimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private TimePickerDialog mTimePickerDialog;
    private SpecificTimeRestrictionFinalTimePickerDialogFragment.FinalTimePickerInterface mFinalTimePickerInterface;

    public SpecificTimeRestrictionFinalTimePickerDialogFragment() {
        // Required empty public constructor
    }

    public interface FinalTimePickerInterface {
        void FinalTimeSetListener(int hour, int minute);

        void onBackButtonFinalTimePickerFragmentSelected();
    }

    public static SpecificTimeRestrictionFinalTimePickerDialogFragment newInstance() {
        return new SpecificTimeRestrictionFinalTimePickerDialogFragment();
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        mFinalTimePickerInterface = (SpecificTimeRestrictionFinalTimePickerDialogFragment.FinalTimePickerInterface) getActivity();

        // Create a new instance of TimePickerDialog and return it
        mTimePickerDialog = new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));

        mTimePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "ATUR WAKTU AKHIR", mTimePickerDialog);
        mTimePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "KEMBALI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mFinalTimePickerInterface.onBackButtonFinalTimePickerFragmentSelected();
            }
        });
        return mTimePickerDialog;
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mFinalTimePickerInterface.FinalTimeSetListener(hourOfDay, minute);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        ToastDisplay.makeLongDurationToast(getContext(), ToastMessages.TIME_LIMIT_NOT_SET);
        super.onCancel(dialog);
    }


}
