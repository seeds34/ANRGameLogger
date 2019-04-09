package org.seeds.anrgamelogger.addgame;

import android.app.DatePickerDialog;
import android.util.Log;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public  class CustomDPDLister implements DatePickerDialog.OnDateSetListener{

    private static final String LOG_TAG = CustomDPDLister.class.getSimpleName();
    private final PublishSubject<Calendar> temp = PublishSubject.create();

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String date = dayOfMonth + "/" + month+1 + "/" + year;
        Log.d(LOG_TAG, "onDateSet Called. Sending date of: " + date);
        temp.onNext(cal);
    }

    public Observable<Calendar> alertDateSelected(){
        return temp;
    }
}