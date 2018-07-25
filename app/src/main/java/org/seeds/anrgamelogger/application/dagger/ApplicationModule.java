package org.seeds.anrgamelogger.application.dagger;

import android.app.Application;
import android.content.ContentResolver;
import com.pushtorefresh.storio3.contentresolver.ContentResolverTypeMapping;
import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio3.contentresolver.impl.DefaultStorIOContentResolver;
import com.squareup.moshi.Moshi;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

import org.seeds.anrgamelogger.buisnessobjects.Identity;
import org.seeds.anrgamelogger.buisnessobjects.Location;
import org.seeds.anrgamelogger.buisnessobjects.LoggedGameFlat;
import org.seeds.anrgamelogger.buisnessobjects.LoggedGameOverview;
import org.seeds.anrgamelogger.buisnessobjects.LoggedGamePlayer;
import org.seeds.anrgamelogger.buisnessobjects.Player;
import org.seeds.anrgamelogger.database.DatabaseModel;
import org.seeds.anrgamelogger.network.NetworkModel;
import org.seeds.anrgamelogger.database.customputresolvers.CustomDeckPutResolver;
import org.seeds.anrgamelogger.database.customputresolvers.CustomIdentityPutResolver;
import org.seeds.anrgamelogger.database.customputresolvers.CustomLocationPutResolver;
import org.seeds.anrgamelogger.database.customputresolvers.CustomPlayerPutResolver;
import org.seeds.anrgamelogger.buisnessobjects.Deck;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Tomas Seymour-Turner on 16/02/2018.
 */
@Module
public class ApplicationModule {

  private final Application application;
  private final String NRDB_BASE_API_URL = "https://netrunnerdb.com/api/2.0/public/";;

  public ApplicationModule(Application applicationIn){
    this.application = applicationIn;
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
                    .putResolver(new CustomIdentityPutResolver())
                    .getResolver(new IdentityStorIOContentResolverGetResolver())
                    .deleteResolver(new IdentityStorIOContentResolverDeleteResolver())
                    .build())
            .addTypeMapping(LoggedGamePlayer.CardImage.class, ContentResolverTypeMapping.<LoggedGamePlayer.CardImage>builder()
                    .putResolver(new CardImageStorIOContentResolverPutResolver())
                    .getResolver(new CardImageStorIOContentResolverGetResolver())
                    .deleteResolver(new CardImageStorIOContentResolverDeleteResolver())
                    .build())
            .addTypeMapping(Player.class, ContentResolverTypeMapping.<Player>builder()
                    .putResolver(new CustomPlayerPutResolver())
                    .getResolver(new PlayerStorIOContentResolverGetResolver())
                    .deleteResolver(new PlayerStorIOContentResolverDeleteResolver())
                    .build())
            .addTypeMapping(Location.class, ContentResolverTypeMapping.<Location>builder()
                    .putResolver(new CustomLocationPutResolver())
                    .getResolver(new LocationStorIOContentResolverGetResolver())
                    .deleteResolver(new LocationStorIOContentResolverDeleteResolver())
                    .build())
            .addTypeMapping(Deck.class, ContentResolverTypeMapping.<Deck>builder()
                    .putResolver(new CustomDeckPutResolver())
                    .getResolver(new DeckStorIOContentResolverGetResolver())
                    .deleteResolver(new DeckStorIOContentResolverDeleteResolver())
                    .build())
            .addTypeMapping(LoggedGameOverview.class, ContentResolverTypeMapping.<LoggedGameOverview>builder()
                    .putResolver(new LoggedGameOverviewsStorIOContentResolverPutResolver())
                    .getResolver(new LoggedGameOverviewsStorIOContentResolverGetResolver())
                    .deleteResolver(new LoggedGameOverviewsStorIOContentResolverDeleteResolver())
                    .build())
        .addTypeMapping(LoggedGamePlayer.class, ContentResolverTypeMapping.<LoggedGamePlayer>builder()
            .putResolver(new LoggedGamePlayerStorIOContentResolverPutResolver())
            .getResolver(new LoggedGamePlayerStorIOContentResolverGetResolver())
            .deleteResolver(new LoggedGamePlayerStorIOContentResolverDeleteResolver())
            .build())
            .addTypeMapping(LoggedGameFlat.class, ContentResolverTypeMapping.<LoggedGameFlat>builder()
                    .putResolver(new LoggedGameFlatStorIOContentResolverPutResolver())
                    .getResolver(new LoggedGameFlatStorIOContentResolverGetResolver())
                    .deleteResolver(new LoggedGameFlatStorIOContentResolverDeleteResolver())
                    .build())
            .build();


  }

  @Provides
  @ApplicationScope
  public OkHttpClient getHttpClient(){
   return new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).build();
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

  @Provides
  @ApplicationScope
  public DatabaseModel getDatabaseModel(StorIOContentResolver storIOContentResolverIn){
    return new DatabaseModel(storIOContentResolverIn);
  }

  @Provides
  @ApplicationScope
  public NetworkModel getNetworkModel(OkHttpClient okHttpClientIn, Retrofit retrofitIn){
    return new NetworkModel(okHttpClientIn,retrofitIn);
  }
}