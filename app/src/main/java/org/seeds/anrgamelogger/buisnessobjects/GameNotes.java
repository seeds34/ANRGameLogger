package org.seeds.anrgamelogger.buisnessobjects;

import android.provider.BaseColumns;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverType;
import org.seeds.anrgamelogger.database.contracts.GameNotesContract;
import org.seeds.anrgamelogger.database.contracts.GameNotesContract.GameNotesColumns;

@StorIOContentResolverType(uri = "content://" + GameNotesContract.CONTENT_AUTHORITY + "/" + GameNotesContract.PATH_GAME_NOTES)
public class GameNotes {

  @StorIOContentResolverColumn(name = GameNotesColumns.GAME_ID, key = true)
  String gameID;

  @StorIOContentResolverColumn(name = GameNotesColumns.GAME_NOTE)
  String gameNotes;

  @StorIOContentResolverColumn(name = BaseColumns._ID)
  int rowid;

  public GameNotes(String gameID, String gameNotes, int rowid) {
    this.gameID = gameID;
    this.gameNotes = gameNotes;
    this.rowid = rowid;
  }

  public String getGameID() {
    return gameID;
  }

  public void setGameID(String gameID) {
    this.gameID = gameID;
  }

  public String getGameNotes() {
    return gameNotes;
  }

  public void setGameNotes(String gameNotes) {
    this.gameNotes = gameNotes;
  }

  public int getRowid() {
    return rowid;
  }

  public void setRowid(int rowid) {
    this.rowid = rowid;
  }
}
