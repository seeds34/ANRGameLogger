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
import org.seeds.anrgamelogger.application.DatabaseModel;
import org.seeds.anrgamelogger.application.NetworkModel;
import org.seeds.anrgamelogger.buisnessobjects.Deck;
import org.seeds.anrgamelogger.buisnessobjects.DeckStorIOContentResolverDeleteResolver;
import org.seeds.anrgamelogger.buisnessobjects.DeckStorIOContentResolverGetResolver;
import org.seeds.anrgamelogger.buisnessobjects.DeckStorIOContentResolverPutResolver;
import org.seeds.anrgamelogger.buisnessobjects.Identity;
import org.seeds.anrgamelogger.buisnessobjects.IdentityStorIOContentResolverDeleteResolver;
import org.seeds.anrgamelogger.buisnessobjects.IdentityStorIOContentResolverGetResolver;
import org.seeds.anrgamelogger.buisnessobjects.IdentityStorIOContentResolverPutResolver;
import org.seeds.anrgamelogger.buisnessobjects.Location;
import org.seeds.anrgamelogger.buisnessobjects.LocationStorIOContentResolverDeleteResolver;
import org.seeds.anrgamelogger.buisnessobjects.LocationStorIOContentResolverGetResolver;
import org.seeds.anrgamelogger.buisnessobjects.LocationStorIOContentResolverPutResolver;
import org.seeds.anrgamelogger.buisnessobjects.LoggedGameFlat;
import org.seeds.anrgamelogger.buisnessobjects.LoggedGameFlatStorIOContentResolverDeleteResolver;
import org.seeds.anrgamelogger.buisnessobjects.LoggedGameFlatStorIOContentResolverGetResolver;
import org.seeds.anrgamelogger.buisnessobjects.LoggedGameFlatStorIOContentResolverPutResolver;
import org.seeds.anrgamelogger.buisnessobjects.Player;
import org.seeds.anrgamelogger.buisnessobjects.PlayerStorIOContentResolverDeleteResolver;
import org.seeds.anrgamelogger.buisnessobjects.PlayerStorIOContentResolverGetResolver;
import org.seeds.anrgamelogger.buisnessobjects.PlayerStorIOContentResolverPutResolver;
import org.seeds.anrgamelogger.model.CardImage;
import org.seeds.anrgamelogger.model.CardImageStorIOContentResolverDeleteResolver;
import org.seeds.anrgamelogger.model.CardImageStorIOContentResolverGetResolver;
import org.seeds.anrgamelogger.model.CardImageStorIOContentResolverPutResolver;
import org.seeds.anrgamelogger.model.LoggedGame;
import org.seeds.anrgamelogger.model.LoggedGameStorIOContentResolverDeleteResolver;
import org.seeds.anrgamelogger.model.LoggedGameStorIOContentResolverGetResolver;
import org.seeds.anrgamelogger.model.LoggedGameStorIOContentResolverPutResolver;


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
                    .putResolver(new IdentityStorIOContentResolverPutResolver())
                    .getResolver(new IdentityStorIOContentResolverGetResolver())
                    .deleteResolver(new IdentityStorIOContentResolverDeleteResolver())
                    .build())
            .addTypeMapping(CardImage.class, ContentResolverTypeMapping.<CardImage>builder()
                    .putResolver(new CardImageStorIOContentResolverPutResolver())
                    .getResolver(new CardImageStorIOContentResolverGetResolver())
                    .deleteResolver(new CardImageStorIOContentResolverDeleteResolver())
                    .build())
            .addTypeMapping(Player.class, ContentResolverTypeMapping.<Player>builder()
                    .putResolver(new PlayerStorIOContentResolverPutResolver())
                    .getResolver(new PlayerStorIOContentResolverGetResolver())
                    .deleteResolver(new PlayerStorIOContentResolverDeleteResolver())
                    .build())
            .addTypeMapping(Location.class, ContentResolverTypeMapping.<Location>builder()
                    .putResolver(new LocationStorIOContentResolverPutResolver())
                    .getResolver(new LocationStorIOContentResolverGetResolver())
                    .deleteResolver(new LocationStorIOContentResolverDeleteResolver())
                    .build())
            .addTypeMapping(Deck.class, ContentResolverTypeMapping.<Deck>builder()
                    .putResolver(new DeckStorIOContentResolverPutResolver())
                    .getResolver(new DeckStorIOContentResolverGetResolver())
                    .deleteResolver(new DeckStorIOContentResolverDeleteResolver())
                    .build())
            .addTypeMapping(LoggedGame.class, ContentResolverTypeMapping.<LoggedGame>builder()
                    .putResolver(new LoggedGameStorIOContentResolverPutResolver())
                    .getResolver(new LoggedGameStorIOContentResolverGetResolver())
                    .deleteResolver(new LoggedGameStorIOContentResolverDeleteResolver())
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