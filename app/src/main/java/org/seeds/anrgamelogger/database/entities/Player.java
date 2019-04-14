package org.seeds.anrgamelogger.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import java.io.Serializable;
import org.seeds.anrgamelogger.database.GameLoggerDatabase.Tables;


@Entity(tableName = Tables.PLAYERS)
public class Player implements Serializable {

    public interface PlayersColumns{
        String PLAYER_NAME= "playername";
        String JNET_ID = "jnetid";
        String PLAYER_NICK_NAME = "nickname"; //Unique Name
        String ID = "id";
    }

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = PlayersColumns.ID)
    public int id;

    @NonNull
    @ColumnInfo(name = PlayersColumns.PLAYER_NAME)
    public String name;

    @ColumnInfo(name = PlayersColumns.JNET_ID)
    public String jnetName;

    @ColumnInfo(name = PlayersColumns.PLAYER_NICK_NAME)
    public String nickName;

    public Player(@NonNull int id, @NonNull String name, String jnetName, String nickName) {
        this.id = id;
        this.name = name;
        this.jnetName = jnetName;
        this.nickName = nickName;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getJnetName() {
        return jnetName;
    }

    public void setJnetName(String jnetName) {
        this.jnetName = jnetName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
