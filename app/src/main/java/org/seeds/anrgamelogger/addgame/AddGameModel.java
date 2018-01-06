package org.seeds.anrgamelogger.addgame;

import android.app.Activity;
import android.database.Cursor;
import com.pushtorefresh.storio2.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio2.contentresolver.impl.DefaultStorIOContentResolver;
import com.pushtorefresh.storio2.contentresolver.queries.Query;
import java.util.ArrayList;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract.IdentitiesColumns;


/**
 * Created by user on 08/12/2017.
 */

public class AddGameModel {

    private static final String LOG_TAG = AddGameModel.class.getSimpleName();
    private Activity activity;
    private StorIOContentResolver storIOContentResolver;

    public AddGameModel(Activity activityIn){
        activity = activityIn;
        storIOContentResolver = DefaultStorIOContentResolver.builder()
                .contentResolver(activity.getContentResolver())
                .build();

    }

public ArrayList<String> getListOfIdentitesNames(int side){

    ArrayList<String> ret = new ArrayList<String>();

    String sideName = activity.getString(side);

    Cursor queryResult = storIOContentResolver
        .get()
        .cursor()
        .withQuery(Query.builder()
            .uri(IdentitiesContract.URI_TABLE)
            .where(IdentitiesContract.IdentitiesColumns.IDENTITY_SIDE + " = '" + sideName + "'")
                .sortOrder(IdentitiesColumns.IDENTITY_FACTION + " asc")
            .build())
        .prepare()
        .executeAsBlocking();

    if (queryResult != null) {
        if (queryResult.moveToFirst()) {
            do {
                ret.add(queryResult.getString(queryResult.getColumnIndex(IdentitiesColumns.IDENTITY_NAME)));
            }while (queryResult.moveToNext());
            queryResult.close();
        }
    }
    return  ret;
}

    public ArrayList<byte[]> getListOfIdentitesImages(int side) {
        ArrayList<byte[]> ret = new ArrayList<>();

        String sideName = activity.getString(side);

        Cursor queryResult = storIOContentResolver
                .get()
                .cursor()
                .withQuery(Query.builder()
                        .uri(IdentitiesContract.URI_TABLE)
                        .where(IdentitiesContract.IdentitiesColumns.IDENTITY_SIDE + " = '" + sideName + "'")
                        .sortOrder(IdentitiesColumns.IDENTITY_FACTION + " asc")
                        .build())
                .prepare()
                .executeAsBlocking();

        if (queryResult != null) {
            if (queryResult.moveToFirst()) {
                do {
                    ret.add(queryResult.getBlob(queryResult.getColumnIndex(IdentitiesColumns.IMAGE_BIT_ARRAY)));
                }while (queryResult.moveToNext());
                queryResult.close();
            }
        }
        return  ret;

    }

    public int getSide(){
        return activity.getIntent().getIntExtra(AddGameActivity.SIDE, R.string.title_corp);
    }
}
