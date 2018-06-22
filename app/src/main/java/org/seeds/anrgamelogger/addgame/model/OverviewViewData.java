package org.seeds.anrgamelogger.addgame.model;

public class OverviewViewData {

  public String location;
  public String playedDate;
  public String winningSide;
  public String winType;

  public OverviewViewData(String location, String playedDate, String winningSide,
      String winType) {
    this.location = location;
    this.playedDate = playedDate;
    this.winningSide = winningSide;
    this.winType = winType;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getPlayedDate() {
    return playedDate;
  }

  public void setPlayedDate(String playedDate) {
    this.playedDate = playedDate;
  }

  public String getWinningSide() {
    return winningSide;
  }

  public void setWinningSide(String winningSide) {
    this.winningSide = winningSide;
  }

  public String getWinType() {
    return winType;
  }

  public void setWinType(String winType) {
    this.winType = winType;
  }
}
