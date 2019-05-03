package org.seeds.anrgamelogger.database.buisnessobjects;

import android.provider.BaseColumns;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteType;
import org.seeds.anrgamelogger.database.GameLoggerDatabase.Tables;

@StorIOSQLiteType(table= Tables.LOCATIONS)
public class Location {

  @StorIOSQLiteColumn(name = Location.LocationsColumns.LOCATION_NAME)
  public String name;

  @StorIOSQLiteColumn(name = BaseColumns._ID, key = true)
  public Integer rowid;

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

  public Integer getRowid() {
    return rowid;
  }

  public void setRowid(Integer rowid) {
    this.rowid = rowid;
  }

  public interface LocationsColumns{
      String LOCATION_NAME= "locationname";
  }
}
