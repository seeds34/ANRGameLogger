package org.seeds.anrgamelogger.activities.gamehandler.dagger;

import android.app.Activity;
import dagger.Module;
import dagger.Provides;
import org.seeds.anrgamelogger.activities.gamehandler.presenter.GameHandlerPresenter;
import org.seeds.anrgamelogger.activities.gamehandler.model.GameHandlerModel;
import org.seeds.anrgamelogger.activities.gamehandler.views.GameHandlerHandlerHandlerCorpView;
import org.seeds.anrgamelogger.activities.gamehandler.views.GameHandlerHandlerOverviewView;
import org.seeds.anrgamelogger.activities.gamehandler.views.GameHandlerHandlerRunnerView;
import org.seeds.anrgamelogger.activities.gamehandler.views.GameHandlerView;
import org.seeds.anrgamelogger.application.database.DatabaseModel;

/**
 * Created by Tomas Seymour-Turner on 14/10/2017.
 */
@Module
public class GameHandlerModule {

    private final Activity activity;

    public GameHandlerModule(Activity activityIn){
        this.activity = activityIn;
    }

    @Provides
    @GameHandlerScope
    public GameHandlerView GetGameHandlerView(){
        return new GameHandlerView(activity);
    }

    @Provides
    @GameHandlerScope
    public GameHandlerHandlerOverviewView GetGameHandlerOverviewView(){
        return new GameHandlerHandlerOverviewView(activity);
    }

    @Provides
    @GameHandlerScope
    public GameHandlerHandlerRunnerView GetGameHandlerRunnerView(){
        return new GameHandlerHandlerRunnerView(activity);
    }

    @Provides
    @GameHandlerScope
    public GameHandlerHandlerHandlerCorpView GetGameHandlerCorpView(){
        return new GameHandlerHandlerHandlerCorpView(activity);
    }

    @Provides
    @GameHandlerScope
    public GameHandlerModel GetGameHandlerModel(DatabaseModel databaseModel){
        return new GameHandlerModel(activity, databaseModel);
    }

    @Provides
    @GameHandlerScope
    public GameHandlerPresenter GetGameHandlerPresenter(GameHandlerView view, GameHandlerModel model, GameHandlerHandlerRunnerView runnerSubView, GameHandlerHandlerHandlerCorpView corpSubView, GameHandlerHandlerOverviewView overviewSubView){
        //return new GameHandlerPresenter(view, model);
        return new GameHandlerPresenter(view, model, runnerSubView, corpSubView, overviewSubView);
    }


}
