package org.seeds.anrgamelogger.model;


import android.app.Activity;
import android.content.ContentResolver;
import android.util.Log;
import com.pushtorefresh.storio3.contentresolver.ContentResolverTypeMapping;
import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio3.contentresolver.impl.DefaultStorIOContentResolver;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;


/**
 * Created by Tomas Seymour-Turner on 09/01/2018.
 */

public class ImportDefaultData {

  private static final String LOG_TAG = ImportDefaultData.class.getSimpleName();


  private final String NRDB_CARD_LIST_API_URL = "https://netrunnerdb.com/api/2.0/public/cards";
  private final String NRDB_BASE_API_URL = "https://netrunnerdb.com/api/2.0/public/";

  private ContentResolver contentResolver;

  public ImportDefaultData(Activity activity){
    contentResolver = activity.getContentResolver();
  }

  public void populateIdentitiesTable(){

    RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());


    Moshi moshi = new Moshi.Builder().build();
    JsonAdapter<IdentitiesData> jsonAdapter = moshi.adapter(IdentitiesData.class);



    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(NRDB_BASE_API_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(rxAdapter)
        .build();


      Log.d(LOG_TAG, "Retrofit Started");

    NRDBApiEndpointInterface apiService = retrofit.create(NRDBApiEndpointInterface.class);

      Log.d(LOG_TAG, "RAPI Services Started");

    Single<IdentitiesData> call = apiService.getAllIdentities();

      Log.d(LOG_TAG, "Observiable Made");

    return call.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())







//        .map(new Function<IdentitiesData, List<Identity>>() {
//          @Override
//          public List<Identity> apply(
//              @io.reactivex.annotations.NonNull final IdentitiesData cityResponse)
//              throws Exception {
//            // we want to have the geonames and not the wrapper object
//
//            Log.d(LOG_TAG, "Fire Call.apply");
//
//            Log.d(LOG_TAG, (cityResponse.identities == null)?"IDS is NULL":"IDS is not NULL");
//
//            return cityResponse.data;
//          }
//        })

        .subscribe(new Consumer<List<Identity>>() {
          @Override
          public void accept(
              @io.reactivex.annotations.NonNull final List<Identity> ident)
              throws Exception {

            Log.d(LOG_TAG, "Fire Call.accept");

            insertID(ident);
          }
        });
  }


  public void insertID(List<Identity> identitiesIn){

    StorIOContentResolver storIOContentResolver = DefaultStorIOContentResolver.builder()
        .contentResolver(contentResolver)
        .addTypeMapping(Identity.class, ContentResolverTypeMapping.<Identity>builder()
            .putResolver(new IdentityStorIOContentResolverPutResolver())
            .getResolver(new IdentityStorIOContentResolverGetResolver())
            .deleteResolver(new IdentityStorIOContentResolverDeleteResolver())
            .build()
        ).build();

//    List<Identity> a = identitiesIn.getIDS();

   Log.d(LOG_TAG, identitiesIn.toString());


    for (Identity i : identitiesIn){
      Log.d(LOG_TAG, i.toString());

      if(i.type_code == "identity") {
        storIOContentResolver.put()
            .object(i)
            .prepare()
            .executeAsBlocking();
      }
      }
  }

}
