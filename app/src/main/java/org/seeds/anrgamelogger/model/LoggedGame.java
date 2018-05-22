package org.seeds.anrgamelogger.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverType;

import org.seeds.anrgamelogger.buisnessobjects.Player;
import org.seeds.anrgamelogger.database.contracts.LoggedGamesContract;

/**
 * Created by Tomas Seymour-Turner on 19/03/2017.
 */

@StorIOContentResolverType(uri = "content://" + LoggedGamesContract.CONTENT_AUTHORITY + "/" + LoggedGamesContract.PATH_LOGGED_GAMES)
public class LoggedGame implements Parcelable {

    @StorIOContentResolverColumn(name = LoggedGamesContract.LoggedGamesColumns.GAME_ID, key = true)
    int gameID;

    @StorIOContentResolverColumn(name = LoggedGamesContract.LoggedGamesColumns.PLAYER_ID)
    int player_id;

    @StorIOContentResolverColumn(name = LoggedGamesContract.LoggedGamesColumns.DECK_ID)
    int deck_id;

    @StorIOContentResolverColumn(name = LoggedGamesContract.LoggedGamesColumns.LOCATION_ID)
    int location_id;

    @StorIOContentResolverColumn(name = LoggedGamesContract.LoggedGamesColumns.PLAYED_DATE)
    String played_date;

    @StorIOContentResolverColumn(name = LoggedGamesContract.LoggedGamesColumns.WIN_TYPE)
    String win_type;

    @StorIOContentResolverColumn(name = LoggedGamesContract.LoggedGamesColumns.PLAYER_SIDE)
    String side;

    @StorIOContentResolverColumn(name = LoggedGamesContract.LoggedGamesColumns.WIN_FLAG)
    String win_flag;

    @StorIOContentResolverColumn(name = LoggedGamesContract.LoggedGamesColumns.SCORE)
    String score;



    private Player playerOne;
    private Player playerTwo;
    private String locationName;
    private String winType;
    private String playedDate;
    private String winnerFlag = "Y";

    public LoggedGame(){}

    public LoggedGame(int gameID, int player_id, int deck_id, int location_id, String played_date, String win_type, String side, String win_flag, String score) {
        this.gameID = gameID;
        this.player_id = player_id;
        this.deck_id = deck_id;
        this.location_id = location_id;
        this.played_date = played_date;
        this.win_type = win_type;
        this.side = side;
        this.win_flag = win_flag;
        this.score = score;
    }

    public LoggedGame(String locationName, String playedDate, String winType){
        this.locationName = locationName;
        this.playedDate = playedDate;
        this.winType = winType;
    }
    public LoggedGame(Player playerOne, Player playerTwo, String locationName, String playedDate, int gameID, String winType) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.locationName = locationName;
        this.playedDate = playedDate;
        this.gameID = gameID;
        this.winType = winType;
    }



    public LoggedGame(Cursor loggedGamesCursor){
        //TODO: Load game data directly from cursor
    }

    public int getGameID() {
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
    protected LoggedGame(Parcel in) {
        gameID = in.readInt();
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
        dest.writeInt(gameID);
        dest.writeValue(playerOne);
        dest.writeValue(playerTwo);
        dest.writeString(locationName);
        dest.writeString(winType);
        dest.writeString(playedDate);
        dest.writeString(winnerFlag);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<LoggedGame> CREATOR = new Parcelable.Creator<LoggedGame>() {
        @Override
        public LoggedGame createFromParcel(Parcel in) {
            return new LoggedGame(in);
        }

        @Override
        public LoggedGame[] newArray(int size) {
            return new LoggedGame[size];
        }
    };
}

