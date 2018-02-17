package org.seeds.anrgamelogger.gamelist.dagger;

import dagger.Component;
import org.seeds.anrgamelogger.ANRLoggerMainActivity;
import org.seeds.anrgamelogger.application.dagger.ApplicationComponent;

/**
 * Created by Tomas Seymour-Turner on 14/10/2017.
 */


@Component(modules={GameListModule.class}, dependencies = ApplicationComponent.class)
@GameListScope
public interface GameListComponent {

        void inject(ANRLoggerMainActivity anrLoggerMainActivity);

}
