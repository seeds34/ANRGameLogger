package org.seeds.anrgamelogger.addgame;

import android.app.Activity;
import android.database.Cursor;

import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;
import org.seeds.anrgamelogger.database.contracts.LoggedGamesFlatViewContract;
import org.seeds.anrgamelogger.model.LocalLoggedGame;

import java.util.ArrayList;
import rx.Observable;
import rx.functions.Func0;

/**
 * Created by user on 08/12/2017.
 */

public class AddGameModel {

    private Activity activity;

    public AddGameModel(Activity activityIn){
        activity = activityIn;
    }

    public Observable<ArrayList<String>> getListOfIdentites(){

        return Observable.defer(new Func0<Observable<ArrayList<String>>>() {
            @Override
            public Observable<ArrayList<String>> call() {
                return Observable.fromCallable(()->genarateIdentiesList());
            }
        });}

    private ArrayList<String> genarateIdentiesList(){

        ArrayList<String> ret = new ArrayList<String>();

        Cursor queryResult = activity.getContentResolver().query(IdentitiesContract.URI_TABLE,null, null ,null, null);

        if (queryResult != null) {
            if (queryResult.moveToFirst()) {
                do {
                    ret.add(queryResult.getString(
                            queryResult.getColumnIndex(IdentitiesContract.IdentitiesColumns.IDENTITY_NAME)));
                }while (queryResult.moveToNext());
                queryResult.close();
            }
        }

        return ret;
    }
}
