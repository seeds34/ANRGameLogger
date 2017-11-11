package org.seeds.anrgamelogger.database.datacreater;

import android.content.Context;

/**
 * Created by Tomas Seymour-Turner on 10/11/2017.
 */

public class SetUpTestData {

    private static PopulateIdentitiesData c;
    private static  InportedLoggedGame e;

    public static void setUpTestData(Context contextIn){
       // context = contextIn;
        c = new PopulateIdentitiesData(contextIn);
        e = new InportedLoggedGame(contextIn);

        if(c.isIdentitiesTableEmpty()){
            c.extractIdentitiesFromNRDB();
        }

        if(e.isLoggedGamesTableEmpty()) {
            e.inportLoggedGames();
        }

    }
}
