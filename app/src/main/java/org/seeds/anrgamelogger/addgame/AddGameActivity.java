package org.seeds.anrgamelogger.addgame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import javax.inject.Inject;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.addgame.dagger.AddGameModule;
import org.seeds.anrgamelogger.addgame.dagger.DaggerAddGameComponent;
import org.seeds.anrgamelogger.addgame.views.AddGameView;
import org.seeds.anrgamelogger.application.ANRLoggerApplication;

public class AddGameActivity extends AppCompatActivity {

    private static final String LOG_TAG = AddGameActivity.class.getSimpleName();
//    public static final String SIDE = "SIDE";
    public static final String GAMENO = "GAMENO";

    @Inject
    AddGamePresenter presenter;

    @Inject
    AddGameView view;



//    public static void start(Context contextIn, String side) {
//        Intent intent = new Intent(contextIn, AddGameActivity.class);
//        intent.putExtra(SIDE, side); //NOTE: Note sure if this is still needed
//        contextIn.startActivity(intent);
//    }

    public static void start(Context contextIn, String gameNo) {
        Intent intent = new Intent(contextIn, AddGameActivity.class);
        intent.putExtra(GAMENO, gameNo);
        contextIn.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        DaggerAddGameComponent.builder()
                .applicationComponent(ANRLoggerApplication.get(this).getApplicationComponent())
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }
}
