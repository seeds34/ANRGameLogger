package org.seeds.anrgamelogger.gamedetail;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.gamedetail.Views.GameDetailFragment;
import org.seeds.anrgamelogger.gamedetail.Views.GameDetailNoteFragment;
import org.seeds.anrgamelogger.gamedetail.Views.GameDetailStatsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Tomas Seymour-Turner on 19/11/2017.
 */

public class GameDetailView extends FrameLayout {

    private GameDetailPagerAdapter mGameDetailPagerAdapter;
    private ViewPager gameDetailViewPager;
    private TabLayout tabLayout;

    public GameDetailView(Activity activity){
        super(activity);

        ButterKnife.setDebug(true);
        ButterKnife.bind(this);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
 //   setSupportActionBar(toolbar);
    // Create the adapter that will return a fragment for each of the three
    // primary sections of the activity.


    // Set up the ViewPager with the sections adapter.
    gameDetailViewPager = (ViewPager) findViewById(R.id.container);

    mGameDetailPagerAdapter = new GameDetailPagerAdapter(getSupportFragmentManager());

    Fragment gdf = new GameDetailFragment();
    Fragment gdnf = new GameDetailNoteFragment();
    Fragment gdsf = new GameDetailStatsFragment();




//    Intent intent = getIntent();
//
//        gdf.setArguments(intent.getExtras());
//
        mGameDetailPagerAdapter.addFragment(gdf,"Game Details");
        mGameDetailPagerAdapter.addFragment(gdnf,"Game Notes");
        mGameDetailPagerAdapter.addFragment(gdsf,"Game Stats");
//        mGameDetailPagerAdapter.addFragment(new GameDetailFragment(),"LocalLoggedGame Details");
//        mGameDetailPagerAdapter.addFragment(new GameDetailNoteFragment(),"LocalLoggedGame Notes");
//        mGameDetailPagerAdapter.addFragment(new GameDetailStatsFragment(),"LocalLoggedGame Stats");
        gameDetailViewPager.setAdapter(mGameDetailPagerAdapter);
//
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(gameDetailViewPager);


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



/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class GameDetailPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public GameDetailPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}


}
