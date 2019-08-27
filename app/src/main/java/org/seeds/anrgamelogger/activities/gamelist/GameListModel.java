package org.seeds.anrgamelogger.activities.gamelist;

import android.app.Activity;
import android.util.Log;
import java.util.ArrayList;
import org.seeds.anrgamelogger.activities.gamehandler.GameHandlerActivity;
import org.seeds.anrgamelogger.application.PredefinedGame;
import org.seeds.anrgamelogger.application.SetupDatabaseDataModel;
import org.seeds.anrgamelogger.application.database.DatabaseModel;
import org.seeds.anrgamelogger.application.database.buisnessobjects.LoggedGameFlat;
import org.seeds.anrgamelogger.activities.gamedetail.GameDetailActivity;
import org.seeds.anrgamelogger.application.model.GameListManager;
import org.seeds.anrgamelogger.application.network.NetworkModel;


/**
 * Created by Tomas Seymour-Turner on 04/11/2017.
 */

public class GameListModel {

    private static final String LOG_TAG = GameListModel.class.getSimpleName();
    private final String GAME_LIST = "GAME_LIST";
    private Activity activity;
    private GameListManager gameListManager;
    private DatabaseModel databaseModel;
    private NetworkModel networkModel;
    //TODO: I don't like how idd is setup (also need to change variable name)
    private SetupDatabaseDataModel idd;


    public GameListModel(Activity activityIn, DatabaseModel databaseModelIn, NetworkModel networkModelIn){
        activity = activityIn;
        gameListManager = new GameListManager();
        databaseModel = databaseModelIn;
        networkModel = networkModelIn;
        idd = new SetupDatabaseDataModel(databaseModel, networkModel);
    }

    public void databaseFirstTimeSetup(){

        Log.i(LOG_TAG,"Checking if first time data has been set up");

        Log.i(LOG_TAG,"Database Model is: " + databaseModel);


        if(databaseModel.isIdentitiesTableEmpty()){
            Log.i(LOG_TAG,"Starting to load first time data");
            idd.populateIdentitiesTable();
            //idd.setUpTestPlayers();
        }
    }

    public void loadIdentityImages() {
      idd.insertIdentityImages();
    }


  public ArrayList<LoggedGameFlat> getGameList(int lengthLimit) {
    ArrayList ret = new ArrayList(databaseModel.getLoggedGameFlatList(lengthLimit));
        Log.d(LOG_TAG,".getGameList(int) | ret is " + ret.size());
      gameListManager.setLoggedGamesList(ret);
      return ret;
    }


    public void startGameDetailActivity(String selectedGame){
      //GameDetailActivity.start(activity.getApplicationContext(),gameListManager.getGame(selectedGame));
      Log.d(LOG_TAG,".startGameDetailActivity(String) : Game No: " + selectedGame);
      GameDetailActivity.start(activity.getApplicationContext(),selectedGame);
    }

    public void startAddGameActivity(String gameNo){
      GameHandlerActivity.start(activity.getApplicationContext(),gameNo, PredefinedGame.PARTIAL);
    }

    public void startAddGameActivity(){
        GameHandlerActivity.start(activity.getApplicationContext(),"", PredefinedGame.NEW);
    }

    public String getLastUsedGameNo() {
        return String.valueOf(databaseModel.getLastGameNoUsed());
    }

    public void purgeDatabse(){
        databaseModel.purgeDatabase();
    }
}
