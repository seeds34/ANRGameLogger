package org.seeds.anrgamelogger.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import org.seeds.anrgamelogger.room.Deck.DecksColumns;
import org.seeds.anrgamelogger.room.GameLoggerDatabase.Tables;

@Dao
public interface DeckDao {

  @Insert
  void insert(Deck deck);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  void update(Deck deck);

  @Query("SELECT * FROM " + Tables.DECKS +" WHERE "
      + DecksColumns.DECK_NAME + "= :name AND "
      + DecksColumns.DECK_VERSION + "= :version AND "
      + DecksColumns.DECK_IDENTITY + "= :identityName")
  Deck findDeckByNameVersionIdentity(String name, int version, String identityName);
}
