package org.seeds.anrgamelogger.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import org.seeds.anrgamelogger.application.ANRLoggerApplication;
import org.seeds.anrgamelogger.database.buisnessobjects.Deck;
import org.seeds.anrgamelogger.database.buisnessobjects.Identity;
import org.seeds.anrgamelogger.database.buisnessobjects.Location;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameFlat.LoggedGamesFlatColumns;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameOverview;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGamePlayer;
import org.seeds.anrgamelogger.database.buisnessobjects.Player;

/**
 * Created by Tomas Seymour-Turner on 26/04/2017.
 */

public class GameLoggerDatabase extends SQLiteOpenHelper {

    private static final String LOG_TAG = GameLoggerDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "ANRGAMELOGGER.db";
    private static final int DATABASE_VERSION = 2;
    private final Context conext;

    public interface Tables{
        String LOGGED_GAME_OVERVIEWS = "loggedgameoverviews";
        String DECKS = "decks";
        String IDENTITIES = "identities";
        String LOCATIONS = "locations";
        String GAME_NOTES ="gamenotes";
        String PLAYERS = "players";
        String LOGGED_GAME_PLAYERS ="loggedgameplayers";
        String SQLITE_SEQ = "sqlite_sequence";
    }

    public interface Views{
        String LOGGED_GAMES_FLAT_VIEW = "loggedgamesflatview";
    }

    //TODO: Need to sort Rotated flag for mutiple IDs
    private final String IDENTITIES_DDL = "CREATE TABLE IF NOT EXISTS " + Tables.IDENTITIES + " ( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Identity.IdentitiesColumns.IDENTITY_NAME + " TEXT NOT NULL COLLATE NOCASE ,"
            + Identity.IdentitiesColumns.IDENTITY_FACTION + " TEXT NOT NULL COLLATE NOCASE,"
            + Identity.IdentitiesColumns.IDENTITY_SIDE + " TEXT NOT NULL COLLATE NOCASE, "
            + Identity.IdentitiesColumns.ROTATED_FLAG + " TEXT NOT NULL, "
            + Identity.IdentitiesColumns.NRDB_CODE + " TEXT NOT NULL, "
            + Identity.IdentitiesColumns.NRDB_PACK_CODE + " TEXT, "
            + Identity.IdentitiesColumns.POSTION_IN_PACK + " TEXT, "
            + Identity.IdentitiesColumns.IMAGE_BIT_ARRAY + " BLOB );";
           // + "CONSTRAINT identitity_name_unique UNIQUE("+ IdentitiesContract.IdentitiesColumns.IDENTITY_NAME +"));";

    private final String LOCATIONS_DDL = "CREATE TABLE IF NOT EXISTS " + Tables.LOCATIONS + " ( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Location.LocationsColumns.LOCATION_NAME + " TEXT NOT NULL COLLATE NOCASE, "
            + "CONSTRAINT location_name_unique UNIQUE("+ Location.LocationsColumns.LOCATION_NAME +"));"; //Hmm not sure about this being Unique

    private final String PLAYERS_DDL = "CREATE TABLE IF NOT EXISTS " + Tables.PLAYERS + " ( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Player.PlayersColumns.PLAYER_NAME + " TEXT NOT NULL COLLATE NOCASE,"
            + Player.PlayersColumns.JNET_ID + " TEXT, "
            + Player.PlayersColumns.PLAYER_NICK_NAME + " TEXT,"
            + "CONSTRAINT player_nickname_unique UNIQUE("+ Player.PlayersColumns.PLAYER_NICK_NAME +"));";

//    private final String GAME_NOTES_DDL = "CREATE TABLE IF NOT EXISTS " + Tables.GAME_NOTES + " ( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//            + GameNotesContract.GameNotesColumns.GAME_NOTE + " TEXT,"
//            + GameNotesContract.GameNotesColumns.GAME_ID + " INTEGER NOT NULL);"
//            + "FOREIGN KEY("+GameNotesContract.GameNotesColumns.GAME_ID +") REFERENCES "+ Tables.LOGGED_GAME_OVERVIEWS +"("+BaseColumns._ID+"));";

