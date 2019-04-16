package org.seeds.anrgamelogger.application.dagger;

import dagger.Component;
import okhttp3.OkHttpClient;
import org.seeds.anrgamelogger.database.DatabaseModel;
import org.seeds.anrgamelogger.database.GameLoggerDatabase;
import org.seeds.anrgamelogger.network.NetworkModel;
import retrofit2.Retrofit;

/**
 * Created by Tomas Seymour-Turner on 16/02/2018.
 */
@Component(modules={ApplicationModule.class})
@ApplicationScope
public interface ApplicationComponent {

  OkHttpClient okHttpClient();

  GameLoggerDatabase getDatabse();

  Retrofit retrofit();

  DatabaseModel databaseModel();

  NetworkModel networkModel();
}
