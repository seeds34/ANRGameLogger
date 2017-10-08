package org.seeds.anrgamelogger.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

import org.seeds.anrgamelogger.database.contracts.LoggedGamesFlatViewContract;

import java.util.ArrayList;

/**
 * Created by Tomas Seymour-Turner on 23/05/2017.
 */

public class LazyDataGen {

    private static final String LOG_TAG = LazyDataGen.class.getSimpleName();
    private ArrayList<LocalLoggedGame> playedGamesList;
    private ContentResolver contentResolver;
    private int returnLimit;
    private String resultOrder;

    public LazyDataGen(Context contextIn) {
        contentResolver = contextIn.getContentResolver();
        playedGamesList = new ArrayList<LocalLoggedGame>();
        returnLimit = -1;
        resultOrder = "DESC";
    }

    public ArrayList<LocalLoggedGame> getPlayedGamesList(){
        return playedGamesList;
    }

    //TODO: Better filitering, inlcuding order by chossen coloumn
    public void setFilters(int returnLimitIn){
        returnLimit = returnLimitIn;
    }

    public void setResultOrder(String resultOrderIn){
        resultOrder = resultOrderIn;
    }

    public void genarateAllGames(){

        String orderBy = LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.GAME_ID + " " + resultOrder;
        if(returnLimit > -1){
            orderBy = orderBy + " LIMIT " + returnLimit;
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

                    playedGamesList.add(new LocalLoggedGame(playerOne, playerTwo, location, playedDate, gameID, winType));


                }while (queryResult.moveToNext());
                queryResult.close();
    }
        }


}
}