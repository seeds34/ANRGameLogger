package org.seeds.anrgamelogger.dagger;

import android.app.Application;
import android.content.ContentResolver;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tomas Seymour-Turner on 14/10/2017.
 */
@Module
public class ContentResolverModule {
    ContentResolver mContentResolver;

    public ContentResolverModule(){}

    @Provides
    @ANRLoggerActivityScope
    public ContentResolver provideContentResolver(Application application){
        return application.getContentResolver();
    }
}
