package org.seeds.anrgamelogger.addgame.subpresenters;

import android.app.Activity;
import android.view.View;

import org.seeds.anrgamelogger.addgame.views.AddGameBaseView;
import org.seeds.anrgamelogger.model.IdentityList;

import io.reactivex.Observable;

public abstract class SubPresenter {

    //protected AddGameBaseView view;
    private Activity activity;
    private String title;

    public SubPresenter(Activity activity, String title) {
        this.activity = activity;
        this.title = title;
        onCreateView();
    }

    public abstract View getView();

    public abstract void onCreateView();

    public String getTitle(){
        return title;
    }

    public Observable<Object> observeSave(){return null;}

    public abstract void setUpIdentitySpiner(IdentityList oneSidedList);
}
