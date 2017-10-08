package org.seeds.anrgamelogger.database.contracts;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Tomas Seymour-Turner on 02/05/2017.
 */

public class DecksContract {

    public interface DecksColumns{
        String DECK_NAME= "name";
        String DECK_VERSION= "version";
        String DECK_ARCHETYPE= "archetype";
        String DECK_IDENTITY = "identity";
        String NRDB_LINK= "nrdblink";
    }

    public static final String CONTENT_AUTHORITY = "org.seeds.anrgl.procider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    //Table identifyers
    public static final String PATH_DECKS = "decks";

    public  static final Uri URI_TABLE = Uri.parse(BASE_CONTENT_URI.toString()+"/"+PATH_DECKS);

    //Array of table identifiers
    public static final String[] TOP_LEVEL_PATHS = {
            PATH_DECKS
    };

    public static class Deck implements DecksColumns, BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendEncodedPath(PATH_DECKS).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd."+CONTENT_AUTHORITY+".decks";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."+CONTENT_AUTHORITY+".decks";

        public static Uri buildDecksUri(String deckId){
            return CONTENT_URI.buildUpon().appendEncodedPath(deckId).build();
        }

        public static String getDeckId(Uri uri){
            return uri.getPathSegments().get(1);
        }
    }
}
