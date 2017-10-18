package org.seeds.anrgamelogger.dagger;

import android.content.ContentResolver;

import dagger.Component;

/**
 * Created by Tomas Seymour-Turner on 14/10/2017.
 */


@Component(modules={ContentResolverModule.class})
@AppScope
public interface DataBaseComponent {

        void inject(ContentResolver contentResolver);

      //  ContentResolver getContentRes();

        // void inject(MyFragment fragment);
        // void inject(MyService service);

}
