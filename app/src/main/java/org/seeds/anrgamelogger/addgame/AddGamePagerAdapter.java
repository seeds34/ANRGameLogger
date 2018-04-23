package org.seeds.anrgamelogger.addgame;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.view.RxView;

import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;

import org.seeds.anrgamelogger.addgame.views.AddGameBaseView;
import org.seeds.anrgamelogger.addgame.views.AddGameOverview;
import org.seeds.anrgamelogger.addgame.views.AddGamePlayerView;
import org.seeds.anrgamelogger.model.IdentityList;

/**
 * Created by Tomas Seymour-Turner on 04/01/2018.
 */

public class AddGamePagerAdapter extends PagerAdapter {

  private static final String LOG_TAG = AddGamePagerAdapter.class.getSimpleName();

  private List<AddGameBaseView> viewList;
  private List<String> viewTitleList;
  private Activity activity;
  private Context context;

  public AddGamePagerAdapter(Activity activity, ArrayList<String> viewOrderList){
    this.activity = activity;
    this.context = activity.getApplicationContext();
    viewList = new ArrayList<>();
    viewTitleList = new ArrayList<>();
    setViewList(viewOrderList);
    setViewTitleList(viewOrderList);
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    ViewGroup layout = (ViewGroup)viewList.get(position);
    container.addView(layout);
    return layout;
  }



  private void setViewList(ArrayList<String> titleRid){
    viewList.add(new AddGamePlayerView(activity, titleRid.get(0)));
    viewList.add(new AddGamePlayerView(activity, titleRid.get(1)));
    viewList.add(new AddGameOverview(activity));
  }

  private void setViewTitleList(ArrayList<String> titleRid){
    for (String id: titleRid) {
      viewTitleList.add(id);
    }
  }



  @Override
  public int getCount() {
    return viewList.size();
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == ((View)object);
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return viewTitleList.get(position);
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
  }

    public void setUpIdSpinnerAndImageView(IdentityList idList) {
      for (AddGameBaseView i : viewList) {
        i.setIdApadters(idList.getOneSidedList(i.getTitle()));
      }
    }

    public Observable<Object> saveGame(){
      Log.d(LOG_TAG, "OverviewView is: " + viewList.get(2));
      AddGameOverview overviewView = (AddGameOverview)viewList.get(2);
      return overviewView.save();
      //return Observable.just(1);
    }

}
