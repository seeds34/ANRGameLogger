package org.seeds.anrgamelogger.activities.gamedetail.dagger;

import dagger.Component;
import org.seeds.anrgamelogger.application.dagger.ApplicationComponent;
import org.seeds.anrgamelogger.activities.gamedetail.GameDetailActivity;

/**
 * Created by Tomas Seymour-Turner on 14/10/2017.
 */


@Component(modules={GameDetailModule.class},dependencies = ApplicationComponent.class)
@GameDetailScope
public interface GameDetailComponent {

        void inject(GameDetailActivity activity);
}
