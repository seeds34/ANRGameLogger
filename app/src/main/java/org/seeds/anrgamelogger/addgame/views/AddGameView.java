package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import java.util.ArrayList;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.addgame.AddGamePagerAdapter;
import org.seeds.anrgamelogger.addgame.subpresenters.OverviewSubPresenter;
import org.seeds.anrgamelogger.addgame.subpresenters.PlayerSubPresenter;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGamePlayer;
import org.seeds.anrgamelogger.model.IdentityList;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameOverview;

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
    private Activity activity;

    public AddGameView(Activity activity){
        super(activity);
        this.activity = activity;
        inflate(getContext(), R.layout.view_addgame_base, this);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.title_add_new_game);

        addGamePagerAdapter =  new AddGamePagerAdapter();
        addGameViewPager.setOffscreenPageLimit(2);

        tabLayout.setupWithViewPager(addGameViewPager);
    }

    public void setUpPagerViews(AddGameSubView viewIn) {
        addGamePagerAdapter.addView(viewIn);
    }

    public void startPageViewer(){
        addGameViewPager.setAdapter(addGamePagerAdapter);
    }

    public void setIDSelecters(IdentityList idList) {
        //addGamePagerAdapter.setUpIdSpinnerAndImageView(idList);
    }

//    public Observable<Object> save() {
//        return addGamePagerAdapter.observeSave();
//    }

//    public void showMessage(String messageIn){
//        Log.d(LOG_TAG, "Game Save Preesed");
//        Toast.makeText(this.getContext(), messageIn, Toast.LENGTH_LONG).show();
//    }
//
//    public LoggedGamePlayer getPlayerOne(){
//        return addGamePagerAdapter.getPlayerOne();
//    }
//
//    public LoggedGamePlayer getPlayerTwo(){
//        return addGamePagerAdapter.getPlayerTwo();
//    }
//
//    public LoggedGameOverview getGameOverview(){
//        return addGamePagerAdapter.getGameOverview();
//    }

}
