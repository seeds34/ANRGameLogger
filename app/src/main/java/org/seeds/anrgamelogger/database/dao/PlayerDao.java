package org.seeds.anrgamelogger.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;
import org.seeds.anrgamelogger.database.GameLoggerDatabase.Tables;
import org.seeds.anrgamelogger.database.entities.Player;
import org.seeds.anrgamelogger.database.entities.Player.PlayersColumns;

@Dao
public interface PlayerDao {

  @Insert
  void insert(Player player);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  void update(Player player);

  @Query("SELECT * FROM " + Tables.PLAYERS +" WHERE "
      + PlayersColumns.PLAYER_NAME + "= :name")
  List<Player> findPlayerByName(String name);

  @Query("SELECT * FROM " + Tables.PLAYERS +" WHERE "
      + PlayersColumns.PLAYER_NICK_NAME + "= :name")
  List<Player> findPlayerByNickName(String name);

  @Query("SELECT * FROM " + Tables.PLAYERS +" WHERE "
      + PlayersColumns.ID + "= :id")
  Player findPlayerByID(int id);

}
