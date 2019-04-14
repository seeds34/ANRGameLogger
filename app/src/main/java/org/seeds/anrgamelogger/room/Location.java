package org.seeds.anrgamelogger.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import org.seeds.anrgamelogger.room.GameLoggerDatabase.Tables;

@Entity(tableName = Tables.LOCATIONS)
public class Location {

  public interface LocationsColumns{
    String LOCATION_NAME= "locationname";
  }

  @PrimaryKey
  @NonNull
  @ColumnInfo(name = LocationsColumns.LOCATION_NAME)
  public String name;


  public Location(){};

  public Location(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
