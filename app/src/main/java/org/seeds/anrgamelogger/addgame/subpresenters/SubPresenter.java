package org.seeds.anrgamelogger.addgame.subpresenters;

import android.app.Activity;
import android.view.View;

import org.seeds.anrgamelogger.addgame.views.AddGameBaseView;
import org.seeds.anrgamelogger.model.IdentityList;

import io.reactivex.Observable;

public abstract class SubPresenter {

    protected AddGameBaseView view;
    private Activity activity;
    private String title;

    public SubPresenter(Activity activity, AddGameBaseView view, String title) {
        this.activity = activity;
        this.view = view;
        this.title = title;
        onCreateView();
    }

    public View getView() {
        return view;
    }

    public abstract void onCreateView();

    public String getTitle(){
        return title;
    }

    public Observable<Object> save(){return null;}

    public abstract void setUpIdentitySpiner(IdentityList oneSidedList);
}
