package org.seeds.anrgamelogger.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import org.seeds.anrgamelogger.database.GameLoggerDatabase.Tables;
import org.seeds.anrgamelogger.database.entities.Location.LocationsColumns;
import org.seeds.anrgamelogger.database.entities.LoggedGameOverview.LoggedGameOverviewsColumns;

/**
 * Created by Tomas Seymour-Turner on 19/03/2017.
 */

@Entity(tableName = Tables.LOGGED_GAME_OVERVIEWS, foreignKeys = @ForeignKey(entity = Location.class, parentColumns = LocationsColumns.ID, childColumns = LoggedGameOverviewsColumns.LOCATION_ID))
public class LoggedGameOverview {

    public interface LoggedGameOverviewsColumns{
        String GAME_ID = "gameid";
        String LOCATION_ID = "locationid";
        String WIN_TYPE = "wintype";
        String PLAYED_DATE = "playeddate";
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = LoggedGameOverviewsColumns.GAME_ID)
    public int gameID;

    @ColumnInfo(name = LoggedGameOverviewsColumns.LOCATION_ID)
    public int location_id;

    @ColumnInfo(name = LoggedGameOverviewsColumns.PLAYED_DATE)
    public String played_date;

    @ColumnInfo(name = LoggedGameOverviewsColumns.WIN_TYPE)
    public String win_type;

    public LoggedGameOverview(@NonNull int gameID, int location_id, String played_date,
        String win_type) {
        this.gameID = gameID;
        this.location_id = location_id;
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

    @Override
    public String toString() {
        return "LoggedGameOverview{" +
            "gameID=" + gameID +
            ", location_id=" + location_id +
            ", played_date='" + played_date + '\'' +
            ", win_type='" + win_type + '\'' +
            '}';
    }
}

