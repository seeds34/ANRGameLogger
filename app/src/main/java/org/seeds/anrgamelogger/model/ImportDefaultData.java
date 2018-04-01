package org.seeds.anrgamelogger.model;


import android.app.Activity;
import android.content.ContentResolver;
import android.util.Log;
import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;

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

import org.seeds.anrgamelogger.application.DatabaseModel;
import org.seeds.anrgamelogger.application.NRDBApiEndpointInterface;
import org.seeds.anrgamelogger.application.NetworkModel;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


/**
 * Created by Tomas Seymour-Turner on 09/01/2018.
 */

public class ImportDefaultData {

    private static final String LOG_TAG = ImportDefaultData.class.getSimpleName();
    private final String NRDB_CARD_LIST_API_URL = "https://netrunnerdb.com/api/2.0/public/cards";
    private final String NRDB_IMAGE_URL = "https://netrunnerdb.com/card_image/";
    private final String IMAGE_FILE_EXT = ".png";

    private ContentResolver contentResolver;

    private byte[] imageByteArray;
    private InputStream is;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private DatabaseModel databaseModel;
    private NetworkModel networkModel;

    private StorIOContentResolver storIOContentResolver;

//    public ImportDefaultData(DatabaseModel databaseModelIn, Activity activity, StorIOContentResolver storIOContentResolverIn, OkHttpClient okHttpClientIn, Retrofit retrofitIn) {
//        contentResolver = activity.getContentResolver();data
//        storIOContentResolver = storIOContentResolverIn;
//        okHttpClient = okHttpClientIn;
//        retrofit = retrofitIn;
//        databaseModel = databaseModelIn;
//    }

    public ImportDefaultData(DatabaseModel databaseModelIn, NetworkModel networkModelIn){
        databaseModel = databaseModelIn;
        networkModel = networkModelIn;

        Log.i(LOG_TAG,"Database Model is: " + databaseModel);
    }



    public void populateIdentitiesTable() {

        networkModel.getNRDBCardList()
        .subscribe(i -> insertID(i));

//        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());

//        Moshi moshi = new Moshi.Builder().build();
//        JsonAdapter<CardList> jsonAdapter = moshi.adapter(CardList.class);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(NRDB_BASE_API_URL)
//                .addConverterFactory(MoshiConverterFactory.create(moshi))
//                .addCallAdapterFactory(rxAdapter)
//                .build();

//        Log.d(LOG_TAG, "Retrofit Started");
//
//        NRDBApiEndpointInterface apiService = retrofit.create(NRDBApiEndpointInterface.class);
//
//        Log.d(LOG_TAG, "RAPI Services Started");
//
//        Single<CardList> call = apiService.getAllIdentities();
//
//        Log.d(LOG_TAG, "Observiable Made");
//
//        call.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(i -> insertID(i));
    }


    public void insertID(CardList identitiesIn) {

//        StorIOContentResolver storIOContentResolver = DefaultStorIOContentResolver.builder()
//                .contentResolver(contentResolver)
//                .addTypeMapping(Card.class, ContentResolverTypeMapping.<Card>builder()
//                        .putResolver(new IdentityStorIOContentResolverPutResolver())
//                        .getResolver(new IdentityStorIOContentResolverGetResolver())
//                        .deleteResolver(new IdentityStorIOContentResolverDeleteResolver())
//                        .build()
//                ).build();

        List<Card> ids = identitiesIn.getIdentities();

        Log.d(LOG_TAG,"Inserting IDs");

        for (Card i : ids) {

            Log.d(LOG_TAG, "Type: " + i.type_code);

            if (i.type_code.equals("identity")) {

                Log.d(LOG_TAG, i.toString());

                databaseModel.insertIdentity(i);
            }
        }
    }

    public void setUpIdentityImages() {

        Log.d(LOG_TAG,"Add Images to IDs");

        List<Card> cardImageList = databaseModel.getIdentities();
        for (Card i : cardImageList) {
            Log.d(LOG_TAG, "Reading in image");

           // i.setImageByteArray(networkModel.getNRDBCardImage(i.getCode()));

            Log.d(LOG_TAG, (i.getImageByteArrayOutputStream() == null)?"[IDD]Image Array is null":"[IDD]Image Array is not null");
        }
        databaseModel.insertIdentities(cardImageList);

//        StorIOContentResolver storIOContentResolver = DefaultStorIOContentResolver.builder()
//                .contentResolver(contentResolver)
//                .addTypeMapping(Card.class, ContentResolverTypeMapping.<Card>builder()
//                        .putResolver(new IdentityStorIOContentResolverPutResolver())
//                        .getResolver(new IdentityStorIOContentResolverGetResolver())
//                        .deleteResolver(new IdentityStorIOContentResolverDeleteResolver())
//                        .build()
//                ).build();


//        List<Card> cardImageList = databaseModel.getIdentities();
//
////        storIOContentResolver
////                .get()
////                .listOfObjects(Card.class)
////                .withQuery(Query.builder()
////                        .uri(IdentitiesContract.URI_TABLE)
////                        .build())
////                .prepare()
////                .executeAsBlocking();
//
//
//
//        for (Card i : cardImageList) {
//
////            String url = NRDB_IMAGE_URL + i.getCode() + IMAGE_FILE_EXT;
//
////            OkHttpClient okHttpClient = new OkHttpClient();
////            Request request = new Request.Builder().url(url)
////                    .build();
//
////            okHttpClient.newCall(request).enqueue(new Callback() {
//
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    Log.d(LOG_TAG, "request failed: " + e.getMessage());
//
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//
//
//                    ByteArrayOutputStream imageByteArrayOutputStream = new ByteArrayOutputStream();
//                    int index;
//                    byte[] byteChunk = new byte[1024];
//
//                    InputStream is = response.body().byteStream();
//
//                    if (is != null) {
//                        while ((index = is.read(byteChunk)) > 0) {
//                            imageByteArrayOutputStream.write(byteChunk, 0, index);
//                        }
//                    }
//
//                    i.setImageByteArray(imageByteArrayOutputStream.toByteArray()); // Read the data from the stream
//
//
//
////                    PutResult p = storIOContentResolver
////                            .put()
////                            .object(i)
////                            .prepare()
////                            .executeAsBlocking();
////
////                    Log.d(LOG_TAG,"Affected URI: " + p.affectedUri());
////                    Log.d(LOG_TAG, "Row updated: "+p.wasUpdated());
////                    Log.d(LOG_TAG, "Number of rows updated " + p.numberOfRowsUpdated());
//                }
////            });
//
//
//
////                      PutResults p = storIOContentResolver
////                            .put()
////                            .objects(cardImageList)
////                            .prepare()
////                            .executeAsBlocking();
//        }
    }
}
















  //        .map(new Function<CardList, List<Card>>() {
//          @Override
//          public List<Card> apply(
//              @io.reactivex.annotations.NonNull final CardList cityResponse)
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

//        .subscribe(new Consumer<List<Card>>() {
//          @Override
//          public void accept(
//              @io.reactivex.annotations.NonNull final List<Card> ident)
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

