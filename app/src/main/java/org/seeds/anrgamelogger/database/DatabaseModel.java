package org.seeds.anrgamelogger.database;

import android.database.Cursor;
import android.util.Log;
import com.pushtorefresh.storio3.StorIOException;
import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.operations.put.PutResult;
import com.pushtorefresh.storio3.sqlite.operations.put.PutResults;
import com.pushtorefresh.storio3.sqlite.queries.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.seeds.anrgamelogger.database.GameLoggerDatabase.Tables;
import org.seeds.anrgamelogger.database.GameLoggerDatabase.Views;
import org.seeds.anrgamelogger.database.buisnessobjects.CardImage;
import org.seeds.anrgamelogger.database.buisnessobjects.Deck;
import org.seeds.anrgamelogger.database.buisnessobjects.Identity;
import org.seeds.anrgamelogger.database.buisnessobjects.Location;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameFlat;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameOverview;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGamePlayer;
import org.seeds.anrgamelogger.database.buisnessobjects.Player;
import org.seeds.anrgamelogger.database.contracts.DecksContract;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract.IdentitiesColumns;
import org.seeds.anrgamelogger.database.contracts.LocationsContract.LocationsColumns;
import org.seeds.anrgamelogger.database.contracts.LoggedGameOverviewsContract;
import org.seeds.anrgamelogger.database.contracts.PlayersContract.PlayersColumns;

/**
 * Created by Tomas Seymour-Turner on 21/02/2018.
 */

//TODO: Should the DB Setup e.g. Add IDs and Images code be in the model??
public class DatabaseModel {

  private final String LOG_TAG = this.getClass().getName();
  private StorIOSQLite storIOSQLite;

  public DatabaseModel(StorIOSQLite storIOContentResolverIn){
      storIOSQLite = storIOContentResolverIn;
  }

  public boolean isIdentitiesTableEmpty(){
    return isTableEmpty(Tables.IDENTITIES.toString());
  }

  public boolean isLocationTableEmpty(){
    return isTableEmpty(Tables.LOCATIONS.toString());
  }

  public boolean isPlayerTableEmpty(){
    return isTableEmpty(Tables.PLAYERS.toString());
  }

  public boolean isLoggedgamesTableEmpty(){
    return isTableEmpty(Tables.LOGGED_GAME_OVERVIEWS.toString());
  }

  public boolean isTableEmpty(String table){

    boolean ret = false;

    Log.d(LOG_TAG,".isTableEmpty(String) : Starting is Table Empty Check for " + table);

    Cursor queryResult = storIOSQLite
        .get()
        .cursor()
        .withQuery(Query.builder()
            .table(table)
            .build())
        .prepare()
        .executeAsBlocking();

    if(queryResult != null && queryResult.getCount() > 0){
      ret = false;
    }else{
      ret = true;
    }

    Log.d(LOG_TAG,"Finished is Table Empty Check for " + table + " Returning: " + ret);

    return ret;
  }

  //----------  Identities ----------//

  //TODO: Fix sorting
  public List<Identity> getAllIdentities(){
    return storIOSQLite
        .get()
        .listOfObjects(Identity.class)
        .withQuery(Query.builder()
            .table(Tables.IDENTITIES.toString())
            .build())
        .prepare()
        .executeAsBlocking();
  }

  //TODO: Fix sorting
  public Identity getIdentity(String identityName){
    return storIOSQLite
        .get()
        .object(Identity.class)
        .withQuery(Query.builder()
            .table(Tables.IDENTITIES.toString())
            .where(IdentitiesColumns.IDENTITY_NAME + " = ?")
            .whereArgs(identityName)
            .build())
        .prepare()
        .executeAsBlocking();
  }

  //Use Card instead of Identity Object as IDs will always be inserted 'remotely' not by user
  public PutResult insertIdentity(Identity i) {
    return storIOSQLite
            .put()
            .object(i)
            .prepare()
            .executeAsBlocking();
  }

  public PutResults insertIdentities(List<Identity> i) {
    return
    storIOSQLite
            .put()
            .objects(i)
            .prepare()
            .executeAsBlocking();
  }

  public PutResult insertIdentitieImage(CardImage ci){
    return
    storIOSQLite
            .put()
            .object(ci)
            .prepare()
            .executeAsBlocking();
  }

  public PutResults insertIdedentiteImages(List<CardImage> ci){
    return
    storIOSQLite
            .put()
            .objects(ci)
            .prepare()
            .executeAsBlocking();
  }

  //No Get IdentityImage as Image is part of the identity.

