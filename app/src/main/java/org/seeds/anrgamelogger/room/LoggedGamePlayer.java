package org.seeds.anrgamelogger.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import org.seeds.anrgamelogger.room.GameLoggerDatabase.Tables;

@Entity(tableName = Tables.LOGGED_GAME_PLAYERS)
public class LoggedGamePlayer {

  public interface LoggedGamePlayersColumns{
    String GAME_ID = "gameid";
    String PLAYER_ID = "playerid";
    String DECK_ID = "deckid";
    String WIN_FLAG = "winflag";
    String SCORE = "score";
    String PLAYER_SIDE = "playerside";
  }

  @PrimaryKey
  @NonNull
  @ColumnInfo(name = LoggedGamePlayersColumns.GAME_ID)
  public int gameID;

  @ColumnInfo(name = LoggedGamePlayersColumns.PLAYER_ID)
  public int player_id;

  @ColumnInfo(name = LoggedGamePlayersColumns.DECK_ID)
  public int deck_id;

  @PrimaryKey
  @NonNull
  @ColumnInfo(name = LoggedGamePlayersColumns.PLAYER_SIDE)
  public String side;

  @ColumnInfo(name = LoggedGamePlayersColumns.WIN_FLAG)
  public String win_flag;

  @ColumnInfo(name = LoggedGamePlayersColumns.SCORE)
  public int score;

  public LoggedGamePlayer(@NonNull int gameID, int player_id, int deck_id,
      @NonNull String side, String win_flag, int score) {
    this.gameID = gameID;
    this.player_id = player_id;
    this.deck_id = deck_id;
    this.side = side;
    this.win_flag = win_flag;
    this.score = score;
  }

  @NonNull
  public int getGameID() {
    return gameID;
  }

  public void setGameID(@NonNull int gameID) {
    this.gameID = gameID;
  }

  public int getPlayer_id() {
    return player_id;
  }

  public void setPlayer_id(int player_id) {
    this.player_id = player_id;
  }

  public int getDeck_id() {
    return deck_id;
  }

  public void setDeck_id(int deck_id) {
    this.deck_id = deck_id;
  }

  @NonNull
  public String getSide() {
    return side;
  }

  public void setSide(@NonNull String side) {
    this.side = side;
  }

  public String getWin_flag() {
    return win_flag;
  }

  public void setWin_flag(String win_flag) {
    this.win_flag = win_flag;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }
}


