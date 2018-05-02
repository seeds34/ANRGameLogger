package org.seeds.anrgamelogger.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverType;
import java.io.Serializable;
import org.seeds.anrgamelogger.addgame.ViewData;
import org.seeds.anrgamelogger.database.contracts.PlayersContract;
import org.seeds.anrgamelogger.database.contracts.PlayersContract.PlayersColumns;

/**
 * Created by Tomas Seymour-Turner on 19/03/2017.
 */

@StorIOContentResolverType(uri = "content://" + PlayersContract.CONTENT_AUTHORITY + "/" + PlayersContract.PATH_PLAYERS)
public class Player implements Serializable, ViewData {

    @StorIOContentResolverColumn(name = PlayersColumns.PLAYER_NAME, key = true)
    public String name;

    private String deck;
    private int score;
    private String winnerFlag;
    private static final long SERAILVERSIONUID = 1L;
    private  byte[]  imageByteArray;
    private String identityName;

    public Player(){}

    public Player(String name){
        this.name = name;
    }

    public Player(String name, String deck) {
        this.name = name;
        this.deck = deck;
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

    /*Genrated at: http://www.parcelabler.com/ */
    protected Player(Parcel in) {
        name = in.readString();
        deck = in.readString();
        score = in.readInt();
        winnerFlag = in.readString();
        identityName = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(deck);
        dest.writeInt(score);
        dest.writeString(winnerFlag);
        dest.writeString(identityName);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };


}
