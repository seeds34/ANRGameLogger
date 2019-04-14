package org.seeds.anrgamelogger.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import org.seeds.anrgamelogger.room.GameLoggerDatabase.Tables;
import org.seeds.anrgamelogger.room.Player.PlayersColumns;

@Dao
public interface PlayerDao {

  @Insert
  void insert(Player player);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  void update(Player player);

  @Query("SELECT * FROM " + Tables.PLAYERS +" WHERE "
      + PlayersColumns.PLAYER_NAME + "= :name")
  Deck findPlayerByName(String name);

  @Query("SELECT * FROM " + Tables.PLAYERS +" WHERE "
      + PlayersColumns.PLAYER_NICK_NAME + "= :name")
  Deck findPlayerByNickName(String name);

}
