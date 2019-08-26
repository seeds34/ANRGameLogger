package org.seeds.anrgamelogger.activities.gamehandler.views;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas Seymour-Turner on 04/01/2018.
 */

//Ref: http://www.ottodroid.net/?p=523

public class GameHandlerPagerAdapter extends PagerAdapter {

  private static final String LOG_TAG = GameHandlerPagerAdapter.class.getSimpleName();
  private final List<GameHandlerSubView> viewList = new ArrayList<>();

  @Override
  public Object instantiateItem(ViewGroup collection, int position) {
    View currentView = viewList.get(position).getView();
    collection.addView(currentView);
    return currentView;
  }

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
    Log.d(LOG_TAG,"Tab Title: " + viewList.get(position).getSide());
    return viewList.get(position).getSide();
  }

  public void addView(GameHandlerSubView view) {
    viewList.add(view);
  }

}
