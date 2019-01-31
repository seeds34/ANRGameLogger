package org.seeds.anrgamelogger.addgame.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class CustomDatePickerDialog extends DatePickerDialog {

    public CustomDatePickerDialog(@NonNull Context context, int themeResId, @Nullable OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth) {
        super(context, themeResId, listener, year, monthOfYear, dayOfMonth);
    }


    //Forget about the listner and create my on ONClick/RXClick on the ok button??
}
