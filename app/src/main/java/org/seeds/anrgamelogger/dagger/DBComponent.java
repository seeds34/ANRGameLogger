package org.seeds.anrgamelogger.dagger;

import org.seeds.anrgamelogger.gamelistview.GameListViewActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Tomas Seymour-Turner on 14/10/2017.
 */

@Singleton
@Component(modules={AppModule.class, CRModule.class})
public interface DBComponent {

        void inject(GameListViewActivity activity);
        // void inject(MyFragment fragment);
        // void inject(MyService service);

}
