package org.seeds.anrgamelogger.addgame;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.seeds.anrgamelogger.addgame.dagger.AddGameModule;
import org.seeds.anrgamelogger.addgame.dagger.DaggerAddGameComponent;
import org.seeds.anrgamelogger.gamedetail.GameDetailActivity;
import org.seeds.anrgamelogger.model.LocalLoggedGame;

import javax.inject.Inject;

public class AddGameActivity extends AppCompatActivity {

    private static final String LOG_TAG = AddGameActivity.class.getSimpleName();
    public static final String GAME_TRNASFER = "GAME_TRNASFER";

    @Inject
    AddGamePresenter presenter;

    @Inject
    AddGameView view;

    public static void start(Context contextIn) {
        Intent intent = new Intent(contextIn, AddGameActivity.class);
        contextIn.startActivity(intent);
    }

    public static void start(Context contextIn, LocalLoggedGame loggedGame) {

        Intent intent = new Intent(contextIn, GameDetailActivity.class);
        intent.putExtra(GAME_TRNASFER, loggedGame);
        contextIn.startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerAddGameComponent.builder()
                .addGameModule(new AddGameModule(this))
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
