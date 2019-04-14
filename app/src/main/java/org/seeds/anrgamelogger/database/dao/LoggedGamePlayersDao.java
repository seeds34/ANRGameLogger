package org.seeds.anrgamelogger.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;
import org.seeds.anrgamelogger.database.GameLoggerDatabase.Tables;
import org.seeds.anrgamelogger.database.entities.LoggedGamePlayer;
import org.seeds.anrgamelogger.database.entities.LoggedGamePlayer.LoggedGamePlayersColumns;

@Dao
public interface LoggedGamePlayersDao {


  @Insert
  void insert(LoggedGamePlayer loggedGamePlayer);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  void update(LoggedGamePlayer loggedGamePlayer);

  @Query("SELECT * FROM " + Tables.LOGGED_GAME_PLAYERS +" WHERE "+ LoggedGamePlayersColumns.GAME_ID +"= :gameID")
  List<LoggedGamePlayer> findLoggedGamePlayersByGameId(int gameID);

  @Query("SELECT * FROM " + Tables.LOGGED_GAME_PLAYERS +" WHERE "
      + LoggedGamePlayersColumns.GAME_ID + "= :gameID "
      + " AND " + LoggedGamePlayersColumns.PLAYER_ID + "= :playerId")
  List<LoggedGamePlayer> findLoggedGamePlayersByGameAndPlayer(int gameID, int playerId);

}
