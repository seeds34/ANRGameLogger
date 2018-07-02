package org.seeds.anrgamelogger.model;

import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverType;
import org.seeds.anrgamelogger.database.contracts.LoggedGameOverviewsContract;

/**
 * Created by Tomas Seymour-Turner on 19/03/2017.
 */

@StorIOContentResolverType(uri = "content://" + LoggedGameOverviewsContract.CONTENT_AUTHORITY + "/" + LoggedGameOverviewsContract.PATH_LOGGED_GAMES_OVERVIEW)
public class LoggedGameOverviews {
//public class LoggedGameOverviews implements Parcelable {

    //TODO: How am I going to do the Game ID??
    @StorIOContentResolverColumn(name = LoggedGameOverviewsContract.LoggedGameOverviewsColumns.GAME_ID, key = true)
    int gameID;

    @StorIOContentResolverColumn(name = LoggedGameOverviewsContract.LoggedGameOverviewsColumns.LOCATION_ID)
    int location_id;

    @StorIOContentResolverColumn(name = LoggedGameOverviewsContract.LoggedGameOverviewsColumns.PLAYED_DATE)
    String played_date;

    @StorIOContentResolverColumn(name = LoggedGameOverviewsContract.LoggedGameOverviewsColumns.WIN_TYPE)
    String win_type;

    public LoggedGameOverviews(){}

    public LoggedGameOverviews(int gameID, int location_id,
        String played_date, String win_type) {
        this.gameID = gameID;
        this.location_id = location_id;
        this.played_date = played_date;
        this.win_type = win_type;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getPlayed_date() {
        return played_date;
    }

    public void setPlayed_date(String played_date) {
        this.played_date = played_date;
    }

    public String getWin_type() {
        return win_type;
    }

    public void setWin_type(String win_type) {
        this.win_type = win_type;
    }

//    private Player playerOne;
//    private Player playerTwo;
//    private String locationName;
//    private String winType;
//    private String playedDate;
//    private String winnerFlag = "Y";

//    public LoggedGameOverviews(){}
//
//    public LoggedGameOverviews(int gameID, int player_id, int deck_id, int location_id, String played_date, String win_type, String side, String win_flag, String score) {
//        this.gameID = gameID;
//        this.player_id = player_id;
//        this.deck_id = deck_id;
//        this.location_id = location_id;
//        this.played_date = played_date;
//        this.win_type = win_type;
//        this.side = side;
//        this.win_flag = win_flag;
//        this.score = score;
//    }
//
//    public LoggedGameOverviews(String locationName, String playedDate, String winType){
//        this.locationName = locationName;
//        this.playedDate = playedDate;
//        this.winType = winType;
//    }
//    public LoggedGameOverviews(Player playerOne, Player playerTwo, String locationName, String playedDate, int gameID, String winType) {
//        this.playerOne = playerOne;
//        this.playerTwo = playerTwo;
//        this.locationName = locationName;
//        this.playedDate = playedDate;
//        this.gameID = gameID;
//        this.winType = winType;
//    }
//
//
//
//    public LoggedGameOverviews(Cursor loggedGamesCursor){
//        //TODO: Load game data directly from cursor
//    }
//
//    public int getGameID() {
//        return gameID;
//    }
//
//    public Player getPlayerOne() {
//        return playerOne;
//    }
//
//    public Player getPlayerTwo() {
//        return playerTwo;
//    }
//
//    public String getLocationName() {
//        return locationName;
//    }
//
//    public String getWinnerName() {
//        String ret;
//        if(playerOne.isWinner().toUpperCase() == winnerFlag || playerOne.isWinner().toUpperCase() == winnerFlag){
//            ret = playerOne.getName();
//        }else{
//            ret = playerTwo.getName();
//        }
//        return ret;
//    }
//
//    public String getWinType() {
//        return winType;
//    }
//
//    public String getPlayedDate() {
//        return playedDate;
//    }
//
//
//    /*Genrated at: http://www.parcelabler.com/ */
//    protected LoggedGameOverviews(Parcel in) {
//        gameID = in.readInt();
//        playerOne = (Player) in.readValue(Player.class.getClassLoader());
//        playerTwo = (Player) in.readValue(Player.class.getClassLoader());
//        locationName = in.readString();
//        winType = in.readString();
//        playedDate = in.readString();
//        winnerFlag = in.readString();
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(gameID);
//        dest.writeValue(playerOne);
//        dest.writeValue(playerTwo);
//        dest.writeString(locationName);
//        dest.writeString(winType);
//        dest.writeString(playedDate);
//        dest.writeString(winnerFlag);
//    }
//
//    @SuppressWarnings("unused")
//    public static final Parcelable.Creator<LoggedGameOverviews> CREATOR = new Parcelable.Creator<LoggedGameOverviews>() {
//        @Override
//        public LoggedGameOverviews createFromParcel(Parcel in) {
//            return new LoggedGameOverviews(in);
//        }
//
//        @Override
//        public LoggedGameOverviews[] newArray(int size) {
//            return new LoggedGameOverviews[size];
//        }
//    };
}

