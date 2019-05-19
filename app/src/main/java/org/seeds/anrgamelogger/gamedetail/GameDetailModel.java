package org.seeds.anrgamelogger.gamedetail;

import android.app.Activity;
import android.util.Log;

import org.seeds.anrgamelogger.addgame.AddGameActivity;
import org.seeds.anrgamelogger.database.DatabaseModel;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameFlat;

/**
 * Created by Tomas Seymour-Turner on 19/11/2017.
 */

public class GameDetailModel {

    private final String LOG_TAG = this.getClass().getName();

    private Activity activity;
    private DatabaseModel databaseModel;

    public GameDetailModel(Activity activity, DatabaseModel databaseModel){
        this.activity = activity;
        this.databaseModel = databaseModel;
    }

    public LoggedGameFlat getLoggedGame() {
        //return activity.getIntent().getParcelableExtra(GameDetailActivity.GAME_TRNASFER);

        String gameIDFromIntent = activity.getIntent().getStringExtra(GameDetailActivity.GAME_TRNASFER);
        Log.d(LOG_TAG, ".getLoggedGame() : Game Number passed is " + gameIDFromIntent);
        int gameID = Integer.valueOf(gameIDFromIntent);

        return databaseModel.getLoggedGame(gameID);
    }

    public void editGame(Integer gameNo) {
        AddGameActivity.start(activity.getApplicationContext(),String.valueOf(gameNo));
    }
}
