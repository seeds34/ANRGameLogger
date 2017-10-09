package org.seeds.anrgamelogger.database.datacreater;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.database.PlayersContract;

import java.util.HashSet;

/**
 * Created by Tomas Seymour-Turner on 19/05/2017.
 */

public class PopulateTestPlayerData {


    private static final String LOG_TAG = PopulateTestLocationData.class.getSimpleName();
    private final String PLAYERONE = "Player 1";
    private final String PLAYERTWO = "Player 2";
    private final String PARENT_NAME = "LocalLoggedGame Tracker";
    private ContentResolver contentResolver;


    public void extractPlayers(Context contextIn){

        String data = GetRawData.ExtractData(contextIn, R.raw.allgamesplayedtest);
        HashSet<String> players = new HashSet<String>();

        contentResolver = contextIn.getContentResolver();

        Log.v(LOG_TAG,"JSON Data: " + data);

        try{
            //Get top level object, then into "LocalLoggedGame Tracker" Object
            JSONObject jsonData = new JSONObject(data).getJSONObject(PARENT_NAME);
            JSONArray nameArray = jsonData.names();

            //Cycle through each object
            for(int i = 0 ; i < nameArray.length() ; i++){
                players.add(jsonData.getJSONObject(nameArray.getString(i)).getString(PLAYERONE));
                players.add(jsonData.getJSONObject(nameArray.getString(i)).getString(PLAYERTWO));
            }


        }catch (JSONException e){
            e.printStackTrace();
            Log.e(LOG_TAG,"Error Proccessing JSON" + e.toString());
        }

        Log.v(LOG_TAG,"Players: " + players.toString());

        ContentValues values;
        for (String player : players) {
            values = new ContentValues();
            values.put(PlayersContract.PlayersColumns.PLAYER_NAME, player.toLowerCase());
            contentResolver.insert(PlayersContract.URI_TABLE, values);

        }

    }

}
