package com.cmcinnis.craig.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimePickerFragment extends DialogFragment {
    private TimePicker mTimePicker;
    public static final String EXTRA_TIME = "com.cmcinnis.craig.criminalintent.time";

    public static TimePickerFragment newInstance(Date date){
        //pass bundle with the object into the onCreateDialog method
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_TIME, date);

        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.setArguments(args);
        return timePickerFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //retrieve date from the bundle
        Date date = (Date) getArguments().getSerializable(EXTRA_TIME);

        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        View v = (TimePicker) LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time, null);

        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_picker);

        // setHour/setMinute are only available after api v23
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTimePicker.setHour(hour);
            mTimePicker.setMinute(minute);
        }else{
            mTimePicker.setCurrentHour(hour);
            mTimePicker.setCurrentMinute(minute);
        }

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Time of crime:")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        int hour;
                        int minute;
                        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            hour = mTimePicker.getHour();
                            minute = mTimePicker.getMinute();
                        }else{
                            hour = mTimePicker.getCurrentHour();
                            minute = mTimePicker.getCurrentMinute();
                        }
                        Date date = new GregorianCalendar(year, month, day, hour, minute).getTime();
                        sendResult(Activity.RESULT_OK, date);
                    }
                })
                .create();

    }

    // send the result back to CrimeFragment once a time is selected
    private void sendResult(int resultCode, Date date){
        if(getTargetFragment() == null)
        {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, date);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }


}
