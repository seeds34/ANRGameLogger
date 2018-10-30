package org.seeds.anrgamelogger.addgame.subpresenters;

import android.app.Activity;
import android.view.View;

import java.util.ArrayList;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.addgame.views.AddGameOverviewView;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGamePlayer;
import org.seeds.anrgamelogger.model.IdentityList;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameOverview;

import io.reactivex.Observable;

public class OverviewSubPresenter extends SubPresenter{

    private AddGameOverviewView view;
    private int gameNumber;

    public OverviewSubPresenter(Activity activity, AddGameOverviewView view, ArrayList<String> locationList, int gameNumber){
        super(activity, activity.getString(R.string.title_overview));
        this.view = view;
        this.view.setUpLocationAutoComplete(locationList);
        this.gameNumber = gameNumber;
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
    public LoggedGameOverview getGameOverview(){

        return new LoggedGameOverview(
            view.getLocation(),
            view.getPlayedDate(),
            view.getWinType(),
                gameNumber,
                "corp"
        );
    }

    @Override
    public LoggedGamePlayer getPlayerData() {
        return null;
    }
}
