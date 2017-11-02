package org.seeds.anrgamelogger.dagger;

import android.app.Activity;

import org.seeds.anrgamelogger.gamelistview.GameListPresenter;
import org.seeds.anrgamelogger.gamelistview.GameListView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tomas Seymour-Turner on 14/10/2017.
 */
@Module
public class ApplicationModule{

    private final Activity activity;

    public ApplicationModule(Activity activityIn){
        this.activity = activityIn;
    }

    @Provides
    @AppScope
    public GameListView GetGameListView(){
        return new GameListView(activity);
    }


    @Provides
    @AppScope
    public GameListPresenter GetGameListPresenter(GameListView view){
        return new GameListPresenter(view);
    }

}
