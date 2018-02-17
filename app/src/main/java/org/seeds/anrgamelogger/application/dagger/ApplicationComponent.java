package org.seeds.anrgamelogger.application.dagger;

import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;
import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Tomas Seymour-Turner on 16/02/2018.
 */
@Component(modules={ApplicationModule.class})
@ApplicationScope
public interface ApplicationComponent {

  OkHttpClient okHttpClient();

  StorIOContentResolver storIOContentResolver();

  Retrofit retrofit();

}
