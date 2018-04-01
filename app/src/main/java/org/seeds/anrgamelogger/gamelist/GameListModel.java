package org.seeds.anrgamelogger.gamelist;

import android.app.Activity;
import android.util.Log;
import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio3.contentresolver.operations.put.PutResult;

import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import org.seeds.anrgamelogger.addgame.AddGameActivity;
import org.seeds.anrgamelogger.application.DatabaseModel;
import org.seeds.anrgamelogger.application.NetworkModel;
import org.seeds.anrgamelogger.gamedetail.GameDetailActivity;
import org.seeds.anrgamelogger.model.Card;
import org.seeds.anrgamelogger.model.CardImage;
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


        Observable.fromIterable(cardImageList)
//                .subscribeOn(Schedulers.io())
                .subscribe(
                     i ->
                        networkModel.getNRDBCardImage(i.getSide_code())
                             .subscribe(a -> i.setImageByteArray(a))
                );


        databaseModel.insertIdentities(cardImageList);



//        for (Card i : cardImageList) {
//            Log.d(LOG_TAG, "Reading in image");
//
//            networkModel.getNRDBCardImage(i.getCode())
//                .subscribeOn(Schedulers.io())
//                //.subscribe(iba -> insertIDImage(iba, i));
//                //.subscribe(iba -> new CardImage(i.getCode(), iba))
//                 .subscribe(iba -> i.setImageByteArray(iba));
//
//            ///i.setImageByteArray(networkModel.getNRDBCardImage(i.getCode()));
//
//            //Log.d(LOG_TAG, (i.getImageByteArrayOutputStream() == null)?"[IDD]Image Array is null":"[IDD]Image Array is not null");
//        }
//        databaseModel.insertIdentities(cardImageList);
    }

    private void insertIDImage(byte[] iba, Card i){

        PutResult p = databaseModel.insertIdentitieImage(new CardImage(i.getCode(), iba));

        Log.d(LOG_TAG, "Was image updated: " + p.wasUpdated());
    }

    public void startGameDetailActivity(String selectedGame){
        GameDetailActivity.start(activity.getApplicationContext(),gameListManager.getGame(selectedGame));
    }

    public void startAddGameActivity(int side){
        AddGameActivity.start(activity.getApplicationContext(),side);
    }
}