  //----------  Players ----------//

  public List<Player> getAllPlayers() {
    return storIOSQLite
        .get()
        .listOfObjects(Player.class)
        .withQuery(Query.builder()
            .table(Tables.PLAYERS.toString())
            .build())
        .prepare()
        .executeAsBlocking();
  }

  public Player getPlayer(String playerName){
    return storIOSQLite
        .get()
        .object(Player.class)
        .withQuery(Query.builder()
            .table(Tables.PLAYERS.toString())
            .where(PlayersColumns.PLAYER_NAME + " = ?")
            .whereArgs(playerName)
            .build())
        .prepare()
        .executeAsBlocking();
  }


  public PutResult insertPlayer(Player playerIn) throws StorIOException {
    return storIOSQLite
            .put()
            .object(playerIn)
            .prepare()
            .executeAsBlocking();
  }

  //----------  Location ----------//

  public List<Location> getAllLocations() {
    return     storIOSQLite
        .get()
        .listOfObjects(Location.class)
        .withQuery(Query.builder()
            .table(Tables.LOCATIONS.toString())
            .build())
        .prepare()
        .executeAsBlocking();
  }

  public Location getLocation(String locationName){
    return
    storIOSQLite
        .get()
        .object(Location.class)
        .withQuery(Query.builder()
            .table(Tables.LOCATIONS.toString())
            .where(LocationsColumns.LOCATION_NAME + " = ?")
            .whereArgs(locationName)
            .build())
        .prepare()
        .executeAsBlocking();
  }

  public PutResult insertLocation(Location locationIn){
    return
    storIOSQLite
        .put()
        .object(locationIn)
        .prepare()
        .executeAsBlocking();
  }

  //----------  Deck ----------//

  public List<Deck> getAllDecks() {
    return
    storIOSQLite
        .get()
        .listOfObjects(Deck.class)
        .withQuery(Query.builder()
            .table(Tables.DECKS.toString())
            .build())
        .prepare()
        .executeAsBlocking();
  }

  public Deck getDeck(String deckName, String deckVersion, int identityNo){

    Log.d(LOG_TAG, ".getDeck() : deckVersion = " + deckVersion);

    return

    storIOSQLite
            .get()
            .object(Deck.class)
            .withQuery(Query.builder()
                .table(Tables.DECKS.toString())
                    .where(DecksContract.DecksColumns.DECK_NAME + " = ? AND " + DecksContract.DecksColumns.DECK_VERSION + " = ? AND " + DecksContract.DecksColumns.DECK_IDENTITY + " = ?")
                    .whereArgs(deckName, deckVersion, identityNo)
                    .build())
            .prepare()
            .executeAsBlocking();
  }

//  public Deck getDeck(String deckName, String deckVersion, String identityName){
//    return storIOSQLite
//            .get()
//            .object(Deck.class)
//            .withQuery(Query.builder()
//                    .uri(DecksContract.URI_TABLE)
//                    .where(DecksContract.DecksColumns.DECK_NAME + " = ? AND " + DecksContract.DecksColumns.DECK_VERSION + " = ? AND " + DecksContract.DecksColumns.DECK_IDENTITY + " = ?")
//                    .whereArgs(deckName, deckVersion, identityName)
//                    .build())
//            .prepare()
//            .executeAsBlocking();
//  }

  public PutResult insertDeck(Deck deckIn){
    return
        storIOSQLite
            .put()
            .object(deckIn)
            .prepare()
            .executeAsBlocking();
  }

  //----------  Logged Game ----------//

  public LoggedGameOverview getLoggedGame(int gameId){
    return
    storIOSQLite
            .get()
            .object(LoggedGameOverview.class)
            .withQuery(Query.builder()
                  .table(Tables.LOGGED_GAME_OVERVIEWS.toString())
                  .where(LoggedGameOverviewsContract.LoggedGameOverviewsColumns.GAME_ID + " = ?")
                    .whereArgs(gameId)
                    .build())
            .prepare()
            .executeAsBlocking();
  }

  public int getNextGameNo(){

    int ret = 0;

    Cursor queryResult = storIOSQLite
        .get()
        .cursor()
        .withQuery(Query.builder()
            .table("sqlite_sequence")
            .columns("name", "seq")
//            .where("name")
//            .whereArgs(Tables.LOGGED_GAME_OVERVIEWS.toString())
            .build())
        .prepare()
        .executeAsBlocking();

    int nameIndex =  queryResult.getColumnIndex("name");
    int seqIndex =  queryResult.getColumnIndex("seq");

    if(queryResult != null && queryResult.moveToFirst()){
      do {
        if(queryResult.getString(nameIndex).equals(Tables.LOGGED_GAME_OVERVIEWS)){
            ret = Integer.parseInt(queryResult.getString(seqIndex));
        }
      }while(queryResult.moveToNext());
    }

    ret = ret+1;

    return ret;
  }

