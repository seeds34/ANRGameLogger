package org.seeds.anrgamelogger.database.contracts;

import android.net.Uri;
import android.provider.BaseColumns;

public class LoggedGamePlayersContract {

  public interface LoggedGamePlayersColumns{
    String GAME_ID = "gameid";
    String PLAYER_ID = "playerid";
    String DECK_ID = "deckid";
    String WIN_FLAG = "winflag";
    String SCORE = "score";
    String PLAYER_SIDE = "playerside";
  }

  public static final String CONTENT_AUTHORITY = "org.seeds.anrgl.procider";
  public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

  //Table identifyers
  public static final String PATH_LOGGED_GAMES_PLAYERS = "loggedgameplayers";

  public  static final Uri URI_TABLE = Uri.parse(BASE_CONTENT_URI.toString()+"/"+PATH_LOGGED_GAMES_PLAYERS);

  //Array of table identifiers
  public static final String[] TOP_LEVEL_PATHS = {
      PATH_LOGGED_GAMES_PLAYERS
  };

  public static class LoggedGamePlayers implements LoggedGamePlayersColumns, BaseColumns {
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendEncodedPath(PATH_LOGGED_GAMES_PLAYERS).build();
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd."+CONTENT_AUTHORITY+".loggedgameplayers";
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."+CONTENT_AUTHORITY+".loggedgameplayers";

    public static Uri buildLoggedGamePlayersUri(String locationId){
      return CONTENT_URI.buildUpon().appendEncodedPath(locationId).build();
    }

    public static String getLoggedGamePlayerId(Uri uri){
      return uri.getPathSegments().get(1);
    }
  }
}
