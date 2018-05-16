package org.seeds.anrgamelogger.buisnessobjects;

import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverType;

import org.seeds.anrgamelogger.database.contracts.LoggedGamesContract;
import org.seeds.anrgamelogger.database.contracts.LoggedGamesFlatViewContract;

@StorIOContentResolverType(uri = "content://" + LoggedGamesFlatViewContract.CONTENT_AUTHORITY + "/" + LoggedGamesFlatViewContract.PATH_LOGGED_GAMES)
public class LoggedGameFlat {


    @StorIOContentResolverColumn(name = LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.GAME_ID, key = true)
    int gameID;

    @StorIOContentResolverColumn(name = LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.LOCATION_NAME)
    String locationName;

    @StorIOContentResolverColumn(name = LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYED_DATE)
    String playedDate;

    @StorIOContentResolverColumn(name = LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_DECK_NAME)
    String pO_DeckName;

    @StorIOContentResolverColumn(name = LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_ID_NAME)
    String pO_Identity;

    @StorIOContentResolverColumn(name = LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_ID_IMAGE)
    Byte[] pO_IdentityImage;

    @StorIOContentResolverColumn(name = LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_SCORE)
    int    pO_Score;

    @StorIOContentResolverColumn(name = LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_WIN_FLAG)
    String pO_WinFlag;

    @StorIOContentResolverColumn(name = LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_DECK_NAME)
    String pT_DeckName;

    @StorIOContentResolverColumn(name = LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_ID_NAME)
    String pT_Identity;

    @StorIOContentResolverColumn(name = LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_ID_IMAGE)
    Byte[] pT_IdentityImage;

    @StorIOContentResolverColumn(name = LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_SCORE)
    int    pT_Score;

    @StorIOContentResolverColumn(name = LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_WIN_FLAG)
    String pT_WinFlag;

    @StorIOContentResolverColumn(name = LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.WIN_TYPE)
    String winType;

    public LoggedGameFlat(){}

    public LoggedGameFlat(int gameID, String locationName, String playedDate, String pO_DeckName, String pO_Identity, Byte[] pO_IdentityImage, int pO_Score, String pO_WinFlag, String pT_DeckName, String pT_Identity, Byte[] pT_IdentityImage, int pT_Score, String pT_WinFlag, String winType) {
        this.gameID = gameID;
        this.locationName = locationName;
        this.playedDate = playedDate;
        this.pO_DeckName = pO_DeckName;
        this.pO_Identity = pO_Identity;
        this.pO_IdentityImage = pO_IdentityImage;
        this.pO_Score = pO_Score;
        this.pO_WinFlag = pO_WinFlag;
        this.pT_DeckName = pT_DeckName;
        this.pT_Identity = pT_Identity;
        this.pT_IdentityImage = pT_IdentityImage;
        this.pT_Score = pT_Score;
        this.pT_WinFlag = pT_WinFlag;
        this.winType = winType;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getPlayedDate() {
        return playedDate;
    }

    public void setPlayedDate(String playedDate) {
        this.playedDate = playedDate;
    }

    public String getpO_DeckName() {
        return pO_DeckName;
    }

    public void setpO_DeckName(String pO_DeckName) {
        this.pO_DeckName = pO_DeckName;
    }

    public String getpO_Identity() {
        return pO_Identity;
    }

    public void setpO_Identity(String pO_Identity) {
        this.pO_Identity = pO_Identity;
    }

    public Byte[] getpO_IdentityImage() {
        return pO_IdentityImage;
    }

    public void setpO_IdentityImage(Byte[] pO_IdentityImage) {
        this.pO_IdentityImage = pO_IdentityImage;
    }

    public int getpO_Score() {
        return pO_Score;
    }

    public void setpO_Score(int pO_Score) {
        this.pO_Score = pO_Score;
    }

    public String getpO_WinFlag() {
        return pO_WinFlag;
    }

    public void setpO_WinFlag(String pO_WinFlag) {
        this.pO_WinFlag = pO_WinFlag;
    }

    public String getpT_DeckName() {
        return pT_DeckName;
    }

    public void setpT_DeckName(String pT_DeckName) {
        this.pT_DeckName = pT_DeckName;
    }

    public String getpT_Identity() {
        return pT_Identity;
    }

    public void setpT_Identity(String pT_Identity) {
        this.pT_Identity = pT_Identity;
    }

    public Byte[] getpT_IdentityImage() {
        return pT_IdentityImage;
    }

    public void setpT_IdentityImage(Byte[] pT_IdentityImage) {
        this.pT_IdentityImage = pT_IdentityImage;
    }

    public int getpT_Score() {
        return pT_Score;
    }

    public void setpT_Score(int pT_Score) {
        this.pT_Score = pT_Score;
    }

    public String getpT_WinFlag() {
        return pT_WinFlag;
    }

    public void setpT_WinFlag(String pT_WinFlag) {
        this.pT_WinFlag = pT_WinFlag;
    }

    public String getWinType() {
        return winType;
    }

    public void setWinType(String winType) {
        this.winType = winType;
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

}
