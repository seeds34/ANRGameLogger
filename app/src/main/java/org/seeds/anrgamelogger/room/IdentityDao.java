package org.seeds.anrgamelogger.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import org.seeds.anrgamelogger.room.GameLoggerDatabase.Tables;
import org.seeds.anrgamelogger.room.Identity.IdentitiesColumns;

@Dao
public interface IdentityDao {

  @Insert
  void insert(Identity identity);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  void update(Identity identity);

  @Query("SELECT * FROM " + Tables.IDENTITIES +" WHERE "+ IdentitiesColumns.IDENTITY_NAME +"= :name")
  Identity findIdentityByName(String name);

}
