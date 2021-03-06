package org.seeds.anrgamelogger.application.dagger;

import dagger.Component;
import org.seeds.anrgamelogger.application.database.DatabaseModel;
import org.seeds.anrgamelogger.application.network.NetworkModel;

/**
 * Created by Tomas Seymour-Turner on 16/02/2018.
 */
@Component(modules={ApplicationModule.class})
@ApplicationScope
public interface ApplicationComponent {

  DatabaseModel databaseModel();

  NetworkModel networkModel();
}
