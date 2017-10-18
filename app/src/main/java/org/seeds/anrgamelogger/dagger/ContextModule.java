package org.seeds.anrgamelogger.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tomas Seymour-Turner on 18/10/2017.
 */

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @AppScope
    public Context context() {
        return context;
    }
}