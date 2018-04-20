package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;
import android.widget.FrameLayout;

import org.seeds.anrgamelogger.model.IdentityList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Tomas Seymour-Turner on 06/01/2018.
 */

public abstract class AddGameBaseView extends FrameLayout {

  public AddGameBaseView(Activity activity){
    super(activity);
  }

  public abstract String getTitle();

  public void setIdentitiesImageViewPager(LinkedHashMap<String, byte[]> imageListIn){};
  public void setSpiinerAdaptor(ArrayList<String> identitiesList){};

  public void setIdApadters(IdentityList idList){};
}
