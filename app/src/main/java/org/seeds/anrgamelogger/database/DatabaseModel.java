package org.seeds.anrgamelogger.database;

import android.database.Cursor;
import android.util.Log;

import com.pushtorefresh.storio3.StorIOException;
import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.operations.put.PutResult;
import com.pushtorefresh.storio3.sqlite.operations.put.PutResults;
import com.pushtorefresh.storio3.sqlite.queries.DeleteQuery;
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

  public DatabaseModel(StorIOSQLite storIOContentResolverIn) {
    storIOSQLite = storIOContentResolverIn;
  }

  //----------  Identities ----------//

  //TODO: Fix sorting
  public List<Identity> getAllIdentities() {
    return storIOSQLite
        .get()
        .listOfObjects(Identity.class)
        .withQuery(Query.builder()
            .table(Tables.IDENTITIES.toString())
            .orderBy(Identity.IdentitiesColumns.IDENTITY_FACTION + ","
                + Identity.IdentitiesColumns.IDENTITY_NAME)
            .build())
        .prepare()
        .executeAsBlocking();
  }

  //TODO: Fix sorting
  public Identity getIdentity(String identityName) {
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

  public PutResult insertIdentitieImage(CardImage ci) {

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

  public PutResults insertIdedentiteImages(List<CardImage> ci) {

    ArrayList<Identity> identities = new ArrayList<>();

    for (CardImage c : ci) {
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

  public Player getPlayer(String playerName) {
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
    return storIOSQLite
        .get()
        .listOfObjects(Location.class)
        .withQuery(Query.builder()
            .table(Tables.LOCATIONS.toString())
            .build())
        .prepare()
        .executeAsBlocking();
  }

  public Location getLocation(String locationName) {
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

  public PutResult insertLocation(Location locationIn) {
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

  public Deck getDeck(String deckName, String deckVersion, int identityNo) {

    Log.d(LOG_TAG, ".getDeck() : deckVersion = " + deckVersion);

    return
        storIOSQLite
            .get()
            .object(Deck.class)
            .withQuery(Query.builder()
                .table(Tables.DECKS.toString())
                .where(
                    Deck.DecksColumns.DECK_NAME + " = ? AND " + Deck.DecksColumns.DECK_VERSION
                        + " = ? AND " + Deck.DecksColumns.DECK_IDENTITY + " = ?")
                .whereArgs(deckName, deckVersion, identityNo)
                .build())
            .prepare()
            .executeAsBlocking();
  }

  public PutResult insertDeck(Deck deckIn) {
    return
        storIOSQLite
            .put()
            .object(deckIn)
            .prepare()
            .executeAsBlocking();
  }

  //----------  Logged Game ----------//

  public LoggedGameFlat getLoggedGame(int gameId) {
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

  public int getLastGameNoUsed() {

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

    int nameIndex = queryResult.getColumnIndex("name");
    int seqIndex = queryResult.getColumnIndex("seq");

    if (queryResult != null && queryResult.moveToFirst()) {
      do {
        if (queryResult.getString(nameIndex).equals(Tables.LOGGED_GAME_OVERVIEWS)) {
          ret = Integer.parseInt(queryResult.getString(seqIndex));
        }
      } while (queryResult.moveToNext());
    }

    return ret;
  }

  public int getNextGameNo() {
    return getLastGameNoUsed() + 1;
  }

  private LoggedGameOverview populateLoggedGameOverviewInternalValues(LoggedGameOverview lgo){

    Location location = getLocation(lgo.getLocation_name());

    if (location == null) {
      insertLocation(new Location(lgo.getLocation_name()));
      location = getLocation(lgo.getLocation_name());
    }
    lgo.setLocation_id(location.getRowid());

    return lgo;
  }

  public void insertLoggedGame(LoggedGameOverview lgo, LoggedGamePlayer playerOne,
      LoggedGamePlayer playerTwo) {

    Log.d(LOG_TAG, ".insertLoggedGame() : Starting");
    insertLoggedGamePlayer(playerOne);
    insertLoggedGamePlayer(playerTwo);
    populateLoggedGameOverviewInternalValues(lgo);

    Log.d(LOG_TAG, ".insertLoggedGame() : Inserting Overview");
    storIOSQLite
        .put()
        .object(lgo)
        .prepare()
        .executeAsBlocking();
  }

  public void updateLoggedGame(LoggedGameOverview lgo, LoggedGamePlayer playerOne,
      LoggedGamePlayer playerTwo) {

    Log.d(LOG_TAG, ".insertLoggedGame() : Starting");

    updateLoggedGamePlayer(playerOne);
    updateLoggedGamePlayer(playerTwo);
    populateLoggedGameOverviewInternalValues(lgo);

    //TODO: Need to make GAMID in Overviews Unique
    lgo.setRowid(getLoggedGameOverview(lgo.getGameID()));

    Log.d(LOG_TAG, ".insertLoggedGame() : Inserting Overview: " + lgo.toString());
    storIOSQLite
        .put()
        .object(lgo)
        .prepare()
        .executeAsBlocking();
  }

  private Integer getLoggedGameOverview(Integer gameId) {
    LoggedGameOverview ret =
        storIOSQLite
            .get()
            .object(LoggedGameOverview.class)
            .withQuery(Query.builder()
                .table(Tables.LOGGED_GAME_OVERVIEWS)
                .where(LoggedGameOverview.LoggedGameOverviewsColumns.GAME_ID + " = ?")
                .whereArgs(gameId)
                .build())
            .prepare()
            .executeAsBlocking();
    return ret.getRowid();
  }

  public void deleteLoggedGame(Integer gameNo) {
    storIOSQLite
        .delete()
        .byQuery(DeleteQuery.builder()
            .table(Tables.LOGGED_GAME_PLAYERS)
            .where(LoggedGamePlayer.LoggedGamePlayersColumns.GAME_ID + " = ?")
            .whereArgs(gameNo)
            .build())
        .prepare()
        .executeAsBlocking();

    storIOSQLite
        .delete()
        .byQuery(DeleteQuery.builder()
            .table(Tables.LOGGED_GAME_OVERVIEWS)
            .where(LoggedGameOverview.LoggedGameOverviewsColumns.GAME_ID + " = ?")
            .whereArgs(gameNo)
            .build())
        .prepare()
        .executeAsBlocking();
  }

  //----------  Logged Game Player ----------//


  private LoggedGamePlayer populateLoggedGamePlayerInternalValues(LoggedGamePlayer lgp) {
    int identityID = getIdentity(lgp.getIdentity_name()).getRowid();
    Deck deck = getDeck(lgp.getDeck_name(), lgp.getDeck_version(), identityID);
    Player player = getPlayer(lgp.getPlayer_name());

    if (player == null) {
      player = new Player(lgp.getPlayer_name());
      Log.d(LOG_TAG, ".insertLoggedGamePlayer() : Inserting player: " + "\n" + player.toString());
      insertPlayer(player);
      player = getPlayer(lgp.getPlayer_name());
    }
    Log.d(LOG_TAG, ".insertLoggedGamePlayer() : Player is now: " + "\n" + player.toString());
    lgp.setPlayer_id(player.getRowid());

    if (deck == null) {
      deck = new Deck(lgp.getDeck_name(), lgp.getDeck_version(), identityID);
      Log.d(LOG_TAG, ".insertLoggedGamePlayer() : Inserting deck: " + "\n" + deck.toString());
      PutResult pr = insertDeck(deck);
      Log.d(LOG_TAG,".insertLoggedGamePlayer() : Inserting new Deck and PutResult was: " + pr.wasInserted());
      deck = getDeck(lgp.getDeck_name(), lgp.getDeck_version(), identityID);
    }
    Log.d(LOG_TAG, ".insertLoggedGamePlayer() : Deck is now: " + "\n" + deck.toString());
    lgp.setDeck_id(deck.rowid);
    return lgp;
  }

  private void insertLoggedGamePlayer(LoggedGamePlayer lgp) {
    Log.d(LOG_TAG, ".insertLoggedGamePlayer() : Setting up player to insert");
    populateLoggedGamePlayerInternalValues(lgp);
    Log.d(LOG_TAG,".insertLoggedGamePlayer() : Inserting Player Game Log: " + "\n" + lgp.toString());
    try {
      storIOSQLite
          .put()
          .object(lgp)
          .prepare()
          .executeAsBlocking();
    } catch (Exception e) {
      Log.e(LOG_TAG, "insertLoggedGamePlayer: ", e);
    }
  }

  private void updateLoggedGamePlayer(LoggedGamePlayer lgp) {
    Log.d(LOG_TAG, ".updateLoggedGamePlayer() : Setting up player to update");
    populateLoggedGamePlayerInternalValues(lgp);
    lgp.setRowid(getLoggedGamePlayer(lgp.getGameID(), lgp.getPlayer_id(), lgp.getSide()));
    Log.d(LOG_TAG,".updateLoggedGamePlayer() : Inserting Player Game Log: " + "\n" + lgp.toString());

    try {
      storIOSQLite
          .put()
          .object(lgp)
          .prepare()
          .executeAsBlocking();
    } catch (Exception e) {
      Log.e(LOG_TAG, "updateLoggedGamePlayer: ", e);
    }
  }


  private Integer getLoggedGamePlayer(Integer gameId, Integer player_id, String side) {
    LoggedGamePlayer ret = storIOSQLite
        .get()
        .object(LoggedGamePlayer.class)
        .withQuery(Query.builder()
            .table(Tables.LOGGED_GAME_PLAYERS)
            .where(LoggedGamePlayer.LoggedGamePlayersColumns.GAME_ID + " = ? AND "
                + LoggedGamePlayer.LoggedGamePlayersColumns.PLAYER_ID + " = ? and "
                + LoggedGamePlayer.LoggedGamePlayersColumns.PLAYER_SIDE + " = ?")
            .whereArgs(gameId, player_id, side)
            .build())
        .prepare()
        .executeAsBlocking();

    Log.d(LOG_TAG,
        "getLoggedGamePlayer: Gamid = " + gameId + " | Player_ID = " + player_id + " | Side = "
            + side);

    Log.d(LOG_TAG, "getLoggedGamePlayer: Getting Rowid for: " + ret.toString());
    return ret.getRowid();
  }

  //----------  Logged Game Flat ----------//

  public List<LoggedGameFlat> getLoggedGameFlatList(int listLength) {

    List ret = storIOSQLite
        .get()
        .listOfObjects(LoggedGameFlat.class)
        .withQuery(Query.builder()
            .table(Views.LOGGED_GAMES_FLAT_VIEW)
            .orderBy("date(" + LoggedGamesFlatColumns.PLAYED_DATE + ") desc")
            .limit(listLength)
            .build())
        .prepare()
        .executeAsBlocking();

    return ret;
  }

  //----------  Admin ----------//

  public boolean isIdentitiesTableEmpty() {
    return isTableEmpty(Tables.IDENTITIES.toString());
  }

  public boolean isLocationTableEmpty() {
    return isTableEmpty(Tables.LOCATIONS.toString());
  }

  public boolean isPlayerTableEmpty() {
    return isTableEmpty(Tables.PLAYERS.toString());
  }

  public boolean isLoggedgamesTableEmpty() {
    return isTableEmpty(Tables.LOGGED_GAME_OVERVIEWS.toString());
  }

  public boolean isTableEmpty(String table) {

    boolean ret = false;

    Log.d(LOG_TAG, ".isTableEmpty(String) : Starting is Table Empty Check for " + table);

    Cursor queryResult = storIOSQLite
        .get()
        .cursor()
        .withQuery(Query.builder()
            .table(table)
            .build())
        .prepare()
        .executeAsBlocking();

    if (queryResult != null && queryResult.getCount() > 0) {
      ret = false;
    } else {
      ret = true;
    }

    Log.d(LOG_TAG, "Finished is Table Empty Check for " + table + " Returning: " + ret);

    return ret;
  }

  public void purgeDatabase() {

    storIOSQLite
        .delete()
        .byQuery(DeleteQuery.builder()
            .table(Tables.LOGGED_GAME_PLAYERS)
            .build())
        .prepare()
        .executeAsBlocking();

    storIOSQLite
        .delete()
        .byQuery(DeleteQuery.builder()
            .table(Tables.LOGGED_GAME_OVERVIEWS)
            .build())
        .prepare()
        .executeAsBlocking();

    storIOSQLite
        .delete()
        .byQuery(DeleteQuery.builder()
            .table(Tables.PLAYERS)
            .build())
        .prepare()
        .executeAsBlocking();

    storIOSQLite
        .delete()
        .byQuery(DeleteQuery.builder()
            .table(Tables.LOCATIONS)
            .build())
        .prepare()
        .executeAsBlocking();

    storIOSQLite
        .delete()
        .byQuery(DeleteQuery.builder()
            .table(Tables.DECKS)
            .build())
        .prepare()
        .executeAsBlocking();
  }
}
