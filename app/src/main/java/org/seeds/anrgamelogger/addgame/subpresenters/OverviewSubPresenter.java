package org.seeds.anrgamelogger.addgame.subpresenters;

import android.app.Activity;
import android.view.View;

import java.util.ArrayList;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.addgame.model.OverviewViewData;
import org.seeds.anrgamelogger.addgame.model.PlayerViewData;
import org.seeds.anrgamelogger.addgame.views.AddGameOverviewView;
import org.seeds.anrgamelogger.model.IdentityList;

import io.reactivex.Observable;

public class OverviewSubPresenter extends SubPresenter{

    private AddGameOverviewView view;

    public OverviewSubPresenter(Activity activity, AddGameOverviewView view, ArrayList<String> locationList){
        super(activity, activity.getString(R.string.title_overview));
        this.view = view;
        this.view.setUpLocationAutoComplete(locationList);
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void setUpIdentitySpiner(IdentityList oneSidedList) {}

    @Override
    public void onCreateView(){

    }

    @Override
    public Observable<Object> observeSave(){
        return view.save();
    }

    @Override
    public OverviewViewData getGameOverview(){
        return new OverviewViewData(
            view.getLocation(),
            view.getPlayedDate(),
            view.getWiningSide(),
            view.getWinType()
        );
    }

    @Override
    public PlayerViewData getPlayerData() {
        return null;
    }
}
