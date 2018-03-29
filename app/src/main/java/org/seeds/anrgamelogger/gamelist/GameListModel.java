package org.seeds.anrgamelogger.gamelist;

import android.app.Activity;
import android.util.Log;

import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;

import io.reactivex.Observable;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import org.seeds.anrgamelogger.addgame.AddGameActivity;
import org.seeds.anrgamelogger.application.DatabaseModel;
import org.seeds.anrgamelogger.application.NetworkModel;
import org.seeds.anrgamelogger.gamedetail.GameDetailActivity;
import org.seeds.anrgamelogger.model.GameListManager;
import org.seeds.anrgamelogger.model.ImportDefaultData;
import org.seeds.anrgamelogger.model.LocalLoggedGame;
import retrofit2.Retrofit;


/**
 * Created by Tomas Seymour-Turner on 04/11/2017.
 */

public class GameListModel {

    private static final String LOG_TAG = GameListModel.class.getSimpleName();
    private final String GAME_LIST = "GAME_LIST";
    private Activity activity;
    private GameListManager gameListManager;
    private StorIOContentResolver storIOContentResolver;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private DatabaseModel databaseModel;
    private NetworkModel networkModel;

    public GameListModel(Activity a, DatabaseModel databaseModelIn, NetworkModel networkModelIn){
        activity = a;
        gameListManager = new GameListManager();
//        storIOContentResolver = storIOContentResolverIn;
//        okHttpClient = okHttpClientIn;
//        retrofit = retrofitIn;
        databaseModel = databaseModelIn;
        networkModel = networkModelIn;
    }

    public void databaseFirstTimeSetup(){

        Log.d(LOG_TAG,"Checking if first time data has been set up");

        if(databaseModel.isIdentitiesTableEmpty() ){

            Log.d(LOG_TAG,"Starting to load first time data");

            ImportDefaultData idd = new ImportDefaultData(databaseModel, networkModel);
            idd.populateIdentitiesTable();
            idd.setUpIdentityImages();
        }
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

    public void startAddGameActivity(int side){
        AddGameActivity.start(activity.getApplicationContext(),side);
    }
}
