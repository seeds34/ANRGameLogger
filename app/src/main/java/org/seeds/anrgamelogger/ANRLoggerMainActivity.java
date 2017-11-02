package org.seeds.anrgamelogger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.seeds.anrgamelogger.dagger.ApplicationComponent;
import org.seeds.anrgamelogger.dagger.ApplicationModule;
import org.seeds.anrgamelogger.dagger.DaggerApplicationComponent;
import org.seeds.anrgamelogger.gamelistview.GameListView;

import javax.inject.Inject;

/**
 * Created by Tomas Seymour-Turner on 17/10/2017.
 */

public class ANRLoggerMainActivity extends AppCompatActivity{

    @Inject
    GameListView view;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);

        DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build()
                .inject(this);

        setContentView(view);
    }
}
