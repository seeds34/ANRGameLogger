package org.seeds.anrgamelogger.database.datacreater;

import static android.R.attr.version;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.database.contracts.DecksContract;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;
import org.seeds.anrgamelogger.database.contracts.LocationsContract;
import org.seeds.anrgamelogger.database.contracts.LoggedGameOverviewsContract;
import org.seeds.anrgamelogger.database.contracts.PlayersContract;

/**
 * Created by Tomas Seymour-Turner on 29/05/2017.
 */

public class InportedLoggedGame{

    private static final String LOG_TAG = InportedLoggedGame.class.getSimpleName();
    private ContentResolver contentResolver;
    private String data;
    private final String PARENT_NAME = "Game Tracker";

//    public InportedLoggedGame(Context contextIn){
//        data = GetRawData.GetRawDataFromFile(contextIn, R.raw.allgamesplayedtestall);
//        contentResolver = contextIn.getContentResolver();
//     //   inportLoggedGames();
//    }


    public InportedLoggedGame(Activity activityIn){
        data = GetRawData.GetRawDataFromFile(activityIn, R.raw.allgamesplayedtestall);
        contentResolver = activityIn.getContentResolver();
        //   inportLoggedGames();
    }

    public void inportLoggedGames(){

        try{
            JSONObject jsonData = new JSONObject(data).getJSONObject(PARENT_NAME);
            JSONArray allGameData = jsonData.names();
            ContentValues values;

            //Cycle through each object
            for(int i = 0 ; i < allGameData.length() ; i++) {
//                JSONObject currentGame = jsonData.getJSONObject(allGameData.getString(i));
//
//                int gameNo = currentGame.getInt("Game No");
//                String gameDate = currentGame.getString("Game Date").substring(0,10);
//                String location = getLocationId(currentGame.getString("Location"));
//                String playerOne = getPlayerId(currentGame.getString("Player 1"));
//                String playerTwo = getPlayerId(currentGame.getString("Player 2"));
//                String playerOneDeck = getDeckId(currentGame.getString("Player 1 Archtype or Deckname"), currentGame.getString("Player 1 ID"),null);
//                String playerTwoDeck = getDeckId(currentGame.getString("Player 2 Archtype or Deckname"), currentGame.getString("Player 2 ID"),null);
//                String playerOneWinFlag = getWinner(currentGame.getString("Player 1"), currentGame.getString("Winner"));
//                String playerTwoWinFlag = getWinner(currentGame.getString("Player 2"), currentGame.getString("Winner"));
//                String winMethord = currentGame.getString("Winner Tomthord (S/K/M)");
//                int playerOneScore = currentGame.getInt("Player 1 Score");
//                int playerTwoScore = currentGame.getInt("Player 2 Score");
//
//
//                ContentValues playerOneCV = new ContentValues();
//                playerOneCV.put(LoggedGameOverviewsContract.LoggedGameOverviewsColumns.GAME_ID, gameNo);
//                playerOneCV.put(LoggedGameOverviewsContract.LoggedGameOverviewsColumns.PLAYED_DATE, gameDate);
//                playerOneCV.put(LoggedGameOverviewsContract.LoggedGameOverviewsColumns.PLAYER_ID, playerOne);
//                playerOneCV.put(LoggedGameOverviewsContract.LoggedGameOverviewsColumns.DECK_ID, playerOneDeck);
//                playerOneCV.put(LoggedGameOverviewsContract.LoggedGameOverviewsColumns.WIN_FLAG, playerOneWinFlag);
//                playerOneCV.put(LoggedGameOverviewsContract.LoggedGameOverviewsColumns.LOCATION_ID, location);
//                playerOneCV.put(LoggedGameOverviewsContract.LoggedGameOverviewsColumns.WIN_TYPE, winMethord);
//                playerOneCV.put(LoggedGameOverviewsContract.LoggedGameOverviewsColumns.SCORE, playerOneScore);
//                playerOneCV.put(LoggedGameOverviewsContract.LoggedGameOverviewsColumns.PLAYER_SIDE, "1");
//
//                ContentValues playerTwoCV = new ContentValues();
//                playerTwoCV.put(LoggedGameOverviewsContract.LoggedGamesColumns.GAME_ID, gameNo);
//                playerTwoCV.put(LoggedGameOverviewsContract.LoggedGamesColumns.PLAYED_DATE, gameDate);
//                playerTwoCV.put(LoggedGameOverviewsContract.LoggedGamesColumns.PLAYER_ID, playerTwo);
//                playerTwoCV.put(LoggedGameOverviewsContract.LoggedGamesColumns.DECK_ID, playerTwoDeck);
//                playerTwoCV.put(LoggedGameOverviewsContract.LoggedGamesColumns.WIN_FLAG, playerTwoWinFlag);
//                playerTwoCV.put(LoggedGameOverviewsContract.LoggedGamesColumns.LOCATION_ID, location);
//                playerTwoCV.put(LoggedGameOverviewsContract.LoggedGamesColumns.WIN_TYPE, winMethord);
//                playerTwoCV.put(LoggedGameOverviewsContract.LoggedGamesColumns.SCORE, playerTwoScore);
//                playerTwoCV.put(LoggedGameOverviewsContract.LoggedGamesColumns.PLAYER_SIDE, "2");
//
//                //Log.v(LOG_TAG, "Inserting Game: " + gameNo + " | Game between " + playerOne + " and " + playerTwo);
//                contentResolver.insert(LoggedGameOverviewsContract.URI_TABLE, playerOneCV);
//                contentResolver.insert(LoggedGameOverviewsContract.URI_TABLE, playerTwoCV);
            }

        }catch (JSONException e){
            e.printStackTrace();
            Log.e(LOG_TAG,"Error Proccessing JSON" + e.toString());
        }

    }

