package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jakewharton.rxbinding2.view.RxView;
import butterknife.OnClick;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.Calendar;
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

  @BindView(R.id.btn_save)
  public Button btn_save;

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
  public Observable<Object> save(){
    return RxView.clicks(btn_save);
  }

  @OnClick(R.id.addGameDateSelector)
  public void onClickDate() {
    Log.d(LOG_TAG,"onClickDate() : Starting Date Dialog");
    setupDateDialog();
    dialog.show();
  }

  private void setupDateDialog() {
    //NOTE: Worried this might course a mem leak or to much garbuge in heap
    dialog = new DatePickerDialog(activity, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
  }

  public void setDate(Calendar calIn){
    //TODO: Need to loclize date

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
}