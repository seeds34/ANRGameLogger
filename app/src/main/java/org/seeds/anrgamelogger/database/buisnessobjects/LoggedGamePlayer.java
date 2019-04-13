package org.seeds.anrgamelogger.database.buisnessobjects;

import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverType;

import org.seeds.anrgamelogger.database.contracts.LoggedGamePlayersContract;

@StorIOContentResolverType(uri = "content://" + LoggedGamePlayersContract.CONTENT_AUTHORITY + "/" + LoggedGamePlayersContract.PATH_LOGGED_GAMES_PLAYERS)
public class LoggedGamePlayer {

  @StorIOContentResolverColumn(name = LoggedGamePlayersContract.LoggedGamePlayersColumns.GAME_ID, key = true)
  public int gameID;

  @StorIOContentResolverColumn(name = LoggedGamePlayersContract.LoggedGamePlayersColumns.PLAYER_ID)
  public int player_id;

  @StorIOContentResolverColumn(name = LoggedGamePlayersContract.LoggedGamePlayersColumns.DECK_ID)
  public int deck_id;

  @StorIOContentResolverColumn(name = LoggedGamePlayersContract.LoggedGamePlayersColumns.PLAYER_SIDE)
  public String side;

  @StorIOContentResolverColumn(name = LoggedGamePlayersContract.LoggedGamePlayersColumns.WIN_FLAG)
  public String win_flag;

  @StorIOContentResolverColumn(name = LoggedGamePlayersContract.LoggedGamePlayersColumns.SCORE)
  public int score;



  private String player_name;
  private String deck_name;
  private String identity_name;
  private String deck_version;

  public LoggedGamePlayer(){}

  public LoggedGamePlayer(int player_id, int deck_id, String side, String win_flag,
      int score, int gameNumber) {
    this.player_id = player_id;
    this.deck_id = deck_id;
    this.side = side;
    this.win_flag = win_flag;
    this.score = score;
    this.gameID = gameNumber;
  }

  public LoggedGamePlayer(String player_name, String deck_name, String identity_name, String side, String win_flag,
                          int score, int gameNumber, String deck_version) {
    this.player_name = player_name;
    this.deck_name = deck_name;
    this.identity_name = identity_name;
    this.side = side;
    this.win_flag = win_flag;
    this.gameID = gameNumber;
    this.deck_version = deck_version;
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

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getGameID() {
    return gameID;
  }

  public void setGameID(int gameID) {
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

  public String toString(){
    return
        "GameID = " + gameID + "\n" +
            "PlayerID = " + player_id + "\n" +
            "DeckID = " +  deck_id + "\n" +
            "Win Flag = " +  win_flag + "\n" +
            "Score = " +  score + "\n" +
            "PlayerSide = " +  side;
  }




}

