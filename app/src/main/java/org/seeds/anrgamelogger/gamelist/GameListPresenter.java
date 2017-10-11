package org.seeds.anrgamelogger.gamelist;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import org.seeds.anrgamelogger.database.datacreater.InportedLoggedGame;
import org.seeds.anrgamelogger.database.datacreater.PopulateIdentitiesData;
import org.seeds.anrgamelogger.model.LazyDataGen;

/**
 * Created by Tomas Seymour-Turner on 31/07/2017.
 */

public class GameListPresenter extends MvpBasePresenter<GameListView> {

    private static final String LOG_TAG = GameListPresenter.class.getSimpleName();

private void getGameData(){

    LazyDataGen dataGen = new LazyDataGen(context);
    dataGen.setFilters(25);
    dataGen.genarateAllGames();
    testData = dataGen.getPlayedGamesList();

}

private void getGame(int gameID){

}

    public class SetUpData extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        Log.v(LOG_TAG, "Started Proccessing Data");
        Context context = getApplicationContext();
        PopulateIdentitiesData c = new PopulateIdentitiesData(context);
        if(c.isIdentitiesTableEmpty()){
            c.extractIdentitiesFromNRDB();
        }

        InportedLoggedGame e = new InportedLoggedGame(context);
        if(e.isLoggedGamesTableEmpty()) {
            e.inportLoggedGames();
        }


        Log.v(LOG_TAG, "Finished Proccessing Data");


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mGameListAdaptor.loadNewData(testData);
    }
}


}
