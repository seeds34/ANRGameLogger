package org.seeds.anrgamelogger.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import org.seeds.anrgamelogger.room.GameLoggerDatabase.Tables;
import org.seeds.anrgamelogger.room.LoggedGameOverview.LoggedGameOverviewsColumns;

@Dao
public interface LoggedGameOverviewDao {

  @Insert
  void insert(LoggedGameOverview loggedGameOverview);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  void update(LoggedGameOverview loggedGameOverview);

  @Query("SELECT * FROM " + Tables.LOGGED_GAME_OVERVIEWS +" WHERE "+ LoggedGameOverviewsColumns.GAME_ID +"= :gameID")
  Identity findLoggedGameOverviewByGameID(int gameID);

}
