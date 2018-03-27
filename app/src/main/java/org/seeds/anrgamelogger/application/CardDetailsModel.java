package org.seeds.anrgamelogger.application;

import com.jakewharton.rxbinding2.widget.RxAdapter;
import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;
import com.squareup.moshi.Moshi;

import org.seeds.anrgamelogger.model.IdentitiesData;
import org.seeds.anrgamelogger.model.NRDBApiEndpointInterface;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by user on 27/03/2018.
 */

public class CardDetailsModel {

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private Retrofit NRDBRetrofit;
    private Retrofit GCDBRetrofit;
    private final String NRDB_BASE_API_URL = "https://netrunnerdb.com/card_image/";
    private final String CGDB_BASE_URL = "http://www.cardgamedb.com/forums/uploads/an/";

    private IdentitiesData data;

    public CardDetailsModel(OkHttpClient okHttpClientIn, Moshi moshi, RxJava2CallAdapterFactory rxAdapter){

    okHttpClient = okHttpClientIn;

        NRDBRetrofit = new Retrofit.Builder()
                .baseUrl(NRDB_BASE_API_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(rxAdapter)
                .build();

        GCDBRetrofit = new Retrofit.Builder()
                .baseUrl(CGDB_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(rxAdapter)
                .build();
    }

    public void getNRDBCardList(){
        NRDBApiEndpointInterface apiService = retrofit.create(NRDBApiEndpointInterface.class);
        Single<IdentitiesData> call = apiService.getAllIdentities();


         call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(i -> data);

    }

    public void getNRDBPackList(){}

    public void getNRDBCardImage(){}

    public void getCGDBCardImage(){}

    public void getCardImage(){}
}
