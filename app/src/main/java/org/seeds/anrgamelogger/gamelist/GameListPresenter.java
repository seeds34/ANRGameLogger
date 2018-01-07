package org.seeds.anrgamelogger.gamelist;

import org.seeds.anrgamelogger.R;
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

        model.databaseFirstTimeSetup();

        model.getGameList(25)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list->{view.setData(list);});

       compositeSubscription.add(onItemClick());
        compositeSubscription.add(observeCorpFab());
        compositeSubscription.add(observeRunnerFab());
    }

    public void onDestroy() {
        compositeSubscription.clear();
    }

    private Subscription onItemClick(){
        return view.observeRecyckerView()
                .subscribe(pos ->{ model.startGameDetailActivity(pos.toString());
        });
    }

    private Subscription observeCorpFab() {
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

    private Subscription observeRunnerFab() {
        return view.observeRunnerFab()

            .subscribe(__ -> {
                model.startAddGameActivity(R.string.title_runner);
            });
    }
}
