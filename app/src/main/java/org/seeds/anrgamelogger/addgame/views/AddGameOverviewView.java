package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.jakewharton.rxbinding2.view.RxView;

import butterknife.OnClick;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.Calendar;

import org.seeds.anrgamelogger.R;

/**
 * Created by Tomas Seymour-Turner on 04/01/2018.
 */

public class AddGameOverviewView extends FrameLayout implements AddGameSubView {

  private static final String LOG_TAG = AddGameOverviewView.class.getSimpleName();

  //TODO: Change Name of SIDE as this is overciew
  //TODO: Add common type between ADDGame Views??

  private Activity activity;

  @BindView(R.id.btn_save)
  public Button btn_save;

  @BindView(R.id.txt_location)
  public AutoCompleteTextView location;

  @BindView(R.id.addGameDateSelector)
  public TextView playedDate;

  private final int viewNo = R.layout.view_addgame_overview;
  private ArrayAdapter<String> locationListAdapter;

  private String title;

  //private DatePickerDialog mDateSetListener;

  public AddGameOverviewView(Activity activity){
    super(activity);
    this.activity = activity;
    inflate(getContext(), viewNo, this);
    ButterKnife.setDebug(true);
    ButterKnife.bind(this);
  }

  public int getViewNo(){
    return viewNo;
  }

  public void setUpLocationAutoComplete(ArrayList<String> locationList){
    locationListAdapter = new ArrayAdapter<>(activity, R.layout.support_simple_spinner_dropdown_item, locationList);
    locationListAdapter.setNotifyOnChange(true);
    location.setAdapter(locationListAdapter);
  }

  public String getTitle(){
    return title;
  }

 public Observable<Object> save(){
   return RxView.clicks(btn_save);
 }

  public String getLocation(){
      return location.getText().toString();
    }

  public String getPlayedDate(){
    return playedDate.getText().toString();
  }

  public String getWinType() {
    return "ABC";
  }

//  public String getWiningSide() {
//    return "ABC";
//  }

//  public static class CustomDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{
//extends DialogFragment implements DatePickerDialog.OnDateSetListener

//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//      // Use the current date as the default date in the picker
//      final Calendar c = Calendar.getInstance();
//      int year = c.get(Calendar.YEAR);
//      int month = c.get(Calendar.MONTH);
//      int day = c.get(Calendar.DAY_OF_MONTH);
//
//      // Create a new instance of DatePickerDialog and return it
//      return new DatePickerDialog(getActivity(), this, year, month, day);
//    }
//
//    public void onDateSet(DatePicker view, int year, int month, int day) {
//      // Do something with the date chosen by the user
//    }
//  }


  DatePickerDialog mDateSetListener = new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker v, int year, int month, int dayOfMonth) {
      onDateSet(year, month, dayOfMonth);
    }
  };


  public void onDateSet(int year, int month, int dayOfMonth){
    month = month + 1;
    String date = dayOfMonth + "/" + month + "/" + year;
  }


  @OnClick(R.id.addGameDateSelector)
  public void onClickDate() {

    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    DatePickerDialog dialog = new DatePickerDialog(activity, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    dialog.show();

  }

  public void setDateText (String date){
    dateText.setText(date);
  }



}
