package org.seeds.anrgamelogger.dagger;

import android.app.Application;

import org.seeds.anrgamelogger.ANRLoggerApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tomas Seymour-Turner on 14/10/2017.
 */
@Module
public class AnrLoggerApplicationModule {
    Application mAnrLoggerApplication;

    public AnrLoggerApplicationModule(ANRLoggerApplication mAnrLoggerApplication){
        this.mAnrLoggerApplication = mAnrLoggerApplication;
    }

    @Provides
    @ANRLoggerActivityScope
    public Application provideApplication(){
        return mAnrLoggerApplication;
    }
}
