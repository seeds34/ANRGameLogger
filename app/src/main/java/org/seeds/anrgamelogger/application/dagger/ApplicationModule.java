package org.seeds.anrgamelogger.application.dagger;

import android.app.Application;
import com.pushtorefresh.storio3.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.impl.DefaultStorIOSQLite;
import com.squareup.moshi.Moshi;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import org.seeds.anrgamelogger.database.DatabaseModel;
import org.seeds.anrgamelogger.database.GameLoggerDatabase;
import org.seeds.anrgamelogger.database.buisnessobjects.Deck;
import org.seeds.anrgamelogger.database.buisnessobjects.DeckStorIOSQLiteDeleteResolver;
import org.seeds.anrgamelogger.database.buisnessobjects.DeckStorIOSQLiteGetResolver;
import org.seeds.anrgamelogger.database.buisnessobjects.DeckStorIOSQLitePutResolver;
import org.seeds.anrgamelogger.database.buisnessobjects.Identity;
import org.seeds.anrgamelogger.database.buisnessobjects.IdentityStorIOSQLiteDeleteResolver;
import org.seeds.anrgamelogger.database.buisnessobjects.IdentityStorIOSQLiteGetResolver;
import org.seeds.anrgamelogger.database.buisnessobjects.IdentityStorIOSQLitePutResolver;
import org.seeds.anrgamelogger.database.buisnessobjects.Location;
import org.seeds.anrgamelogger.database.buisnessobjects.LocationStorIOSQLiteDeleteResolver;
import org.seeds.anrgamelogger.database.buisnessobjects.LocationStorIOSQLiteGetResolver;
import org.seeds.anrgamelogger.database.buisnessobjects.LocationStorIOSQLitePutResolver;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameFlat;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameFlatStorIOSQLiteDeleteResolver;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameFlatStorIOSQLiteGetResolver;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameFlatStorIOSQLitePutResolver;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameOverview;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameOverviewStorIOSQLiteDeleteResolver;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameOverviewStorIOSQLiteGetResolver;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameOverviewStorIOSQLitePutResolver;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGamePlayer;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGamePlayerStorIOSQLiteDeleteResolver;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGamePlayerStorIOSQLiteGetResolver;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGamePlayerStorIOSQLitePutResolver;
import org.seeds.anrgamelogger.database.buisnessobjects.Player;
import org.seeds.anrgamelogger.database.buisnessobjects.PlayerStorIOSQLiteDeleteResolver;
import org.seeds.anrgamelogger.database.buisnessobjects.PlayerStorIOSQLiteGetResolver;
import org.seeds.anrgamelogger.database.buisnessobjects.PlayerStorIOSQLitePutResolver;
import org.seeds.anrgamelogger.network.NetworkModel;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Tomas Seymour-Turner on 16/02/2018.
 */
@Module
public class ApplicationModule {

  private final Application application;
  private final String NRDB_BASE_API_URL = "https://netrunnerdb.com/api/2.0/public/";
  ;

  public ApplicationModule(Application applicationIn) {
    this.application = applicationIn;
  }

  @Provides
  @ApplicationScope
  public StorIOSQLite getStorIOSQLite() {

    GameLoggerDatabase db = new GameLoggerDatabase(application.getApplicationContext());

    return DefaultStorIOSQLite.builder()
        .sqliteOpenHelper(db)
        .addTypeMapping(Identity.class, SQLiteTypeMapping.<Identity>builder()
            .putResolver(new IdentityStorIOSQLitePutResolver())
            .getResolver(new IdentityStorIOSQLiteGetResolver())
            .deleteResolver(new IdentityStorIOSQLiteDeleteResolver())
            .build())
//            .addTypeMapping(CardImage.class, SQLiteTypeMapping.<CardImage>builder()
//                    .putResolver(new )
//                    .getResolver()
//                    .deleteResolver()
//                    .build())
        .addTypeMapping(Player.class, SQLiteTypeMapping.<Player>builder()
            .putResolver(new PlayerStorIOSQLitePutResolver())
            .getResolver(new PlayerStorIOSQLiteGetResolver())
            .deleteResolver(new PlayerStorIOSQLiteDeleteResolver())
            .build())
        .addTypeMapping(Location.class, SQLiteTypeMapping.<Location>builder()
            .putResolver(new LocationStorIOSQLitePutResolver())
            .getResolver(new LocationStorIOSQLiteGetResolver())
            .deleteResolver(new LocationStorIOSQLiteDeleteResolver())
            .build())
        .addTypeMapping(Deck.class, SQLiteTypeMapping.<Deck>builder()
            .putResolver(new DeckStorIOSQLitePutResolver())
            .getResolver(new DeckStorIOSQLiteGetResolver())
            .deleteResolver(new DeckStorIOSQLiteDeleteResolver())
            .build())
        .addTypeMapping(LoggedGameOverview.class, SQLiteTypeMapping.<LoggedGameOverview>builder()
            .putResolver(new LoggedGameOverviewStorIOSQLitePutResolver())
            .getResolver(new LoggedGameOverviewStorIOSQLiteGetResolver())
            .deleteResolver(new LoggedGameOverviewStorIOSQLiteDeleteResolver())
            .build())
        .addTypeMapping(LoggedGamePlayer.class, SQLiteTypeMapping.<LoggedGamePlayer>builder()
            .putResolver(new LoggedGamePlayerStorIOSQLitePutResolver())
            .getResolver(new LoggedGamePlayerStorIOSQLiteGetResolver())
            .deleteResolver(new LoggedGamePlayerStorIOSQLiteDeleteResolver())
            .build())
        .addTypeMapping(LoggedGameFlat.class, SQLiteTypeMapping.<LoggedGameFlat>builder()
            .putResolver(new LoggedGameFlatStorIOSQLitePutResolver())
            .getResolver(new LoggedGameFlatStorIOSQLiteGetResolver())
            .deleteResolver(new LoggedGameFlatStorIOSQLiteDeleteResolver())
            .build())
        .build();


  }

  @Provides
  @ApplicationScope
  public OkHttpClient getHttpClient() {
    return new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).build();
  }

  @Provides
  @ApplicationScope
  public Moshi getMoshi() {
    return new Moshi.Builder().build();
  }

  @Provides
  @ApplicationScope
  public Retrofit getRetrofit(Moshi moshi, RxJava2CallAdapterFactory rxAdapter) {
    return new Retrofit.Builder()
        .baseUrl(NRDB_BASE_API_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(rxAdapter)
        .build();
  }

  @Provides
  @ApplicationScope
  public RxJava2CallAdapterFactory getRxAdapter() {
    return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
  }

  @Provides
  @ApplicationScope
  public DatabaseModel getDatabaseModel(StorIOSQLite storeIOSQL) {
    return new DatabaseModel(storeIOSQL);
  }

  @Provides
  @ApplicationScope
  public NetworkModel getNetworkModel(OkHttpClient okHttpClientIn, Retrofit retrofitIn) {
    return new NetworkModel(okHttpClientIn, retrofitIn);
  }
}