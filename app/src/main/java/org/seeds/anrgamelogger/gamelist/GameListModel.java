package org.seeds.anrgamelogger.gamelist;

import android.content.ContentResolver;
import android.content.Context;

import org.seeds.anrgamelogger.gamedetail.GameDetailActivity;
import org.seeds.anrgamelogger.model.LocalLoggedGame;
import org.seeds.anrgamelogger.model.LoggedGameList;

import java.util.ArrayList;

import rx.Observable;
import rx.schedulers.Schedulers;


/**
 * Created by Tomas Seymour-Turner on 04/11/2017.
 */

public class GameListModel {

    private ContentResolver contentResolver;
    private Context context;
    LoggedGameList lgl;

    public GameListModel(ContentResolver cr, Context c){
        contentResolver = cr;
        context = c;
    }

    public Observable<ArrayList<LocalLoggedGame>> createList(int lengthLimit){

        lgl = new LoggedGameList(contentResolver, context);

        return Observable.<ArrayList<LocalLoggedGame>>create(subscriber -> {
            subscriber.onNext(lgl.getLoggedGameList(lengthLimit));
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io());


        //return ModelObservable.test(contentResolver, context, lengthLimit);
    }


    public void startGameDetailActivity(String selectedGame){
        GameDetailActivity.start(context, lgl.getGame(selectedGame));
    }

}
