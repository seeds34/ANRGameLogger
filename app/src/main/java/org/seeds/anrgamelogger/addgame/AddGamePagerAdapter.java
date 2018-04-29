package org.seeds.anrgamelogger.addgame;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import org.seeds.anrgamelogger.addgame.subpresenters.PlayerSubPresenter;
import org.seeds.anrgamelogger.addgame.subpresenters.SubPresenter;
import org.seeds.anrgamelogger.model.IdentityList;

/**
 * Created by Tomas Seymour-Turner on 04/01/2018.
 */

//Ref: http://www.ottodroid.net/?p=523

public class AddGamePagerAdapter extends PagerAdapter {

  private static final String LOG_TAG = AddGamePagerAdapter.class.getSimpleName();
  private final List<SubPresenter> presenterList = new ArrayList<>();

  @Override
  public Object instantiateItem(ViewGroup collection, int position) {
    View currentView = presenterList.get(position).getView();
    collection.addView(currentView);
    return currentView;
  }
  @Override
  public int getCount() {
    return presenterList.size();
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return presenterList.get(position).getTitle();
  }

//  @Override
//  public void destroyItem(ViewGroup container, int position, Object object) {
//    container.removeView(presenterList.get(position).getView());
//    container.removeView(object);
//  }

  public void addView(SubPresenter presenter) {
    presenterList.add(presenter);
  }

  public void setUpIdSpinnerAndImageView(IdentityList idList) {

    for(SubPresenter sp : presenterList){
        if(sp instanceof PlayerSubPresenter) {
          sp.setUpIdentitySpiner(idList.getOneSidedList(sp.getTitle()));
        }
    }
//
//
//      for (AddGameBaseView i : viewList) {
//        i.setIdApadters(idList.getOneSidedList(i.getTitle()));
//      }
    }

}
