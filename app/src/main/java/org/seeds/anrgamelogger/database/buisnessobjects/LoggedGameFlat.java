package org.seeds.anrgamelogger.database.buisnessobjects;

import android.os.Parcel;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteType;
import org.seeds.anrgamelogger.database.GameLoggerDatabase.Views;

@StorIOSQLiteType(table= "temp." + Views.LOGGED_GAMES_FLAT_VIEW)
public class LoggedGameFlat implements android.os.Parcelable {

    public interface LoggedGamesFlatColumns {
        String GAME_ID = "gameid";
        String LOCATION_NAME = "locationname";
        String WIN_TYPE = "wintype";
        String PLAYED_DATE = "playeddate";
        String PLAYER_ONE_NAME = "playeronename";
        String PLAYER_ONE_SCORE = "playeronescore";
        String PLAYER_ONE_WIN_FLAG = "playeronewinflag";
        String PLAYER_ONE_DECK_NAME = "playerOneDeckName";
        String PLAYER_ONE_ID_NAME = "playeroneID";
        String PLAYER_ONE_NRDB_CODE = "playeronenrdbcode";
        String PLAYER_TWO_NAME = "playertwoname";
        String PLAYER_TWO_SCORE = "playertwoscore";
        String PLAYER_TWO_WIN_FLAG = "playertwowinflag";
        String PLAYER_TWO_DECK_NAME = "playertwodeckname";
        String PLAYER_TWO_ID_NAME = "playertwoid";
        String PLAYER_TWO_NRDB_CODE = "playertwonrdbcode";
        String PLAYER_ONE_ID_IMAGE = "playeroneidimage";
        String PLAYER_TWO_ID_IMAGE = "playertwoidimage";
    }

    @StorIOSQLiteColumn(name = LoggedGamesFlatColumns.GAME_ID, key = true)
    public Integer gameID;

    @StorIOSQLiteColumn(name = LoggedGamesFlatColumns.PLAYER_ONE_NAME)
    public String pO_Name;

    @StorIOSQLiteColumn(name = LoggedGamesFlatColumns.LOCATION_NAME)
    public String locationName;

    @StorIOSQLiteColumn(name = LoggedGamesFlatColumns.PLAYED_DATE)
    public String playedDate;

    @StorIOSQLiteColumn(name = LoggedGamesFlatColumns.PLAYER_ONE_DECK_NAME)
    public String pO_DeckName;

    @StorIOSQLiteColumn(name = LoggedGamesFlatColumns.PLAYER_ONE_ID_NAME)
    public String pO_Identity;

    @StorIOSQLiteColumn(name = LoggedGamesFlatColumns.PLAYER_ONE_ID_IMAGE)
    public byte[] pO_IdentityImage;

    @StorIOSQLiteColumn(name = LoggedGamesFlatColumns.PLAYER_ONE_SCORE)
    public Integer    pO_Score;

    @StorIOSQLiteColumn(name = LoggedGamesFlatColumns.PLAYER_ONE_WIN_FLAG)
    public String pO_WinFlag;

    @StorIOSQLiteColumn(name = LoggedGamesFlatColumns.PLAYER_TWO_NAME)
    public String pT_Name;

    @StorIOSQLiteColumn(name = LoggedGamesFlatColumns.PLAYER_TWO_DECK_NAME)
    public String pT_DeckName;

    @StorIOSQLiteColumn(name = LoggedGamesFlatColumns.PLAYER_TWO_ID_NAME)
    public String pT_Identity;

    @StorIOSQLiteColumn(name = LoggedGamesFlatColumns.PLAYER_TWO_ID_IMAGE)
    public byte[] pT_IdentityImage;

    @StorIOSQLiteColumn(name = LoggedGamesFlatColumns.PLAYER_TWO_SCORE)
    public Integer    pT_Score;

    @StorIOSQLiteColumn(name = LoggedGamesFlatColumns.PLAYER_TWO_WIN_FLAG)
    public String pT_WinFlag;

    @StorIOSQLiteColumn(name = LoggedGamesFlatColumns.WIN_TYPE)
    public String winType;

    public LoggedGameFlat(){}

