package org.seeds.anrgamelogger.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Tomas Seymour-Turner on 19/03/2017.
 */

public class Player implements Serializable{

    private String name;
    private String deck;
    private int score;
    private String winnerFlag;
    private static final long SERAILVERSIONUID = 1L;
    private  byte[]  imageByteArray;
    private String identityName;

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
