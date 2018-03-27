package org.seeds.anrgamelogger.gamedetail;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.gamedetail.views.GameDetailNoteView;
import org.seeds.anrgamelogger.gamedetail.views.GameDetailOverview;
import org.seeds.anrgamelogger.model.LocalLoggedGame;

/**
 * Created by user on 21/11/2017.
 */

public class GameDetailPagerAdapter extends PagerAdapter {


    private  List<View> viewList = new ArrayList<>();
    private  List<String> viewTitleList = new ArrayList<>();
    private  LocalLoggedGame game;
    private Context context;

    public GameDetailPagerAdapter(LocalLoggedGame data, Activity activity) {
        game = data;
        this.context = activity.getApplicationContext();

        viewList.add(new GameDetailOverview(activity,data));
        viewList.add(new GameDetailNoteView(activity));
        viewTitleList.add(activity.getString(R.string.title_overview));
        viewTitleList.add(activity.getString(R.string.title_gamenotes));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        DetailViewsEnum customPagerEnum = DetailViewsEnum.values()[position];
//        LayoutInflater inflater = LayoutInflater.from(context);
//        ViewGroup layout = (ViewGroup) inflater.inflate(customPagerEnum.getLayoutID(), container, false);
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
