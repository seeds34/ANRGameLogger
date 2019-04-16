package org.seeds.anrgamelogger.database.dao;

import static org.seeds.anrgamelogger.application.ANRLoggerApplication.CORP_SIDE_IDENTIFIER;
import static org.seeds.anrgamelogger.application.ANRLoggerApplication.RUNNER_SIDE_IDENTIFIER;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.provider.BaseColumns;
import org.seeds.anrgamelogger.database.GameLoggerDatabase.Tables;
import org.seeds.anrgamelogger.database.LoggedGameFlat;
import org.seeds.anrgamelogger.database.LoggedGameFlat.LoggedGamesFlatColumns;
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

  @Query(LOGGED_GAMES_FLAT_QUERY)
  LoggedGameFlat getLoggedGame(int gameID);

   String VIEW_SELECT_PLAYER = "SELECT "
      + "LGP." + BaseColumns._ID + "  porowid, "
      + "LGP." + LoggedGamePlayersColumns.GAME_ID + " " + LoggedGamePlayersColumns.GAME_ID + ", "
      + "D." + DecksColumns.DECK_NAME + " " + DecksColumns.DECK_NAME + ", "
      + "I." + IdentitiesColumns.IDENTITY_NAME + " " + IdentitiesColumns.IDENTITY_NAME + ", "
      + "I." + IdentitiesColumns.IMAGE_BIT_ARRAY + " " + IdentitiesColumns.IMAGE_BIT_ARRAY + ", "
      + "P." + PlayersColumns.PLAYER_NAME + " " + PlayersColumns.PLAYER_NAME + ", "
      + "LGP." + LoggedGamePlayersColumns.SCORE + " " + LoggedGamePlayersColumns.SCORE + ", "
      + "LGP." + LoggedGamePlayersColumns.WIN_FLAG + " " + LoggedGamePlayersColumns.WIN_FLAG + ", "
      + "LGP." + LoggedGamePlayersColumns.PLAYER_SIDE + " " + LoggedGamePlayersColumns.PLAYER_SIDE
      + " FROM " + Tables.LOGGED_GAME_PLAYERS + " LGP "
      + " INNER JOIN " + Tables.PLAYERS + " P ON P." + BaseColumns._ID + " = LGP." + LoggedGamePlayersColumns.PLAYER_ID
      + " INNER JOIN " + Tables.DECKS + " D ON D." + BaseColumns._ID + " = LGP." + LoggedGamePlayersColumns.DECK_ID
      + " INNER JOIN " + Tables.IDENTITIES + " I ON I." + BaseColumns._ID + " = D." + DecksColumns.DECK_IDENTITY;

  String LOGGED_GAMES_FLAT_QUERY = "SELECT "
      + "OV." + LoggedGameOverviewsColumns.GAME_ID + " " +  LoggedGamesFlatColumns.GAME_ID + ", "
      + "OV." + LoggedGameOverviewsColumns.LOCATION_ID + " " + LoggedGamesFlatColumns.LOCATION_NAME + ", "
      + "OV." + LoggedGameOverviewsColumns.PLAYED_DATE  + " " + LoggedGamesFlatColumns.PLAYED_DATE + ", "
      + "RUNNER." + DecksColumns.DECK_NAME + " " + LoggedGamesFlatColumns.PLAYER_ONE_DECK_NAME + ", "
      + "RUNNER." + PlayersColumns.PLAYER_NAME  + " " + LoggedGamesFlatColumns.PLAYER_ONE_NAME + ", "
      + "RUNNER." + IdentitiesColumns.IDENTITY_NAME  + " " + LoggedGamesFlatColumns.PLAYER_ONE_ID_NAME + ", "
      + "RUNNER." + IdentitiesColumns.IMAGE_BIT_ARRAY  + " " + LoggedGamesFlatColumns.PLAYER_ONE_ID_IMAGE + ", "
      + "RUNNER." + LoggedGamePlayersColumns.SCORE + " " + LoggedGamesFlatColumns.PLAYER_ONE_SCORE + ", "
      + "RUNNER." + LoggedGamePlayersColumns.WIN_FLAG  + " " + LoggedGamesFlatColumns.PLAYER_ONE_WIN_FLAG + ", "
      + "CORP." + DecksColumns.DECK_NAME  + " " + LoggedGamesFlatColumns.PLAYER_TWO_DECK_NAME + ", "
      + "CORP." + PlayersColumns.PLAYER_NAME  + " " + LoggedGamesFlatColumns.PLAYER_TWO_NAME + ", "
      + "CORP." + IdentitiesColumns.IDENTITY_NAME  + " " + LoggedGamesFlatColumns.PLAYER_TWO_ID_NAME + ", "
      + "CORP." + IdentitiesColumns.IMAGE_BIT_ARRAY  + " " + LoggedGamesFlatColumns.PLAYER_TWO_ID_IMAGE + ", "
      + "CORP." + LoggedGamePlayersColumns.WIN_FLAG  + " " + LoggedGamesFlatColumns.PLAYER_TWO_WIN_FLAG + ", "
      + "OV." + LoggedGameOverviewsColumns.WIN_TYPE  + " " + LoggedGamesFlatColumns.WIN_TYPE + ", "
      + "CORP." + LoggedGamePlayersColumns.SCORE  + " " + LoggedGamesFlatColumns.PLAYER_TWO_SCORE
      + " FROM " + Tables.LOGGED_GAME_OVERVIEWS + " OV "
      + " INNER JOIN (" + VIEW_SELECT_PLAYER + ") RUNNER ON RUNNER." + LoggedGamesFlatColumns.GAME_ID + " = OV." + LoggedGamesFlatColumns.GAME_ID
      + " INNER JOIN (" + VIEW_SELECT_PLAYER + ") CORP ON CORP." + LoggedGamesFlatColumns.GAME_ID + " = OV." + LoggedGamesFlatColumns.GAME_ID
      + " WHERE RUNNER." + LoggedGamePlayersColumns.PLAYER_SIDE + " = " + RUNNER_SIDE_IDENTIFIER
      + " AND CORP." + LoggedGamePlayersColumns.PLAYER_SIDE + " = " + CORP_SIDE_IDENTIFIER ;


}
