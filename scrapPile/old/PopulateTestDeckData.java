package org.seeds.anrgamelogger.database.datacreater;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.database.DecksContract;
import org.seeds.anrgamelogger.database.IdentitiesContract;

/**
 * Created by Tomas Seymour-Turner on 20/05/2017.
 */

public class PopulateTestDeckData {

    private static final String LOG_TAG = PopulateTestDeckData.class.getSimpleName();
    private final String ELEMENT_ID = "ID";
    private final String ELEMENT_ARCH = "Archtype or Deckname";
    private final String ELEMENT_PLAYER = "Player";

    private final String PARENT_NAME = "LocalLoggedGame Tracker";
    private ContentResolver contentResolver;
    private String data;


    public void extractDecks(Context contextIn){
        data = GetRawData.ExtractData(contextIn, R.raw.allgamesplayedtest);
        contentResolver = contextIn.getContentResolver();
        extractSide('1');
        extractSide('2');
    }

    private void extractSide (char side){

        try{
            //Get top level object, then into "LocalLoggedGame Tracker" Object
            JSONObject jsonData = new JSONObject(data).getJSONObject(PARENT_NAME);
            JSONArray nameArray = jsonData.names();
            ContentValues values;

            //Cycle through each object

            Log.e(LOG_TAG, "There are " +nameArray.length()+ " JSON Objects");

            for(int i = 0 ; i < nameArray.length() ; i++){
                String id = jsonData.getJSONObject(nameArray.getString(i)).getString(ELEMENT_PLAYER + " " + side + " " + ELEMENT_ID);
                String arch = jsonData.getJSONObject(nameArray.getString(i)).getString(ELEMENT_PLAYER + " " + side + " " + ELEMENT_ARCH);

                id = DatabaseUtils.sqlEscapeString(id);

                String where = IdentitiesContract.IdentitiesColumns.IDENTITY_NAME + " = " + id ;

                Cursor matchingIdentities = contentResolver.query(IdentitiesContract.URI_TABLE,null, where ,null,null);
                int internalKey = 0;
                //TODO: Don't like this construct. This will break if there are ever mutiple Identites with the same name. For now also putting UNIQUE Constraint on Identites Name
                if(matchingIdentities == null ||  matchingIdentities.getCount() != 1) {
                    //TODO: Should throw an exception, in the grand scheme this would be an excption with Database and inport
                    Log.e(LOG_TAG, "Too many or too few Identities Results");
                }else{
                    if(matchingIdentities.moveToFirst()){
                        internalKey = matchingIdentities.getInt(matchingIdentities.getColumnIndex(BaseColumns._ID));
                    }}

                where = DecksContract.DecksColumns.DECK_NAME + " = " + DatabaseUtils.sqlEscapeString(arch);
                Cursor deckExcists = contentResolver.query(DecksContract.URI_TABLE,null, where ,null,null);

                if (deckExcists.getCount() < 1){
                    if(internalKey != 0) {
                        values = new ContentValues();
                        values.put(DecksContract.DecksColumns.DECK_NAME, arch);
                        values.put(DecksContract.DecksColumns.DECK_IDENTITY, internalKey);

                        //TODO: For now make deck name unique. But might want Deck+ID+Version to be unique
                        Uri insertedRow = contentResolver.insert(DecksContract.URI_TABLE, values);
                        Log.v(LOG_TAG, "Inserted row. Uri: " +insertedRow + " Data: Deck Name- " + arch + " Identity Internal Key: " + internalKey);

                    }else{
                        //TODO: Throw import failer exception: Can't find ID
                        Log.e(LOG_TAG, "ERROR: No matching ID found in Database for: " + id);
                    }
                }else{
                    //deck already excists
                }



            }

        }catch (JSONException e){
            e.printStackTrace();
            Log.e(LOG_TAG,"Error Proccessing JSON" + e.toString());
        }

    }

}
