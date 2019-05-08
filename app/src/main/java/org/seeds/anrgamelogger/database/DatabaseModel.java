package org.seeds.anrgamelogger.database;

import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import io.reactivex.Maybe;
import java.util.List;
import org.seeds.anrgamelogger.database.dao.LocationDao;
import org.seeds.anrgamelogger.database.dao.LoggedGameOverviewDao;
import org.seeds.anrgamelogger.database.entities.Deck;
import org.seeds.anrgamelogger.database.entities.Identity;
import org.seeds.anrgamelogger.database.entities.Location;
import org.seeds.anrgamelogger.database.entities.LoggedGameOverview;
import org.seeds.anrgamelogger.database.entities.LoggedGamePlayer;
import org.seeds.anrgamelogger.database.entities.Player;


/**
 * Created by Tomas Seymour-Turner on 21/02/2018.
 */

//TODO: Should the DB Setup e.g. Add IDs and Images code be in the model??
public class DatabaseModel {

  private final String LOG_TAG = this.getClass().getName();
  private GameLoggerDatabase database;

  public DatabaseModel(GameLoggerDatabase gld){
    database = gld;
  }


  public boolean isIdentitiesTableEmpty(){
    return isTableEmpty(Identityties.URI_TABLE);
  }

  public boolean isLoggedgamesTableEmpty(){
    return isTableEmpty(LoggedGameOverviewsContract.URI_TABLE);
  }

  public boolean isTableEmpty(Uri tableUri){

    boolean ret = false;

    Log.d(LOG_TAG,"Starting is Table Empty Check for " + tableUri.toString());

    Cursor queryResult = storIOContentResolver
        .get()
        .cursor()
        .withQuery(Query.builder()
            .uri(tableUri)
            .build())
        .prepare()
        .executeAsBlocking();

    if(queryResult != null && queryResult.getCount() > 0){
      ret = false;
    }else{
      ret = true;
    }

    Log.d(LOG_TAG,"Finished is Table Empty Check for " + tableUri.toString() + " Returning: " + ret);

    return ret;
  }

  //----------  Identities ----------//

  public List<Identity> getAllIdentities(){
    return storIOContentResolver
        .get()
        .listOfObjects(Identity.class)
        .withQuery(Query.builder()
            .uri(IdentitiesContract.URI_TABLE)
                .sortOrder(IdentitiesContract.IdentitiesColumns.IDENTITY_FACTION + " asc")
            .build())
        .prepare()
        .executeAsBlocking();
  }

  public Identity getIdentity(String identityName){
    return storIOContentResolver
        .get()
        .object(Identity.class)
        .withQuery(Query.builder()
            .uri(IdentitiesContract.URI_TABLE)
            .where(IdentitiesColumns.IDENTITY_NAME + " = ?")
            .whereArgs(identityName)
            .sortOrder(IdentitiesContract.IdentitiesColumns.IDENTITY_FACTION + " asc")
            .build())
        .prepare()
        .executeAsBlocking();
  }

  public PutResult insertIdentity(Identity i) {
    return storIOContentResolver.put()
            .object(i)
            //.withPutResolver(new CustomIdentityPutResolver())
            .prepare()
            .executeAsBlocking();
  }

  public PutResults insertIdentities(List<Identity> i) {
    return storIOContentResolver
            .put()
            .objects(i)
            .prepare()
            .executeAsBlocking();
  }

  public PutResult insertIdentitieImage(CardImage ci){
    return storIOContentResolver
            .put()
            .object(ci)
            .prepare()
            .executeAsBlocking();
  }

  public PutResults insertIdedentiteImages(List<CardImage> ci){
    return storIOContentResolver
            .put()
            .objects(ci)
            .prepare()
            .executeAsBlocking();
  }

  //No Get IdentityImage as Image is part of the identity.

  //----------  Players ----------//

  public List<Player> getAllPlayers() {
    return storIOContentResolver
        .get()
        .listOfObjects(Player.class)
        .withQuery(Query.builder()
            .uri(PlayersContract.URI_TABLE)
            .build())
        .prepare()
        .executeAsBlocking();
  }

  public Player getPlayer(String playerName){
    return storIOContentResolver
        .get()
        .object(Player.class)
        .withQuery(Query.builder()
            .uri(PlayersContract.URI_TABLE)
            .where(PlayersColumns.PLAYER_NAME + " = ?")
            .whereArgs(playerName)
            .build())
        .prepare()
        .executeAsBlocking();
  }


  public PutResult insertPlayer(Player playerIn) throws StorIOException {
    return storIOContentResolver
            .put()
            .object(playerIn)
            .prepare()
            .executeAsBlocking();
  }

  //----------  Location ----------//

  public List<Location> getAllLocations() {
    return storIOContentResolver
        .get()
        .listOfObjects(Location.class)
        .withQuery(Query.builder()
            .uri(LocationsContract.URI_TABLE)
            .build())
        .prepare()
        .executeAsBlocking();
  }

  public Maybe<List<Location>> getLocation(String locationName){

    LocationDao ld = database.locationDao();


    return ld.findLocationyByName(locationName);

    }

