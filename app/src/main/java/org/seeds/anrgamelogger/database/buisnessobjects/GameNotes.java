package org.seeds.anrgamelogger.database.buisnessobjects;

import android.provider.BaseColumns;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteType;
import org.seeds.anrgamelogger.database.GameLoggerDatabase.Tables;

@StorIOSQLiteType(table= Tables.GAME_NOTES)
public class GameNotes {

  @StorIOSQLiteColumn(name = GameNotes.GameNotesColumns.GAME_ID)
  public Integer gameID;

  @StorIOSQLiteColumn(name = GameNotes.GameNotesColumns.GAME_NOTE)
  public String gameNotes;

  @StorIOSQLiteColumn(name = BaseColumns._ID, key = true)
  Integer rowid;

  public GameNotes(){};

  public GameNotes(Integer gameID, String gameNotes, int rowid) {
    this.gameID = gameID;
    this.gameNotes = gameNotes;
    this.rowid = rowid;
  }

  public Integer getGameID() {
    return gameID;
  }

  public void setGameID(Integer gameID) {
    this.gameID = gameID;
  }

  public String getGameNotes() {
    return gameNotes;
  }

  public void setGameNotes(String gameNotes) {
    this.gameNotes = gameNotes;
  }

  public Integer getRowid() {
    return rowid;
  }

  public void setRowid(Integer rowid) {
    this.rowid = rowid;
  }

  public interface GameNotesColumns{
      String GAME_ID = "gameid";
      String GAME_NOTE = "gamenote";
  }
}
