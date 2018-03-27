package org.seeds.anrgamelogger.application.dagger;

import android.app.Activity;
import android.app.Application;
import android.content.ContentResolver;
import com.pushtorefresh.storio3.contentresolver.ContentResolverTypeMapping;
import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio3.contentresolver.impl.DefaultStorIOContentResolver;
import com.squareup.moshi.Moshi;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.application.CardDetailsModel;
import org.seeds.anrgamelogger.application.DatabaseModel;
import org.seeds.anrgamelogger.model.Identity;
import org.seeds.anrgamelogger.model.IdentityStorIOContentResolverDeleteResolver;
import org.seeds.anrgamelogger.model.IdentityStorIOContentResolverGetResolver;
import org.seeds.anrgamelogger.model.IdentityStorIOContentResolverPutResolver;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Tomas Seymour-Turner on 16/02/2018.
 */
@Module
public class ApplicationModule {

  private final Application application;
  private final String NRDB_BASE_API_URL;
  private final String CGDB_BASE_URL;

  public ApplicationModule(Application applicationIn){
    this.application = applicationIn;
    NRDB_BASE_API_URL = application.getString(R.string.nrdb_base_api_url);
    CGDB_BASE_URL = "http://www.cardgamedb.com/forums/uploads/an/";
  }

  @Provides
  @ApplicationScope
  public ContentResolver getContentResolver(){
    return application.getContentResolver();
  }

  @Provides
  @ApplicationScope
  public StorIOContentResolver getStorIOContentResolver(ContentResolver contentResolverIn){
    return DefaultStorIOContentResolver.builder()
        .contentResolver(contentResolverIn)
        .addTypeMapping(Identity.class, ContentResolverTypeMapping.<Identity>builder()
            .putResolver(new IdentityStorIOContentResolverPutResolver())
            .getResolver(new IdentityStorIOContentResolverGetResolver())
            .deleteResolver(new IdentityStorIOContentResolverDeleteResolver())
            .build()
        ).build();
  }

  @Provides
  @ApplicationScope
  public OkHttpClient getHttpClient(){
   return new OkHttpClient();
  }

  @Provides
  @ApplicationScope
      public Moshi getMoshi() {
    return new Moshi.Builder().build();
  }

  @Provides
  @ApplicationScope
  public Retrofit getNRDBRetrofit(Moshi moshi, RxJava2CallAdapterFactory rxAdapter){
    return new Retrofit.Builder()
        .baseUrl(NRDB_BASE_API_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(rxAdapter)
        .build();
  }

  @Provides
  @ApplicationScope
  public Retrofit getCGDBRetrofit(Moshi moshi, RxJava2CallAdapterFactory rxAdapter){
    return new Retrofit.Builder()
            .baseUrl(CGDB_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(rxAdapter)
            .build();
  }

  @Provides
  @ApplicationScope
  public RxJava2CallAdapterFactory getRxAdapter(){
    return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
  }

  @Provides
  @ApplicationScope
  public DatabaseModel getDatabaseModel(StorIOContentResolver storIOContentResolverIn){
    return new DatabaseModel(storIOContentResolverIn);
  }

  @Provides
  @ApplicationScope
  public CardDetailsModel getCardDetailsModel(OkHttpClient okHttpClientIn, Moshi moshi, RxJava2CallAdapterFactory rxAdapter ){
    return new CardDetailsModel(okHttpClientIn, moshi, rxAdapter);
  }
}