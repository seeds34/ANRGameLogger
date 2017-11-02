package org.seeds.anrgamelogger.dagger;

import org.seeds.anrgamelogger.ANRLoggerMainActivity;

import dagger.Component;

/**
 * Created by Tomas Seymour-Turner on 14/10/2017.
 */


@Component(modules={ApplicationModule.class})
@AppScope
public interface ApplicationComponent {

        void inject(ANRLoggerMainActivity anrLoggerMainActivity);

}
