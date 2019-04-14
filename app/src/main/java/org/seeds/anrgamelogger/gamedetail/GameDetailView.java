package org.seeds.anrgamelogger.gamedetail;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.database.LoggedGameFlat;

/**
 * Created by Tomas Seymour-Turner on 19/11/2017.
 */

public class GameDetailView extends FrameLayout {

    private String LOG_TAG = GameDetailView.class.getSimpleName();

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.gameDetailViewPager)
    public ViewPager gameDetailViewPager;

    @BindView(R.id.tabs)
    public TabLayout tabLayout;

    private LoggedGameFlat data;
    private Activity activity;
    private GameDetailPagerAdapter gameDetailPagerAdapter;

    //TODO: Add ButterKnife Injection
    public GameDetailView(Activity activity){
        super(activity);

        this.activity = activity;

        inflate(getContext(), R.layout.view_gamedetail_base, this);

        ButterKnife.setDebug(true);
        ButterKnife.bind(this);


    // setSupportActionBar(toolbar);
    // Create the adapter that will return a fragment for each of the three
    // primary sections of the activity.
    // Set up the ViewPager with the sections adapter.
    //gameDetailViewPager = (ViewPager) this.findViewById(R.id.container);

    Log.i(LOG_TAG, "gameDetailViewPager is " + gameDetailViewPager);


    }

    public void setTitle(){
        toolbar.setTitle("Game " + data.getGameID() + "     " + data.getPlayedDate());
    }

    public void setData(LoggedGameFlat gameIn){
            data = gameIn;
    }

    public void setUpPages(){
        tabLayout.setupWithViewPager(gameDetailViewPager);
        gameDetailPagerAdapter = new GameDetailPagerAdapter(data, activity);
        gameDetailViewPager.setAdapter(gameDetailPagerAdapter);
    }



}
