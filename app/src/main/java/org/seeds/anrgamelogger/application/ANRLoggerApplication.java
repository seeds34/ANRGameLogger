package org.seeds.anrgamelogger.application;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import org.seeds.anrgamelogger.application.dagger.ApplicationComponent;
import org.seeds.anrgamelogger.application.dagger.ApplicationModule;
import org.seeds.anrgamelogger.application.dagger.DaggerApplicationComponent;

import javax.inject.Inject;

/**
 * Created by user on 17/02/2018.
 */

public class ANRLoggerApplication extends Application {

    private ApplicationComponent applicationComponent;

    public static final String CORP_SIDE_IDENTIFIER = "corp";
    public static final String RUNNER_SIDE_IDENTIFIER = "runner";

//    @Inject
//    DatabaseModel databaseModel;
//
//    @Inject
//    NetworkModel nwModel;

    public static ANRLoggerApplication get(Activity activity){
         Application a = activity.getApplication();
        return (ANRLoggerApplication) a; //activity.getApplication();
    }

    @Override
    public void onCreate(){
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();


//        if(databaseModel.isIdentitiesTableEmpty()){
//            Log.i(LOG_TAG,"Starting to load first time data");
//            idd.populateIdentitiesTable();
//            //idd.setUpTestPlayers();
//        }
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }
}
