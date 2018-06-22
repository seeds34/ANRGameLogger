package org.seeds.anrgamelogger.addgame.model;

public class PlayerViewData {

    private String identityName;
    private String playerNames;
    private String deckName;
    private String deckVersion;
    private String side;

    public PlayerViewData(String identityName, String playerNames, String deckName, String deckVersion, String side) {
        this.identityName = identityName;
        this.playerNames = playerNames;
        this.deckName = deckName;
        this.deckVersion = deckVersion;
        this.side = side;
    }

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }

    public String getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(String playerNames) {
        this.playerNames = playerNames;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public String getDeckVersion() {
        return deckVersion;
    }

    public void setDeckVersion(String deckVersion) {
        this.deckVersion = deckVersion;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
