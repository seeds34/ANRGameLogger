package org.seeds.anrgamelogger.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import org.seeds.anrgamelogger.database.contracts.DecksContract;
import org.seeds.anrgamelogger.database.contracts.GameNotesContract;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;
import org.seeds.anrgamelogger.database.contracts.LocationsContract;
import org.seeds.anrgamelogger.database.contracts.LoggedGamesContract;
import org.seeds.anrgamelogger.database.contracts.LoggedGamesFlatViewContract;
import org.seeds.anrgamelogger.database.contracts.PlayersContract;

/**
 * Created by Tomas Seymour-Turner on 26/04/2017.
 */

public class GameLoggerDatabase extends SQLiteOpenHelper {

    private static final String LOG_TAG = GameLoggerDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "ANRGAMELOGGER.db";
    private static final int DATABASE_VERSION = 2;
    private final Context conext;

    public interface Tables{
        String LOGGED_GAMES = "loggedgames";
        String DECKS = "decks";
        String IDENTITIES = "identities";
        String LOCATIONS = "locations";
        String GAME_NOTES ="gamenotes";
        String PLAYERS = "players";
//        String WIN_TYPE = "wintype";
    }

    public interface Views{
        String LOGGED_GAMES_FLAT_VIEW = "loggedgamesflatview";
    }

    private final String IDENTITIES_DDL = "CREATE TABLE IF NOT EXISTS " + Tables.IDENTITIES + " ( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + IdentitiesContract.IdentitiesColumns.IDENTITY_NAME + " TEXT NOT NULL COLLATE NOCASE ,"
            + IdentitiesContract.IdentitiesColumns.IDENTITY_FACTION + " TEXT NOT NULL COLLATE NOCASE,"
            + IdentitiesContract.IdentitiesColumns.IDENTITY_SIDE + " TEXT NOT NULL COLLATE NOCASE, "
            + IdentitiesContract.IdentitiesColumns.ROTATED_FLAG + " TEXT NOT NULL, "
            + IdentitiesContract.IdentitiesColumns.NRDB_CODE + " TEXT NOT NULL, "
            + IdentitiesContract.IdentitiesColumns.IMAGE_BIT_ARRAY + " BLOB, "
            + "CONSTRAINT identitity_name_unique UNIQUE("+ IdentitiesContract.IdentitiesColumns.IDENTITY_NAME +"));";

    private final String LOCATIONS_DDL = "CREATE TABLE IF NOT EXISTS " + Tables.LOCATIONS + " ( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + LocationsContract.LocationsColumns.LOCATION_NAME + " TEXT NOT NULL COLLATE NOCASE, "
            + "CONSTRAINT location_name_unique UNIQUE("+ LocationsContract.LocationsColumns.LOCATION_NAME +"));"; //Hmm not sure about this being Unique

    private final String PLAYERS_DDL = "CREATE TABLE IF NOT EXISTS " + Tables.PLAYERS + " ( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PlayersContract.PlayersColumns.PLAYER_NAME + " TEXT NOT NULL COLLATE NOCASE,"
            + PlayersContract.PlayersColumns.JNET_ID + " TEXT, "
            + PlayersContract.PlayersColumns.PLAYER_NICK_NAME + " TEXT,"
            + "CONSTRAINT player_nickname_unique UNIQUE("+ PlayersContract.PlayersColumns.PLAYER_NICK_NAME +"));";

    private final String GAME_NOTES_DDL = "CREATE TABLE IF NOT EXISTS " + Tables.GAME_NOTES + " ( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + GameNotesContract.GameNotesColumns.GAME_NOTE + " TEXT,"
            + GameNotesContract.GameNotesColumns.GAME_ID + " INTEGER NOT NULL);"
            + "FOREIGN KEY("+GameNotesContract.GameNotesColumns.GAME_ID +") REFERENCES "+ Tables.LOGGED_GAMES +"("+BaseColumns._ID+"));";

    private final String DECKS_DDL = "CREATE TABLE IF NOT EXISTS " + Tables.DECKS + " ( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DecksContract.DecksColumns.DECK_NAME + " TEXT NOT NULL COLLATE NOCASE,"
            + DecksContract.DecksColumns.DECK_VERSION + " TEXT,"
            + DecksContract.DecksColumns.DECK_ARCHETYPE + " TEXT COLLATE NOCASE,"
            + DecksContract.DecksColumns.DECK_IDENTITY + " INTEGER NOT NULL,"
            + DecksContract.DecksColumns.NRDB_LINK + " TEXT,"
            + "FOREIGN KEY("+DecksContract.DecksColumns.DECK_IDENTITY +") REFERENCES "+ Tables.IDENTITIES +"("+BaseColumns._ID+"));";

