package org.seeds.anrgamelogger.gamedetail.dagger;

import org.seeds.anrgamelogger.gamedetail.GameDetailActivity;

import dagger.Component;

/**
 * Created by Tomas Seymour-Turner on 14/10/2017.
 */


@Component(modules={GameDetailModule.class})
@GameDetailScope
public interface GameDetailComponent {

        void inject(GameDetailActivity gameDetailActivity);

}
