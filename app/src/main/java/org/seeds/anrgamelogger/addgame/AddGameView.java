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
//ArrayList<String> viewTitleList
    public void startPageViewer(){
//        addGamePagerAdapter.addView(new PlayerSubPresenter(activity, findViewById(R.id.playerOneView), viewTitleList.get(0)) );
//        addGamePagerAdapter.addView(new PlayerSubPresenter(activity, findViewById(R.id.playerTwoView), viewTitleList.get(1)) );
//        addGamePagerAdapter.addView(new OverviewSubPresenter(activity, findViewById(R.id.overviewView)) );

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


//    public void setImageSpinner(int side, LinkedHashMap<String, byte[]> imageListIn) {
//        //addGamePagerAdapter.setImageSpinner(side,imageListIn);
//    }
//    public Observable<Object> saveGame(){
//        return addGamePagerAdapter.saveGame();
//    }
//public void setIDNameSpinner(int side, ArrayList<String> idNameList) {
//    //addGamePagerAdapter.setIDNameSpinner(side, idNameList);
//}
//    public class GetIdentityData extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            Log.v(LOG_TAG, "Started Proccessing Data");
//            Context context = getApplicationContext();
//
//            Log.v(LOG_TAG, "Finished Proccessing Data");
//
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//
//        }
//    }




}
