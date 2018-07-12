package org.seeds.anrgamelogger.buisnessobjects;

import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverType;

import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;
import org.seeds.anrgamelogger.database.contracts.LoggedGamePlayersContract;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@StorIOContentResolverType(uri = "content://" + LoggedGamePlayersContract.CONTENT_AUTHORITY + "/" + LoggedGamePlayersContract.PATH_LOGGED_GAMES_PLAYERS)
public class LoggedGamePlayer {

  @StorIOContentResolverColumn(name = LoggedGamePlayersContract.LoggedGamePlayersColumns.GAME_ID, key = true)
  int gameID;

  @StorIOContentResolverColumn(name = LoggedGamePlayersContract.LoggedGamePlayersColumns.PLAYER_ID)
  int player_id;

  String player_name;

  @StorIOContentResolverColumn(name = LoggedGamePlayersContract.LoggedGamePlayersColumns.DECK_ID)
  int deck_id;

  String deck_name;

  String id_name;

  @StorIOContentResolverColumn(name = LoggedGamePlayersContract.LoggedGamePlayersColumns.PLAYER_SIDE, key = true)
  String side;

  @StorIOContentResolverColumn(name = LoggedGamePlayersContract.LoggedGamePlayersColumns.WIN_FLAG)
  String win_flag;

  @StorIOContentResolverColumn(name = LoggedGamePlayersContract.LoggedGamePlayersColumns.SCORE)
  String score;

  public LoggedGamePlayer(){}

  public LoggedGamePlayer(int player_id, int deck_id, String side, String win_flag,
      String score) {
    this.player_id = player_id;
    this.deck_id = deck_id;
    this.side = side;
    this.win_flag = win_flag;
    this.score = score;
  }

  public LoggedGamePlayer(String player_name, String deck_name, String id_name, String side, String win_flag,
                          String score) {
    this.player_id = player_id;
    this.deck_id = deck_id;
    this.id_name = id_name;
    this.side = side;
    this.win_flag = win_flag;
    this.score = score;
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

  public String getScore() {
    return score;
  }

  public void setScore(String score) {
    this.score = score;
  }

}
