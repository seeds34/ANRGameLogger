package org.seeds.anrgamelogger;

import android.app.Application;

import org.seeds.anrgamelogger.dagger.DataBaseComponent;

/**
 * Created by Tomas Seymour-Turner on 17/10/2017.
 */

public class ANRLoggerApplication extends Application{

    private DataBaseComponent mDataBaseComponent;

    @Override
    public void onCreate(){
       super.onCreate();

     //   mDataBaseComponent = Dagger.
    }
}
