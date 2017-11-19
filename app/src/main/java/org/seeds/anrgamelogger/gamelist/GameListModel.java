package org.seeds.anrgamelogger.gamelist;

import android.app.Activity;

import org.seeds.anrgamelogger.model.LocalLoggedGame;

import java.util.ArrayList;

import rx.Observable;
import rx.functions.Func0;


/**
 * Created by Tomas Seymour-Turner on 04/11/2017.
 */

public class GameListModel {

    private final String GAME_LIST = "GAME_LIST";
    private Activity activity;


    public GameListModel(Activity a){
        activity = a;
    }

    public Observable<ArrayList<LocalLoggedGame>> getGameList(int lengthLimit){

       return Observable.defer(new Func0<Observable<ArrayList<LocalLoggedGame>>>() {
            @Override
            public Observable<ArrayList<LocalLoggedGame>> call() {
                return Observable.fromCallable(()-> LoggedGameList.genarateGameList(lengthLimit, activity.getContentResolver()));
            }
        });}

       public void saveGameList(ArrayList<LocalLoggedGame> saveGameList){
           //RxSaveState.updateSaveState(activity, bundle -> bundle.putParcelableArrayList(GAME_LIST, new ArrayList<LocalLoggedGame>(saveGameList)) );
    }

//    public Observable<ArrayList<LocalLoggedGame>> getGameListFromSaveState() {
//           return RxSaveState.getSavedState(activity)
//                   .map(bundle -> bundle.getParcelableArrayList(GAME_LIST));
//    }


//        Observable.from(lgl.genarateLoggedGameList());
//
//       Observable.fromCallable(() -> lgl.genarateLoggedGameList(lengthLimit));

      //  return Observable.just(lgl).doOnNext(__ -> lgl.genarateLoggedGameList(lengthLimit));

//        return Observable.<ArrayList<LocalLoggedGame>>just(null).doOnNext(__->lgl.genarateLoggedGameList(lengthLimit)).observeOn(Schedulers.io());

//        return Observable.<ArrayList<LocalLoggedGame>>create(subscriber -> {
//            subscriber.onNext(lgl.getLoggedGameList(lengthLimit));
//            subscriber.onCompleted();
//        }).subscribeOn(Schedulers.io());


//    public ArrayList<LocalLoggedGame> getloggedGameList(){
//        return lgl.getLoggedGameList();
//    }


    public void startGameDetailActivity(String selectedGame){
        //GameDetailActivity.start(activity.getApplication(),);
    }

}
