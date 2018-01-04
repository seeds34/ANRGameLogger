package org.seeds.anrgamelogger.addgame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import javax.inject.Inject;
import org.seeds.anrgamelogger.addgame.dagger.AddGameModule;
import org.seeds.anrgamelogger.addgame.dagger.DaggerAddGameComponent;

public class AddGameActivity extends AppCompatActivity {

    private static final String LOG_TAG = AddGameActivity.class.getSimpleName();
    public static final String SIDE = "SIDE";

    @Inject
    AddGamePresenter presenter;

    @Inject
    AddGameView view;

    public static void start(Context contextIn, int side) {
        Intent intent = new Intent(contextIn, AddGameActivity.class);
        intent.putExtra(SIDE, side);
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
