package org.seeds.anrgamelogger.activities.gamedetail;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.application.database.buisnessobjects.LoggedGameFlat;

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

    private final PublishSubject<Object> editGamePubSubject = PublishSubject.create();
    private final PublishSubject<Object> deleteGamePubSubject = PublishSubject.create();


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


        ((AppCompatActivity)activity).setSupportActionBar(toolbar);
        ((AppCompatActivity)activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        //noinspection SimplifiableIfStatement
                        if (id == R.id.edit) {
                            editGame();
                            return true;
                        }else if(id == R.id.delete){

                            new AlertDialog.Builder(activity)
                                    .setTitle("Do you wish to delete this Log?")
                                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            deleteGame();
                                        }
                                    })
                                    .setNegativeButton("NO", null)
                                    .show();
                            return true;
                        }
                        return false;
                    }
                });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }});
    }

    public Observable<Object> editGame(){
        editGamePubSubject.onNext(1);
        return editGamePubSubject;
    }

    public void setTitle(){
        toolbar.setTitle("Game " + data.getGameID() + "     " + data.getPlayedDate());
    }

    public void setData(LoggedGameFlat gameIn){
            data = gameIn;
    }

    public void setUpPages(){
        tabLayout.setupWithViewPager(gameDetailViewPager);
        Log.i(LOG_TAG,".setUpPages : setting up GameDetailPageAdpator");
        gameDetailPagerAdapter = new GameDetailPagerAdapter(data, activity);
        gameDetailViewPager.setAdapter(gameDetailPagerAdapter);
    }

    public void refreshData(LoggedGameFlat lgf){
        data = lgf;
        gameDetailPagerAdapter = new GameDetailPagerAdapter(data, activity);

        gameDetailViewPager.setAdapter(gameDetailPagerAdapter);
    }


    public Observable<Object> deleteGame() {
        deleteGamePubSubject.onNext(1);
        return deleteGamePubSubject;
    }
}
