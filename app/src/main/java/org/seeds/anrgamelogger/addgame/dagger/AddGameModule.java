package org.seeds.anrgamelogger.addgame.dagger;

import android.app.Activity;

import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;

import dagger.Module;
import dagger.Provides;
import org.seeds.anrgamelogger.addgame.AddGameModel;
import org.seeds.anrgamelogger.addgame.AddGamePresenter;
import org.seeds.anrgamelogger.addgame.AddGameView;

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
    public AddGameModel GetAddGameModel(StorIOContentResolver storIOContentResolver){
        return new AddGameModel(activity, storIOContentResolver);
    }

    @Provides
    @AddGameScope
    public AddGamePresenter GetAddGamePresenter(AddGameView view, AddGameModel model){
        return new AddGamePresenter(view, model);
    }


}
