package org.seeds.anrgamelogger.activities.gamelist.dagger;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

import org.seeds.anrgamelogger.application.database.DatabaseModel;
import org.seeds.anrgamelogger.application.network.NetworkModel;
import org.seeds.anrgamelogger.activities.gamelist.GameListModel;
import org.seeds.anrgamelogger.activities.gamelist.GameListPresenter;
import org.seeds.anrgamelogger.activities.gamelist.GameListView;

/**
 * Created by Tomas Seymour-Turner on 14/10/2017.
 */
@Module
public class GameListModule {

    private final Activity activity;

    public GameListModule(Activity activityIn){
        this.activity = activityIn;
    }

    @Provides
    @GameListScope
    public GameListView GetGameListView(){
        return new GameListView(activity);
    }

    @Provides
    @GameListScope
    public GameListModel GetGameGameListModel(DatabaseModel databaseModel, NetworkModel networkModelIn){
        return new GameListModel(activity, databaseModel, networkModelIn);
    }

    @Provides
    @GameListScope
    public GameListPresenter GetGameListPresenter(GameListView view, GameListModel model){
        return new GameListPresenter(view, model);
    }


}
