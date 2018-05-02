package org.seeds.anrgamelogger.application;

import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio3.contentresolver.operations.put.PutResult;
import com.pushtorefresh.storio3.contentresolver.operations.put.PutResults;
import com.pushtorefresh.storio3.contentresolver.queries.Query;
import java.util.List;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;
import org.seeds.anrgamelogger.database.contracts.LocationsContract;
import org.seeds.anrgamelogger.database.contracts.LoggedGamesContract;
import org.seeds.anrgamelogger.database.contracts.PlayersContract;
import org.seeds.anrgamelogger.model.Card;
import org.seeds.anrgamelogger.model.CardImage;
import org.seeds.anrgamelogger.model.Player;

/**
 * Created by Tomas Seymour-Turner on 21/02/2018.
 */

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
    return isTableEmpty(LoggedGamesContract.URI_TABLE);
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

  public List<Card> getIdentities(){
    return storIOContentResolver
        .get()
        .listOfObjects(Card.class)
        .withQuery(Query.builder()
            .uri(IdentitiesContract.URI_TABLE)
                .sortOrder(IdentitiesContract.IdentitiesColumns.IDENTITY_FACTION + " asc")
            .build())
        .prepare()
        .executeAsBlocking();
  }

  public PutResult insertIdentity(Card i) {
    return storIOContentResolver.put()
            .object(i)
            .prepare()
            .executeAsBlocking();
  }

  public PutResults insertIdentities(List<Card> i) {
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

  public List<Player> getPlayers() {
    return storIOContentResolver
        .get()
        .listOfObjects(Player.class)
        .withQuery(Query.builder()
            .uri(PlayersContract.URI_TABLE)
            .build())
        .prepare()
        .executeAsBlocking();
  }

  public PutResult insertPlayer(Player playerIn){
    return storIOContentResolver.put()
        .object(playerIn)
        .prepare()
        .executeAsBlocking();
  }

//Genric soultion ideas

//  public void databaseToClassModel(){
//
//    tableToClassMap = new HashMap();
//    tableToClassMap.put(IdentitiesContract.URI_TABLE, Card.class);
//    //tableToClassMap.put(LocationsContract.URI_TABLE, )
//    tableToClassMap.put(LoggedGamesContract.URI_TABLE, Game.class);
//    tableToClassMap.put(PlayersContract.URI_TABLE, Player.class);
//  }



//  private boolean find(Uri tableUri, String coloumnName, String valueIn){
//
//    boolean ret = false;
//    String value = DatabaseUtils.sqlEscapeString(valueIn);
//    String whereClause = coloumnName + " = " + value ;
//    Class typeClass = tableToClassMap.get(tableUri);
//
//
//      ArrayList<?> queryResult = storIOContentResolver
//            .get()
//            .listOfObjects(typeClass.getClass())
//            .withQuery(Query.builder()
//                    .uri(tableUri)
//                    .whereArgs(coloumnName + " = ?")
//                    .where(value)
//                    .build())
//            .prepare()
//            .executeAsBlocking();
//
//    if(queryResult != null && queryResult.getCount() > 0){
//      ret = true;
//    }
//
//    Log.d(LOG_TAG, "Looking for: D: " + coloumnName + " I: " + valueIn + " V: " + version + " Returned: " + ret);
//
//    return ret;
//  }


}
