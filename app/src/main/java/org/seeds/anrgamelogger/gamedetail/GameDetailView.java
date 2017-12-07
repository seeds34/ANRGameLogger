package org.seeds.anrgamelogger.gamedetail;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;

import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.gamedetail.views.*;
import org.seeds.anrgamelogger.model.LocalLoggedGame;

/**
 * Created by Tomas Seymour-Turner on 19/11/2017.
 */

public class GameDetailView extends FrameLayout {

    private String LOG_TAG = GameDetailView.class.getSimpleName();

    private GameDetailPagerAdapter gameDetailPagerAdapter;
    private ViewPager gameDetailViewPager;
    private TabLayout tabLayout;
    private LocalLoggedGame data;
    private GameDetailNoteView gameDetailNoteView;
    private GameDetailStatsView gameDetailStatView;
    private GameDetailOverview gameDetailView;
    private Activity activity;

    public GameDetailView(Activity activity){
        super(activity);

        this.activity = activity;

        inflate(getContext(), R.layout.view_gamedetail_base, this);

        //  ButterKnife.setDebug(true);
        //  ButterKnife.bind(this);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    // setSupportActionBar(toolbar);
    // Create the adapter that will return a fragment for each of the three
    // primary sections of the activity.

    // Set up the ViewPager with the sections adapter.
    gameDetailViewPager = (ViewPager) this.findViewById(R.id.container);

    Log.i(LOG_TAG, "gameDetailViewPager is " + gameDetailViewPager);


    }

//    Fragment gdf = new GameDetailFragment();
//    Fragment gdnf = new GameDetailNoteFragment();
//    Fragment gdsf = new GameDetailStatsFragment();
//
//
//
//
////    Intent intent = getIntent();
////
////        gdf.setArguments(intent.getExtras());
////
//        mGameDetailPagerAdapter.addFragment(gdf,"Game Details");
//        mGameDetailPagerAdapter.addFragment(gdnf,"Game Notes");
//        mGameDetailPagerAdapter.addFragment(gdsf,"Game Stats");
////        mGameDetailPagerAdapter.addFragment(new GameDetailFragment(),"LocalLoggedGame Details");
////        mGameDetailPagerAdapter.addFragment(new GameDetailNoteFragment(),"LocalLoggedGame Notes");
////        mGameDetailPagerAdapter.addFragment(new GameDetailStatsFragment(),"LocalLoggedGame Stats");
//        gameDetailViewPager.setAdapter(mGameDetailPagerAdapter);
////

public void setData(LocalLoggedGame gameIn){
        data = gameIn;
}

public void setUpPages(){

//    gameDetailView = new GameDetailOverview(activity,data);
//    gameDetailNoteView = new GameDetailNoteView(activity);
//      gameDetailStatView = new GameDetailStatsView(activity);



    //gameDetailPagerAdapter.addView(gameDetailView ,"Game Details");
    //gameDetailPagerAdapter.addView(gameDetailNoteView ,"Game Notes");

    //gameDetailPagerAdapter.addView(gameDetailStatView ,"Game Stats");

    tabLayout = (TabLayout) findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(gameDetailViewPager);

    gameDetailPagerAdapter = new GameDetailPagerAdapter(data, activity);

    gameDetailViewPager.setAdapter(gameDetailPagerAdapter);


}

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_game_detail_view, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

//
//
///**
// * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
// * one of the sections/tabs/pages.
// */
//public class GameDetailPagerAdapter extends FragmentPagerAdapter {
//    private final List<Fragment> mFragmentList = new ArrayList<>();
//    private final List<String> mFragmentTitleList = new ArrayList<>();
//
//    public GameDetailPagerAdapter(FragmentManager fm) {
//        super(fm);
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        return mFragmentList.get(position);
//    }
//
//    public void addFragment(Fragment fragment, String title) {
//        mFragmentList.add(fragment);
//        mFragmentTitleList.add(title);
//    }
//
//    @Override
//    public int getCount() {
//        // Show 3 total pages.
//        return mFragmentList.size();
//    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mFragmentTitleList.get(position);
//    }
//}


}
