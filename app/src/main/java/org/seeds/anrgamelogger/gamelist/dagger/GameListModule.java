package org.seeds.anrgamelogger.gamelist.dagger;

import android.app.Activity;
import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

import org.seeds.anrgamelogger.application.DatabaseModel;
import org.seeds.anrgamelogger.application.NetworkModel;
import org.seeds.anrgamelogger.gamelist.GameListModel;
import org.seeds.anrgamelogger.gamelist.GameListPresenter;
import org.seeds.anrgamelogger.gamelist.GameListView;
import retrofit2.Retrofit;

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
