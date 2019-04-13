package org.seeds.anrgamelogger.database.buisnessobjects;

import android.provider.BaseColumns;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverType;
import java.io.Serializable;
import org.seeds.anrgamelogger.database.contracts.PlayersContract;
import org.seeds.anrgamelogger.database.contracts.PlayersContract.PlayersColumns;
import org.seeds.anrgamelogger.model.ViewData;

/**
 * Created by Tomas Seymour-Turner on 19/03/2017.
 */


//TODO: Need to change so its only refrance to Player DB but need to change all the LOCAL LOGGED GAME Stuff and logic
@StorIOContentResolverType(uri = "content://" + PlayersContract.CONTENT_AUTHORITY + "/" + PlayersContract.PATH_PLAYERS)
public class Player implements Serializable, ViewData {

    @StorIOContentResolverColumn(name = PlayersColumns.PLAYER_NAME, key = true)
    public String name;

    @StorIOContentResolverColumn(name = PlayersColumns.JNET_ID)
    public String jnetName;

    @StorIOContentResolverColumn(name = PlayersColumns.PLAYER_NICK_NAME)
    public String nickName;

    @StorIOContentResolverColumn(name = BaseColumns._ID)
    public int rowid;

    private String deck;
    private int score;
    private String winnerFlag;
    //private static final long SERAILVERSIONUID = 1L;
    private  byte[]  imageByteArray;
    private String identityName;
    private String side;

    public Player(){}

    public Player(String name){
        this.name = name;
    }

    public Player(String name, String jnetName, String nickName) {
        this.name = name;
        this.jnetName = jnetName;
        this.nickName = nickName;
    }

    public Player(String name, String deck) {
        this.name = name;
        this.deck = deck;
    }

    public Player(String name, String deck, String identityName, String side) {
        this.name = name;
        this.deck = deck;
        this.identityName = identityName;
        this.side = side;
    }

    public Player(String name, String deck, int score,  byte[]  imageByteArray, String winnerFlag, String identityName) {
        this.name = name;
        this.deck = deck;
        this.score = score;
        this.imageByteArray = imageByteArray;
        this.winnerFlag = winnerFlag;
        this.identityName = identityName;
    }

    public String getName() {
        return name;
    }

    public String getDeck() {
        return deck;
    }

    public int getScore() {
        return score;
    }

    public String isWinner() {
        return winnerFlag;
    }

    public  byte[]  getImageByteArray(){
        return imageByteArray;
    }

    public void setName(String name) {
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

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }

    public String toString(){
        return
            "Name = " + name + "\n" +
            "Nickname = " + nickName + "\n" +
                "jnetid = " +  jnetName + "\n" +
                "rowid + " + rowid;
    }

//
//    /*Genrated at: http://www.parcelabler.com/ */
//    protected Player(Parcel in) {
//        name = in.readString();
//        deck = in.readString();
//        score = in.readInt();
//        winnerFlag = in.readString();
//        identityName = in.readString();
//    }
//
//    public int describeContents() {
//        return 0;
//    }
//
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(name);
//        dest.writeString(deck);
//        dest.writeInt(score);
//        dest.writeString(winnerFlag);
//        dest.writeString(identityName);
//    }
//
//    @SuppressWarnings("unused")
//    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
//        @Override
//        public Player createFromParcel(Parcel in) {
//            return new Player(in);
//        }
//
//        @Override
//        public Player[] newArray(int size) {
//            return new Player[size];
//        }
//    };


}