    private final String DECKS_DDL = "CREATE TABLE IF NOT EXISTS " + Tables.DECKS + " ( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Deck.DecksColumns.DECK_NAME + " TEXT NOT NULL COLLATE NOCASE,"
            + Deck.DecksColumns.DECK_VERSION + " TEXT,"
            + Deck.DecksColumns.DECK_ARCHETYPE + " TEXT COLLATE NOCASE,"
            + Deck.DecksColumns.DECK_IDENTITY + " INTEGER NOT NULL,"
            + Deck.DecksColumns.NRDB_LINK + " TEXT,"
            + "FOREIGN KEY("+ Deck.DecksColumns.DECK_IDENTITY +") REFERENCES "+ Tables.IDENTITIES +"("+BaseColumns._ID+"));";

    /* New Version */

    private final String LOGGED_GAMES_OVERVIEW_DDL = "CREATE TABLE IF NOT EXISTS " + Tables.LOGGED_GAME_OVERVIEWS + " ( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + LoggedGameOverview.LoggedGameOverviewsColumns.GAME_ID + " INTEGER NOT NULL,"
            + LoggedGameOverview.LoggedGameOverviewsColumns.LOCATION_ID + " INTEGER,"
            + LoggedGameOverview.LoggedGameOverviewsColumns.WIN_TYPE + " TEXT NOT NULL,"
            + LoggedGameOverview.LoggedGameOverviewsColumns.PLAYED_DATE + " TEXT,"
            + "FOREIGN KEY("+ LoggedGameOverview.LoggedGameOverviewsColumns.LOCATION_ID +") REFERENCES "+ Tables.LOCATIONS +"("+BaseColumns._ID+"));";


  private final String LOGGED_GAME_PLAYER_DDL = "CREATE TABLE IF NOT EXISTS " + Tables.LOGGED_GAME_PLAYERS + " ( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + LoggedGamePlayer.LoggedGamePlayersColumns.GAME_ID + " INTEGER NOT NULL,"
            + LoggedGamePlayer.LoggedGamePlayersColumns.PLAYER_ID + " INTEGER NOT NULL,"
            + LoggedGamePlayer.LoggedGamePlayersColumns.DECK_ID + " INTEGER ," /*NOT NULL Removed so DECK_ID is no longer mandatory */
            + LoggedGamePlayer.LoggedGamePlayersColumns.WIN_FLAG + " TEXT NOT NULL,"
            + LoggedGamePlayer.LoggedGamePlayersColumns.SCORE + " INTEGER,"
            + LoggedGamePlayer.LoggedGamePlayersColumns.PLAYER_SIDE + " TEXT NOT NULL,"
            + "FOREIGN KEY("+ LoggedGamePlayer.LoggedGamePlayersColumns.PLAYER_ID +") REFERENCES "+ Tables.PLAYERS +"("+BaseColumns._ID+"),"
            + "FOREIGN KEY("+ LoggedGamePlayer.LoggedGamePlayersColumns.DECK_ID +") REFERENCES "+ Tables.DECKS +"("+BaseColumns._ID+"),"
            + "CONSTRAINT game_player_unique UNIQUE("+ LoggedGamePlayer.LoggedGamePlayersColumns.GAME_ID + "," + LoggedGamePlayer.LoggedGamePlayersColumns.PLAYER_ID   +"));";


