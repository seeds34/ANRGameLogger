package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

public class AddGameOverviewView extends AddGameBaseView{

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

  private DatePickerDialog.OnDateSetListener dateSetListener;

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

  public void onCreate(){}

  @Override
  public void setUpLocationAutoComplete(ArrayList<String> locationList){
    locationListAdapter = new ArrayAdapter<>(activity, R.layout.support_simple_spinner_dropdown_item, locationList);
    locationListAdapter.setNotifyOnChange(true);
    location.setAdapter(locationListAdapter);
  }

  @OnClick(R.id.addGameDateSelector)
  public void dateDiaolog(){
    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int day = cal.get(Calendar.DAY_OF_MONTH);

    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.AppTheme, dateSetListener,year,month,day);
    datePickerDialog.show();
  }

//  public static class CustomDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{
//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState){
//
//
//    }
//  }

  @Override
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

  public String getWiningSide() {
    return "ABC";
  }
}