package org.seeds.anrgamelogger.application.database.datacreater;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.provider.BaseColumns;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.application.database.DecksContract;
import org.seeds.anrgamelogger.application.database.LocationsContract;
import org.seeds.anrgamelogger.application.database.LoggedGamesContract;
import org.seeds.anrgamelogger.application.database.PlayersContract;

/**
 * Created by Tomas Seymour-Turner on 22/05/2017.
 */

public class PopulateLoggedGamesData {

    private static final String LOG_TAG = PopulateLoggedGamesData.class.getSimpleName();

    private final String PARENT_NAME = "LocalLoggedGame Tracker";
    private int gameNumber;

    private ContentResolver contentResolver;
    private String data;

    public PopulateLoggedGamesData(Context contextIn){
        data = GetRawData.ExtractData(contextIn, R.raw.allgamesplayedtest);
        contentResolver = contextIn.getContentResolver();
    }

    public void exctractGameLogData(){

        try{
        JSONObject jsonData = new JSONObject(data).getJSONObject(PARENT_NAME);
        JSONArray nameArray = jsonData.names();
        ContentValues values;

        //Cycle through each object
        for(int i = 0 ; i < nameArray.length() ; i++) {
            JSONObject currentGame = jsonData.getJSONObject(nameArray.getString(i));
           // gameNumber  = findNextGameNo();

            //Create Player 1 Row:
            ContentValues playerOneCV  = new ContentValues();
            ContentValues playerTwoCV  = new ContentValues();

            playerOneCV = genarateGenralData(currentGame,playerOneCV);
            playerOneCV = genaratePlayerSpicficData(currentGame,playerOneCV,'1');

            playerTwoCV = genarateGenralData(currentGame,playerTwoCV);
            playerTwoCV = genaratePlayerSpicficData(currentGame,playerTwoCV,'2');

            contentResolver.insert(LoggedGamesContract.URI_TABLE, playerOneCV);
            contentResolver.insert(LoggedGamesContract.URI_TABLE, playerTwoCV);


        }
        }catch (JSONException e){
            e.printStackTrace();
            Log.e(LOG_TAG,"Error Proccessing JSON" + e.toString());
        }


    }

    private ContentValues genarateGenralData(JSONObject objIn, ContentValues playerCV) throws JSONException{

        playerCV.put(LoggedGamesContract.LoggedGamesColumns.PLAYED_DATE,objIn.getString("LocalLoggedGame Date"));
        playerCV.put(LoggedGamesContract.LoggedGamesColumns.WIN_TYPE,objIn.getString("Winner Tomthord (S/K/M)"));
        playerCV.put(LoggedGamesContract.LoggedGamesColumns.GAME_ID,objIn.getString("LocalLoggedGame No"));

        String loc = objIn.getString("Location");
        loc = DatabaseUtils.sqlEscapeString(loc);
        String where = LocationsContract.LocationsColumns.LOCATION_NAME + " = " + loc ;

        Cursor matchingLocations = contentResolver.query(LocationsContract.URI_TABLE,null, where ,null,null);
        //TODO: Don't like this construct. This will break if there are ever mutiple Identites with the same name. For now also putting UNIQUE Constraint on Identites Name
        if(matchingLocations == null || matchingLocations.getCount() != 1) {
            //TODO: Should throw an exception, in the grand scheme this would be an excption with Database and inport
            Log.e(LOG_TAG, "(matchingLocations) Too many or too few Results returned when trying to find " + loc + ". " + matchingLocations.getCount() + " results where returned.");
        }else{
            if(matchingLocations.moveToFirst()){
                playerCV.put(LoggedGamesContract.LoggedGamesColumns.LOCATION_ID, matchingLocations.getInt(matchingLocations.getColumnIndex(BaseColumns._ID)));
            }}

        return playerCV;
    }