  //----------  Logged Game Flat ----------//

  public List<LoggedGameFlat> getLoggedGameFlat(int listLength){

    List ret = storIOSQLite
        .get()
        .listOfObjects(LoggedGameFlat.class)
        .withQuery(Query.builder()
            .table(Views.LOGGED_GAMES_FLAT_VIEW)
            .build())
        .prepare()
        .executeAsBlocking();

    return  ret;
  }

  public Map<Enum, Boolean> validateLogggedGame(LoggedGameOverview lgo, LoggedGamePlayer playerOne, LoggedGamePlayer playerTwo ){
    Map<Enum, Boolean> ret = new HashMap<>();

    //Validate: 1-Format and style is correct
    //          2-Meets game rules
    //            3-Check against DB

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
    //TODO: Breaks when Medtech played as check of Score nad less the n 7 will fail
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

    if(getDeck(playerOne.getDeck_name(),playerOne.getDeck_version(),getIdentity(playerOne.getIdentity_name()).getRowid()) != null){
      ret.put(LoggedGameValidationList.DECK_ONE_EXISTS, true);
    }else{
      ret.put(LoggedGameValidationList.DECK_ONE_EXISTS, false);
    }

    if(getDeck(playerTwo.getDeck_name(),playerTwo.getDeck_version(),getIdentity(playerTwo.getIdentity_name()).getRowid()) != null){
      ret.put(LoggedGameValidationList.DECK_TWO_EXISTS, true);
    }else{
      ret.put(LoggedGameValidationList.DECK_TWO_EXISTS, false);
    }
    
    return ret;
  }

  ////Insert Entire Game

  public void insertLoggedGame(LoggedGameOverview lgo, LoggedGamePlayer playerOne, LoggedGamePlayer playerTwo ){

    Log.d(LOG_TAG, ".insertLoggedGame() : Starting");
    insertLoggedGamePlayer(playerOne);
    insertLoggedGamePlayer(playerTwo);

    Location location = getLocation(lgo.getLocation_name());

    if(location == null){
      insertLocation(new Location(lgo.getLocation_name()));
      location = getLocation(lgo.getLocation_name());
    }
    lgo.setLocation_id(location.getRowid());


    Log.d(LOG_TAG, ".insertLoggedGame() : Inserting Overview");
    storIOSQLite
            .put()
            .object(lgo)
            .prepare()
            .executeAsBlocking();
  }

  private void insertLoggedGamePlayer(LoggedGamePlayer lgp) {
    Log.d(LOG_TAG, ".insertLoggedGamePlayer() : Setting up player to insert");

    int identityID = getIdentity(lgp.getIdentity_name()).getRowid();
    Deck deck = getDeck(lgp.getDeck_name(),lgp.getDeck_version(), identityID);
    Player player = getPlayer(lgp.getPlayer_name());

    if(player == null){
      player = new Player(lgp.getPlayer_name());
      Log.d(LOG_TAG,".insertLoggedGamePlayer() : Inserting player: " + "\n" + player.toString());
      insertPlayer(player);
      player = getPlayer(lgp.getPlayer_name());
    }
    Log.d(LOG_TAG,".insertLoggedGamePlayer() : Player is now: " + "\n" + player.toString());
    lgp.setPlayer_id(player.getRowid());

    if(deck == null){
      deck = new Deck(lgp.getDeck_name(),lgp.getDeck_version(), identityID);
      Log.d(LOG_TAG,".insertLoggedGamePlayer() : Inserting deck: " + "\n" + deck.toString());
      PutResult pr = insertDeck(deck);
      Log.d(LOG_TAG,".insertLoggedGamePlayer() : Inserting new Deck and PutResult was: " + pr.wasInserted());
      deck = getDeck(lgp.getDeck_name(),lgp.getDeck_version(), identityID);
    }
    Log.d(LOG_TAG,".insertLoggedGamePlayer() : Deck is now: " + "\n" + deck.toString());
    lgp.setDeck_id(deck.rowid);

    Log.d(LOG_TAG, ".insertLoggedGamePlayer() : Inserting Player Game Log: " + "\n" + lgp.toString());

    storIOSQLite
            .put()
            .object(lgp)
            .prepare()
            .executeAsBlocking();
  }


}
