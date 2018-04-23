package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;

import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.addgame.AddGamePagerAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * Created by Tomas Seymour-Turner on 04/01/2018.
 */

public class AddGameOverview extends AddGameBaseView{

  private static final String LOG_TAG = AddGameOverview.class.getSimpleName();

  //TODO: Change Name of SIDE as this is overciew
  //TODO: Add common type between ADDGame Views??
  private final String TITLE = "Overview";
  private Activity activity;

  @BindView(R.id.btn_save)
  public Button btn_save;

  private final int viewNo = R.layout.view_addgame_overview;

  public AddGameOverview(Activity activity){
    super(activity);
    this.activity = activity;
    inflate(getContext(), viewNo, this);
  }

  public int getViewNo(){
    return viewNo;
  }

  public void onCreate(){}

  public String getTitle(){
    return TITLE;
  }

  @Override
  @OnClick(R.id.btn_save)
  public void savePress(){
    Log.d(LOG_TAG, "Save Button Pressed");
    Toast.makeText(this.getContext(), "Saved", Toast.LENGTH_LONG).show();
  }

  public Observable<Object> save(){
    return RxView.clicks(btn_save);
  }
}