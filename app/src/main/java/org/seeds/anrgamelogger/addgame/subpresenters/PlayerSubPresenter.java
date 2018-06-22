package org.seeds.anrgamelogger.addgame.subpresenters;

import android.app.Activity;
import android.view.View;
import java.util.ArrayList;
import org.seeds.anrgamelogger.addgame.model.OverviewViewData;
import org.seeds.anrgamelogger.addgame.model.PlayerViewData;
import org.seeds.anrgamelogger.addgame.views.AddGamePlayerView;
import org.seeds.anrgamelogger.model.IdentityList;

public class PlayerSubPresenter extends SubPresenter{

    AddGamePlayerView view;

    //ToDo: Change to title id and convert??
    public PlayerSubPresenter(Activity activity, AddGamePlayerView view, String title, ArrayList<String> playerList, ArrayList<String> deckList){
        super(activity,  title);
        this.view = view;
        this.view.setUpNameAutoComplete(playerList);
        this.view.setUpDeckNameAutoComplete(deckList);
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void onCreateView(){ }

    @Override
    public void setUpIdentitySpiner(IdentityList idList){
        view.setIdApadters(idList);
    }

    @Override
    public PlayerViewData getPlayerData(){

        return  new PlayerViewData(
                view.getIdentitiesName(),
                view.getPlayerName(),
                view.getDeckName(),
                Integer.toString(1),
                getTitle()
        );
    }

    @Override
    public OverviewViewData getGameOverview() {
        return null;
    }
}
