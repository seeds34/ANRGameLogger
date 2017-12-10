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

    public Observable<ArrayList<String>> getListOfIdentitesNames(){

        return Observable.defer(new Func0<Observable<ArrayList<String>>>() {
            @Override
            public Observable<ArrayList<String>> call() {
                return Observable.fromCallable(()->genarateIdentiesListNames());
            }
        });}

    private ArrayList<String> genarateIdentiesListNames(){
        ArrayList<String> ret = new ArrayList<String>();
        Cursor queryResult = activity.getContentResolver().query(IdentitiesContract.URI_TABLE,null, null ,null, IdentitiesContract.IdentitiesColumns.IDENTITY_FACATION + " asc");
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

    public Observable<ArrayList<byte[]>> getListOfIdentitesImages(){

        return Observable.defer(new Func0<Observable<ArrayList<byte[]>>>() {
            @Override
            public Observable<ArrayList<byte[]>> call() {
                return Observable.fromCallable(()->genarateIdentiesListImages());
            }
        });}

    private ArrayList<byte[]> genarateIdentiesListImages(){
        ArrayList<byte[]> ret = new ArrayList<byte[]>();
        Cursor queryResult = activity.getContentResolver().query(IdentitiesContract.URI_TABLE,null, null ,null, IdentitiesContract.IdentitiesColumns.IDENTITY_FACATION + " asc");
        if (queryResult != null) {
            if (queryResult.moveToFirst()) {
                do {
                    ret.add(queryResult.getBlob(
                            queryResult.getColumnIndex(IdentitiesContract.IdentitiesColumns.IMAGE_BIT_ARRAY)));
                }while (queryResult.moveToNext());
                queryResult.close();
            }
        }
        return ret;
    }




}
