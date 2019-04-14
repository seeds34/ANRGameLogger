package org.seeds.anrgamelogger.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import org.seeds.anrgamelogger.room.GameLoggerDatabase.Tables;
import org.seeds.anrgamelogger.room.LoggedGamePlayer.LoggedGamePlayersColumns;

@Dao
public interface LoggedGamePlayersDao {


  @Insert
  void insert(LoggedGamePlayer loggedGamePlayer);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  void update(LoggedGamePlayer loggedGamePlayer);

  @Query("SELECT * FROM " + Tables.LOGGED_GAME_PLAYERS +" WHERE "+ LoggedGamePlayersColumns.GAME_ID +"= :gameID")
  Identity findLoggedGamePlayersByGame(int gameID);

  @Query("SELECT * FROM " + Tables.LOGGED_GAME_PLAYERS +" WHERE "
      + LoggedGamePlayersColumns.GAME_ID + "= :gameID "
      + " AND " + LoggedGamePlayersColumns.PLAYER_ID + "= :playerId")
  Identity findLoggedGamePlayersByGameAndPlayer(int gameID, int playerId);

}
