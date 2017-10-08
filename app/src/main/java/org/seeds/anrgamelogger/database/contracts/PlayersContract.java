package org.seeds.anrgamelogger.database.contracts;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Tomas Seymour-Turner on 03/05/2017.
 */

public class PlayersContract {

    public interface PlayersColumns{
        String PLAYER_NAME= "playername";
        String JNET_ID = "jnetid";
        String PLAYER_NICK_NAME = "nickname"; //Unique Name
    }

    public static final String CONTENT_AUTHORITY = "org.seeds.anrgl.procider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    //Table identifyers
    public static final String PATH_PLAYERS = "players";

    public  static final Uri URI_TABLE = Uri.parse(BASE_CONTENT_URI.toString()+"/"+PATH_PLAYERS);

    //Array of table identifiers
    public static final String[] TOP_LEVEL_PATHS = {
            PATH_PLAYERS
    };

    public static class Player implements PlayersColumns, BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendEncodedPath(PATH_PLAYERS).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd."+CONTENT_AUTHORITY+".players";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."+CONTENT_AUTHORITY+".players";

        public static Uri buildPlayersUri(String playerId){
            return CONTENT_URI.buildUpon().appendEncodedPath(playerId).build();
        }

        public static String getPlayerId(Uri uri){
            return uri.getPathSegments().get(1);
        }
    }

}
