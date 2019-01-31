package org.seeds.anrgamelogger.addgame.dagger;

import android.app.Activity;
import android.util.Log;

import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;

import dagger.Module;
import dagger.Provides;
import org.seeds.anrgamelogger.addgame.model.AddGameModel;
import org.seeds.anrgamelogger.addgame.AddGamePresenter;
import org.seeds.anrgamelogger.addgame.views.AddGameCorpView;
import org.seeds.anrgamelogger.addgame.views.AddGameOverviewView;
import org.seeds.anrgamelogger.addgame.views.AddGamePlayerView;
import org.seeds.anrgamelogger.addgame.views.AddGameRunnerView;
import org.seeds.anrgamelogger.addgame.views.AddGameView;
import org.seeds.anrgamelogger.database.DatabaseModel;

/**
 * Created by Tomas Seymour-Turner on 14/10/2017.
 */
@Module
public class AddGameModule {

    private final Activity activity;

    public AddGameModule(Activity activityIn){
        this.activity = activityIn;
    }

    @Provides
    @AddGameScope
    public AddGameView GetAddGameView(){
        return new AddGameView(activity);
    }

    @Provides
    @AddGameScope
    public AddGameOverviewView GetAddGameOverviewView(){
        return new AddGameOverviewView(activity);
    }

    @Provides
    @AddGameScope
    public AddGameRunnerView GetAddGameRunnerView(){
        return new AddGameRunnerView(activity);
    }

    @Provides
    @AddGameScope
    public AddGameCorpView GetAddGameCorpView(){
        return new AddGameCorpView(activity);
    }

    @Provides
    @AddGameScope
    public AddGameModel GetAddGameModel(DatabaseModel databaseModel, StorIOContentResolver storIOContentResolver){
        return new AddGameModel(activity, storIOContentResolver, databaseModel);
    }

    @Provides
    @AddGameScope
    public AddGamePresenter GetAddGamePresenter(AddGameView view, AddGameModel model, AddGameRunnerView runnerSubView, AddGameCorpView corpSubView, AddGameOverviewView overviewSubView){
        //return new AddGamePresenter(view, model);
        return new AddGamePresenter(view, model, runnerSubView, corpSubView, overviewSubView);
    }


}
