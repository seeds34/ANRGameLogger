package org.seeds.anrgamelogger.addgame;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;

import org.seeds.anrgamelogger.addgame.subpresenters.PlayerSubPresenter;
import org.seeds.anrgamelogger.addgame.subpresenters.SubPresenter;
import org.seeds.anrgamelogger.addgame.views.AddGameBaseView;
import org.seeds.anrgamelogger.addgame.views.AddGameSubView;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGamePlayer;
import org.seeds.anrgamelogger.model.IdentityList;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameOverview;

/**
 * Created by Tomas Seymour-Turner on 04/01/2018.
 */

//Ref: http://www.ottodroid.net/?p=523

public class AddGamePagerAdapter extends PagerAdapter {

  private static final String LOG_TAG = AddGamePagerAdapter.class.getSimpleName();
  private final List<AddGameSubView> viewList = new ArrayList<>();
//
//  @Override
//  public Object instantiateItem(ViewGroup collection, int position) {
//    View currentView = viewList.get(position);
//    collection.addView(currentView);
//    return currentView;
//  }
  @Override
  public int getCount() {
    return viewList.size();
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return viewList.get(position).getTitle();
  }

  public void addView(AddGameSubView view) {
    viewList.add(view);
  }

}
