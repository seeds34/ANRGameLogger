package org.seeds.anrgamelogger.gamelist;

import android.app.Activity;

import org.seeds.anrgamelogger.addgame.AddGameActivity;
import org.seeds.anrgamelogger.addgame.views.AddGameEnum;
import org.seeds.anrgamelogger.database.datacreater.SetUpTestData;
import org.seeds.anrgamelogger.gamedetail.GameDetailActivity;
import org.seeds.anrgamelogger.model.GameListManager;
import org.seeds.anrgamelogger.model.LocalLoggedGame;

import java.util.ArrayList;

import rx.Observable;
import rx.functions.Func0;


/**
 * Created by Tomas Seymour-Turner on 04/11/2017.
 */

public class GameListModel {

    private final String GAME_LIST = "GAME_LIST";
    private Activity activity;
    private GameListManager gameListManager;

    public GameListModel(Activity a){
        activity = a;
        gameListManager = new GameListManager();
    }

    public void databaseFirstTimeSetup(){
        SetUpTestData.setUpTestData(activity);
    }

    public Observable<ArrayList<LocalLoggedGame>> getGameList(int lengthLimit){
       return Observable.defer(new Func0<Observable<ArrayList<LocalLoggedGame>>>() {
            @Override
            public Observable<ArrayList<LocalLoggedGame>> call() {
                return Observable.fromCallable(()-> gameListManager.getGameList()).doOnNext(ret -> gameListManager.genarateGameList(lengthLimit, activity.getContentResolver()));
            }
        });}

    public void startGameDetailActivity(String selectedGame){
        GameDetailActivity.start(activity.getApplicationContext(),gameListManager.getGame(selectedGame));
    }

    public void startAddGameActivity(int side){
        AddGameActivity.start(activity.getApplicationContext(),side);
    }
}
