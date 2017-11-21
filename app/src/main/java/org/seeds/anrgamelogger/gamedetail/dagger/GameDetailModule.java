package org.seeds.anrgamelogger.gamedetail.dagger;

import android.app.Activity;

import org.seeds.anrgamelogger.gamedetail.GameDetailModel;
import org.seeds.anrgamelogger.gamedetail.GameDetailPresenter;
import org.seeds.anrgamelogger.gamedetail.GameDetailView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tomas Seymour-Turner on 14/10/2017.
 */
@Module
public class GameDetailModule {

    private final Activity activity;

    public GameDetailModule(Activity activityIn){
        this.activity = activityIn;
    }

    @Provides
    @GameDetailScope
    public GameDetailView GetDetailView(){
        return new GameDetailView(activity);
    }

    @Provides
    @GameDetailScope
    public GameDetailModel GetDetailGameModel(){
        return new GameDetailModel(activity);
    }

    @Provides
    @GameDetailScope
    public GameDetailPresenter GetDetailPresenter(GameDetailView view, GameDetailModel model){
        return new GameDetailPresenter(view, model);
    }


}
