package org.seeds.anrgamelogger.dagger;

import org.seeds.anrgamelogger.ANRLoggerApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tomas Seymour-Turner on 14/10/2017.
 */
@Module
public class ANRLoggerAppModule {

    ANRLoggerApplication mANRLoggerApplication;

    public ANRLoggerAppModule(ANRLoggerApplication anrLoggerApplicationIn){
        this.mANRLoggerApplication = anrLoggerApplicationIn;
    }

    @Provides
    @AppScope
    ANRLoggerApplication provideApplication(){
        return mANRLoggerApplication;
    }
}