  private final String VIEW_SELECT_PLAYER = "SELECT "
            + "LGP." + BaseColumns._ID + "  porowid, "
            + "LGP." + LoggedGamePlayer.LoggedGamePlayersColumns.GAME_ID + " " + LoggedGamePlayer.LoggedGamePlayersColumns.GAME_ID + ", "
            + "D." + Deck.DecksColumns.DECK_NAME + " " + Deck.DecksColumns.DECK_NAME + ", "
            + "D." + Deck.DecksColumns.DECK_VERSION + " " + Deck.DecksColumns.DECK_VERSION + ", "
            + "I." + Identity.IdentitiesColumns.IDENTITY_NAME + " " + Identity.IdentitiesColumns.IDENTITY_NAME + ", "
            + "I." + Identity.IdentitiesColumns.NRDB_CODE + " " + Identity.IdentitiesColumns.NRDB_CODE + ", "
            + "I." + Identity.IdentitiesColumns.IMAGE_BIT_ARRAY + " " + Identity.IdentitiesColumns.IMAGE_BIT_ARRAY + ", "
            + "P." + Player.PlayersColumns.PLAYER_NAME + " " + Player.PlayersColumns.PLAYER_NAME + ", "
            + "LGP." + LoggedGamePlayer.LoggedGamePlayersColumns.SCORE + " " + LoggedGamePlayer.LoggedGamePlayersColumns.SCORE + ", "
            + "LGP." + LoggedGamePlayer.LoggedGamePlayersColumns.WIN_FLAG + " " + LoggedGamePlayer.LoggedGamePlayersColumns.WIN_FLAG + ", "
            + "LGP." + LoggedGamePlayer.LoggedGamePlayersColumns.PLAYER_SIDE + " " + LoggedGamePlayer.LoggedGamePlayersColumns.PLAYER_SIDE
            + " FROM " + Tables.LOGGED_GAME_PLAYERS + " LGP "
            + " INNER JOIN " + Tables.PLAYERS + " P ON P." + BaseColumns._ID + " = LGP." + LoggedGamePlayer.LoggedGamePlayersColumns.PLAYER_ID
            + " INNER JOIN " + Tables.DECKS + " D ON D." + BaseColumns._ID + " = LGP." + LoggedGamePlayer.LoggedGamePlayersColumns.DECK_ID
            + " INNER JOIN " + Tables.IDENTITIES + " I ON I." + BaseColumns._ID + " = D." + Deck.DecksColumns.DECK_IDENTITY;

