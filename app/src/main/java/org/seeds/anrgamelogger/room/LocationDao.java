package org.seeds.anrgamelogger.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import org.seeds.anrgamelogger.room.GameLoggerDatabase.Tables;
import org.seeds.anrgamelogger.room.Location.LocationsColumns;

@Dao
public interface LocationDao {

  @Insert
  void insert(Location location);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  void update(Location location);

  @Query("SELECT * FROM " + Tables.LOCATIONS +" WHERE "+ LocationsColumns.LOCATION_NAME +"= :name")
  Identity findLocationyByName(String name);


}
