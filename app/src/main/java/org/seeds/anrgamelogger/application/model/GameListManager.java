package org.seeds.anrgamelogger.application.model;

import android.util.Log;
import java.util.ArrayList;
import org.seeds.anrgamelogger.application.database.buisnessobjects.LoggedGameFlat;

/**
 * Created by Tomas Seymour-Turner on 23/05/2017.
 */

public class GameListManager {

    private static final String LOG_TAG = GameListManager.class.getSimpleName();
    private ArrayList<LoggedGameFlat> loggedGamesList;

    public GameListManager(){
        loggedGamesList = new ArrayList<>();
    }

    public void setLoggedGamesList(ArrayList<LoggedGameFlat> gamesListIn){
        loggedGamesList = gamesListIn;
    }

    public LoggedGameFlat getGame(String gameIdIn){
        LoggedGameFlat ret = null;

        for(LoggedGameFlat llg: loggedGamesList){
            if(llg.getGameID() == Integer.valueOf(gameIdIn)) {
                Log.d(LOG_TAG, ".getGame(String) : Getting game " + gameIdIn);
                ret = llg;
            }
        }
        return ret;
    }
}



