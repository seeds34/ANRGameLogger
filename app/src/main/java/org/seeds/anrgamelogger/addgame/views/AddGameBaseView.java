package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;
import android.widget.FrameLayout;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.seeds.anrgamelogger.model.IdentityList;

/**
 * Created by Tomas Seymour-Turner on 06/01/2018.
 */

public abstract class AddGameBaseView extends FrameLayout {

  public AddGameBaseView(Activity activity){
    super(activity);
  }

//  public abstract String getTitle();

  public void setIdentitiesImageViewPager(LinkedHashMap<String, byte[]> imageListIn){};

  public void setIdApadters(IdentityList idList){};

  public abstract Observable<Object> save();

  public void setUpNameAutoComplete(ArrayList<String> playerList) {}
  public void setUpDeckNameAutoComplete(ArrayList<String> playerList) {}
  public void setUpLocationAutoComplete(ArrayList<String> locationList) {}

}
