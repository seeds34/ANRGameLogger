package org.seeds.anrgamelogger.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by Tomas Seymour-Turner on 19/03/2017.
 */

public class LocalLoggedGame implements Parcelable

{

    private String gameID;
    private Player playerOne;
    private Player playerTwo;
    private String locationName;
    private String winType;
    private String playedDate;
    private String winnerFlag = "Y";

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
        if(playerOne.isWinner().toUpperCase() == winnerFlag || playerOne.isWinner().toUpperCase() == winnerFlag){
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


    /*Genrated at: http://www.parcelabler.com/ */
        protected LocalLoggedGame(Parcel in) {
            gameID = in.readString();
            playerOne = (Player) in.readValue(Player.class.getClassLoader());
            playerTwo = (Player) in.readValue(Player.class.getClassLoader());
            locationName = in.readString();
            winType = in.readString();
            playedDate = in.readString();
            winnerFlag = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(gameID);
            dest.writeValue(playerOne);
            dest.writeValue(playerTwo);
            dest.writeString(locationName);
            dest.writeString(winType);
            dest.writeString(playedDate);
            dest.writeString(winnerFlag);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<LocalLoggedGame> CREATOR = new Parcelable.Creator<LocalLoggedGame>() {
            @Override
            public LocalLoggedGame createFromParcel(Parcel in) {
                return new LocalLoggedGame(in);
            }

            @Override
            public LocalLoggedGame[] newArray(int size) {
                return new LocalLoggedGame[size];
            }
        };
}
