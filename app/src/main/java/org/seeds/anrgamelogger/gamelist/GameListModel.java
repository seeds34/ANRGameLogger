package org.seeds.anrgamelogger.gamelist;

import android.app.Activity;
import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio3.contentresolver.queries.Query;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import org.seeds.anrgamelogger.addgame.AddGameActivity;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;
import org.seeds.anrgamelogger.gamedetail.GameDetailActivity;
import org.seeds.anrgamelogger.model.GameListManager;
import org.seeds.anrgamelogger.model.Identity;
import org.seeds.anrgamelogger.model.ImportDefaultData;
import org.seeds.anrgamelogger.model.LocalLoggedGame;
import retrofit2.Retrofit;


/**
 * Created by Tomas Seymour-Turner on 04/11/2017.
 */

public class GameListModel {

    private final String GAME_LIST = "GAME_LIST";
    private Activity activity;
    private GameListManager gameListManager;
    private StorIOContentResolver storIOContentResolver;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;


    public GameListModel(Activity a, StorIOContentResolver storIOContentResolverIn, OkHttpClient okHttpClientIn, Retrofit retrofitIn){
        activity = a;
        gameListManager = new GameListManager();
        storIOContentResolver = storIOContentResolverIn;
        okHttpClient = okHttpClientIn;
        retrofit = retrofitIn;
    }

    public void databaseFirstTimeSetup(){

        //Check if tables are set up

        List<Identity> ids = storIOContentResolver
            .get()
            .listOfObjects(Identity.class)
            .withQuery(Query.builder()
                .uri(IdentitiesContract.URI_TABLE)
                .build())
            .prepare()
            .executeAsBlocking();

        if(ids.size() <= 0 ){
            ImportDefaultData idd = new ImportDefaultData(activity, storIOContentResolver, okHttpClient, retrofit);
            idd.populateIdentitiesTable();
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
