package org.seeds.anrgamelogger.database.contracts;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Tomas Seymour-Turner on 03/05/2017.
 */

public class LocationsContract {

    public interface LocationsColumns{
        String LOCATION_NAME= "locationname";
    }

    public static final String CONTENT_AUTHORITY = "org.seeds.anrgl.procider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    //Table identifyers
    public static final String PATH_LOCATIONS = "locations";

    public  static final Uri URI_TABLE = Uri.parse(BASE_CONTENT_URI.toString()+"/"+PATH_LOCATIONS);

    //Array of table identifiers
    public static final String[] TOP_LEVEL_PATHS = {
            PATH_LOCATIONS
    };

    public static class Location implements LocationsColumns, BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendEncodedPath(PATH_LOCATIONS).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd."+CONTENT_AUTHORITY+".locations";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."+CONTENT_AUTHORITY+".locations";

        public static Uri buildLocationUri(String locationId){
            return CONTENT_URI.buildUpon().appendEncodedPath(locationId).build();
        }

        public static String getLocationId(Uri uri){
            return uri.getPathSegments().get(1);
        }
    }
}