    private final String LOGGED_GAMES_DDL = "CREATE TABLE IF NOT EXISTS " + Tables.LOGGED_GAMES + " ( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + LoggedGamesContract.LoggedGamesColumns.GAME_ID + " INTEGER NOT NULL,"
            + LoggedGamesContract.LoggedGamesColumns.PLAYER_ID + " INTEGER NOT NULL,"
            + LoggedGamesContract.LoggedGamesColumns.DECK_ID + " INTEGER NOT NULL,"
            + LoggedGamesContract.LoggedGamesColumns.LOCATION_ID + " INTEGER NOT NULL,"
            + LoggedGamesContract.LoggedGamesColumns.WIN_FLAG + " TEXT NOT NULL,"
            + LoggedGamesContract.LoggedGamesColumns.SCORE + " INTEGER NOT NULL,"
            + LoggedGamesContract.LoggedGamesColumns.WIN_TYPE + " TEXT NOT NULL,"
            + LoggedGamesContract.LoggedGamesColumns.PLAYED_DATE + " TEXT NOT NULL,"
            + LoggedGamesContract.LoggedGamesColumns.PLAYER_SIDE + " TEXT ,"
            + "FOREIGN KEY("+LoggedGamesContract.LoggedGamesColumns.PLAYER_ID +") REFERENCES "+ Tables.PLAYERS +"("+BaseColumns._ID+")"
            + "FOREIGN KEY("+LoggedGamesContract.LoggedGamesColumns.DECK_ID +") REFERENCES "+ Tables.DECKS +"("+BaseColumns._ID+")"
            + "FOREIGN KEY("+LoggedGamesContract.LoggedGamesColumns.LOCATION_ID +") REFERENCES "+ Tables.LOCATIONS +"("+BaseColumns._ID+")"
            + "CONSTRAINT game_player_unique UNIQUE("+LoggedGamesContract.LoggedGamesColumns.GAME_ID + "," + LoggedGamesContract.LoggedGamesColumns.PLAYER_ID   +"));";

    private final String VIEW_SUB_SELECT_PLAYER_ONE = "SELECT "
            + "L." + BaseColumns._ID + "  porowid, "
            + "L." + LoggedGamesContract.LoggedGamesColumns.GAME_ID + " " + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.GAME_ID + ", "
            + "D." + DecksContract.DecksColumns.DECK_NAME + " " + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_DECK_NAME + ", "
            + "I." + IdentitiesContract.IdentitiesColumns.IDENTITY_NAME + " " + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_ID_NAME + ", "
            + "I." + IdentitiesContract.IdentitiesColumns.NRDB_CODE + " " + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_NRDB_CODE + ", "
            + "I." + IdentitiesContract.IdentitiesColumns.IMAGE_BIT_ARRAY + " " + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_ID_IMAGE + ", "
            + "P." + PlayersContract.PlayersColumns.PLAYER_NAME + " " + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_NAME + ", "
            + "L." + LoggedGamesContract.LoggedGamesColumns.SCORE + " " + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_SCORE + ", "
            + "L." + LoggedGamesContract.LoggedGamesColumns.WIN_FLAG + " " + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_WIN_FLAG
            + " FROM " + Tables.LOGGED_GAMES + " L "
            + " INNER JOIN " + Tables.PLAYERS + " P ON P." + BaseColumns._ID + " = L." + LoggedGamesContract.LoggedGamesColumns.PLAYER_ID
            + " INNER JOIN " + Tables.DECKS + " D ON D." + BaseColumns._ID + " = L." + LoggedGamesContract.LoggedGamesColumns.DECK_ID
            + " INNER JOIN " + Tables.IDENTITIES + " I ON I." + BaseColumns._ID + " = D." + DecksContract.DecksColumns.DECK_IDENTITY
            + " WHERE L." + LoggedGamesContract.LoggedGamesColumns.PLAYER_SIDE + " = 1";

    private final String VIEW_SUB_SELECT_PLAYER_TWO = "SELECT "
            + "L." + BaseColumns._ID + "  porowid, "
            + "L." + LoggedGamesContract.LoggedGamesColumns.GAME_ID + " " + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.GAME_ID + ", "
            + "D." + DecksContract.DecksColumns.DECK_NAME + " " + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_DECK_NAME + ", "
            + "I." + IdentitiesContract.IdentitiesColumns.IDENTITY_NAME + " " + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_ID_NAME + ", "
            + "I." + IdentitiesContract.IdentitiesColumns.NRDB_CODE + " " + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_NRDB_CODE + ", "
            + "I." + IdentitiesContract.IdentitiesColumns.IMAGE_BIT_ARRAY + " " + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_ID_IMAGE + ", "
            + "P." + PlayersContract.PlayersColumns.PLAYER_NAME + " " + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_NAME + ", "
            + "L." + LoggedGamesContract.LoggedGamesColumns.SCORE + " " + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_SCORE + ", "
            + "L." + LoggedGamesContract.LoggedGamesColumns.WIN_FLAG + " " + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_WIN_FLAG
            + " FROM " + Tables.LOGGED_GAMES + " L "
            + " INNER JOIN " + Tables.PLAYERS + " P ON P." + BaseColumns._ID + " = L." + LoggedGamesContract.LoggedGamesColumns.PLAYER_ID
            + " INNER JOIN " + Tables.DECKS + " D ON D." + BaseColumns._ID + " = L." + LoggedGamesContract.LoggedGamesColumns.DECK_ID
            + " INNER JOIN " + Tables.IDENTITIES + " I ON I." + BaseColumns._ID + " = D." + DecksContract.DecksColumns.DECK_IDENTITY
            + " WHERE L." + LoggedGamesContract.LoggedGamesColumns.PLAYER_SIDE + " = 2";

