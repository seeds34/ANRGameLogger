package org.seeds.anrgamelogger.gamelist;

import android.content.ContentResolver;

import org.seeds.anrgamelogger.model.LocalLoggedGame;

import java.util.ArrayList;

import rx.Observable;


/**
 * Created by Tomas Seymour-Turner on 04/11/2017.
 */

public class GameListModel {

    private ContentResolver contentResolver;

    public GameListModel(ContentResolver CR){
        contentResolver = CR;
    }

    public Observable<ArrayList<LocalLoggedGame>> createList(int lengthLimit){
        return ModelObservable.test(contentResolver, lengthLimit);
    }

}
