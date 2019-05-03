package org.seeds.anrgamelogger.gamedetail;

import android.util.Log;

/**
 * Created by Tomas Seymour-Turner on 19/11/2017.
 */

public class GameDetailPresenter {

    private static final String LOG_TAG = GameDetailPresenter.class.getSimpleName();

    private  GameDetailModel model;
    private GameDetailView view;

    public GameDetailPresenter(GameDetailView view, GameDetailModel model){
        this.view = view;
        this.model = model;
    }

    public void onCreate(){

        Log.i(LOG_TAG,".onCreate() : Setting up view data");
        view.setData(model.getLoggedGame());
        view.setUpPages();
        view.setTitle();
       // view.setLLGData(model.getLoggedGame());
    }

    public void onDestroy(){

    }
}