  public PutResult insertLocation(Location locationIn){
    return storIOContentResolver
        .put()
        .object(locationIn)
        .prepare()
        .executeAsBlocking();
  }

  //----------  Deck ----------//

  public List<Deck> getAllDecks() {
    return storIOContentResolver
        .get()
        .listOfObjects(Deck.class)
        .withQuery(Query.builder()
            .uri(DecksContract.URI_TABLE)
            .build())
        .prepare()
        .executeAsBlocking();
  }

  public Deck getDeck(String deckName, String deckVersion, int identityNo){
    return storIOContentResolver
            .get()
            .object(Deck.class)
            .withQuery(Query.builder()
                    .uri(DecksContract.URI_TABLE)
                    .where(DecksContract.DecksColumns.DECK_NAME + " = ? AND " + DecksContract.DecksColumns.DECK_VERSION + " = ? AND " + DecksContract.DecksColumns.DECK_IDENTITY + " = ?")
                    .whereArgs(deckName, deckVersion, identityNo)
                    .build())
            .prepare()
            .executeAsBlocking();
  }

  public PutResult insertDeck(Deck deckIn){
    return storIOContentResolver
            .put()
            .object(deckIn)
            .prepare()
            .executeAsBlocking();
  }

  //----------  Logged Game ----------//

  public LoggedGameOverview getLoggedGame(int gameId){
    return storIOContentResolver
            .get()
            .object(LoggedGameOverview.class)
            .withQuery(Query.builder()
                    .uri(LoggedGameOverviewsContract.URI_TABLE)
                    .where(LoggedGameOverviewsContract.LoggedGameOverviewsColumns.GAME_ID + " = ?")
                    .whereArgs(gameId)
                    .build())
            .prepare()
            .executeAsBlocking();
  }

  public int getNextGameNo(){

    int ret = 1;
    if(isLoggedgamesTableEmpty() == false) {

      LoggedGameOverview lg = storIOContentResolver
              .get()
              .object(LoggedGameOverview.class)
              .withQuery(Query.builder()
                      .uri(LoggedGameOverviewsContract.URI_TABLE)
                      .columns("MAX(" + LoggedGameOverviewsContract.LoggedGameOverviewsColumns.GAME_ID + ")")
                      .build())
              .prepare()
              .executeAsBlocking();
      ret = lg.getGameID()+1;
    }
    return ret;
  }
  public PutResult insertLoggedGame(LoggedGameOverview loggedGameOverview){
    return storIOContentResolver
            .put()
            .object(loggedGameOverview)
            .prepare()
            .executeAsBlocking();
  }

  //----------  Logged Game Flat ----------//

  public List<LoggedGameFlat> getLoggedGameFlat(int listLength){

    LoggedGameOverviewDao d = database.loggedGameOverviewDao();
    d.getLoggedGame(1);

  }



  ////Insert Entire Game

  public void insertLoggedGame(LoggedGameOverview lgo, LoggedGamePlayer playerOne, LoggedGamePlayer playerTwo ){

    Log.d(LOG_TAG, ".insertLoggedGameN() : Starting");
    insertLoggedGamePlayer(playerOne);
    insertLoggedGamePlayer(playerTwo);

    Location location = getLocation(lgo.getLocation_name());

    if(location == null){
      insertLocation(new Location(lgo.getLocation_name()));
      location = getLocation(lgo.getLocation_name());
    }
    lgo.setLocation_id(location.getRowid());


    Log.d(LOG_TAG, ".insertLoggedGameN() : Inserting Overview");
    storIOContentResolver
            .put()
            .object(lgo)
            .prepare()
            .executeAsBlocking();
  }

  private void insertLoggedGamePlayer(LoggedGamePlayer lgp) {
    Log.d(LOG_TAG, ".insertLoggedGamePlayer() : Setting up player to insert");
    /*
    1: Check and insert deck
    2: C&I Location
    3: C&I Player
    4: C&I Notes
     */

    //Get Identity
    int identityID = getIdentity(lgp.getIdentity_name()).getRowid();
    Deck deck = getDeck(lgp.getDeck_name(),lgp.getDeck_version(), identityID);
    Player player = getPlayer(lgp.getPlayer_name());

    //Get Deck

    if(player == null){
      insertPlayer(new Player(lgp.getPlayer_name()));
      player = getPlayer(lgp.getPlayer_name());
    }
    lgp.setPlayer_id(player.getRowid());




    if(deck == null){
      deck = new Deck(lgp.getDeck_name(),lgp.getDeck_version(), identityID);
      Log.d(LOG_TAG,".insertLoggedGamePlayer() : Making a new temp deck = " + deck.toString());
      PutResult pr = insertDeck(deck);
      Log.d(LOG_TAG,".insertLoggedGamePlayer() : Inserting new Deck and PutResult was: " + pr.wasInserted());
      deck = getDeck(lgp.getDeck_name(),lgp.getDeck_version(), identityID);
    }
    lgp.setDeck_id(deck.rowid);




    Log.d(LOG_TAG, ".insertLoggedGamePlayer() : Inserting Player Log");
    storIOContentResolver
            .put()
            .object(lgp)
            .prepare()
            .executeAsBlocking();
  }


}
