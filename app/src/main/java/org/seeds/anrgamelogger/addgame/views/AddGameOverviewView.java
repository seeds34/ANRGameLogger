package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.addgame.CustomDPDLister;

/**
 * Created by Tomas Seymour-Turner on 04/01/2018.
 */

public class AddGameOverviewView extends FrameLayout implements AddGameSubView {

  private static final String LOG_TAG = AddGameOverviewView.class.getSimpleName();

  //TODO: Change Name of SIDE as this is overciew
  //TODO: Add common type between ADDGame Views??

  private Activity activity;

  @BindView(R.id.txt_location)
  public AutoCompleteTextView location;

  @BindView(R.id.addGameDateSelector)
  public TextView playedDate;

  @BindView(R.id.rg_winingSide)
  public RadioGroup winningSideGroup;

  @BindView(R.id.rg_winType)
  public RadioGroup winTypeGroup;

  private final int viewNo = R.layout.view_addgame_overview;
  private ArrayAdapter<String> locationListAdapter;

  private String title;

  private Calendar cal;
  private String date;

  private DatePickerDialog dialog;
  private CustomDPDLister mDateSetListener;

  public AddGameOverviewView(Activity activity){
    super(activity);
    this.activity = activity;
    inflate(getContext(), viewNo, this);
    ButterKnife.setDebug(true);
    ButterKnife.bind(this);

    setDate(Calendar.getInstance());
  }

  public View getView(){
    return this;
  }

  public void setUpLocationAutoComplete(ArrayList<String> locationList){
    locationListAdapter = new ArrayAdapter<>(activity, R.layout.support_simple_spinner_dropdown_item, locationList);
    locationListAdapter.setNotifyOnChange(true);
    location.setAdapter(locationListAdapter);
  }

  public void setListener(CustomDPDLister in){
    mDateSetListener = in;
  }

  public String getSide(){
    return title;
  }

  public void setTitle(String title){
    this.title = title;
  }

  //TODO: Shorten this.
  public String getWiningSide(){
    int id = winningSideGroup.getCheckedRadioButtonId();
    RadioButton rb = findViewById(id);
    String name =  (String)rb.getText();
    return name;
  }

  public String getWinType(){
    RadioButton rb = findViewById(winTypeGroup.getCheckedRadioButtonId());
    return (String)rb.getText();
  }

  //TODO: Add setWinType & setWinningSide

  public String getPlayedDate(){
    return date;
  }

  @OnClick(R.id.addGameDateSelector)
  public void onClickDate() {
    Log.d(LOG_TAG,"onClickDate() : Starting Date Dialog");
    setupDateDialog();
    dialog.show();
  }

  private void setupDateDialog() {
    //NOTE: Worried this might course a mem leak or to much garbuge in heap
    dialog = new DatePickerDialog(activity, android.R.style.Theme_Holo_Light_Dialog, mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

    dialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());

    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
  }

  public void setDate(Calendar calIn){
    //TODO: Need to localize date
    cal = calIn;
      int year = cal.get(Calendar.YEAR);
      int month = cal.get(Calendar.MONTH)+1;
      int day = cal.get(Calendar.DAY_OF_MONTH);

      date = day + "/" + month + "/" + year;

      Log.d(LOG_TAG,"setDate() : Setting date to: " + date);
    playedDate.setText(date);
  }

  public String getLocation(){
    return location.getText().toString();
  }

  public void setLocation(String locationIn){
    location.setText(locationIn, false);
  }

  public void setWinningSide(String winningSide){
     RadioButton runnerRB = findViewById(R.id.rbtn_winnerRunner);
     RadioButton corpRB = findViewById(R.id.rbtn_winnerCorp);

     if(runnerRB.getText().equals(winningSide)){
       runnerRB.setChecked(true);
     }else{
        corpRB.setChecked(true);
    }
  }

  public void setWinType(String winType){
    RadioButton kill = findViewById(R.id.rbtn_killWin);
    RadioButton mill = findViewById(R.id.rbtn_millWin);
    RadioButton score = findViewById(R.id.rbtn_scoreWin);

    if(kill.getText().equals(winType)){
      kill.setChecked(true);
    }else if(mill.getText().equals(winType)){
      mill.setChecked(true);
    }else{
      score.setChecked(true);
    }
  }

  public void setPlayedDate(String playedDateIn){
      String[] dateParts = playedDateIn.split("/");
      int day = Integer.getInteger(dateParts[0]);
      int month = Integer.getInteger(dateParts[1]);
      int year = Integer.getInteger(dateParts[2]);

      cal.set(year,month,day);
    playedDate.setText(playedDateIn);
  }
}