package org.seeds.anrgamelogger.model;

/**
 * Created by Tomas Seymour-Turner on 15/11/2017.
 */

public class GameOverview extends Game {

    private String pOneName;
    private String pTwoName;
    private byte[] pOneIDImage;
    private byte[] pTeoIDImage;
    private String location;
    private String playedDate;

    public GameOverview(String gameNumber, String pOneName, String pTwoName, byte[] pOneIDImage, byte[] pTeoIDImage, String location, String playedDate) {
        super(gameNumber);
        this.pOneName = pOneName;
        this.pTwoName = pTwoName;
        this.pOneIDImage = pOneIDImage;
        this.pTeoIDImage = pTeoIDImage;
        this.location = location;
        this.playedDate = playedDate;
    }

    public String getpOneName() {
        return pOneName;
    }

    public String getpTwoName() {
        return pTwoName;
    }

    public byte[] getpOneIDImage() {
        return pOneIDImage;
    }

    public byte[] getpTeoIDImage() {
        return pTeoIDImage;
    }

    public String getLocation() {
        return location;
    }

    public String getPlayedDate() {
        return playedDate;
    }
}
