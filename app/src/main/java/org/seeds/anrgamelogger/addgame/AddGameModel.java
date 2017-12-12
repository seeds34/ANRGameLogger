package org.seeds.anrgamelogger.addgame;

import android.app.Activity;
import android.database.Cursor;
import android.util.Log;

import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;
import org.seeds.anrgamelogger.database.contracts.LoggedGamesFlatViewContract;
import org.seeds.anrgamelogger.database.datacreater.InportedLoggedGame;
import org.seeds.anrgamelogger.model.LocalLoggedGame;

import java.util.ArrayList;
import rx.Observable;
import rx.functions.Func0;

/**
 * Created by user on 08/12/2017.
 */

public class AddGameModel {

    private static final String LOG_TAG = AddGameModel.class.getSimpleName();
    private Activity activity;

    public AddGameModel(Activity activityIn){
        activity = activityIn;
    }

    public Observable<ArrayList<String>> getListOfIdentitesNames(String side){

        return Observable.defer(new Func0<Observable<ArrayList<String>>>() {
            @Override
            public Observable<ArrayList<String>> call() {
                return Observable.fromCallable(()->genarateIdentiesListNames(side));
            }
        });}

    private ArrayList<String> genarateIdentiesListNames(String side){
        ArrayList<String> ret = new ArrayList<String>();
        Cursor queryResult = activity.getContentResolver().query(IdentitiesContract.URI_TABLE,null, IdentitiesContract.IdentitiesColumns.IDENTITY_SIDE + " = '" + side + "'" ,null, IdentitiesContract.IdentitiesColumns.IDENTITY_FACATION + " asc");
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

    public Observable<ArrayList<byte[]>> getListOfIdentitesImages(String side){

        return Observable.defer(new Func0<Observable<ArrayList<byte[]>>>() {
            @Override
            public Observable<ArrayList<byte[]>> call() {
                return Observable.fromCallable(()->genarateIdentiesListImages(side));
            }
        });}

    private ArrayList<byte[]> genarateIdentiesListImages(String side){
        ArrayList<byte[]> ret = new ArrayList<byte[]>();

        Log.d(LOG_TAG, IdentitiesContract.IdentitiesColumns.IDENTITY_SIDE + " = " + side);

        Cursor queryResult = activity.getContentResolver().query(IdentitiesContract.URI_TABLE,null, IdentitiesContract.IdentitiesColumns.IDENTITY_SIDE + " = '" + side + "'", null , IdentitiesContract.IdentitiesColumns.IDENTITY_FACATION + " asc");

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
