package org.seeds.anrgamelogger.application;

import static android.R.attr.version;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.util.Log;
import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio3.contentresolver.queries.Query;
import java.util.List;
import okhttp3.OkHttpClient;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;
import org.seeds.anrgamelogger.model.Identity;
import retrofit2.Retrofit;

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

  private boolean isTableEmpty(Uri tableUri){

    boolean ret = false;

    Cursor queryResult = storIOContentResolver
        .get()
            .cursor()
        .withQuery(Query.builder()
            .uri(tableUri)
            .build())
        .prepare()
        .executeAsBlocking();

    if(queryResult != null && queryResult.getCount() > 0){
      ret = true;
    }

    return ret;
  }

  private boolean find(Uri tableUri, String coloumnName, String valueIn){

    boolean ret = false;
    String value = DatabaseUtils.sqlEscapeString(valueIn);
    String whereClause = coloumnName + " = " + value ;

    Cursor queryResult = storIOContentResolver
            .get()
            .cursor()
            .withQuery(Query.builder()
                    .uri(tableUri)
                    .whereArgs(coloumnName + " = ?")
                    .where(value)
                    .build())
            .prepare()
            .executeAsBlocking();

    if(queryResult != null && queryResult.getCount() > 0){
      ret = true;
    }

    Log.d(LOG_TAG, "Looking for: D: " + coloumnName + " I: " + valueIn + " V: " + version + " Returned: " + ret);

    return ret;
  }


}
