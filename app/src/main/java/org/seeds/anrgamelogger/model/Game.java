package org.seeds.anrgamelogger.model;

/**
 * Created by Tomas Seymour-Turner on 15/11/2017.
 */

public abstract class Game {

    final String gameNumber;

    protected Game(String gameNumber) {
        this.gameNumber = gameNumber;
    }

    public String getGameNumber(){
        return gameNumber;
    };



}
