package org.seeds.anrgamelogger.model;

import android.database.Cursor;

/**
 * Created by Tomas Seymour-Turner on 19/03/2017.
 */

public class LocalLoggedGame {

    private String gameID;
    private Player playerOne;
    private Player playerTwo;
    private String locationName;
    private String winType;
    private String playedDate;
    private static final long SERAILVERSIONUID = 1L;

    private final String WINNER_FLAG = "Y";


//    public  static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
//      public LocalLoggedGame createFromParcel(Parcel in){
//          return new LocalLoggedGame(in);
//      }
//
//      public LocalLoggedGame[] newArray(int size){
//          return new LocalLoggedGame[size];
//      }
//    };

    public LocalLoggedGame(Player playerOne, Player playerTwo, String locationName,  String playedDate, String gameID, String winType) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.locationName = locationName;
       this.playedDate = playedDate;
        this.gameID = gameID;
        this.winType = winType;
    }



    public LocalLoggedGame(Cursor loggedGamesCursor){
        //TODO: Load game data directly from cursor
    }

    public String getGameID() {
        return gameID;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getWinnerName() {
        String ret;
        if(playerOne.isWinner().toUpperCase() == WINNER_FLAG || playerOne.isWinner().toUpperCase() == WINNER_FLAG ){
            ret = playerOne.getName();
        }else{
            ret = playerTwo.getName();
        }
        return ret;
    }

    public String getWinType() {
        return winType;
    }

    public String getPlayedDate() {
        return playedDate;
    }
}