    private final String VIEW_SUB_SELECT_SUMMARY = "SELECT "
            + "L." +  LoggedGamesContract.LoggedGamesColumns.GAME_ID  + " " +  LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.GAME_ID + ", "
            + "LO." +  LocationsContract.LocationsColumns.LOCATION_NAME + " " +  LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.LOCATION_NAME + ", "
            + "L." +  LoggedGamesContract.LoggedGamesColumns.WIN_TYPE  + " " +  LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.WIN_TYPE + ", "
            + "L." +  LoggedGamesContract.LoggedGamesColumns.PLAYED_DATE  + " " +  LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYED_DATE
            + " FROM " + Tables.LOGGED_GAMES + " L "
            + " INNER JOIN " + Tables.LOCATIONS + " LO ON LO." + BaseColumns._ID + " = " + " L." + LoggedGamesContract.LoggedGamesColumns.LOCATION_ID
            + " GROUP BY "
            + "L." +  LoggedGamesContract.LoggedGamesColumns.GAME_ID +  ", "
            + "LO." +  LocationsContract.LocationsColumns.LOCATION_NAME + ", "
            + "L." +  LoggedGamesContract.LoggedGamesColumns.WIN_TYPE + ", "
            + "L." +  LoggedGamesContract.LoggedGamesColumns.PLAYED_DATE;

   //TODO: Will need to support the indvidural Line IDs for LoggedGames table so I can update both lines when needed
    private final String LOGGED_GAMES_FLAT_VIEW_DDL = "CREATE VIEW IF NOT EXISTS " + Views.LOGGED_GAMES_FLAT_VIEW + " AS SELECT "
            + "su." + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.GAME_ID + ", "
            + "su." + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.LOCATION_NAME + ", "
            + "su." + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYED_DATE + ", "
            + "po." + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_DECK_NAME + ", "
            + "po." + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_NAME + ", "
            + "po." + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_ID_NAME + ", "
            + "po." + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_ID_IMAGE + ", "
            + "po." + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_SCORE + ", "
            + "po." + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_WIN_FLAG + ", "
            + "pt." + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_DECK_NAME + ", "
            + "pt." + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_NAME + ", "
            + "pt." + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_ID_NAME + ", "
            + "pt." + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_ID_IMAGE + ", "
            + "pt." + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_WIN_FLAG + ", "
            + "su." + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.WIN_TYPE + ", "
            + "po." + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_NRDB_CODE + ", "
            + "pt." + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_NRDB_CODE + ", "
            + "pt." + LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_SCORE
            + " FROM "
            + " (" + VIEW_SUB_SELECT_PLAYER_ONE + ") po, "
            + " (" + VIEW_SUB_SELECT_PLAYER_TWO + ") pt, "
            + " (" + VIEW_SUB_SELECT_SUMMARY + ") su "
            + " WHERE po." + LoggedGamesContract.LoggedGamesColumns.GAME_ID + " = pt." + LoggedGamesContract.LoggedGamesColumns.GAME_ID
            + " AND po." + LoggedGamesContract.LoggedGamesColumns.GAME_ID + " = su." + LoggedGamesContract.LoggedGamesColumns.GAME_ID + ";";

    public GameLoggerDatabase(Context contextIn){
        super(contextIn,DATABASE_NAME,null,DATABASE_VERSION);
        conext = contextIn;
        deleteDatabase(conext);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(IDENTITIES_DDL);
        sqLiteDatabase.execSQL(DECKS_DDL);
        sqLiteDatabase.execSQL(LOCATIONS_DDL);
        sqLiteDatabase.execSQL(PLAYERS_DDL);
        sqLiteDatabase.execSQL(GAME_NOTES_DDL);
        sqLiteDatabase.execSQL(LOGGED_GAMES_DDL);

        sqLiteDatabase.execSQL(LOGGED_GAMES_FLAT_VIEW_DDL);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        int version = oldVersion;
////        if(version == 1){
////            //add extra filds without deletes exsisting data
////            version = 2;
////        }
//
//        if(oldVersion != DATABASE_VERSION){
//            deleteDatabase(conext);
//            onCreate(sqLiteDatabase);
//        }
    }

    public static void deleteDatabase (Context context){
        context.deleteDatabase(DATABASE_NAME);
    }


}
