package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.Observable;
import java.util.ArrayList;
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

  @BindView(R.id.et_dateChooser)
  public EditText playedDate;

  private final int viewNo = R.layout.view_addgame_overview;
  private ArrayAdapter<String> locationListAdapter;


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