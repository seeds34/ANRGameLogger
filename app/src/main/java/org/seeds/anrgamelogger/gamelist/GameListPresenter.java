package org.seeds.anrgamelogger.gamelist;



import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import org.seeds.anrgamelogger.R;

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

        //TODO: Uncomment once fixed getGameList
//        model.getGameList(25)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        list->{view.setData(list);});

        //compositeSubscription.add(model.databaseFirstTimeSetup());
       compositeSubscription.add(onItemClick());
        compositeSubscription.add(observeCorpFab());
        compositeSubscription.add(observeRunnerFab());
        compositeSubscription.add(observerImageLoadButtonClick());
    }

    public void onDestroy() {
        compositeSubscription.clear();
    }

    private Disposable onItemClick(){
        return view.observeRecyckerView()
                .subscribe(pos ->{ model.startGameDetailActivity(pos.toString());
        });
    }

    private Disposable observerImageLoadButtonClick(){
        return view.observeLoadImageClick()
                .subscribeOn(Schedulers.io())
                .doOnNext(__ -> view.showLoading(true))
                .doOnNext(__ -> model.loadIdentImages())
                .doOnNext((__ -> view.showLoading(false)))
                .subscribe();
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
                model.startAddGameActivity(R.string.title_corp);
    });
    }

    private Disposable observeRunnerFab() {
        return view.observeRunnerFab()

            .subscribe(__ -> {
                model.startAddGameActivity(R.string.title_runner);
            });
    }
}
