package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;

import org.seeds.anrgamelogger.R;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * Created by Tomas Seymour-Turner on 04/01/2018.
 */

public class AddGameOverview extends AddGameBaseView{

  //TODO: Change Name of SIDE as this is overciew
  //TODO: Add common type between ADDGame Views??
  private final String TITLE = "Overview";
  private Activity activity;

  @BindView(R.id.btn_save)
  public Button btn_save;

  public AddGameOverview(Activity activity){
    super(activity);
    this.activity = activity;
    inflate(getContext(), R.layout.view_addgame_overview, this);
  }

  public void onCreate(){}

  public String getTitle(){
    return TITLE;
  }

  public Observable<Object> save(){
    return RxView.clicks(btn_save);
  }
}
