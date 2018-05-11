package org.seeds.anrgamelogger.addgame;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import java.util.ArrayList;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.addgame.subpresenters.OverviewSubPresenter;
import org.seeds.anrgamelogger.addgame.subpresenters.PlayerSubPresenter;
import org.seeds.anrgamelogger.addgame.views.AddGameOverviewView;
import org.seeds.anrgamelogger.addgame.views.AddGamePlayerView;
import org.seeds.anrgamelogger.model.IdentityList;

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

    public void setUpPagerViews(ArrayList<String> viewTitleList, ArrayList<String> playerList){
        addGamePagerAdapter.addView(new PlayerSubPresenter(activity, new AddGamePlayerView(activity), viewTitleList.get(0),playerList) );
        addGamePagerAdapter.addView(new PlayerSubPresenter(activity, new AddGamePlayerView(activity), viewTitleList.get(1), playerList) );
        addGamePagerAdapter.addView(new OverviewSubPresenter(activity, new AddGameOverviewView(activity)) );
    }

    public void startPageViewer(){
        addGameViewPager.setAdapter(addGamePagerAdapter);
    }

    public void setIDSelecters(IdentityList idList) {
        addGamePagerAdapter.setUpIdSpinnerAndImageView(idList);
    }

    public Observable<Object> save() {
        return addGamePagerAdapter.observeSave();
    }

    public void showMessage(String messageIn){
        Log.d(LOG_TAG, "Game Save Preesed");
        Toast.makeText(this.getContext(), messageIn, Toast.LENGTH_LONG).show();
    }

    public PlayerViewData getPlayerOne(){
        return addGamePagerAdapter.getPlayerOne();
    }

    public PlayerViewData getPlayerTwo(){
        return addGamePagerAdapter.getPlayerTwo();
    }

    public OverviewViewData getGameOverview(){
        return addGamePagerAdapter.getGameOverview();
    }

}
