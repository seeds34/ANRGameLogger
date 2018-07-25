package org.seeds.anrgamelogger.database.buisnessobjects;

import android.provider.BaseColumns;

import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverType;
import org.seeds.anrgamelogger.database.contracts.LocationsContract;
import org.seeds.anrgamelogger.database.contracts.LocationsContract.LocationsColumns;

@StorIOContentResolverType(uri = "content://" + LocationsContract.CONTENT_AUTHORITY + "/" + LocationsContract.PATH_LOCATIONS)
public class Location {

  @StorIOContentResolverColumn(name = LocationsColumns.LOCATION_NAME, key = true)
  public String name;

  @StorIOContentResolverColumn(name = BaseColumns._ID)
  public int rowid;

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

  public int getRowid() {
    return rowid;
  }

  public void setRowid(int rowid) {
    this.rowid = rowid;
  }
}
