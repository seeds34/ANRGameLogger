package org.seeds.anrgamelogger.database;

//https://developer.android.com/training/data-storage/room/accessing-data.html
public class LoggedGameFlat {

  public interface LoggedGamesFlatColumns {
    String GAME_ID = "gameid";
    String LOCATION_NAME = "locationname";
    String WIN_TYPE = "wintype";
    String PLAYED_DATE = "playeddate";
    String PLAYER_ONE_NAME = "runnername";
    String PLAYER_ONE_SCORE = "runnerscore";
    String PLAYER_ONE_WIN_FLAG = "runnerwinflag";
    String PLAYER_ONE_DECK_NAME = "runnerdeckname";
    String PLAYER_ONE_ID_NAME = "runneridentity";
    String PLAYER_TWO_NAME = "corpname";
    String PLAYER_TWO_SCORE = "corpscore";
    String PLAYER_TWO_WIN_FLAG = "corpwinflag";
    String PLAYER_TWO_DECK_NAME = "corpdeckname";
    String PLAYER_TWO_ID_NAME = "corpidentity";
    String PLAYER_ONE_ID_IMAGE = "runneridimage";
    String PLAYER_TWO_ID_IMAGE = "corpidimage";
  }

  public String gameid;
  public String locationname;
  public String wintype;
  public String playeddate;
  public String runnername;
  public int runnerscore;
  public String runnerwinflag;
  public String runnerdeckname;
  public String runneridentity;
  public String corpname;
  public int corpscore;
  public String corpwinflag;
  public String corpdeckname;
  public String corpidentity;
  public byte[] runneridimage;
  public byte[] corpidimage;


  public String getPlayedDate() {
    return playeddate;
  }

  public String getGameID() {
    return gameid;
  }

  public String getLocationName() {
    return locationname;
  }

  public String getpO_Name() {
    return runnername;
  }

  public String getpT_Name() {
    return corpname;
  }

  public int getpO_Score() {
    return runnerscore;
  }

  public int getpT_Score() {
    return corpscore;
  }

  public String getpO_DeckName() {
    return runnerdeckname;
  }

  public String getpT_DeckName() {
    return corpdeckname;
  }

  public String getWinnerName() {
    //TODO: Fix this
    return "Winner";
  }

  public byte[] getpO_IdentityImage() {
    return runneridimage;
  }

  public byte[] getpT_IdentityImage() {
    return corpidimage;
  }

  public String getWintype() {
    return wintype;
  }

  public String getRunnerwinflag() {
    return runnerwinflag;
  }

  public String getRunneridentity() {
    return runneridentity;
  }

  public String getCorpwinflag() {
    return corpwinflag;
  }

  public String getCorpidentity() {
    return corpidentity;
  }

  public void setGameid(String gameid) {
    this.gameid = gameid;
  }

  public void setLocationname(String locationname) {
    this.locationname = locationname;
  }

  public void setWintype(String wintype) {
    this.wintype = wintype;
  }

  public void setPlayeddate(String playeddate) {
    this.playeddate = playeddate;
  }

  public void setRunnername(String runnername) {
    this.runnername = runnername;
  }

  public void setRunnerscore(int runnerscore) {
    this.runnerscore = runnerscore;
  }

  public void setRunnerwinflag(String runnerwinflag) {
    this.runnerwinflag = runnerwinflag;
  }

  public void setRunnerdeckname(String runnerdeckname) {
    this.runnerdeckname = runnerdeckname;
  }

  public void setRunneridentity(String runneridentity) {
    this.runneridentity = runneridentity;
  }

  public void setCorpname(String corpname) {
    this.corpname = corpname;
  }

  public void setCorpscore(int corpscore) {
    this.corpscore = corpscore;
  }

  public void setCorpwinflag(String corpwinflag) {
    this.corpwinflag = corpwinflag;
  }

  public void setCorpdeckname(String corpdeckname) {
    this.corpdeckname = corpdeckname;
  }

  public void setCorpidentity(String corpidentity) {
    this.corpidentity = corpidentity;
  }

  public void setRunneridimage(byte[] runneridimage) {
    this.runneridimage = runneridimage;
  }

  public void setCorpidimage(byte[] corpidimage) {
    this.corpidimage = corpidimage;
  }
}
