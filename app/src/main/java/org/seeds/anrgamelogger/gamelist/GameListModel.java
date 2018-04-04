package org.seeds.anrgamelogger.gamelist;

import android.app.Activity;
import android.util.Log;
import com.pushtorefresh.storio3.contentresolver.operations.put.PutResult;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import org.seeds.anrgamelogger.addgame.AddGameActivity;
import org.seeds.anrgamelogger.application.DatabaseModel;
import org.seeds.anrgamelogger.application.NetworkModel;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;
import org.seeds.anrgamelogger.gamedetail.GameDetailActivity;
import org.seeds.anrgamelogger.model.Card;
import org.seeds.anrgamelogger.model.CardImage;
import org.seeds.anrgamelogger.model.GameListManager;
import org.seeds.anrgamelogger.model.ImportDefaultData;
import org.seeds.anrgamelogger.model.LocalLoggedGame;


/**
 * Created by Tomas Seymour-Turner on 04/11/2017.
 */

public class GameListModel {

    private static final String LOG_TAG = GameListModel.class.getSimpleName();
    private final String GAME_LIST = "GAME_LIST";
    private Activity activity;
    private GameListManager gameListManager;
//    private StorIOContentResolver storIOContentResolver;
//    private OkHttpClient okHttpClient;
//    private Retrofit retrofit;
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

        Log.i(LOG_TAG,"Checking if first time data has been set up");

        Log.i(LOG_TAG,"Database Model is: " + databaseModel);

        if(databaseModel.isIdentitiesTableEmpty() ){
        //if(databaseModel.isTableEmpty(IdentitiesContract.URI_TABLE)){

            Log.i(LOG_TAG,"Starting to load first time data");

            ImportDefaultData idd = new ImportDefaultData(databaseModel, networkModel);
            idd.populateIdentitiesTable();
            //idd.setUpIdentityImages();
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

    public void loadIdentImages(){
        Log.d(LOG_TAG,"Add Images to IDs");
        List<Card> cardImageList = databaseModel.getIdentities();
        for(Card c : cardImageList){
            Log.d(LOG_TAG,"Getting image for " + c.getName());
            CardImage ci = networkModel.getCardImage(c.getPack_code(), c.getCode());
//            Log.d(LOG_TAG,"New Card image is: " + ci.toString());
            c.setImageByteArray(ci.getImageByteArray());
//            String b = (c.getImageByteArrayOutputStream().length <1)?"Null":"Not Null";
//                    Log.d(LOG_TAG,"Iamge for " + c.getName() + " is " + b);#
            c.setRotted("Y");
            PutResult pr = databaseModel.insertIdentity(c);
            Log.d(LOG_TAG, "Effected URI: " + pr.affectedUri());
            Log.d(LOG_TAG,"Loading image for " +c.getName() +" was " + pr.wasUpdated() + ". Number of Rows updated " + pr.numberOfRowsUpdated());
            //databaseModel.insertIdentitieImage(networkModel.getCardImage(c.getPack_code(), c.getCode()));
        }
    }

    public void startGameDetailActivity(String selectedGame){
        GameDetailActivity.start(activity.getApplicationContext(),gameListManager.getGame(selectedGame));
    }

    public void startAddGameActivity(int side){
        AddGameActivity.start(activity.getApplicationContext(),side);
    }
}
