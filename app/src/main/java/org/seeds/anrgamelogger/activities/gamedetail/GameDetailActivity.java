package org.seeds.anrgamelogger.activities.gamedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;

import javax.inject.Inject;

import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.application.ANRLoggerApplication;
import org.seeds.anrgamelogger.activities.gamedetail.dagger.DaggerGameDetailComponent;
import org.seeds.anrgamelogger.activities.gamedetail.dagger.GameDetailModule;

public class GameDetailActivity extends AppCompatActivity {

    private static final String LOG_TAG = GameDetailActivity.class.getSimpleName();


    public static final String GAME_TRNASFER = "GAME_TRNASFER";

    @Inject
    GameDetailPresenter presenter;

    @Inject
    GameDetailView view;

    public static void start(Context contextIn, String gameID /*LoggedGameFlat loggedGame*/) {

        Intent intent = new Intent(contextIn, GameDetailActivity.class);
        //intent.putExtra(GAME_TRNASFER, loggedGame);
        Log.d(LOG_TAG,".start(Context,String) : game ID is: " + gameID);
        intent.putExtra(GAME_TRNASFER, gameID);
        contextIn.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerGameDetailComponent.builder()
                .applicationComponent(ANRLoggerApplication.get(this).getApplicationComponent())
                .gameDetailModule(new GameDetailModule(this))
                .build()
                .inject(this);

        setContentView(view);
        presenter.onCreate();

    }

    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
       }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game_detail_view, menu);
        return true;

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.refresh();
    }
}