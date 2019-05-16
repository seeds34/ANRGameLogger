package org.seeds.anrgamelogger.application;

import android.app.Activity;
import android.app.Application;
import org.seeds.anrgamelogger.application.dagger.ApplicationComponent;
import org.seeds.anrgamelogger.application.dagger.ApplicationModule;
import org.seeds.anrgamelogger.application.dagger.DaggerApplicationComponent;

/**
 * Created by user on 17/02/2018.
 */

public class ANRLoggerApplication extends Application {

    private ApplicationComponent applicationComponent;

    //TODO: Need better single place to hold Side strings
    public static final String CORP_SIDE_IDENTIFIER = "corp";//getResources().getString(R.string.corp);
    public static final String RUNNER_SIDE_IDENTIFIER = "runner";//getResources().getString(R.string.runner);

    public static ANRLoggerApplication get(Activity activity){
         Application a = activity.getApplication();
        return (ANRLoggerApplication) a;
        //activity.getApplication();
    }

    @Override
    public void onCreate(){
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        //getResources().getString()

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
