package org.seeds.anrgamelogger.database.datacreater;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.database.LocationsContract;

import java.util.HashSet;

/**
 * Created by Tomas Seymour-Turner on 04/05/2017.
 */

public class PopulateTestLocationData {

    private static final String LOG_TAG = PopulateTestLocationData.class.getSimpleName();
    private final String ELEMENT_NAME = "Location";
    private final String PARENT_NAME = "LocalLoggedGame Tracker";
    private ContentResolver contentResolver;


    public void extractLocations(Context contextIn){

        String data = GetRawData.ExtractData(contextIn, R.raw.allgamesplayedtest);
        HashSet<String> locations = new HashSet<String>();
        locations.add("Test");

        contentResolver = contextIn.getContentResolver();

        Log.v(LOG_TAG,"JSON Data: " + data);

        try{
            //Get top level object, then into "LocalLoggedGame Tracker" Object
            JSONObject jsonData = new JSONObject(data).getJSONObject(PARENT_NAME);
            JSONArray nameArray = jsonData.names();

            //Cycle through each object
            for(int i = 0 ; i < nameArray.length() ; i++){
                String temp = jsonData.getJSONObject(nameArray.getString(i)).getString(ELEMENT_NAME);
                locations.add(temp);
            }


        }catch (JSONException e){
            e.printStackTrace();
            Log.e(LOG_TAG,"Error Proccessing JSON" + e.toString());
        }

        Log.v(LOG_TAG,"Locations: " + locations.toString());

        ContentValues values;
        for (String location : locations) {
            values = new ContentValues();
            values.put(LocationsContract.LocationsColumns.LOCATION_NAME, location.toLowerCase());
            contentResolver.insert(LocationsContract.URI_TABLE, values);

        }

    }

}
