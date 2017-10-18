package org.seeds.anrgamelogger.dagger;

import android.content.ContentResolver;

import org.seeds.anrgamelogger.ANRLoggerApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tomas Seymour-Turner on 14/10/2017.
 */
@Module
public class ContentResolverModule {

    @Provides
    @AppScope
    ContentResolver provideContentResolver(ANRLoggerApplication application){
        return application.getContentResolver();
    }
}
