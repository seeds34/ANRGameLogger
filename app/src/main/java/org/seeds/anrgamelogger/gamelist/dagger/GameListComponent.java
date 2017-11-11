package org.seeds.anrgamelogger.gamelist.dagger;

import org.seeds.anrgamelogger.ANRLoggerMainActivity;

import dagger.Component;

/**
 * Created by Tomas Seymour-Turner on 14/10/2017.
 */


@Component(modules={GameListModule.class})
@GameListScope
public interface GameListComponent {

        void inject(ANRLoggerMainActivity anrLoggerMainActivity);

}
