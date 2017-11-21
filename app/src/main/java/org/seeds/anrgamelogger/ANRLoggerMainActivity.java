package org.seeds.anrgamelogger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.seeds.anrgamelogger.gamelist.GameListPresenter;
import org.seeds.anrgamelogger.gamelist.GameListView;
import org.seeds.anrgamelogger.gamelist.dagger.DaggerGameListComponent;
import org.seeds.anrgamelogger.gamelist.dagger.GameListModule;

import javax.inject.Inject;

/**
 * Created by Tomas Seymour-Turner on 17/10/2017.
 */

public class ANRLoggerMainActivity extends AppCompatActivity{

    @Inject
    GameListView view;

    @Inject
    GameListPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);

        DaggerGameListComponent.builder()
                .gameListModule(new GameListModule(this))
                .build()
                .inject(this);

        setContentView(view);
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
