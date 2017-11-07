package org.seeds.anrgamelogger.gamelist;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Tomas Seymour-Turner on 31/07/2017.
 */

public class GameListPresenter{

    private static final String LOG_TAG = GameListPresenter.class.getSimpleName();

    public GameListView view;
    public GameListModel model;
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();


    public GameListPresenter(GameListView v, GameListModel m){
        view = v;
        model = m;
    }

    public void onCreate() {
        compositeSubscription.add(datastuff());
        //view.setData(model.createList(25));
    }

    public void onDestroy() {
        compositeSubscription.clear();
    }

    private Subscription datastuff(){

      //  ArrayList<LocalLoggedGame> list = new ArrayList();

        return view.observeRV()
                .doOnNext(__ -> view.showMessage("HELP"))
                .observeOn(Schedulers.io())
                .switchMap(__ -> model.createList(25))
                .observeOn(AndroidSchedulers.mainThread())
                //.doOnNext(list -> view.setData(list))
                .retry()
                .subscribe(list->{ view.setData(list);
        });


    }

//private void getGameData(){
//
//   // LoggedGameList dataGen = new LoggedGameList(context);
//    //dataGen.setFilters(25);
//    //dataGen.genarateAllGames();
//    //testData = dataGen.getLoggedGameList(25);
//
//}

//    private void getGame(int gameID){
//
//    }

//    public class SetUpData extends AsyncTask<String, Void, String> {
//    @Override
//    protected String doInBackground(String... strings) {
//        Log.v(LOG_TAG, "Started Proccessing Data");
//        //Context context = getApplicationContext();
//        PopulateIdentitiesData c = new PopulateIdentitiesData(context);
//        if(c.isIdentitiesTableEmpty()){
//            c.extractIdentitiesFromNRDB();
//        }
//
//        InportedLoggedGame e = new InportedLoggedGame(context);
//        if(e.isLoggedGamesTableEmpty()) {
//            e.inportLoggedGames();
//        }
//
//
//        Log.v(LOG_TAG, "Finished Proccessing Data");
//
//
//        return null;
//    }
//
////    @Override
////    protected void onPostExecute(String s) {
////        super.onPostExecute(s);
////        mGameListAdaptor.loadNewData(testData);
////    }
//    }


}
