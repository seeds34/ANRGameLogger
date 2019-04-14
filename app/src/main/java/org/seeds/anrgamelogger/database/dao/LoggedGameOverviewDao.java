package org.seeds.anrgamelogger.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.provider.BaseColumns;
import java.util.List;
import org.seeds.anrgamelogger.database.GameLoggerDatabase.Tables;
import org.seeds.anrgamelogger.database.LoggedGameFlat;
import org.seeds.anrgamelogger.database.entities.Deck.DecksColumns;
import org.seeds.anrgamelogger.database.entities.Identity.IdentitiesColumns;
import org.seeds.anrgamelogger.database.entities.LoggedGameOverview;
import org.seeds.anrgamelogger.database.entities.LoggedGameOverview.LoggedGameOverviewsColumns;
import org.seeds.anrgamelogger.database.entities.LoggedGamePlayer.LoggedGamePlayersColumns;
import org.seeds.anrgamelogger.database.entities.Player.PlayersColumns;

@Dao
public interface LoggedGameOverviewDao {

  @Insert
  void insert(LoggedGameOverview loggedGameOverview);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  void update(LoggedGameOverview loggedGameOverview);

  @Query("SELECT * FROM " + Tables.LOGGED_GAME_OVERVIEWS +" WHERE "+ LoggedGameOverviewsColumns.GAME_ID +"= :gameID")
  LoggedGameOverview findLoggedGameOverviewByGameID(int gameID);

