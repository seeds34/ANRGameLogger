package org.seeds.anrgamelogger.database.contracts;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Tomas Seymour-Turner on 03/05/2017.
 */

public class LoggedGameOverviewsContract {

    public interface LoggedGameOverviewsColumns{
        String GAME_ID = "gameid";
        String LOCATION_ID = "locationid";
        String WIN_TYPE = "wintype";
        String PLAYED_DATE = "playeddate";
    }

    public static final String CONTENT_AUTHORITY = "org.seeds.anrgl.procider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    //Table identifyers
    public static final String PATH_LOGGED_GAMES_OVERVIEW = "loggedgameoverviews";

    public  static final Uri URI_TABLE = Uri.parse(BASE_CONTENT_URI.toString()+"/"+PATH_LOGGED_GAMES_OVERVIEW);

    //Array of table identifiers
    public static final String[] TOP_LEVEL_PATHS = {
        PATH_LOGGED_GAMES_OVERVIEW
    };

    public static class LoggedGameOverview implements LoggedGameOverviewsColumns, BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendEncodedPath(PATH_LOGGED_GAMES_OVERVIEW).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd."+CONTENT_AUTHORITY+".loggedgameoverview";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."+CONTENT_AUTHORITY+".loggedgameoverview";

        public static Uri buildLoggedGameUri(String loggedGameID){
            return CONTENT_URI.buildUpon().appendEncodedPath(loggedGameID).build();
        }

        public static String getLoggedGameId(Uri uri){
            return uri.getPathSegments().get(1);
        }
    }

}
