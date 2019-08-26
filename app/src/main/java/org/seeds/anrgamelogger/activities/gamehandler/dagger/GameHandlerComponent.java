package org.seeds.anrgamelogger.activities.gamehandler.dagger;

import org.seeds.anrgamelogger.activities.gamehandler.GameHandlerActivity;
import org.seeds.anrgamelogger.application.dagger.ApplicationComponent;

import dagger.Component;

/**
 * Created by Tomas Seymour-Turner on 14/10/2017.
 */


@Component(modules={GameHandlerModule.class}, dependencies = ApplicationComponent.class)
@GameHandlerScope
public interface GameHandlerComponent {

        void inject(GameHandlerActivity activity);
}
