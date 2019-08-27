package org.seeds.anrgamelogger.activities.gamedetail.dagger;

import android.app.Activity;
import dagger.Module;
import dagger.Provides;
import org.seeds.anrgamelogger.application.database.DatabaseModel;
import org.seeds.anrgamelogger.activities.gamedetail.GameDetailModel;
import org.seeds.anrgamelogger.activities.gamedetail.GameDetailPresenter;
import org.seeds.anrgamelogger.activities.gamedetail.GameDetailView;

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
    public GameDetailModel GetDetailGameModel(DatabaseModel databaseModel){
        return new GameDetailModel(activity, databaseModel);
    }

    @Provides
    @GameDetailScope
    public GameDetailPresenter GetDetailPresenter(GameDetailView view, GameDetailModel model){
        return new GameDetailPresenter(view, model);
    }


}