  private final String LOGGED_GAMES_FLAT_VIEW_DDL = "CREATE TEMP VIEW IF NOT EXISTS " + Views.LOGGED_GAMES_FLAT_VIEW + " AS SELECT "
      + "OV." + LoggedGameOverview.LoggedGameOverviewsColumns.GAME_ID + " " +  LoggedGamesFlatColumns.GAME_ID + ", "
      + "L." +   Location.LocationsColumns.LOCATION_NAME + " " + LoggedGamesFlatColumns.LOCATION_NAME + ", "
      + "OV." + LoggedGameOverview.LoggedGameOverviewsColumns.PLAYED_DATE  + " " + LoggedGamesFlatColumns.PLAYED_DATE + ", "
      + "PO." + Deck.DecksColumns.DECK_NAME + " " + LoggedGamesFlatColumns.PLAYER_ONE_DECK_NAME + ", "
      + "PO." + Deck.DecksColumns.DECK_VERSION + " " + LoggedGamesFlatColumns.PLAYER_ONE_DECK_VER + ", "
      + "PO." + Player.PlayersColumns.PLAYER_NAME  + " " + LoggedGamesFlatColumns.PLAYER_ONE_NAME + ", "
      + "PO." + Identity.IdentitiesColumns.IDENTITY_NAME  + " " + LoggedGamesFlatColumns.PLAYER_ONE_ID_NAME + ", "
      + "PO." + Identity.IdentitiesColumns.IMAGE_BIT_ARRAY  + " " + LoggedGamesFlatColumns.PLAYER_ONE_ID_IMAGE + ", "
      + "PO." + LoggedGamePlayer.LoggedGamePlayersColumns.SCORE + " " + LoggedGamesFlatColumns.PLAYER_ONE_SCORE + ", "
      + "PO." + LoggedGamePlayer.LoggedGamePlayersColumns.WIN_FLAG  + " " + LoggedGamesFlatColumns.PLAYER_ONE_WIN_FLAG + ", "
      + "PT." + Deck.DecksColumns.DECK_NAME  + " " + LoggedGamesFlatColumns.PLAYER_TWO_DECK_NAME + ", "
      + "PT." + Deck.DecksColumns.DECK_VERSION + " " + LoggedGamesFlatColumns.PLAYER_TWO_DECK_VER + ", "
      + "PT." + Player.PlayersColumns.PLAYER_NAME  + " " + LoggedGamesFlatColumns.PLAYER_TWO_NAME + ", "
      + "PT." + Identity.IdentitiesColumns.IDENTITY_NAME  + " " + LoggedGamesFlatColumns.PLAYER_TWO_ID_NAME + ", "
      + "PT." + Identity.IdentitiesColumns.IMAGE_BIT_ARRAY  + " " + LoggedGamesFlatColumns.PLAYER_TWO_ID_IMAGE + ", "
      + "PT." + LoggedGamePlayer.LoggedGamePlayersColumns.WIN_FLAG  + " " + LoggedGamesFlatColumns.PLAYER_TWO_WIN_FLAG + ", "
      + "OV." + LoggedGameOverview.LoggedGameOverviewsColumns.WIN_TYPE  + " " + LoggedGamesFlatColumns.WIN_TYPE + ", "
      + "PO." + Identity.IdentitiesColumns.NRDB_CODE  + " " + LoggedGamesFlatColumns.PLAYER_ONE_NRDB_CODE + ", "
      + "PT." + Identity.IdentitiesColumns.NRDB_CODE  + " " + LoggedGamesFlatColumns.PLAYER_TWO_NRDB_CODE + ", "
      + "PT." + LoggedGamePlayer.LoggedGamePlayersColumns.SCORE  + " " + LoggedGamesFlatColumns.PLAYER_TWO_SCORE
      + " FROM " + Tables.LOGGED_GAME_OVERVIEWS + " OV "
      + " INNER JOIN (" + VIEW_SELECT_PLAYER + ") PO ON PO." + LoggedGamesFlatColumns.GAME_ID + " = OV." + LoggedGamesFlatColumns.GAME_ID
      + " INNER JOIN (" + VIEW_SELECT_PLAYER + ") PT ON PT." + LoggedGamesFlatColumns.GAME_ID + " = OV." + LoggedGamesFlatColumns.GAME_ID
      + " INNER JOIN " + Tables.LOCATIONS + " L ON L." + BaseColumns._ID + " = OV." + LoggedGameOverview.LoggedGameOverviewsColumns.LOCATION_ID
      + " WHERE PO." + LoggedGamePlayer.LoggedGamePlayersColumns.PLAYER_SIDE + " = \""  + ANRLoggerApplication.RUNNER_SIDE_IDENTIFIER + "\""
      + " AND PT." + LoggedGamePlayer.LoggedGamePlayersColumns.PLAYER_SIDE + " = \""  + ANRLoggerApplication.CORP_SIDE_IDENTIFIER + "\"";

  public GameLoggerDatabase(Context contextIn){
        super(contextIn,DATABASE_NAME,null,DATABASE_VERSION);
        conext = contextIn;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //deleteDatabase(conext);
        sqLiteDatabase.execSQL(IDENTITIES_DDL);
        sqLiteDatabase.execSQL(DECKS_DDL);
        sqLiteDatabase.execSQL(LOCATIONS_DDL);
        sqLiteDatabase.execSQL(PLAYERS_DDL);
       // sqLiteDatabase.execSQL(GAME_NOTES_DDL);
        sqLiteDatabase.execSQL(LOGGED_GAMES_OVERVIEW_DDL);
        sqLiteDatabase.execSQL(LOGGED_GAME_PLAYER_DDL);
        sqLiteDatabase.execSQL(LOGGED_GAMES_FLAT_VIEW_DDL);
    }


  @Override
  public void onOpen(SQLiteDatabase db) {
    super.onOpen(db);

      //Print Out SQL
      Log.i(LOG_TAG, VIEW_SELECT_PLAYER);
      Log.i(LOG_TAG, LOGGED_GAMES_FLAT_VIEW_DDL);
    db.execSQL(LOGGED_GAMES_FLAT_VIEW_DDL);
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
