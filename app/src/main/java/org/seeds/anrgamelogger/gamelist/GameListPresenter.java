package org.seeds.anrgamelogger.gamelist;



import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import org.seeds.anrgamelogger.application.ANRLoggerApplication;

/**
 * Created by Tomas Seymour-Turner on 31/07/2017.
 */

public class GameListPresenter{

    private static final String LOG_TAG = GameListPresenter.class.getSimpleName();

    public GameListView view;
    public GameListModel model;
    private final CompositeDisposable compositeSubscription = new CompositeDisposable();


    public GameListPresenter(GameListView v, GameListModel m){
        view = v;
        model = m;
    }

    public void onCreate() {

        model.databaseFirstTimeSetup();

        //TODO: Uncomment once fixed

        view.setData(model.getGameList(10));

//        model.getGameList(25)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        list->{view.setData(list);});

        //compositeSubscription.add(model.databaseFirstTimeSetup());
        //model.getGameList(50);
        compositeSubscription.add(onItemClick());
        compositeSubscription.add(observeCorpFab());
        compositeSubscription.add(observeRunnerFab());
        compositeSubscription.add(observerImageLoadButtonClick());
    }

    public void onDestroy() {
        compositeSubscription.clear();
    }

    private Disposable onItemClick(){
        return view.observeRecyclerView()
                .subscribe(pos ->{ model.startGameDetailActivity(pos.toString());
        });
    }

    private Disposable observerImageLoadButtonClick(){
        return view.observeLoadImageClick()
                .subscribe(__ -> model.loadIdentityImages());
    }

    private Disposable observeCorpFab() {
        return view.observeCorpFab()
            //.doOnNext(__ -> view.showLoading(true))
            //.map(__ -> view.getUsernameEdit())
            //.observeOn(Schedulers.io())
            //.switchMap(username -> model.getUserReops(username))
            //.observeOn(AndroidSchedulers.mainThread())
            //.doOnNext(() -> model.startAddGameActivity())
            //.doOnEach(__ -> view.showLoading(false))
            //.retry()
            .subscribe(__ -> {
                model.startAddGameActivity(ANRLoggerApplication.CORP_SIDE_IDENTIFIER);
    });
    }

    private Disposable observeRunnerFab() {
        return view.observeRunnerFab()

            .subscribe(__ -> {
                model.startAddGameActivity(ANRLoggerApplication.RUNNER_SIDE_IDENTIFIER);
            });
    }
}
