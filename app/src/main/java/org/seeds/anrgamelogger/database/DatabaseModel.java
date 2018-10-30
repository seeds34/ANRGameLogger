package org.seeds.anrgamelogger.database;

import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.pushtorefresh.storio3.StorIOException;
import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio3.contentresolver.operations.put.PutResult;
import com.pushtorefresh.storio3.contentresolver.operations.put.PutResults;
import com.pushtorefresh.storio3.contentresolver.queries.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seeds.anrgamelogger.database.buisnessobjects.CardImage;
import org.seeds.anrgamelogger.database.buisnessobjects.Deck;
import org.seeds.anrgamelogger.database.buisnessobjects.Identity;
import org.seeds.anrgamelogger.database.buisnessobjects.Location;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameFlat;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGamePlayer;
import org.seeds.anrgamelogger.database.buisnessobjects.Player;
import org.seeds.anrgamelogger.database.contracts.DecksContract;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract.IdentitiesColumns;
import org.seeds.anrgamelogger.database.contracts.LocationsContract;
import org.seeds.anrgamelogger.database.contracts.LocationsContract.LocationsColumns;
import org.seeds.anrgamelogger.database.contracts.LoggedGameOverviewsContract;
import org.seeds.anrgamelogger.database.contracts.LoggedGamesFlatViewContract;
import org.seeds.anrgamelogger.database.contracts.PlayersContract;
import org.seeds.anrgamelogger.database.contracts.PlayersContract.PlayersColumns;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameOverview;

/**
 * Created by Tomas Seymour-Turner on 21/02/2018.
 */

//TODO: Should the DB Setup e.g. Add IDs and Images code be in the model??
public class DatabaseModel {

  private final String LOG_TAG = this.getClass().getName();
  private StorIOContentResolver storIOContentResolver;

  public DatabaseModel(StorIOContentResolver storIOContentResolverIn){
      storIOContentResolver = storIOContentResolverIn;
  }

  public boolean isIdentitiesTableEmpty(){
    return isTableEmpty(IdentitiesContract.URI_TABLE);
  }

  public boolean isLocationTableEmpty(){
    return isTableEmpty(LocationsContract.URI_TABLE);
  }

