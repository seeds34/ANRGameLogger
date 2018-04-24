package org.seeds.anrgamelogger.addgame;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import org.seeds.anrgamelogger.addgame.SubPresenters.SubPresenter;

/**
 * Created by Tomas Seymour-Turner on 04/01/2018.
 */

public class AddGamePagerAdapter extends PagerAdapter {

  private static final String LOG_TAG = AddGamePagerAdapter.class.getSimpleName();
  private final List<SubPresenter> presenterList = new ArrayList<>();

  @Override
  public Object instantiateItem(ViewGroup collection, int position) {
    View currentView = presenterList.get(position).getView();
    return currentView;
  }
  @Override
  public int getCount() {
    return presenterList.size();
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == ((View)object);
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    super.destroyItem(container, position, object);
  }

  public void addView(SubPresenter presenter) {
    presenterList.add(presenter);
  }

//  public void setUpIdSpinnerAndImageView(IdentityList idList) {
//      for (AddGameBaseView i : viewList) {
//        i.setIdApadters(idList.getOneSidedList(i.getTitle()));
//      }
//    }

}