    private String getLocationId(String locationName){
        String ret = "-1";
        Uri uriTable = LocationsContract.URI_TABLE;
        String coloumnName = LocationsContract.LocationsColumns.LOCATION_NAME;

        if(exists(uriTable, coloumnName , locationName)){
            ret = getIDValue(uriTable, coloumnName, locationName);
        }else{

            ContentValues values = new ContentValues();
            values.put(coloumnName, locationName);
            ret = LocationsContract.Location.getLocationId(contentResolver.insert(uriTable, values));

        }
        return ret;
    }

    private String getPlayerId(String palyerName){
        String ret = "-1";
        Uri uriTable = PlayersContract.URI_TABLE;
        String coloumnName = PlayersContract.PlayersColumns.PLAYER_NAME;

        if(exists(uriTable, coloumnName , palyerName)){
            ret = getIDValue(uriTable, coloumnName, palyerName);
        }else{

            ContentValues values = new ContentValues();
            values.put(coloumnName, palyerName);
            ret = PlayersContract.Player.getPlayerId(contentResolver.insert(uriTable, values));
        }
        return ret;
    }

    private String getDeckId(String deckName, String identityName, String version){
        String ret = "-1";
        Uri uriTable = DecksContract.URI_TABLE;
        //TODO: Need to also support Deck Version but Deck version can be NULL so Need to suppor NULL check --Might of fixed 26/07/2017
        String[] coloumns = new String[]{DecksContract.DecksColumns.DECK_NAME, DecksContract.DecksColumns.DECK_IDENTITY, DecksContract.DecksColumns.DECK_VERSION};
        String[] values = new String[] {deckName, identityName, version};

        if(exists(uriTable, coloumns , values)){
            ret = getIDValue(uriTable, coloumns, values);
        }else{

            ContentValues contenVal = new ContentValues();
            contenVal.put(DecksContract.DecksColumns.DECK_NAME, deckName);
            contenVal.put(DecksContract.DecksColumns.DECK_IDENTITY, getIdentityId(identityName));
            ret = DecksContract.Deck.getDeckId(contentResolver.insert(uriTable, contenVal));
        }

        Log.d(LOG_TAG, "Looking for: D: " + values[0] + " I: " + values[1] + " V: " + version + " Returned: " + ret);

        return ret;
    }

