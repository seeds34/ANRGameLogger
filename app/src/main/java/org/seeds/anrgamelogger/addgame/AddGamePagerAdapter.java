package org.seeds.anrgamelogger.addgame;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.seeds.anrgamelogger.addgame.views.AddGameBaseView;
import org.seeds.anrgamelogger.addgame.views.AddGameOverview;
import org.seeds.anrgamelogger.addgame.views.AddGamePlayerView;

/**
 * Created by Tomas Seymour-Turner on 04/01/2018.
 */

public class AddGamePagerAdapter extends PagerAdapter {

  private static final String LOG_TAG = AddGamePagerAdapter.class.getSimpleName();

  private List<AddGameBaseView> viewList;
  private List<String> viewTitleList;
  private Activity activity;
  private Context context;

  public AddGamePagerAdapter(Activity activity, ArrayList<Integer> viewOrderList){
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

  private void setViewList(ArrayList<Integer> titleRid){

    viewList.add(new AddGamePlayerView(activity, titleRid.get(0)));
    viewList.add(new AddGamePlayerView(activity, titleRid.get(1)));
    viewList.add(new AddGameOverview(activity));

  }

  private void setViewTitleList(ArrayList<Integer> titleRid){
    for (int id: titleRid) {
      viewTitleList.add(activity.getString(id));
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

  public void setImageSpinner(int side, Map<String, byte[]> imageListIn){

    for (AddGameBaseView i : viewList) {
      if(i.getTitle() == side){
        i.setIdentitiesImageViewPager(imageListIn);
      }
    }

  }

  public void setIDNameSpinner(int side, ArrayList<String> imageListIn){

    for (AddGameBaseView i : viewList) {
      if(i.getTitle() == side){
        i.setSpiinerAdaptor(imageListIn);
      }
    }

  }
}


//    public View getItem(int position) {
//        return viewList.get(position);
//    }
//
//    public void addView(View view, String title) {
//        viewList.add(view);
//        notifyDataSetChanged();
//        viewTitleList.add(title);
//    }

