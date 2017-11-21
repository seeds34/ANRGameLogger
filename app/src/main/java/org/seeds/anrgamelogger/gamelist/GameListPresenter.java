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

        model.getGameList(25)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list->{view.setData(list);});

       compositeSubscription.add(onItemClick());
    }

    public void onDestroy() {
        compositeSubscription.clear();
    }

    private Subscription onItemClick(){

        return view.observeRecyckerView()
                .subscribe(pos ->{ model.startGameDetailActivity(pos.toString());
        });
    }
}