    private String getIdentityId(String identityName){
        String ret = "-1";
        Uri uriTable = IdentitiesContract.URI_TABLE;
        String coloumnName = IdentitiesContract.IdentitiesColumns.IDENTITY_NAME;

        ret = getIDValue(uriTable, coloumnName, identityName);

        return ret;
    }

    private String getWinner(String player, String winner){
        String ret = "N";
        if(player == winner){
            ret = "Y";
        }
        return ret;
    }


    private boolean exists(Uri tableUri, String coloumnName, String valueIn){

        boolean ret = false;
        String value = DatabaseUtils.sqlEscapeString(valueIn);
        String whereClause = coloumnName + " = " + value ;
        Cursor queryResult = contentResolver.query(tableUri,null, whereClause ,null,null);

        if(queryResult != null && queryResult.getCount() > 0){
            ret = true;
        }
        Log.d(LOG_TAG, "Looking for: D: " + coloumnName + " I: " + valueIn + " V: " + version + " Returned: " + ret);
        queryResult.close();
        return ret;
    }

    private boolean exists(Uri tableUri, String[] coloumns, String[] values){

        boolean ret = false;
        String whereClause = "";
        //Check Arrays are the same size, if not where clause wont match
        if(coloumns.length == values.length) {
            for (int i = 0; i < coloumns.length; i++) {
                if(values[i] != null){
                whereClause = whereClause + coloumns[i] + " = " + DatabaseUtils.sqlEscapeString(values[i]);
                if(i < coloumns.length){
                    whereClause = whereClause +" and ";
                }
            }else{
                    whereClause = whereClause + coloumns[i] + " IS NULL";
                }

            }
        }else{
            //Error!!
        }

        Log.d(LOG_TAG, "Checkin Deck Exsists: WHERE = " + whereClause);


        Cursor queryResult = contentResolver.query(tableUri,null, whereClause ,null,null);

        if(queryResult != null && queryResult.getCount() > 0){
            ret = true;
        }


        queryResult.close();
        return ret;
    }

    public boolean isLoggedGamesTableEmpty(){
        boolean ret = true;

        Cursor queryResult = contentResolver.query(LoggedGameOverviewsContract.URI_TABLE,null, null ,null,null);

        if(queryResult != null && queryResult.getCount() > 0){
            ret = false;
        }

        queryResult.close();

        return  ret;
    }

    private String getIDValue(Uri tableUri, String coloumnName, String valueIn){

        //TODO: Wont work for diffriant deck version or IDs. Broken when using concatanated key

        String ret = "-1";
        String value = DatabaseUtils.sqlEscapeString(valueIn);
        String whereClause = coloumnName + " = " + value ;
        Cursor queryResult = contentResolver.query(tableUri,null, whereClause ,null,null);

        if(queryResult != null && queryResult.getCount() == 1){

            if(queryResult.moveToFirst()) {
                ret = queryResult.getString(queryResult.getColumnIndex(BaseColumns._ID));
            }

        }else{
            //ERROR:
        }

        queryResult.close();
        return ret;
    }


    private String getIDValue(Uri tableUri, String[] coloumns, String[] values){

        String ret = "-1";
        String whereClause = "";
        //Check Arrays are the same size, if not where clause wont match
        if(coloumns.length == values.length) {
            for (int i = 0; i < coloumns.length; i++) {
                if(values[i] != null){
                    whereClause = whereClause.concat(coloumns[i] + " = " + DatabaseUtils.sqlEscapeString(values[i]));
                    if(i < coloumns.length){
                        whereClause = whereClause.concat(" and ");
                    }
                }else{
                    whereClause= whereClause.concat(coloumns[i] + " IS NULL" );
                }

            }
        }else{
            //Error!!
        }

        Log.d(LOG_TAG, "Getting Deck ID: WHERE = " + whereClause);

        Cursor queryResult = contentResolver.query(tableUri,null, whereClause ,null,null);

        if(queryResult != null && queryResult.getCount() == 1){

            if(queryResult.moveToFirst()) {
                ret = queryResult.getString(queryResult.getColumnIndex(BaseColumns._ID));
            }

        }else{
            //ERROR:
        }

        queryResult.close();
        return ret;
    }
}
