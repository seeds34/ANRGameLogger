package org.seeds.anrgamelogger.application;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.seeds.anrgamelogger.model.CardImage;
import org.seeds.anrgamelogger.model.CardImageList;
import org.seeds.anrgamelogger.model.CardList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 27/03/2018.
 */

public class NetworkModel {

    private static final String LOG_TAG = NetworkModel.class.getSimpleName();
    private final OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private final String CGDB_BASE_URL = "http://www.cardgamedb.com/forums/uploads/an/ffg_adn";
    private final String NRDB_IMAGE_URL = "https://netrunnerdb.com/card_image/";
    private final String IMAGE_FILE_EXT = ".png";

    public NetworkModel(OkHttpClient okHttpClientIn, Retrofit retrofitIn){
        okHttpClient = okHttpClientIn;
        retrofit = retrofitIn;
    }

    public Single<CardList> getNRDBCardList(){
        NRDBApiEndpointInterface apiService = retrofit.create(NRDBApiEndpointInterface.class);
        Single<CardList> call = apiService.getAllIdentities();

           return call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<DataPackList> getNRDBPackList(){
        NRDBApiEndpointInterface apiService = retrofit.create(NRDBApiEndpointInterface.class);
        Single<DataPackList> call = apiService.getAllDataPacks();

        return call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Response> getData(String url) {

        final OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url)
                .build();

        return Observable.create(emitter ->
            {try{

                Response response = client.newCall(request).execute();
                emitter.onNext(response);
                emitter.onComplete();
            }catch (IOException e){
                emitter.onError(e);
            }

            }
        );
    }

    public Observable<byte[]> getNRDBCardImage(String card_code) {

        String url = NRDB_IMAGE_URL + card_code + IMAGE_FILE_EXT;

        Request request = new Request.Builder().url(url)
                .build();

        return getData(url)
                .retry()
                .map(i -> i.body().byteStream())
                .map(i -> turnInputStreamToBAOS(i))
                .map(i -> i.toByteArray());

    }


    private ByteArrayOutputStream turnInputStreamToBAOS(InputStream is) {

        int index;
        byte[] byteChunk = new byte[1024];

        ByteArrayOutputStream imageByteArrayOutputStream = new ByteArrayOutputStream();


        if (is != null) {
            try {
                while ((index = is.read(byteChunk)) > 0) {
                    imageByteArrayOutputStream.write(byteChunk, 0, index);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imageByteArrayOutputStream;
    }


    public byte[] getCGDBCardImage(String pack_code){

        String url = CGDB_BASE_URL;

        Request request = new Request.Builder().url(url)
                .build();

//        byte[] ret = new byte[0];
        ByteArrayOutputStream imageByteArrayOutputStream = new ByteArrayOutputStream();


        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(LOG_TAG, "request failed: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(LOG_TAG, "Reading in image");

                int index;
                byte[] byteChunk = new byte[1024];

                InputStream is = response.body().byteStream();

                if (is != null) {
                    while ((index = is.read(byteChunk)) > 0) {
                        imageByteArrayOutputStream.write(byteChunk, 0, index);
                    }
                }
                //ret = imageByteArrayOutputStream.toByteArray(); // Read the data from the stream
            }
        });

        Log.d(LOG_TAG, (imageByteArrayOutputStream.toByteArray() == null)?"[HTTP]Image Array is null":"[HTTP]Image Array is not null");

        return imageByteArrayOutputStream.toByteArray();
    }

    public void getCardImage(){}
}
