package org.seeds.anrgamelogger.database.buisnessobjects;


import android.provider.BaseColumns;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteType;
import org.seeds.anrgamelogger.database.GameLoggerDatabase.Tables;
import org.seeds.anrgamelogger.database.contracts.LoggedGameOverviewsContract;

/**
 * Created by Tomas Seymour-Turner on 19/03/2017.
 */

@StorIOSQLiteType(table= Tables.LOGGED_GAME_OVERVIEWS)
public class LoggedGameOverview {
//public class LoggedGameOverview implements Parcelable {

    //TODO: How am I going to do the Game ID??
    @StorIOSQLiteColumn(name = LoggedGameOverviewsContract.LoggedGameOverviewsColumns.GAME_ID)
    public Integer gameID;

    @StorIOSQLiteColumn(name = LoggedGameOverviewsContract.LoggedGameOverviewsColumns.LOCATION_ID)
    public Integer location_id;

    String location_name;

    @StorIOSQLiteColumn(name = LoggedGameOverviewsContract.LoggedGameOverviewsColumns.PLAYED_DATE)
    public String played_date;

    @StorIOSQLiteColumn(name = LoggedGameOverviewsContract.LoggedGameOverviewsColumns.WIN_TYPE)
    public String win_type;

    @StorIOSQLiteColumn(name = BaseColumns._ID, key = true)
    public Integer rowid;

    private String winning_side;

    public LoggedGameOverview(){}

    public LoggedGameOverview(Integer gameID, Integer location_id,
                              String played_date, String win_type, Integer gameNumber) {
        this.gameID = gameID;
        this.location_id = location_id;
        this.played_date = played_date;
        this.win_type = win_type;
        this.gameID = gameNumber;
    }

    public LoggedGameOverview(String location_name, String played_date, String win_type, Integer gameNumber, String winning_side) {
        this.location_name = location_name;
        this.played_date = played_date;
        this.win_type = win_type;
        this.gameID = gameNumber;
        this.winning_side = winning_side;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
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

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getWinning_side() {
        return winning_side;
    }

    public void setWinning_side(String winning_side) {
        this.winning_side = winning_side;
    }

    public Integer getRowid() {
        return rowid;
    }

    public void setRowid(Integer rowid) {
        this.rowid = rowid;
    }
}