    public LoggedGameFlat(Integer gameID, String pO_Name, String locationName, String playedDate, String pO_DeckName, String pO_Identity, byte[] pO_IdentityImage, Integer pO_Score, String pO_WinFlag, String pT_Name, String pT_DeckName, String pT_Identity, byte[] pT_IdentityImage, Integer pT_Score, String pT_WinFlag, String winType) {
        this.gameID = gameID;
        this.pO_Name = pO_Name;
        this.locationName = locationName;
        this.playedDate = playedDate;
        this.pO_DeckName = pO_DeckName;
        this.pO_Identity = pO_Identity;
        this.pO_IdentityImage = null;//pO_IdentityImage;
        this.pO_Score = pO_Score;
        this.pO_WinFlag = pO_WinFlag;
        this.pT_Name = pT_Name;
        this.pT_DeckName = pT_DeckName;
        this.pT_Identity = pT_Identity;
        this.pT_IdentityImage = null;//pT_IdentityImage;
        this.pT_Score = pT_Score;
        this.pT_WinFlag = pT_WinFlag;
        this.winType = winType;
    }

    public LoggedGameFlat(String pO_Name, String locationName, String playedDate, String pO_DeckName, String pO_Identity, Integer pO_Score, String pO_WinFlag, String pT_Name, String pT_DeckName, String pT_Identity, Integer pT_Score, String pT_WinFlag, String winType) {
     //   this.gameID = gameID;
        this.pO_Name = pO_Name;
        this.locationName = locationName;
        this.playedDate = playedDate;
        this.pO_DeckName = pO_DeckName;
        this.pO_Identity = pO_Identity;
        this.pO_Score = pO_Score;
        this.pO_WinFlag = pO_WinFlag;
        this.pT_Name = pT_Name;
        this.pT_DeckName = pT_DeckName;
        this.pT_Identity = pT_Identity;
        this.pT_Score = pT_Score;
        this.pT_WinFlag = pT_WinFlag;
        this.winType = winType;
    }

    public String getpO_Name() {
        return pO_Name;
    }

    public void setpO_Name(String pO_Name) {
        this.pO_Name = pO_Name;
    }

    public String getpT_Name() {
        return pT_Name;
    }

    public void setpT_Name(String pT_Name) {
        this.pT_Name = pT_Name;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
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

    public byte[] getpO_IdentityImage() {
        return pO_IdentityImage;
    }

    public void setpO_IdentityImage(byte[] pO_IdentityImage) {
        this.pO_IdentityImage = pO_IdentityImage;
    }

    public Integer getpO_Score() {
        return pO_Score;
    }

    public void setpO_Score(Integer pO_Score) {
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

    public byte[] getpT_IdentityImage() {
        return pT_IdentityImage;
    }

    public void setpT_IdentityImage(byte[] pT_IdentityImage) {
        this.pT_IdentityImage = pT_IdentityImage;
    }

    public Integer getpT_Score() {
        return pT_Score;
    }

    public void setpT_Score(Integer pT_Score) {
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
        if(pO_WinFlag.matches("Y")){
            ret = pO_Name;
        }else{
            ret = pT_Name;
        }
        return ret;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.gameID);
        dest.writeString(this.pO_Name);
        dest.writeString(this.locationName);
        dest.writeString(this.playedDate);
        dest.writeString(this.pO_DeckName);
        dest.writeString(this.pO_Identity);
        dest.writeByteArray(this.pO_IdentityImage);
        dest.writeValue(this.pO_Score);
        dest.writeString(this.pO_WinFlag);
        dest.writeString(this.pT_Name);
        dest.writeString(this.pT_DeckName);
        dest.writeString(this.pT_Identity);
        dest.writeByteArray(this.pT_IdentityImage);
        dest.writeValue(this.pT_Score);
        dest.writeString(this.pT_WinFlag);
        dest.writeString(this.winType);

    }

    protected LoggedGameFlat(Parcel in) {
        this.gameID = (Integer) in.readValue(Integer.class.getClassLoader());
        this.pO_Name = in.readString();
        this.locationName = in.readString();
        this.playedDate = in.readString();
        this.pO_DeckName = in.readString();
        this.pO_Identity = in.readString();
        this.pO_IdentityImage = in.createByteArray();
        this.pO_Score = (Integer) in.readValue(Integer.class.getClassLoader());
        this.pO_WinFlag = in.readString();
        this.pT_Name = in.readString();
        this.pT_DeckName = in.readString();
        this.pT_Identity = in.readString();
        this.pT_IdentityImage = in.createByteArray();
        this.pT_Score = (Integer) in.readValue(Integer.class.getClassLoader());
        this.pT_WinFlag = in.readString();
        this.winType = in.readString();
    }

    public static final Creator<LoggedGameFlat> CREATOR = new Creator<LoggedGameFlat>() {
        @Override
        public LoggedGameFlat createFromParcel(Parcel source) {
            return new LoggedGameFlat(source);
        }

        @Override
        public LoggedGameFlat[] newArray(int size) {
            return new LoggedGameFlat[size];
        }
    };
}
