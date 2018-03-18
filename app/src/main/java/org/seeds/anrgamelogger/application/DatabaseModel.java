package org.seeds.anrgamelogger.application;

import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio3.contentresolver.operations.put.PutResult;
import com.pushtorefresh.storio3.contentresolver.operations.put.PutResults;
import com.pushtorefresh.storio3.contentresolver.queries.Query;
import java.util.List;
import okhttp3.OkHttpClient;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;
import org.seeds.anrgamelogger.database.contracts.LocationsContract;
import org.seeds.anrgamelogger.database.contracts.LoggedGamesContract;
import org.seeds.anrgamelogger.database.contracts.PlayersContract;
import org.seeds.anrgamelogger.model.Identity;import retrofit2.Retrofit;

/**
 * Created by Tomas Seymour-Turner on 21/02/2018.
 */

public class DatabaseModel {

  private final String LOG_TAG = this.getClass().getName();
  private StorIOContentResolver storIOContentResolver;
  private OkHttpClient okHttpClient;
  private Retrofit retrofit;

  public DatabaseModel(StorIOContentResolver storIOContentResolverIn, OkHttpClient okHttpClientIn, Retrofit retrofitIn){
    storIOContentResolver = storIOContentResolverIn;
    okHttpClient = okHttpClientIn;
    retrofit = retrofitIn;
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

  private boolean isTableEmpty(Uri tableUri){

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

  public List<Identity> getIdentities(){
    return storIOContentResolver
        .get()
        .listOfObjects(Identity.class)
        .withQuery(Query.builder()
            .uri(IdentitiesContract.URI_TABLE)
            .build())
        .prepare()
        .executeAsBlocking();
  }

  public PutResult insertIdentity(Identity i) {
    return storIOContentResolver.put()
            .object(i)
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


//Genric soultion ideas

//  public void databaseToClassModel(){
//
//    tableToClassMap = new HashMap();
//    tableToClassMap.put(IdentitiesContract.URI_TABLE, Identity.class);
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
