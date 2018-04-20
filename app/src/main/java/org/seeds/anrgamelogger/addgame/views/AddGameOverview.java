package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;
import org.seeds.anrgamelogger.R;

/**
 * Created by Tomas Seymour-Turner on 04/01/2018.
 */

public class AddGameOverview extends AddGameBaseView{

  //TODO: Change Name of SIDE as this is overciew
  //TODO: Add common type between ADDGame Views??
  private final String TITLE = "Overview";
  private Activity activity;

  public AddGameOverview(Activity activity){
    super(activity);
    this.activity = activity;
    inflate(getContext(), R.layout.view_addgame_overview, this);
  }

  public void onCreate(){}

  public String getTitle(){
    return TITLE;
  }

}
