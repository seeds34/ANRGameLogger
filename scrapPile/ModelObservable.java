package org.seeds.anrgamelogger.gamelist;

import android.content.ContentResolver;
import android.content.Context;

import org.seeds.anrgamelogger.model.LocalLoggedGame;
import org.seeds.anrgamelogger.model.LoggedGameList;

import java.util.ArrayList;

import rx.Observable;
//import io.reactivex.Observable;
//import io.reactivex.schedulers.*;
import rx.schedulers.Schedulers;

/**
 * Created by Tomas Seymour-Turner on 06/11/2017.
 */

public class ModelObservable {

    static Observable<ArrayList<LocalLoggedGame>> test(ContentResolver contentResolver, Context contextIn, int lengthLimit){
        LoggedGameList lgl = new LoggedGameList(contentResolver, contextIn);

        return Observable.<ArrayList<LocalLoggedGame>>create(subscriber -> {
            subscriber.onNext(lgl.getLoggedGameList(lengthLimit));
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io());
    }


}
