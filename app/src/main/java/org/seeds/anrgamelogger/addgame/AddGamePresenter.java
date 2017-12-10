package org.seeds.anrgamelogger.addgame;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by user on 08/12/2017.
 */

public class AddGamePresenter {

    private AddGameModel model;
    private AddGameView view;

    public AddGamePresenter(AddGameView view, AddGameModel model){
        this.view = view;
        this.model = model;
    }

    public void onCreate() {
        model.getListOfIdentitesNames()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(a -> view.setSpiinerAdaptor(a));

        model.getListOfIdentitesImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(a -> view.setIdentitiesImageViewPager(a));
    }



    public void onDestroy(){}


}
