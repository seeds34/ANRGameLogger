package org.seeds.anrgamelogger.model;

import java.util.ArrayList;
import org.seeds.anrgamelogger.database.LoggedGameFlat;

/**
 * Created by Tomas Seymour-Turner on 23/05/2017.
 */

public class GameListManager {

    private static final String LOG_TAG = GameListManager.class.getSimpleName();
    private ArrayList<LoggedGameFlat> loggedGamesList;

    public GameListManager(){
        loggedGamesList = new ArrayList<>();
    }

    public ArrayList<LoggedGameFlat>  getGameList(){
        return loggedGamesList;
    }

//    public void genarateGameList(int listLengthLimit, ContentResolver contentResolver){
//
//        //TODO: Add fitler capability
//        String resultOrder = "DESC";
//
//        //TODO: Should it be GAME NO or PLAYED DATE??
//        String orderBy = LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYED_DATE + " " + resultOrder;
//        if(listLengthLimit > -1){
//            orderBy = orderBy + " LIMIT " + listLengthLimit;
//        }
//
//
//        Cursor queryResult = contentResolver.query(LoggedGamesFlatViewContract.URI_TABLE,null, null ,null, orderBy);
//
//        Player playerOne;
//        Player playerTwo;
//
//        if (queryResult != null) {
//            if (queryResult.moveToFirst()) {
//                do {
//
//                    String location = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.LOCATION_NAME));
//                    String playedDate = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYED_DATE));
//                    String gameID = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.GAME_ID));
//                    String winType = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.WIN_TYPE));
//
//                    String playerOneName = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_NAME));
//                    String playerOneDeckName = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_DECK_NAME));
//                    String playerOneIDName = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_ID_NAME));
//                    int playerOnceScore = queryResult.getInt(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_SCORE));
//                    String playerOneWinFlag = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_WIN_FLAG));
//                    byte[]  playerOneIDImage = queryResult.getBlob(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_ID_IMAGE));
//
//                    String playerTwoName = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_NAME));
//                    String playerTwoDeckName = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_DECK_NAME));
//                    String playerTwoIDName = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_ID_NAME));
//                    int playerTwoeScore = queryResult.getInt(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_SCORE));
//                    String playerTwoWinFlag = queryResult.getString(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_WIN_FLAG));
//                    byte[]  playerTwoIDImage = queryResult.getBlob(queryResult.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_ID_IMAGE));
//
//                    //TODO: Shit need to fix as i've now broken the "Load Logged Games Part"
//                    //playerOne = new Player(playerOneName,playerOneDeckName, playerOnceScore, playerOneIDImage, playerOneWinFlag, playerOneIDName);
//                    //playerTwo = new Player(playerTwoName,playerTwoDeckName, playerTwoeScore, playerTwoIDImage, playerTwoWinFlag, playerTwoIDName);
//
//                    //loggedGamesList.add(new LoggedGameOverview(playerOne, playerTwo, location, playedDate, gameID, winType));
//
//
//                }while (queryResult.moveToNext());
//                queryResult.close();
//            }
//        }
//
//       // return loggedGamesList;
//    }

    public LoggedGameFlat getGame(String gameIdIn){
        LoggedGameFlat ret = null;

        for(LoggedGameFlat llg: loggedGamesList){
            if(llg.getGameID() == Integer.valueOf(gameIdIn)) {
                ret = llg;
            }
        }
        return ret;
    }
}



//    private int listLengthLimit;
//    private String resultOrder;
//    ArrayList<LoggedGameOverview> loggedGamesList;
//    private ContentResolver contentResolver;
//    private Context context;

//    public GameListManager(ContentResolver contentResolverIn, Context contextIn) {
//        contentResolver = contentResolverIn;
//        context = contextIn;
//        loggedGamesList = new ArrayList<>();
//        //TODO: This seems wrong
//        listLengthLimit = -1;
//        resultOrder = "DESC";
//        SetUpTestData.setUpTestData(contextIn);
//    }

//    public ArrayList<LoggedGameOverview>  getGameList(int listLengthLimitIn){
//        return loggedGamesList;
//    }
//

