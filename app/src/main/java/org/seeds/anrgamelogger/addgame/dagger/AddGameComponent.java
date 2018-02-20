package org.seeds.anrgamelogger.addgame.dagger;

import org.seeds.anrgamelogger.addgame.AddGameActivity;
import org.seeds.anrgamelogger.application.dagger.ApplicationComponent;
import org.seeds.anrgamelogger.gamedetail.GameDetailActivity;

import dagger.Component;

/**
 * Created by Tomas Seymour-Turner on 14/10/2017.
 */


@Component(modules={AddGameModule.class}, dependencies = ApplicationComponent.class)
@AddGameScope
public interface AddGameComponent {

        void inject(AddGameActivity activity);
}
