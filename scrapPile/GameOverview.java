package org.seeds.anrgamelogger.model;

/**
 * Created by Tomas Seymour-Turner on 15/11/2017.
 */

public class GameOverview implements ViewData {

    private String location;
    private String playedDate;
    private String winningSide;
    private String winType;

    public GameOverview(String location, String playedDate, String wiiningSide) {
        this.location = location;
        this.playedDate = playedDate;
        this.winningSide = wiiningSide;
    }


    public String getLocation() {
        return location;
    }

    public String getPlayedDate() {
        return playedDate;
    }
}
