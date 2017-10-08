package org.seeds.anrgamelogger.database.contracts;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Tomas Seymour-Turner on 02/05/2017.
 */

public class IdentitiesContract {

    public interface IdentitiesColumns {
        String IDENTITY_NAME = "name";
        String IDENTITY_FACATION = "faction";
        String IDENTITY_SIDE = "side";
        String ROTATED_FLAG = "rotated";
        String NRDB_CODE = "nrdbcode";
        String IMAGE_BIT_ARRAY = "imagedata";
    }

    public static final String CONTENT_AUTHORITY = "org.seeds.anrgl.procider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    //Table identifyers
    public static final String PATH_IDENTITIES = "identities";

    public  static final Uri URI_TABLE = Uri.parse(BASE_CONTENT_URI.toString()+"/"+ PATH_IDENTITIES);

    public static final String[] TOP_LEVEL_PATHS = {
            PATH_IDENTITIES
    };

    public static class Identitity implements IdentitiesColumns, BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendEncodedPath(PATH_IDENTITIES).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd."+CONTENT_AUTHORITY+".identities";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."+CONTENT_AUTHORITY+".identities";

        public static Uri buildIdentutyUri(String identutyId){
            return CONTENT_URI.buildUpon().appendEncodedPath(identutyId).build();
        }

        public static String getIdentutynId(Uri uri){
            return uri.getPathSegments().get(1);
        }
    }
}
