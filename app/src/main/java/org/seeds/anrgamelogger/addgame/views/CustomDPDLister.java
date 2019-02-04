package org.seeds.anrgamelogger.addgame.views;


import android.app.DatePickerDialog;
import android.util.Log;
import android.widget.DatePicker;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public  class CustomDPDLister implements DatePickerDialog.OnDateSetListener{

    private static final String LOG_TAG = CustomDPDLister.class.getSimpleName();

    private PublishSubject<String> temp = PublishSubject.create();

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + month + "/" + year;
        Log.d(LOG_TAG, "onDateSet Called. Sending date of: " + date);
        Observable.just(date).subscribe(temp);
    }

    public Observable<String> alertDateSelected(){
        return temp;
    }
}