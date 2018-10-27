package org.seeds.anrgamelogger.addgame.subpresenters;

import android.app.Activity;
import android.view.View;
import io.reactivex.Observable;

import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGamePlayer;
import org.seeds.anrgamelogger.model.IdentityList;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameOverview;

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

    public abstract LoggedGamePlayer getPlayerData();
    public abstract LoggedGameOverview getGameOverview();

}
