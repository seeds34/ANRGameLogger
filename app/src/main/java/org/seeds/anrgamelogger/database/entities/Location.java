package org.seeds.anrgamelogger.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import org.seeds.anrgamelogger.database.GameLoggerDatabase.Tables;
import org.seeds.anrgamelogger.database.entities.Location.LocationsColumns;

@Entity(tableName = Tables.LOCATIONS,  indices = {@Index(value = {LocationsColumns.LOCATION_NAME}, unique = true)})
public class Location {

  public interface LocationsColumns{
    String LOCATION_NAME= "locationname";
    String ID = "id";
  }

  @NonNull
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = LocationsColumns.ID)
  public int id;

  @NonNull
  @ColumnInfo(name = LocationsColumns.LOCATION_NAME)
  public String name;

  public Location(@NonNull int id, @NonNull String name) {
    this.id = id;
    this.name = name;
  }

  @NonNull
  public int getId() {
    return id;
  }

  public void setId(@NonNull int id) {
    this.id = id;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
    this.name = name;
  }
}
