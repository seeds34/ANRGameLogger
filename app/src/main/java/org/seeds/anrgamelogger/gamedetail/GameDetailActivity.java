package org.seeds.anrgamelogger.gamedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.seeds.anrgamelogger.gamedetail.dagger.DaggerGameDetailComponent;
import org.seeds.anrgamelogger.gamedetail.dagger.GameDetailModule;
import org.seeds.anrgamelogger.model.LocalLoggedGame;

import javax.inject.Inject;

public class GameDetailActivity extends AppCompatActivity {

    private static final String LOG_TAG = GameDetailActivity.class.getSimpleName();


    public static final String GAME_TRNASFER = "GAME_TRNASFER";

    @Inject
    GameDetailPresenter presenter;

    @Inject
    GameDetailView view;

    public static void start(Context contextIn, LocalLoggedGame loggedGame) {

        Intent intent = new Intent(contextIn, GameDetailActivity.class);
        intent.putExtra(GAME_TRNASFER, loggedGame);
        contextIn.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerGameDetailComponent.builder()
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
}