package org.seeds.anrgamelogger.model;

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
}
