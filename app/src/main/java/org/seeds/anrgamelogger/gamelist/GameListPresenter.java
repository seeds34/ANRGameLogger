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


        view.setData(model.getGameList(20));

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
            .doOnEach(__ -> view.hideSubFABs())
            .subscribe(__ -> {
                model.startAddGameActivity(ANRLoggerApplication.CORP_SIDE_IDENTIFIER);
    });
    }

    private Disposable observeRunnerFab() {
        return view.observeRunnerFab()
            .doOnEach(__ -> view.hideSubFABs())
            .subscribe(__ -> {
                model.startAddGameActivity(ANRLoggerApplication.RUNNER_SIDE_IDENTIFIER);
            });
    }

  public void refresh() {
      view.setData(model.getGameList(20));
  }
}
