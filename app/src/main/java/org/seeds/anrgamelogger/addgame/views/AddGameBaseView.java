package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;
import android.widget.FrameLayout;
import java.util.ArrayList;

/**
 * Created by Tomas Seymour-Turner on 06/01/2018.
 */

public abstract class AddGameBaseView extends FrameLayout {

  public AddGameBaseView(Activity activity){
    super(activity);
  }

  public abstract int getTitle();

  public void setIdentitiesImageViewPager(ArrayList<byte[]> imageListIn){};
  public void setSpiinerAdaptor(ArrayList<String> identitiesList){};
}