  public boolean isPlayerTableEmpty(){
    return isTableEmpty(PlayersContract.URI_TABLE);
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

  //Use Card instead of Identity Object as IDs will always be inserted 'remotely' not by user
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

  public List<Location> getLocations(){
    return storIOContentResolver
        .get()
        .listOfObjects(Location.class)
        .withQuery(Query.builder()
            .uri(LocationsContract.URI_TABLE)
            .build())
        .prepare()
        .executeAsBlocking();
  }

  public Location getLocation(String locationName){
    return storIOContentResolver
        .get()
        .object(Location.class)
        .withQuery(Query.builder()
            .uri(LocationsContract.URI_TABLE)
            .where(LocationsColumns.LOCATION_NAME + " = ?")
            .whereArgs(locationName)
            .build())
        .prepare()
        .executeAsBlocking();
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

  public List<Deck> getDecks(){
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
                    .where(DecksContract.DecksColumns.DECK_NAME + " = ? AND " + DecksContract.DecksColumns.DECK_IDENTITY + " = ? AND " + DecksContract.DecksColumns.DECK_IDENTITY + " = ?")
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
    LoggedGameOverview lg = storIOContentResolver
            .get()
            .object(LoggedGameOverview.class)
            .withQuery(Query.builder()
                    .uri(LoggedGameOverviewsContract.URI_TABLE)
                    .columns("MAX(" + LoggedGameOverviewsContract.LoggedGameOverviewsColumns.GAME_ID +")")
                    .build())
            .prepare()
            .executeAsBlocking();

    return lg.getGameID();
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
    return storIOContentResolver
            .get()
            .listOfObjects(LoggedGameFlat.class)
            .withQuery(Query.builder()
                    .uri(LoggedGamesFlatViewContract.URI_TABLE)
                      //.
                    .build())
            .prepare()
            .executeAsBlocking();
  }

  public void addLoggedGame(LoggedGameOverview lgo, LoggedGamePlayer playerOne, LoggedGamePlayer playerTwo ){

    Map<Enum, Boolean> validationResults = validateLogggedGame(lgo,playerOne,playerTwo);
    
    boolean allTrue = true;

    for (Map.Entry<Enum, Boolean> entry : validationResults.entrySet() ) {
        if(entry.getValue() != true){
          allTrue = false;
        }
    }

    //TODO: Add Check to only add new entryes etc if validation has passed

    if (!validationResults.get(LoggedGameValidationList.PLAYER_ONE_EXISTS)){
      //Create new player
      PutResult ip = insertPlayer(new Player(playerOne.getPlayer_name()));
      if(ip.wasInserted()){
        playerOne.setPlayer_id(getPlayer(playerOne.getPlayer_name()).getRowid());
      }
      }else{
      playerOne.setPlayer_id(getPlayer(playerOne.getPlayer_name()).getRowid());
      //set Player ID as that from get
    }

    if (!validationResults.get(LoggedGameValidationList.DECK_ONE_EXISTS)){
      //Create new Deck
    }else{
      //set Deck ID as that from get
    }




  }
  
  public Map<Enum, Boolean> validateLogggedGame(LoggedGameOverview lgo, LoggedGamePlayer playerOne, LoggedGamePlayer playerTwo ){
    Map<Enum, Boolean> ret = new HashMap<>();

    if(getLocation(lgo.getLocation_name()) != null){
      ret.put(LoggedGameValidationList.LOCATION_EXISTS , true);
    }else{
      ret.put(LoggedGameValidationList.LOCATION_EXISTS , false);
    }

    //TODO: Need real date check
    if(lgo.getPlayed_date() == "Today"){
      ret.put(LoggedGameValidationList.DATE_VALID,true);
    }else{
      ret.put(LoggedGameValidationList.DATE_VALID, false);
    }

    //TODO: How to check win type. This is why the calidation needs to be done for the entire game
    if(lgo.getWin_type() == "S"){
      if((lgo.getWinning_side() == playerOne.getSide() && playerOne.getScore() >= 7) ||
                      (lgo.getWinning_side() == playerTwo.getSide() && playerTwo.getScore() >= 7)){
        ret.put(LoggedGameValidationList.WIN_TYPE_VALID, true);
      }else{
        ret.put(LoggedGameValidationList.WIN_TYPE_VALID, false);
      }
    }else if(lgo.getWin_type() == "K"){
      if(playerOne.getScore() < 7 && playerTwo.getScore() < 7 && lgo.getWinning_side() == "corp"){
        ret.put(LoggedGameValidationList.WIN_TYPE_VALID, true);
      }else{
        ret.put(LoggedGameValidationList.WIN_TYPE_VALID, false);
      }
    }else if(lgo.getWin_type() == "M"){
      if(playerOne.getScore() < 7 && playerTwo.getScore() < 7 && lgo.getWinning_side() == "runner"){
        ret.put(LoggedGameValidationList.WIN_TYPE_VALID, true);
      }else{
        ret.put(LoggedGameValidationList.WIN_TYPE_VALID, false);
      }
    }else if(lgo.getWin_type() == "C"){
      if(playerOne.getScore() < 7 && playerTwo.getScore() < 7){
        ret.put(LoggedGameValidationList.WIN_TYPE_VALID, true);
      }else{
        ret.put(LoggedGameValidationList.WIN_TYPE_VALID, false);
      }
    }

    if(getPlayer(playerOne.getPlayer_name()) != null){
      ret.put(LoggedGameValidationList.PLAYER_ONE_EXISTS, true);
    }else{
      ret.put(LoggedGameValidationList.PLAYER_ONE_EXISTS, false);
    }

    if(getPlayer(playerTwo.getPlayer_name()) != null){
      ret.put(LoggedGameValidationList.PLAYER_TWO_EXISTS, true);
    }else{
      ret.put(LoggedGameValidationList.PLAYER_TWO_EXISTS, false);
    }

    if(getDeck(playerOne.getDeck_name(),"1",getIdentity(playerOne.getIdentity_name()).getRowid()) != null){
      ret.put(LoggedGameValidationList.DECK_ONE_EXISTS, true);
    }else{
      ret.put(LoggedGameValidationList.DECK_ONE_EXISTS, false);
    }

    if(getDeck(playerTwo.getDeck_name(),"1",getIdentity(playerTwo.getIdentity_name()).getRowid()) != null){
      ret.put(LoggedGameValidationList.DECK_TWO_EXISTS, true);
    }else{
      ret.put(LoggedGameValidationList.DECK_TWO_EXISTS, false);
    }
    
    return ret;
  }

}
