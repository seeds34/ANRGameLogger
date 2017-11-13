package org.seeds.anrgamelogger.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import org.seeds.anrgamelogger.database.contracts.LoggedGamesFlatViewContract;
import org.seeds.anrgamelogger.database.datacreater.SetUpTestData;

import java.util.ArrayList;

/**
 * Created by Tomas Seymour-Turner on 23/05/2017.
 */

public class LoggedGameList {

    private static final String LOG_TAG = LoggedGameList.class.getSimpleName();
    private ArrayList<LocalLoggedGame> loggedGamesList;
    private int listLengthLimit;
    private String resultOrder;

    private ContentResolver contentResolver;
    private Context context;

    public LoggedGameList(ContentResolver contentResolverIn, Context contextIn) {
        contentResolver = contentResolverIn;
        context = contextIn;
        loggedGamesList = new ArrayList<LocalLoggedGame>();
        //TODO: This seems wrong
        listLengthLimit = -1;
        resultOrder = "DESC";
        SetUpTestData.setUpTestData(contextIn);
    }


    public ArrayList<LocalLoggedGame> getLoggedGameList(int listLengthLimitIn){
        listLengthLimit = listLengthLimitIn;
        genarateAllGames();
        return loggedGamesList;
    }

    public ArrayList<LocalLoggedGame> getLoggedGameListAll(){
        return loggedGamesList;
    }

//    //TODO: Better filitering, inlcuding order by chossen coloumn
//    public void setFilters(int returnLimitIn){
//        listLengthLimit = returnLimitIn;
//    }

    public void setResultOrder(String resultOrderIn){
        resultOrder = resultOrderIn;
    }

    public LocalLoggedGame getGame(String gameIdIn){
        LocalLoggedGame ret = null;
        for(LocalLoggedGame l: loggedGamesList){
            Log.d(LOG_TAG, "LLG ID = "+l.getGameID() + " LLG Requested = " +gameIdIn+ "  " + l.getGameID().equals(gameIdIn) + "  " + l.getGameID().compareTo(gameIdIn));
            if(l.getGameID().equals(gameIdIn)) {
                ret = l;
            }
        }
        Log.d(LOG_TAG, "Ret = " + ret);
        return ret;
    }

    private void genarateAllGames(){

        //TODO: Should it be GAME NO or PLAYED DATE??
        String orderBy = LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYED_DATE + " " + resultOrder;
        if(listLengthLimit > -1){
            orderBy = orderBy + " LIMIT " + listLengthLimit;
        }


        Cursor queryResult = contentResolver.query(LoggedGamesFlatViewContract.URI_TABLE,null, null ,null, orderBy);

        Player playerOne;
        Player playerTwo;

        if (queryResult != null) {
            if (queryResult.moveToFirst()) {
                do {

                    String location = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.LOCATION_NAME));
                    String playedDate = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYED_DATE));
                    String gameID = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.GAME_ID));
                    String winType = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.WIN_TYPE));

                    String playerOneName = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_NAME));
                    String playerOneDeckName = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_DECK_NAME));
                    String playerOneIDName = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_ID_NAME));
                    int playerOnceScore = queryResult.getInt(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_SCORE));
                    String playerOneWinFlag = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_WIN_FLAG));
                    byte[]  playerOneIDImage = queryResult.getBlob(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_ID_IMAGE));

                    String playerTwoName = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_NAME));
                    String playerTwoDeckName = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_DECK_NAME));
                    String playerTwoIDName = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_ID_NAME));
                    int playerTwoeScore = queryResult.getInt(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_SCORE));
                    String playerTwoWinFlag = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_WIN_FLAG));
                    byte[]  playerTwoIDImage = queryResult.getBlob(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_ID_IMAGE));

                    playerOne = new Player(playerOneName,playerOneDeckName, playerOnceScore, playerOneIDImage, playerOneWinFlag, playerOneIDName);
                    playerTwo = new Player(playerTwoName,playerTwoDeckName, playerTwoeScore, playerTwoIDImage, playerTwoWinFlag, playerTwoIDName);

                    loggedGamesList.add(new LocalLoggedGame(playerOne, playerTwo, location, playedDate, gameID, winType));


                }while (queryResult.moveToNext());
                queryResult.close();
    }
        }


}
}