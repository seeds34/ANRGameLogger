package org.seeds.anrgamelogger.addgame;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import org.seeds.anrgamelogger.addgame.views.AddGameOverview;
import org.seeds.anrgamelogger.addgame.views.AddGamePlayerView;

/**
 * Created by Tomas Seymour-Turner on 04/01/2018.
 */

public class AddGamePagerAdapter extends PagerAdapter {

  private static final String LOG_TAG = AddGamePagerAdapter.class.getSimpleName();

  private List<View> viewList;
  private List<String> viewTitleList;
  private Activity activity;
  private Context context;

  public AddGamePagerAdapter(Activity activity, ArrayList<Integer> viewOrderList){
    this.activity = activity;
    this.context = activity.getApplicationContext();
    viewList = new ArrayList<>();
    viewTitleList = new ArrayList<>();

    setViewList();
    setViewTitleList(viewOrderList);
  }

  private void setViewList(){

    viewList.add((View)new AddGamePlayerView(activity));
    viewList.add((View)new AddGamePlayerView(activity));
    viewList.add((View)new AddGameOverview(activity));

//    for (AddGameEnum e : a) {
//      try {
//        Log.d(LOG_TAG, "Class is: " + e.getClassID().getName());
//        viewList.add((View)e.getClassID().getDeclaredConstructor(Activity.class).newInstance(activity));
//      } catch (InstantiationException e1) {
//        Log.e(LOG_TAG, "InstantiationException");
//      } catch (IllegalAccessException e1) {
//        Log.e(LOG_TAG, "IllegalAccessException");
//      } catch (NoSuchMethodException e1) {
//        Log.e(LOG_TAG, "NoSuchMethodException");
//      } catch (InvocationTargetException e1) {
//        Log.e(LOG_TAG, "InvocationTargetException");
//      }
//
//    }


  }

  private void setViewTitleList(ArrayList<Integer> titleRid){

    for (int id: titleRid) {
      viewTitleList.add(activity.getString(id));
    }
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    ViewGroup layout = (ViewGroup)viewList.get(position);
    container.addView(layout);
    return layout;

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
}
