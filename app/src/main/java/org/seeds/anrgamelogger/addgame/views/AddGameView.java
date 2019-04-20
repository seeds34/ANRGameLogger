package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.addgame.AddGamePagerAdapter;

/**
 * Created by user on 09/12/2017.
 */

public class AddGameView extends FrameLayout {

    private static final String LOG_TAG = AddGameView.class.getSimpleName();

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.addGameViewPager)
    public ViewPager addGameViewPager;

    @BindView(R.id.tabs)
    public TabLayout tabLayout;

    private AddGamePagerAdapter addGamePagerAdapter;

    public AddGameView(Activity activity){
        super(activity);
        inflate(getContext(), R.layout.view_addgame_base, this);
        ButterKnife.bind(this);

        addGamePagerAdapter =  new AddGamePagerAdapter();
        addGameViewPager.setOffscreenPageLimit(2);

        tabLayout.setupWithViewPager(addGameViewPager);
    }

    public void setTitle(String gameNo){
        String title = getResources().getString(R.string.title_add_new_game);
        title += " | No: " + gameNo;
        toolbar.setTitle(title);
    }

    public void setUpPagerViews(AddGameSubView viewIn) {
        Log.d(LOG_TAG,"Adding View: " + viewIn.getSide());
        addGamePagerAdapter.addView(viewIn);
    }

    public void startPageViewer(){
        addGameViewPager.setAdapter(addGamePagerAdapter);
    }

    public void displayGameLoggedMessagge(String gameNumber){
        Toast.makeText(this.getContext(),"Game "+ gameNumber +" has been Looged",Toast.LENGTH_SHORT).show();
    }

}
