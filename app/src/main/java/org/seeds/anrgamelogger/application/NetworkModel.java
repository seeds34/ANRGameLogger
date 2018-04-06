package org.seeds.anrgamelogger.application;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.seeds.anrgamelogger.model.CardList;
import retrofit2.Retrofit;

/**
 * Created by user on 27/03/2018.
 */

public class NetworkModel {

    private static final String LOG_TAG = "NetworkModel";
    private final OkHttpClient okHttpClient;
    private Retrofit retrofit;

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

    public Observable<Response> getData(String url) {
        //final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url)
                .build();

        return Observable.create(emitter ->
                {try{
                    Response response = okHttpClient.newCall(request).execute();
                    //Log.d(LOG_TAG,"HTTP Response: " +  response.body().string());
                    emitter.onNext(response);
                    emitter.onComplete();
                }catch (IOException e){
                    emitter.onError(e);
                }
                }
        );
    }

}

