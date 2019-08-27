package org.seeds.anrgamelogger.application.database.buisnessobjects;

import android.provider.BaseColumns;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteType;
import org.seeds.anrgamelogger.application.database.GameLoggerDatabase.Tables;

@StorIOSQLiteType(table= Tables.LOGGED_GAME_PLAYERS)
public class LoggedGamePlayer {

  @StorIOSQLiteColumn(name = BaseColumns._ID, key = true)
  public Integer rowid;

  @StorIOSQLiteColumn(name = LoggedGamePlayer.LoggedGamePlayersColumns.GAME_ID)
  public Integer gameID;

  @StorIOSQLiteColumn(name = LoggedGamePlayer.LoggedGamePlayersColumns.PLAYER_ID)
  public Integer player_id;

  @StorIOSQLiteColumn(name = LoggedGamePlayer.LoggedGamePlayersColumns.DECK_ID)
  public Integer deck_id;



  @StorIOSQLiteColumn(name = LoggedGamePlayer.LoggedGamePlayersColumns.WIN_FLAG)
  public String win_flag;

  @StorIOSQLiteColumn(name = LoggedGamePlayer.LoggedGamePlayersColumns.SCORE)
  public Integer score;

  @StorIOSQLiteColumn(name = LoggedGamePlayer.LoggedGamePlayersColumns.PLAYER_SIDE)
  public String side;

  private String player_name;
  private String deck_name;
  private String identity_name;
  private String deck_version;

  public LoggedGamePlayer(){}

  public LoggedGamePlayer(Integer rowid, Integer gameID, Integer player_id, Integer deck_id,
      String win_flag, Integer score,  String side ) {
    this.rowid = rowid;
    this.gameID = gameID;
    this.player_id = player_id;
    this.deck_id = deck_id;
    this.win_flag = win_flag;
    this.score = score;
    this.side = side;
  }

  public LoggedGamePlayer(Integer gameID, Integer player_id, Integer deck_id,
      String win_flag, Integer score,  String side ) {
    this.player_id = player_id;
    this.deck_id = deck_id;
    this.side = side;
    this.win_flag = win_flag;
    this.score = score;
    this.gameID = gameID;
    rowid = null;
  }

  public LoggedGamePlayer(Integer gameID, String player_name, String deck_name, String identity_name, String side, String win_flag,
                          Integer score,  String deck_version) {
    this.gameID = gameID;
    this.player_name = player_name;
    this.deck_name = deck_name;
    this.identity_name = identity_name;
    this.side = side;
    this.win_flag = win_flag;
    this.score = score;
    this.deck_version = deck_version;
  }

  public Integer getPlayer_id() {
    return player_id;
  }

  public void setPlayer_id(Integer player_id) {
    this.player_id = player_id;
  }

  public Integer getDeck_id() {
    return deck_id;
  }

  public void setDeck_id(Integer deck_id) {
    this.deck_id = deck_id;
  }

  public String getSide() {
    return side;
  }

  public void setSide(String side) {
    this.side = side;
  }

  public String getWin_flag() {
    return win_flag;
  }

  public void setWin_flag(String win_flag) {
    this.win_flag = win_flag;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Integer getGameID() {
    return gameID;
  }

  public void setGameID(Integer gameID) {
    this.gameID = gameID;
  }

  public String getPlayer_name() {
    return player_name;
  }

  public void setPlayer_name(String player_name) {
    this.player_name = player_name;
  }

  public String getDeck_name() {
    return deck_name;
  }

  public void setDeck_name(String deck_name) {
    this.deck_name = deck_name;
  }

  public String getIdentity_name() {
    return identity_name;
  }

  public void setIdentity_name(String identity_name) {
    this.identity_name = identity_name;
  }

  public String getDeck_version() {
    return deck_version;
  }

  public void setDeck_version(String deck_version) {
    this.deck_version = deck_version;
  }

  public Integer getRowid() {
    return rowid;
  }

  public void setRowid(Integer rowid) {
    this.rowid = rowid;
  }

  @Override
  public String toString() {
    return "LoggedGamePlayer{" +
        "gameID=" + gameID +
        ", player_id=" + player_id +
        ", deck_id=" + deck_id +
        ", side='" + side + '\'' +
        ", win_flag='" + win_flag + '\'' +
        ", score=" + score +
        ", rowid=" + rowid +
        ", player_name='" + player_name + '\'' +
        ", deck_name='" + deck_name + '\'' +
        ", identity_name='" + identity_name + '\'' +
        ", deck_version='" + deck_version + '\'' +
        '}';
  }

  public interface LoggedGamePlayersColumns{
    String GAME_ID = "gameid";
    String PLAYER_ID = "playerid";
    String DECK_ID = "deckid";
    String WIN_FLAG = "winflag";
    String SCORE = "score";
    String PLAYER_SIDE = "playerside";
  }
}

