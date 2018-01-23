package org.seeds.anrgamelogger.model;


import android.app.Activity;
import android.content.ContentResolver;
import android.util.Log;
import com.pushtorefresh.storio3.contentresolver.ContentResolverTypeMapping;
import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio3.contentresolver.impl.DefaultStorIOContentResolver;
import com.pushtorefresh.storio3.contentresolver.operations.put.PutResult;
import com.pushtorefresh.storio3.contentresolver.queries.Query;
import com.pushtorefresh.storio3.contentresolver.queries.UpdateQuery;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
    private final String NRDB_IMAGE_URL = "https://netrunnerdb.com/card_image/";
    private final String IMAGE_FILE_EXT = ".png";

    private ContentResolver contentResolver;

    private byte[] imageByteArray;
    private InputStream is;

    public ImportDefaultData(Activity activity) {
        contentResolver = activity.getContentResolver();
    }

    public void populateIdentitiesTable() {

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

        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(i -> insertID(i)
                );

    }


    public void insertID(IdentitiesData identitiesIn) {

        StorIOContentResolver storIOContentResolver = DefaultStorIOContentResolver.builder()
                .contentResolver(contentResolver)
                .addTypeMapping(Identity.class, ContentResolverTypeMapping.<Identity>builder()
                        .putResolver(new IdentityStorIOContentResolverPutResolver())
                        .getResolver(new IdentityStorIOContentResolverGetResolver())
                        .deleteResolver(new IdentityStorIOContentResolverDeleteResolver())
                        .build()
                ).build();

        List<Identity> ids = identitiesIn.getIdentities();

        for (Identity i : ids) {

            Log.d(LOG_TAG, "Type: " + i.type_code);
            if (i.type_code.equals("identity")) {

                Log.d(LOG_TAG, i.toString());

                storIOContentResolver.put()
                        .object(i)
                        .prepare()
                        .executeAsBlocking();
            }
        }

        setUpIdentityImages();
    }


    public void setUpIdentityImages() {

        StorIOContentResolver storIOContentResolver = DefaultStorIOContentResolver.builder()
                .contentResolver(contentResolver)
                .addTypeMapping(Identity.class, ContentResolverTypeMapping.<Identity>builder()
                        .putResolver(new IdentityStorIOContentResolverPutResolver())
                        .getResolver(new IdentityStorIOContentResolverGetResolver())
                        .deleteResolver(new IdentityStorIOContentResolverDeleteResolver())
                        .build()
                ).build();


        List<Identity> cardImageList = storIOContentResolver
                .get()
                .listOfObjects(Identity.class)
                .withQuery(Query.builder()
                        .uri(IdentitiesContract.URI_TABLE)
                        .build())
                .prepare()
                .executeAsBlocking();



        for (Identity i : cardImageList) {

            String url = NRDB_IMAGE_URL + i.getCode() + IMAGE_FILE_EXT;

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(LOG_TAG, "request failed: " + e.getMessage());

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d(LOG_TAG, "Reading in image");

                    ByteArrayOutputStream imageByteArrayOutputStream = new ByteArrayOutputStream();
                    int index;
                    byte[] byteChunk = new byte[1024];

                    InputStream is = response.body().byteStream();

                    if (is != null) {
                        while ((index = is.read(byteChunk)) > 0) {
                            imageByteArrayOutputStream.write(byteChunk, 0, index);
                        }
                    }

                    i.setImageByteArray(imageByteArrayOutputStream.toByteArray()); // Read the data from the stream

                    Log.d(LOG_TAG, (i.getImageByteArrayOutputStream() == null)?"Image Array is null":"Image Array is not null");

                    PutResult p = storIOContentResolver
                            .put()
                            .object(i)
                            .prepare()
                            .executeAsBlocking();

                    Log.d(LOG_TAG,"Efffected URI: " + p.affectedUri());
                    Log.d(LOG_TAG, "Row updated: "+p.wasUpdated());

                    Log.d(LOG_TAG, "Number of rows updated " + p.numberOfRowsUpdated());
                }
            });
        }
    }
}
















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

//        .subscribe(new Consumer<List<Identity>>() {
//          @Override
//          public void accept(
//              @io.reactivex.annotations.NonNull final List<Identity> ident)
//              throws Exception {
//
//            Log.d(LOG_TAG, "Fire Call.accept");
//
//            insertID(ident);
//          }
//        });
  //get ID by nrdb number
  //get image for that number
  //store image

//  public void setImageByteArray(InputStream imageInputStream){
//
//        is = imageInputStream;
//
//  }
//
//  public byte[] downloadImage(String imageNumber) {
//
//    String url = NRDB_IMAGE_URL + imageNumber + IMAGE_FILE_EXT;
////
////      OkHttpClient client = new OkHttpClient();
////      Request request = new Request.Builder().url(url)
////              .build();
////
////byte[] ret = null;
////
////      Response response = null;
////      try {
////          response = client.newCall(request).execute();
////
////
////      InputStream in = response.body().byteStream();
////
////      //BufferedReader reader = new BufferedReader(new InputStreamReader(in));
////
////      ByteArrayOutputStream imageByteArrayOutputStream = new ByteArrayOutputStream();
////      int index;
////      byte[] byteChunk = new byte[1024];
////
////      Log.d(LOG_TAG, "Image inptut Strem is " + is.toString());
////
////      if ( is != null ) {
////          while ((index = is.read(byteChunk)) > 0) {
////              imageByteArrayOutputStream.write(byteChunk, 0, index);
////          }
////      }
////
////      ret = imageByteArrayOutputStream.toByteArray();
////
////      } catch (IOException e) {
////
////      }
////      response.body().close();
////
////
////
////
////
////return ret;
//
//
//
//
//        OkHttpClient client = new OkHttpClient();
//
//    Request request = new Request.Builder()
//            .url(url)
//            .build();
//
////    ByteArrayOutputStream imageByteArrayOutputStream = new ByteArrayOutputStream();
////    int index;
////    byte[] byteChunk = new byte[1024];
////
////    InputStream imageInputStream;
//
//
//  client.newCall(request).enqueue(new Callback() {
//
//      @Override
//      public void onFailure(Call call, IOException e) {
//        Log.d(LOG_TAG, "request failed: " + e.getMessage());
//
//      }
//
//      @Override
//      public void onResponse(Call call, Response response) throws IOException {
//        Log.d(LOG_TAG,"Reading in image" );
//        setImageByteArray(response.body().byteStream()); // Read the data from the stream
//      }
//    });
//
//
//
//
//      ByteArrayOutputStream imageByteArrayOutputStream = new ByteArrayOutputStream();
//      int index;
//      byte[] byteChunk = new byte[1024];
//
//      Log.d(LOG_TAG, "Image inptut Strem is " + is.toString());
//
//      if ( is != null ) {
//          try {
//              while ( (index = is.read(byteChunk)) > 0 ) {
//                  imageByteArrayOutputStream.write(byteChunk, 0, index);
//              }
//          } catch (IOException e) {
//              e.printStackTrace();
//          }
//
//          try {
//              is.close();
//          } catch (IOException e) {
//              e.printStackTrace();
//          }
//
//      }
//      return imageByteArrayOutputStream.toByteArray();
////

