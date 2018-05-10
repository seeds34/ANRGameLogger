package org.seeds.anrgamelogger.addgame.subpresenters;

import android.app.Activity;
import android.view.View;

import org.seeds.anrgamelogger.addgame.PlayerViewData;
import org.seeds.anrgamelogger.addgame.views.AddGamePlayerView;
import org.seeds.anrgamelogger.model.IdentityList;

import java.util.ArrayList;

public class PlayerSubPresenter extends SubPresenter{

    AddGamePlayerView view;

    //ToDo: Change to title id and convert??
    public PlayerSubPresenter(Activity activity, AddGamePlayerView view, String title, ArrayList<String> playerList){
        super(activity,  title);
        this.view = view;
        this.view.setUpNameAutoComplete(playerList);
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

    public PlayerViewData createPlayer(){

        return  new PlayerViewData(
                view.getIdentitiesName(),
                view.getPlayerName(),
                view.getDeckName(),
                Integer.toString(1),
                getTitle()
        );
    }

}
