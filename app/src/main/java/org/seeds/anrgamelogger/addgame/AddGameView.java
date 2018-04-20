package org.seeds.anrgamelogger.addgame;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.model.IdentityList;

/**
 * Created by user on 09/12/2017.
 */

public class AddGameView extends FrameLayout {

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
    }

    public void setUpPages(ArrayList<String> titleList){
        tabLayout.setupWithViewPager(addGameViewPager);
        addGamePagerAdapter = new AddGamePagerAdapter(activity, titleList);
       // addGameViewPager.setAdapter(addGamePagerAdapter);
    }


    public void setImageSpinner(int side, LinkedHashMap<String, byte[]> imageListIn) {
        //addGamePagerAdapter.setImageSpinner(side,imageListIn);
    }

    public void startPA(){
        addGameViewPager.setAdapter(addGamePagerAdapter);

    }

    public void setIDNameSpinner(int side, ArrayList<String> idNameList) {
        //addGamePagerAdapter.setIDNameSpinner(side, idNameList);
    }

    public void setIDSelecters(IdentityList idList) {
        addGamePagerAdapter.setUpIdSpinnerAndImageView(idList);
    }


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
