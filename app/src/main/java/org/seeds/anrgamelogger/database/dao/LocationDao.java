package org.seeds.anrgamelogger.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;
import org.seeds.anrgamelogger.database.GameLoggerDatabase.Tables;
import org.seeds.anrgamelogger.database.entities.Location;
import org.seeds.anrgamelogger.database.entities.Location.LocationsColumns;

@Dao
public interface LocationDao {

  @Insert
  void insert(Location location);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  void update(Location location);

  @Query("SELECT * FROM " + Tables.LOCATIONS +" WHERE "+ LocationsColumns.LOCATION_NAME +"= :name")
  List<Location> findLocationyByName(String name);

  @Query("SELECT * FROM " + Tables.LOCATIONS +" WHERE "+ LocationsColumns.ID +"= :id")
  Location findLocationyByID(int id);


}