    private ContentValues genaratePlayerSpicficData(JSONObject objIn, ContentValues playerCv, char side) throws JSONException{

        //Get Players Deck
        //Get Players Name
        //Get Players Score

        final String playerAndNo = "Player " + side;

        playerCv.put(LoggedGamesContract.LoggedGamesColumns.SCORE,objIn.getInt(playerAndNo+" Score"));
        String playerName = objIn.getString(playerAndNo);
        String winner = objIn.getString("Winner");
        playerCv.put(LoggedGamesContract.LoggedGamesColumns.WIN_FLAG,getWinner(playerName,winner));

        playerName = DatabaseUtils.sqlEscapeString(playerName);
        String where = PlayersContract.PlayersColumns.PLAYER_NAME + " = " + playerName ;
        Cursor matchingPlayers = contentResolver.query(PlayersContract.URI_TABLE,null, where ,null,null);

        //TODO: Don't like this construct. This will break if there are ever mutiple Identites with the same name. For now also putting UNIQUE Constraint on Identites Name
        if(matchingPlayers == null || matchingPlayers.getCount() != 1) {
            //TODO: Should throw an exception, in the grand scheme this would be an excption with Database and inport
            Log.e(LOG_TAG, "(matchingPlayers) Too many or too few Results returned when trying to find " + playerName + ". " + matchingPlayers.getCount() + " results where returned.");
        }else{
            if(matchingPlayers.moveToFirst()){
                playerCv.put(LoggedGamesContract.LoggedGamesColumns.PLAYER_ID,matchingPlayers.getInt(matchingPlayers.getColumnIndex(BaseColumns._ID)));
            }}


        String deckName = DatabaseUtils.sqlEscapeString(objIn.getString(playerAndNo+" Archtype or Deckname"));
        where = DecksContract.DecksColumns.DECK_NAME + " = " + deckName ;

        Cursor matchingDecks = contentResolver.query(DecksContract.URI_TABLE,null, where ,null,null);
        //TODO: Don't like this construct. This will break if there are ever mutiple Identites with the same name. For now also putting UNIQUE Constraint on Identites Name
        if(matchingDecks == null || matchingDecks.getCount() != 1) {
            //TODO: Should throw an exception, in the grand scheme this would be an excption with Database and inport
            Log.e(LOG_TAG, "(matchingDecks) Too many or too few Results returned when trying to find " + deckName + ". " + matchingDecks.getCount() + " results where returned.");

        }else{
            if(matchingDecks.moveToFirst()){
                playerCv.put(LoggedGamesContract.LoggedGamesColumns.DECK_ID,matchingDecks.getInt(matchingDecks.getColumnIndex(BaseColumns._ID)));
            }}

        return playerCv;
    }


    private String getWinner(String player, String winner){
        String ret = "N";
        if(player == winner){
            ret = "Y";
        }
        return ret;
    }

    //Broken
    private int findNextGameNo() {
        int ret = 1;

        String maxGameIdSQL = "MAX("+ LoggedGamesContract.LoggedGamesColumns.GAME_ID +")";
        //String maxGameIdSQL = LoggedGamesContract.LoggedGamesColumns.GAME_ID;
        String[] cols = {maxGameIdSQL};
        Cursor maxGameID = contentResolver.query(LoggedGamesContract.URI_TABLE, cols, null, null, null);

        if(maxGameID == null || maxGameID.getCount() != 1) {
            //TODO: Should throw an exception, in the grand scheme this would be an excption with Database and inport
            Log.e(LOG_TAG, "Too many or too few Results when finding the maxumian game number");
        }else{
            Log.d(LOG_TAG, "Cursor Length: " + maxGameID.getCount());
            if(maxGameID.moveToFirst()){
               // ret = maxGameID.getInt(maxGameID.getColumnIndex(LoggedGamesContract.LoggedGamesColumns.GAME_ID));
                ret = maxGameID.getInt(maxGameID.getColumnIndex(maxGameIdSQL));
                ret++;
            }}

        Log.v(LOG_TAG, "Returning Max LocalLoggedGame ID as: " + ret);

        return ret;
    }

}
