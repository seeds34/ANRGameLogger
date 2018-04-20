package org.seeds.anrgamelogger.gamelist;

import android.app.Activity;
import android.util.Log;
import io.reactivex.Observable;
import java.util.ArrayList;
import org.seeds.anrgamelogger.addgame.AddGameActivity;
import org.seeds.anrgamelogger.application.DatabaseModel;
import org.seeds.anrgamelogger.application.NetworkModel;
import org.seeds.anrgamelogger.gamedetail.GameDetailActivity;
import org.seeds.anrgamelogger.model.GameListManager;
import org.seeds.anrgamelogger.model.LocalLoggedGame;
import org.seeds.anrgamelogger.model.SetupDatabaseDataModel;


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

        if(databaseModel.isIdentitiesTableEmpty() ){
            Log.i(LOG_TAG,"Starting to load first time data");
            idd.populateIdentitiesTable();
        }
    }

    public void loadIdentityImages() {
      idd.insertIdentityImages();
    }


  public Observable<ArrayList<LocalLoggedGame>> getGameList(int lengthLimit) {
//       return Observable.defer(new Function<Observable<ArrayList<LocalLoggedGame>>>() {
//         @Override
//         public Object apply(Object o) throws Exception {
//           return null;
//         }
//
//         @Override
//            public Observable<ArrayList<LocalLoggedGame>> call() {
//                return Observable.fromCallable(()-> gameListManager.getGameList()).doOnNext(ret -> gameListManager.genarateGameList(lengthLimit, activity.getContentResolver()));
//            }
//        });
//TODO: Rewite Methord
      return null;
    }


    public void startGameDetailActivity(String selectedGame){
        GameDetailActivity.start(activity.getApplicationContext(),gameListManager.getGame(selectedGame));
    }

    public void startAddGameActivity(String side){
        AddGameActivity.start(activity.getApplicationContext(),side);
    }
}