  @Query(LOGGED_GAMES_FLAT_VIEW_DDL
  LoggedGameFlat getLoggedGame(int gameID);

  List<LoggedGameFlat> getLoggedXGames(int count);

  List<LoggedGameFlat> getLoggedAllGames();


  final String VIEW_SELECT_PLAYER = "SELECT "
      + "LGP." + BaseColumns._ID + "  porowid, "
      + "LGP." + LoggedGamePlayersColumns.GAME_ID + " " + LoggedGamePlayersColumns.GAME_ID + ", "
      + "D." + DecksColumns.DECK_NAME + " " + DecksColumns.DECK_NAME + ", "
      + "I." + IdentitiesColumns.IDENTITY_NAME + " " + IdentitiesColumns.IDENTITY_NAME + ", "
      + "I." + IdentitiesColumns.NRDB_CODE + " " + IdentitiesColumns.NRDB_CODE + ", "
      + "I." + IdentitiesColumns.IMAGE_BIT_ARRAY + " " + IdentitiesColumns.IMAGE_BIT_ARRAY + ", "
      + "P." + PlayersColumns.PLAYER_NAME + " " + PlayersColumns.PLAYER_NAME + ", "
      + "LGP." + LoggedGamePlayersColumns.SCORE + " " + LoggedGamePlayersColumns.SCORE + ", "
      + "LGP." + LoggedGamePlayersColumns.WIN_FLAG + " " + LoggedGamePlayersColumns.WIN_FLAG + ", "
      + "LGP." + LoggedGamePlayersColumns.PLAYER_SIDE + " " + LoggedGamePlayersColumns.PLAYER_SIDE
      + " FROM " + Tables.LOGGED_GAME_PLAYERS + " LGP "
      + " INNER JOIN " + Tables.PLAYERS + " P ON P." + BaseColumns._ID + " = LGP." + LoggedGamePlayersColumns.PLAYER_ID
      + " INNER JOIN " + Tables.DECKS + " D ON D." + BaseColumns._ID + " = LGP." + LoggedGamePlayersColumns.DECK_ID
      + " INNER JOIN " + Tables.IDENTITIES + " I ON I." + BaseColumns._ID + " = D." + DecksColumns.DECK_IDENTITY;

  final String LOGGED_GAMES_FLAT_VIEW_DDL = "CREATE VIEW IF NOT EXISTS " + Views.LOGGED_GAMES_FLAT_VIEW + " AS SELECT "
      + "OV." + LoggedGameOverviewsColumns.GAME_ID + " " +  LoggedGamesFlatViewContractColumns.GAME_ID + ", "
      + "OV." + LoggedGameOverviewsColumns.LOCATION_ID + " " + LoggedGamesFlatViewContractColumns.LOCATION_NAME + ", "
      + "OV." + LoggedGameOverviewsColumns.PLAYED_DATE  + " " + LoggedGamesFlatViewContractColumns.PLAYED_DATE + ", "
      + "PO." + DecksColumns.DECK_NAME + " " + LoggedGamesFlatViewContractColumns.PLAYER_ONE_DECK_NAME + ", "
      + "PO." + PlayersColumns.PLAYER_NAME  + " " + LoggedGamesFlatViewContractColumns.PLAYER_ONE_NAME + ", "
      + "PO." + IdentitiesColumns.IDENTITY_NAME  + " " + LoggedGamesFlatViewContractColumns.PLAYER_ONE_ID_NAME + ", "
      + "PO." + IdentitiesColumns.IMAGE_BIT_ARRAY  + " " + LoggedGamesFlatViewContractColumns.PLAYER_ONE_ID_IMAGE + ", "
      + "PO." + LoggedGamePlayersColumns.SCORE + " " + LoggedGamesFlatViewContractColumns.PLAYER_ONE_SCORE + ", "
      + "PO." + LoggedGamePlayersColumns.WIN_FLAG  + " " + LoggedGamesFlatViewContractColumns.PLAYER_ONE_WIN_FLAG + ", "
      + "PT." + DecksColumns.DECK_NAME  + " " + LoggedGamesFlatViewContractColumns.PLAYER_TWO_DECK_NAME + ", "
      + "PT." + PlayersColumns.PLAYER_NAME  + " " + LoggedGamesFlatViewContractColumns.PLAYER_TWO_NAME + ", "
      + "PT." + IdentitiesColumns.IDENTITY_NAME  + " " + LoggedGamesFlatViewContractColumns.PLAYER_TWO_ID_NAME + ", "
      + "PT." + IdentitiesColumns.IMAGE_BIT_ARRAY  + " " + LoggedGamesFlatViewContractColumns.PLAYER_TWO_ID_IMAGE + ", "
      + "PT." + LoggedGamePlayersColumns.WIN_FLAG  + " " + LoggedGamesFlatViewContractColumns.PLAYER_TWO_WIN_FLAG + ", "
      + "OV." + LoggedGameOverviewsColumns.WIN_TYPE  + " " + LoggedGamesFlatViewContractColumns.WIN_TYPE + ", "
      + "PO." + IdentitiesColumns.NRDB_CODE  + " " + LoggedGamesFlatViewContractColumns.PLAYER_ONE_NRDB_CODE + ", "
      + "PT." + IdentitiesColumns.NRDB_CODE  + " " + LoggedGamesFlatViewContractColumns.PLAYER_TWO_NRDB_CODE + ", "
      + "PT." + LoggedGamePlayersColumns.SCORE  + " " + LoggedGamesFlatViewContractColumns.PLAYER_TWO_SCORE
      + " FROM " + Tables.LOGGED_GAME_OVERVIEWS + " OV "
      + " INNER JOIN (" + VIEW_SELECT_PLAYER + ") PO ON PO." + LoggedGamesFlatViewContractColumns.GAME_ID + " = OV." + LoggedGamesFlatViewContractColumns.GAME_ID
      + " INNER JOIN (" + VIEW_SELECT_PLAYER + ") PT ON PT." + LoggedGamesFlatViewContractColumns.GAME_ID + " = OV." + LoggedGamesFlatViewContractColumns.GAME_ID
      + " WHERE PO." + LoggedGamePlayersColumns.PLAYER_SIDE + " = 1 "
      + " AND PT." + LoggedGamePlayersColumns.PLAYER_SIDE + " = 2 ";


  public interface Views{
    String LOGGED_GAMES_FLAT_VIEW = "loggedgamesflatview";
  }

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
}
