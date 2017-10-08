package org.seeds.anrgamelogger.database.contracts;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Tomas Seymour-Turner on 11/06/2017.
 */

public class LoggedGamesFlatViewContract {

    public interface LoggedGamesFlatViewContractColumns{
        String GAME_ID = "gameid";
        String LOCATION_NAME = "locationname";
        String WIN_TYPE = "wintype";
        String PLAYED_DATE = "playeddate";
        String PLAYER_ONE_NAME = "playeronename";
        String PLAYER_ONE_SCORE = "playeronescore";
        String PLAYER_ONE_WIN_FLAG = "playeronewinflag";
        String PLAYER_ONE_DECK_NAME = "playerOneDeckName";
        String PLAYER_ONE_ID_NAME = "playeroneID";
        String PLAYER_ONE_NRDB_CODE = "playeronenrdbcode";
        String PLAYER_TWO_NAME = "playertwoname";
        String PLAYER_TWO_SCORE = "playertwoscore";
        String PLAYER_TWO_WIN_FLAG = "playertwowinflag";
        String PLAYER_TWO_DECK_NAME = "playertwodeckname";
        String PLAYER_TWO_ID_NAME = "playertwoid";
        String PLAYER_TWO_NRDB_CODE = "playertwonrdbcode";
        String PLAYER_ONE_ID_IMAGE = "playeroneidimage";
        String PLAYER_TWO_ID_IMAGE = "playertwoidimage";
    }

    public static final String CONTENT_AUTHORITY = "org.seeds.anrgl.procider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    //Table identifyers
    public static final String PATH_LOGGED_GAMES = "loggedgamesflatview";

    public  static final Uri URI_TABLE = Uri.parse(BASE_CONTENT_URI.toString()+"/"+PATH_LOGGED_GAMES);

    //Array of table identifiers
    public static final String[] TOP_LEVEL_PATHS = {
            PATH_LOGGED_GAMES
    };

    public static class LoggedGameFlatView implements LoggedGamesFlatViewContractColumns, BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendEncodedPath(PATH_LOGGED_GAMES).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd."+CONTENT_AUTHORITY+".loggedgamesflatview";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."+CONTENT_AUTHORITY+".loggedgamesflatview";

        public static Uri buildLoggedGameFlatViewUri(String loggedGameID){
            return CONTENT_URI.buildUpon().appendEncodedPath(loggedGameID).build();
        }

//        public static String getLoggedGameFlatViewId(Uri uri){
//            return uri.getPathSegments().get(1);
//        }
    }

}
