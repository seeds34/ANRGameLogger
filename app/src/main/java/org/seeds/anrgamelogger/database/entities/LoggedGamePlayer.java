package org.seeds.anrgamelogger.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import org.seeds.anrgamelogger.database.GameLoggerDatabase.Tables;
import org.seeds.anrgamelogger.database.entities.Deck.DecksColumns;
import org.seeds.anrgamelogger.database.entities.LoggedGameOverview.LoggedGameOverviewsColumns;
import org.seeds.anrgamelogger.database.entities.LoggedGamePlayer.LoggedGamePlayersColumns;
import org.seeds.anrgamelogger.database.entities.Player.PlayersColumns;

@Entity(tableName = Tables.LOGGED_GAME_PLAYERS, foreignKeys = {
    @ForeignKey(entity = Deck.class, parentColumns = DecksColumns.ID, childColumns = LoggedGamePlayersColumns.DECK_ID),
    @ForeignKey(entity = Player.class, parentColumns = PlayersColumns.ID, childColumns = LoggedGamePlayersColumns.PLAYER_ID),
    @ForeignKey(entity = LoggedGameOverview.class, parentColumns = LoggedGameOverviewsColumns.GAME_ID, childColumns = LoggedGamePlayersColumns.GAME_ID)},
    indices = {@Index(value = {LoggedGamePlayersColumns.GAME_ID, LoggedGamePlayersColumns.PLAYER_SIDE}, unique = true)})
public class LoggedGamePlayer {

  public interface LoggedGamePlayersColumns{
    String GAME_ID = "gameid";
    String PLAYER_ID = "playerid";
    String DECK_ID = "deckid";
    String WIN_FLAG = "winflag";
    String SCORE = "score";
    String PLAYER_SIDE = "playerside";
    String ID = "id";
  }

  @NonNull
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = LoggedGamePlayersColumns.ID)
  public int id;

  @NonNull
  @ColumnInfo(name = LoggedGamePlayersColumns.GAME_ID)
  public int gameID;

  @NonNull
  @ColumnInfo(name = LoggedGamePlayersColumns.PLAYER_ID)
  public int player_id;

  @NonNull
  @ColumnInfo(name = LoggedGamePlayersColumns.DECK_ID)
  public int deck_id;

  @NonNull
  @ColumnInfo(name = LoggedGamePlayersColumns.PLAYER_SIDE)
  public String side;

  @NonNull
  @ColumnInfo(name = LoggedGamePlayersColumns.WIN_FLAG)
  public String win_flag;

  @NonNull
  @ColumnInfo(name = LoggedGamePlayersColumns.SCORE)
  public int score;

}


