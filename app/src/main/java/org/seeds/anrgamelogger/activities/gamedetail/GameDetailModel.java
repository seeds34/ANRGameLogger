package org.seeds.anrgamelogger.activities.gamedetail;

import android.app.Activity;
import android.util.Log;

import org.seeds.anrgamelogger.activities.gamehandler.GameHandlerActivity;
import org.seeds.anrgamelogger.application.PredefinedGame;
import org.seeds.anrgamelogger.application.database.DatabaseModel;
import org.seeds.anrgamelogger.application.database.buisnessobjects.LoggedGameFlat;

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
        String gameIDFromIntent = activity.getIntent().getStringExtra(GameDetailActivity.GAME_TRNASFER);
        Log.d(LOG_TAG, ".getLoggedGame() : Game Number passed is " + gameIDFromIntent);
        int gameID = Integer.valueOf(gameIDFromIntent);

        return databaseModel.getLoggedGame(gameID);
    }

    public void editGame(Integer gameNo) {
        GameHandlerActivity
            .start(activity.getApplicationContext(),String.valueOf(gameNo), PredefinedGame.EDIT);
    }

    public void deleteGame(Integer gameNo){
        databaseModel.deleteLoggedGame(gameNo);
        activity.finish();
    }
}
