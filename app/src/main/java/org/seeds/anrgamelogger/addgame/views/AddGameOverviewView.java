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

  private DatePickerDialog dialog;
  private CustomDPDLister mDateSetListener;

  public AddGameOverviewView(Activity activity){
    super(activity);
    this.activity = activity;
    inflate(getContext(), viewNo, this);
    ButterKnife.setDebug(true);
    ButterKnife.bind(this);
    //mDateSetListener = new  CustomDPDLister();
  }

  public View getView(){
    return this;
  }

  public void setUpLocationAutoComplete(ArrayList<String> locationList){
    locationListAdapter = new ArrayAdapter<>(activity, R.layout.support_simple_spinner_dropdown_item, locationList);
    locationListAdapter.setNotifyOnChange(true);
    location.setAdapter(locationListAdapter);
  }

  public void setListner(CustomDPDLister in){
    mDateSetListener = in;
  }

  public String getTitle(){
    return title;
  }

  public void setTitle(String title){
    this.title = title;
  }

  public Observable<Object> save(){
    return RxView.clicks(btn_save);
  }

  /* Two options:
         1st: When Data picker pressed this view controls pop up using @OnClick
         2nd: Observible is sent using RxView and obsoveble tells presneter somthing needs to happen
   */

//  public Observable<String> obvsAlertDateSelected(){
//    Log.d(LOG_TAG,"Received Alert to set Date. Passing to Presenter");
//    return mDateSetListener.alertDateSelected();
//  }

  private void setUpDateDialog(){

    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    dialog = new DatePickerDialog(activity, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
  }

  @OnClick(R.id.addGameDateSelector)
  public void onClickDate() {
    Log.d(LOG_TAG,"Startibng Date Dialog");
    setUpDateDialog();
    dialog.show();
  }

  public void setDate(String dateIn){

    Log.d(LOG_TAG, "Setting Date");
    int month = dialog.getDatePicker().getMonth()+1;
    int day = dialog.getDatePicker().getDayOfMonth();
    int year = dialog.getDatePicker().getYear();
    String date = day + "/" + month + "/" + year;

    Log.d(LOG_TAG,"Date will be set as: " + date);

    playedDate.setText(dateIn);
  }
}