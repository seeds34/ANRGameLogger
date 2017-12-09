package org.seeds.anrgamelogger.addgame.dagger;

import android.app.Activity;

import org.seeds.anrgamelogger.addgame.AddGameModel;
import org.seeds.anrgamelogger.addgame.AddGamePresenter;
import org.seeds.anrgamelogger.addgame.AddGameView;
import org.seeds.anrgamelogger.gamedetail.GameDetailModel;
import org.seeds.anrgamelogger.gamedetail.GameDetailPresenter;
import org.seeds.anrgamelogger.gamedetail.GameDetailView;

import dagger.Module;
import dagger.Provides;

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
    public AddGameModel GetAddGameModel(){
        return new AddGameModel(activity);
    }

    @Provides
    @AddGameScope
    public AddGamePresenter GetAddGamePresenter(AddGameView view, AddGameModel model){
        return new AddGamePresenter(view, model);
    }


}