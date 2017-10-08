package org.seeds.anrgamelogger.database.contracts;

import android.net.Uri;

/**
 * Created by Tomas Seymour-Turner on 03/05/2017.
 */

public class GameNotesContract {

    public interface GameNotesColumns{
        String GAME_ID = "gameid";
        String GAME_NOTE = "gamenote";
    }

    public static final String CONTENT_AUTHORITY = "org.seeds.anrgl.procider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    //Table identifyers
    public static final String PATH_GAME_NOTES = "gamenotes";

    public  static final Uri URI_TABLE = Uri.parse(BASE_CONTENT_URI.toString()+"/"+PATH_GAME_NOTES);

    //Array of table identifiers
    public static final String[] TOP_LEVEL_PATHS = {
            PATH_GAME_NOTES
    };
}
