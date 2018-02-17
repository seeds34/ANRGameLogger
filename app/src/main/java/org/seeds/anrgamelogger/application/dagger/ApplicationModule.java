package org.seeds.anrgamelogger.application.dagger;

import android.app.Activity;
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

  private final Activity activity;
  private final String NRDB_BASE_API_URL;

  public ApplicationModule(Activity activityIn){
    this.activity = activityIn;
    NRDB_BASE_API_URL = activity.getString(R.string.nrdb_base_api_url);
  }


  @Provides
  @ApplicationScope
  public ContentResolver getContentResolver(){
    return activity.getContentResolver();
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
  public Retrofit getRetrofit(Moshi moshi, RxJava2CallAdapterFactory rxAdapter){
    return new Retrofit.Builder()
        .baseUrl(NRDB_BASE_API_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(rxAdapter)
        .build();
  }

  @Provides
  @ApplicationScope
  public RxJava2CallAdapterFactory getRxAdapter(){
    return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
  }





}
