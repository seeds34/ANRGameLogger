package org.seeds.anrgamelogger.database;

import android.database.Cursor;
import android.util.Log;
import com.pushtorefresh.storio3.StorIOException;
import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.operations.put.PutResult;
import com.pushtorefresh.storio3.sqlite.operations.put.PutResults;
import com.pushtorefresh.storio3.sqlite.queries.Query;
import java.util.ArrayList;
import java.util.List;
import org.seeds.anrgamelogger.database.GameLoggerDatabase.Tables;
import org.seeds.anrgamelogger.database.GameLoggerDatabase.Views;
import org.seeds.anrgamelogger.database.buisnessobjects.CardImage;
import org.seeds.anrgamelogger.database.buisnessobjects.Deck;
import org.seeds.anrgamelogger.database.buisnessobjects.Identity;
import org.seeds.anrgamelogger.database.buisnessobjects.Location;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameFlat;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameFlat.LoggedGamesFlatColumns;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameOverview;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGamePlayer;
import org.seeds.anrgamelogger.database.buisnessobjects.Player;

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
            .orderBy(Identity.IdentitiesColumns.IDENTITY_FACTION + "," + Identity.IdentitiesColumns.IDENTITY_NAME)
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
            .where(Identity.IdentitiesColumns.IDENTITY_NAME + " = ?")
            .whereArgs(identityName)
            .build())
        .prepare()
        .executeAsBlocking();
  }

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

    Identity i = storIOSQLite.get()
        .object(Identity.class)
        .withQuery(Query.builder()
            .table(Tables.IDENTITIES)
            .where(Identity.IdentitiesColumns.NRDB_CODE + " = ?")
            .whereArgs(ci.getCode()).build()
    ).prepare()
        .executeAsBlocking();

    i.setImageByteArray(ci.getImageByteArray());

    return
    storIOSQLite
            .put()
            .object(i)
            .prepare()
            .executeAsBlocking();
  }

  public PutResults insertIdedentiteImages(List<CardImage> ci){

    ArrayList<Identity> identities = new ArrayList<>();

    for (CardImage c : ci){
      Identity temp = storIOSQLite.get()
          .object(Identity.class)
          .withQuery(Query.builder()
              .table(Tables.IDENTITIES)
              .where(Identity.IdentitiesColumns.NRDB_CODE + " = ?")
              .whereArgs(c.getCode()).build()
          ).prepare()
          .executeAsBlocking();

      temp.setImageByteArray(c.getImageByteArray());

      identities.add(temp);
    }

    return storIOSQLite
            .put()
            .objects(identities)
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
            .where(Player.PlayersColumns.PLAYER_NAME + " = ?")
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
            .where(Location.LocationsColumns.LOCATION_NAME + " = ?")
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
                    .where(
                        Deck.DecksColumns.DECK_NAME + " = ? AND " + Deck.DecksColumns.DECK_VERSION + " = ? AND " + Deck.DecksColumns.DECK_IDENTITY + " = ?")
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

  public LoggedGameFlat getLoggedGame(int gameId){
    return
    storIOSQLite
            .get()
            .object(LoggedGameFlat.class)
            .withQuery(Query.builder()
                  .table(Views.LOGGED_GAMES_FLAT_VIEW.toString())
                  .where(LoggedGamesFlatColumns.GAME_ID + " = ?")
                    .whereArgs(gameId)
                    .build())
            .prepare()
            .executeAsBlocking();
  }

  public int getLastGameNoUsed(){

    int ret = 0;

    Cursor queryResult = storIOSQLite
            .get()
            .cursor()
            .withQuery(Query.builder()
                    .table(Tables.SQLITE_SEQ)
                    .columns("name", "seq")
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

    return ret;
  }

  public int getNextGameNo(){
//
//    int ret = 0;
//
//    Cursor queryResult = storIOSQLite
//        .get()
//        .cursor()
//        .withQuery(Query.builder()
//            .table(Tables.SQLITE_SEQ)
//            .columns("name", "seq")
//            .build())
//        .prepare()
//        .executeAsBlocking();
//
//    int nameIndex =  queryResult.getColumnIndex("name");
//    int seqIndex =  queryResult.getColumnIndex("seq");
//
//    if(queryResult != null && queryResult.moveToFirst()){
//      do {
//        if(queryResult.getString(nameIndex).equals(Tables.LOGGED_GAME_OVERVIEWS)){
//            ret = Integer.parseInt(queryResult.getString(seqIndex));
//        }
//      }while(queryResult.moveToNext());
//    }
//
//    ret = ret+1;

    return getLastGameNoUsed()+1;
  }

  //----------  Logged Game Flat ----------//

  public List<LoggedGameFlat> getLoggedGameFlatList(int listLength){

    List ret = storIOSQLite
        .get()
        .listOfObjects(LoggedGameFlat.class)
        .withQuery(Query.builder()
            .table(Views.LOGGED_GAMES_FLAT_VIEW)
            .orderBy("date(" +LoggedGamesFlatColumns.PLAYED_DATE + ") desc")
            .limit(listLength)
            .build())
        .prepare()
        .executeAsBlocking();

    return  ret;
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




//
//  private String VIEW_SELECT_PLAYER = "SELECT "
//      + "LGP." + BaseColumns._ID + "  porowid, "
//      + "LGP." + LoggedGamePlayersContract.LoggedGamePlayersColumns.GAME_ID + " " + LoggedGamePlayersContract.LoggedGamePlayersColumns.GAME_ID + ", "
//      + "D." + DecksContract.DecksColumns.DECK_NAME + " " + DecksContract.DecksColumns.DECK_NAME + ", "
//      + "I." + IdentitiesContract.IdentitiesColumns.IDENTITY_NAME + " " + IdentitiesContract.IdentitiesColumns.IDENTITY_NAME + ", "
//      + "I." + IdentitiesContract.IdentitiesColumns.NRDB_CODE + " " + IdentitiesContract.IdentitiesColumns.NRDB_CODE + ", "
//      + "I." + IdentitiesContract.IdentitiesColumns.IMAGE_BIT_ARRAY + " " + IdentitiesContract.IdentitiesColumns.IMAGE_BIT_ARRAY + ", "
//      + "P." + PlayersContract.PlayersColumns.PLAYER_NAME + " " + PlayersContract.PlayersColumns.PLAYER_NAME + ", "
//      + "LGP." + LoggedGamePlayersContract.LoggedGamePlayersColumns.SCORE + " " + LoggedGamePlayersContract.LoggedGamePlayersColumns.SCORE + ", "
//      + "LGP." + LoggedGamePlayersContract.LoggedGamePlayersColumns.WIN_FLAG + " " + LoggedGamePlayersContract.LoggedGamePlayersColumns.WIN_FLAG + ", "
//      + "LGP." + LoggedGamePlayersContract.LoggedGamePlayersColumns.PLAYER_SIDE + " " + LoggedGamePlayersContract.LoggedGamePlayersColumns.PLAYER_SIDE
//      + " FROM " + Tables.LOGGED_GAME_PLAYERS + " LGP "
//      + " INNER JOIN " + Tables.PLAYERS + " P ON P." + BaseColumns._ID + " = LGP." + LoggedGamePlayersContract.LoggedGamePlayersColumns.PLAYER_ID
//      + " INNER JOIN " + Tables.DECKS + " D ON D." + BaseColumns._ID + " = LGP." + LoggedGamePlayersContract.LoggedGamePlayersColumns.DECK_ID
//      + " INNER JOIN " + Tables.IDENTITIES + " I ON I." + BaseColumns._ID + " = D." + DecksContract.DecksColumns.DECK_IDENTITY;
//
//
//
//  String temp =  "SELECT "
//          + "OV." + LoggedGameOverviewsContract.LoggedGameOverviewsColumns.GAME_ID + " " +  LoggedGamesFlatViewContract.LoggedGamesFlatColumns.GAME_ID + ", "
//          + "L." +   LocationsColumns.LOCATION_NAME + " " + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.LOCATION_NAME + ", "
//          + "OV." + LoggedGameOverviewsContract.LoggedGameOverviewsColumns.PLAYED_DATE  + " " + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.PLAYED_DATE + ", "
//          + "PO." + DecksContract.DecksColumns.DECK_NAME + " " + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.PLAYER_ONE_DECK_NAME + ", "
//          + "PO." + PlayersContract.PlayersColumns.PLAYER_NAME  + " " + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.PLAYER_ONE_NAME + ", "
//          + "PO." + IdentitiesContract.IdentitiesColumns.IDENTITY_NAME  + " " + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.PLAYER_ONE_ID_NAME + ", "
//          + "PO." + IdentitiesContract.IdentitiesColumns.IMAGE_BIT_ARRAY  + " " + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.PLAYER_ONE_ID_IMAGE + ", "
//          + "PO." + LoggedGamePlayersContract.LoggedGamePlayersColumns.SCORE + " " + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.PLAYER_ONE_SCORE + ", "
//          + "PO." + LoggedGamePlayersContract.LoggedGamePlayersColumns.WIN_FLAG  + " " + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.PLAYER_ONE_WIN_FLAG + ", "
//          + "PT." + DecksContract.DecksColumns.DECK_NAME  + " " + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.PLAYER_TWO_DECK_NAME + ", "
//          + "PT." + PlayersContract.PlayersColumns.PLAYER_NAME  + " " + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.PLAYER_TWO_NAME + ", "
//          + "PT." + IdentitiesContract.IdentitiesColumns.IDENTITY_NAME  + " " + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.PLAYER_TWO_ID_NAME + ", "
//          + "PT." + IdentitiesContract.IdentitiesColumns.IMAGE_BIT_ARRAY  + " " + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.PLAYER_TWO_ID_IMAGE + ", "
//          + "PT." + LoggedGamePlayersContract.LoggedGamePlayersColumns.WIN_FLAG  + " " + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.PLAYER_TWO_WIN_FLAG + ", "
//          + "OV." + LoggedGameOverviewsContract.LoggedGameOverviewsColumns.WIN_TYPE  + " " + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.WIN_TYPE + ", "
//          + "PO." + IdentitiesContract.IdentitiesColumns.NRDB_CODE  + " " + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.PLAYER_ONE_NRDB_CODE + ", "
//          + "PT." + IdentitiesContract.IdentitiesColumns.NRDB_CODE  + " " + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.PLAYER_TWO_NRDB_CODE + ", "
//          + "PT." + LoggedGamePlayersContract.LoggedGamePlayersColumns.SCORE  + " " + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.PLAYER_TWO_SCORE
//          + " FROM " + Tables.LOGGED_GAME_OVERVIEWS + " OV "
//          + " INNER JOIN (" + VIEW_SELECT_PLAYER + ") PO ON PO." + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.GAME_ID + " = OV." + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.GAME_ID
//          + " INNER JOIN (" + VIEW_SELECT_PLAYER + ") PT ON PT." + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.GAME_ID + " = OV." + LoggedGamesFlatViewContract.LoggedGamesFlatColumns.GAME_ID
//          + " INNER JOIN " + Tables.LOCATIONS + " L ON L." + BaseColumns._ID + " = OV." + LoggedGameOverviewsColumns.LOCATION_ID
//          + " WHERE PO." + LoggedGamePlayersContract.LoggedGamePlayersColumns.PLAYER_SIDE + " = \""  + ANRLoggerApplication.RUNNER_SIDE_IDENTIFIER + "\""
//          + " AND PT." + LoggedGamePlayersContract.LoggedGamePlayersColumns.PLAYER_SIDE + " = \""  + ANRLoggerApplication.CORP_SIDE_IDENTIFIER + "\"";


}
