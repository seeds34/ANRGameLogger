package org.seeds.anrgamelogger.activities.gamehandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import javax.inject.Inject;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.activities.gamehandler.dagger.GameHandlerModule;
import org.seeds.anrgamelogger.activities.gamehandler.dagger.DaggerGameHandlerComponent;
import org.seeds.anrgamelogger.activities.gamehandler.presenter.GameHandlerPresenter;
import org.seeds.anrgamelogger.activities.gamehandler.views.GameHandlerView;
import org.seeds.anrgamelogger.application.ANRLoggerApplication;
import org.seeds.anrgamelogger.application.PredefinedGame;

public class GameHandlerActivity extends AppCompatActivity {

    private static final String LOG_TAG = GameHandlerActivity.class.getSimpleName();
//    public static final String SIDE = "SIDE";
    public static final String GAMENO = "GAMENO";
    public static final String TYPE = "TYPE";

    @Inject
    GameHandlerPresenter presenter;

    @Inject
    GameHandlerView view;

    public static void start(Context contextIn, String gameNo, PredefinedGame predefinedGame) {
        Intent intent = new Intent(contextIn, GameHandlerActivity.class);
        intent.putExtra(GAMENO, gameNo);
        intent.putExtra(TYPE,predefinedGame);
        contextIn.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerGameHandlerComponent.builder()
                .applicationComponent(ANRLoggerApplication.get(this).getApplicationComponent())
                .gameHandlerModule(new GameHandlerModule(this))
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
        getMenuInflater().inflate(R.menu.menu_add_game, menu);
        return true;

    }
}
