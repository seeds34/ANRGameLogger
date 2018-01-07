package org.seeds.anrgamelogger.database.datacreater;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;

/**
 * Created by Tomas Seymour-Turner on 10/11/2017.
 */

public class SetUpTestData {

    private static PopulateIdentitiesData c;
    private static  InportedLoggedGame e;

    public static void setUpTestData(Activity activityIn){
       // context = contextIn;
        c = new PopulateIdentitiesData(activityIn);
        e = new InportedLoggedGame(activityIn);

        if(c.isIdentitiesTableEmpty()){
            c.extractIdentitiesFromNRDB();
        }

        if(e.isLoggedGamesTableEmpty()) {
           // e.inportLoggedGames();
        }

    }
}
