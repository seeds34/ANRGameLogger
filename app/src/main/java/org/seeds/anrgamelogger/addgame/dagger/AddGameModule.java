package org.seeds.anrgamelogger.addgame.dagger;

import android.app.Activity;

import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;

import dagger.Module;
import dagger.Provides;
import org.seeds.anrgamelogger.addgame.model.AddGameModel;
import org.seeds.anrgamelogger.addgame.AddGamePresenter;
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
    public AddGameModel GetAddGameModel(DatabaseModel databaseModel, StorIOContentResolver storIOContentResolver){
        return new AddGameModel(activity, storIOContentResolver, databaseModel);
    }

    @Provides
    @AddGameScope
    public AddGamePresenter GetAddGamePresenter(AddGameView view, AddGameModel model){
        return new AddGamePresenter(view, model);
    }


}
