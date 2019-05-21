package org.seeds.anrgamelogger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import org.seeds.anrgamelogger.application.ANRLoggerApplication;
import org.seeds.anrgamelogger.gamelist.GameListPresenter;
import org.seeds.anrgamelogger.gamelist.GameListView;
import org.seeds.anrgamelogger.gamelist.dagger.DaggerGameListComponent;
import org.seeds.anrgamelogger.gamelist.dagger.GameListModule;

import javax.inject.Inject;

/**
 * Created by Tomas Seymour-Turner on 17/10/2017.
 */

public class GameListActivity extends AppCompatActivity{

    @Inject
    GameListView view;

    @Inject
    GameListPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);

        DaggerGameListComponent.builder()
                .applicationComponent(ANRLoggerApplication.get(this).getApplicationComponent())
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

  @Override
  protected void onResume() {
    super.onResume();
    presenter.refresh();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu_game_list, menu);
       return true;
    }

}
