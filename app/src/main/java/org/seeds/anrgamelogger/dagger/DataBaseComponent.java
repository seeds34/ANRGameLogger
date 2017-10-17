package org.seeds.anrgamelogger.dagger;

import org.seeds.anrgamelogger.database.GameLoggerDatabase;
import org.seeds.anrgamelogger.gamelistview.GameListViewActivity;
import org.seeds.anrgamelogger.model.LoggedGameList;

import dagger.Component;

/**
 * Created by Tomas Seymour-Turner on 14/10/2017.
 */


@Component(modules={AnrLoggerApplicationModule.class, ContentResolverModule.class})
@ANRLoggerActivityScope
public interface DataBaseComponent {

        void inject(GameListViewActivity activity);
        void inject (LoggedGameList loggedGameList);
        void inject (GameLoggerDatabase gameLoggerDatabase);
        // void inject(MyFragment fragment);
        // void inject(MyService service);

}
