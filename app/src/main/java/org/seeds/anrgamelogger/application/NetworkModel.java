package org.seeds.anrgamelogger.application;

import android.util.Log;
import org.seeds.anrgamelogger.model.CardList;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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

    public Observable<byte[]> getNRDBCardImage(String card_code) {

        String url = NRDB_IMAGE_URL + card_code + IMAGE_FILE_EXT;

        return getData(url)
                .retry()
                .map(i -> i.body().byteStream())
                .map(i -> turnInputStreamToBAOS(i))
                .map(i -> i.toByteArray());

    }

    public Observable<byte[]> getCGDBCardImage(String nrdb_pack_code, String card_code){

        //Use NRDB pack Code to get FFG_Pack Code
        //Create URL
        //String url = CGDB_BASE_URL;
        //String ffg_pack_code;

        getNRDBPackList()
                .map(pl -> {

                    String ffg_pack_code = "";
                    for(DataPack dp : pl.getDataPacks()) {
                        if (dp.getPack_code() == nrdb_pack_code) {
                            ffg_pack_code = dp.getFfg_code();
                        }
                    }

                    String url = CGDB_BASE_URL + ffg_pack_code + "_" +card_code + IMAGE_FILE_EXT;
                    return url;
                }




        //url +=  ffg_pack_code + "_" +card_code + IMAGE_FILE_EXT;


        return getData(url)
                .retry()
                .map(i -> i.body().byteStream())
                .map(i -> turnInputStreamToBAOS(i))
                .map(i -> i.toByteArray());
    }

    public Observable<byte[]> getCardImage(String nrdb_pack_code, String card_code){

        //1: Get Image From NRDB
        //2: If Image is Null
        //2.1: Get Image from CGDB
        //2.2: If Iamge is Null
        //2.2.1: Use defult iamge

        return getNRDBCardImage(card_code)
                .flatMap(
                        i -> {
                            //Observable<byte[]> ret;
                            if(isImageTrue()){
                                return Observable.just(i);
                            }else{
                                return getCGDBCardImage(nrdb_pack_code, card_code);
                            }
                            //return ret;
                        }
                );
    }

    private boolean isImageTrue(){
        //Check image
        return false;
    }


    private Observable<Response> getData(String url) {

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

}

