package org.seeds.anrgamelogger.gamedetail;

import android.util.Log;

import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameFlat;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Tomas Seymour-Turner on 19/11/2017.
 */

public class GameDetailPresenter {

    private static final String LOG_TAG = GameDetailPresenter.class.getSimpleName();

    private  GameDetailModel model;
    private GameDetailView view;

    private LoggedGameFlat lgf;

    private final CompositeDisposable compositeSubscription = new CompositeDisposable();


    public GameDetailPresenter(GameDetailView view, GameDetailModel model){
        this.view = view;
        this.model = model;
    }

    public void onCreate(){

        lgf = model.getLoggedGame();

        Log.i(LOG_TAG,".onCreate() : Setting up view data");
        view.setData(lgf);
        view.setUpPages();
        view.setTitle();

        compositeSubscription.add(observerEdit());
    }

    public void onDestroy(){

    }

    public Disposable observerEdit(){
        return view.editGame()
                .subscribe( a ->
                        model.editGame(lgf.getGameID())
                );
    }

}
