package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;
import android.widget.FrameLayout;
import org.seeds.anrgamelogger.R;

/**
 * Created by Tomas Seymour-Turner on 04/01/2018.
 */

public class AddGameOverview extends FrameLayout {

  private Activity activity;

  public AddGameOverview(Activity activity){
    super(activity);
    this.activity = activity;
    inflate(getContext(), R.layout.view_addgame_overview, this);
  }

  public void onCreate(){}


}
