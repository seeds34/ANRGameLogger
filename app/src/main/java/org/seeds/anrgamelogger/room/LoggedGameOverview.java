package org.seeds.anrgamelogger.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import org.seeds.anrgamelogger.room.GameLoggerDatabase.Tables;

/**
 * Created by Tomas Seymour-Turner on 19/03/2017.
 */

@Entity(tableName = Tables.LOGGED_GAME_OVERVIEWS)
public class LoggedGameOverview {


    public interface LoggedGameOverviewsColumns{
        String GAME_ID = "gameid";
        String LOCATION_ID = "locationid";
        String WIN_TYPE = "wintype";
        String PLAYED_DATE = "playeddate";
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = LoggedGameOverviewsColumns.GAME_ID)
    public int gameID;

    @ColumnInfo(name = LoggedGameOverviewsColumns.LOCATION_ID)
    public int location_id;

    String location_name;

    @ColumnInfo(name = LoggedGameOverviewsColumns.PLAYED_DATE)
    public String played_date;

    @ColumnInfo(name = LoggedGameOverviewsColumns.WIN_TYPE)
    public String win_type;


    public LoggedGameOverview(@NonNull int gameID, int location_id, String location_name,
        String played_date, String win_type) {
        this.gameID = gameID;
        this.location_id = location_id;
        this.location_name = location_name;
        this.played_date = played_date;
        this.win_type = win_type;
    }

    @NonNull
    public int getGameID() {
        return gameID;
    }

    public void setGameID(@NonNull int gameID) {
        this.gameID = gameID;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
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
}

