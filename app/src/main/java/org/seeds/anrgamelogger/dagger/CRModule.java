package org.seeds.anrgamelogger.dagger;

import android.app.Application;
import android.content.ContentResolver;

import javax.inject.Singleton;

import dagger.Provides;

/**
 * Created by Tomas Seymour-Turner on 14/10/2017.
 */

public class CRModule {
    ContentResolver mContentResolver;

    public CRModule(){}

    @Provides
    @Singleton
    ContentResolver provideCR(Application application){
        return mContentResolver;
    }
}
