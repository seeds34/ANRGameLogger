package org.seeds.anrgamelogger.addgame.subpresenters;

import android.app.Activity;
import android.view.View;

import org.seeds.anrgamelogger.addgame.views.AddGameBaseView;
import org.seeds.anrgamelogger.addgame.views.AddGamePlayerView;
import org.seeds.anrgamelogger.model.IdentityList;
import org.seeds.anrgamelogger.model.Player;

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

    public Player createPlayer(){

        return  new Player(
                view.getPlayerName(),
                view.getDeckName(),
                view.getIdentitiesName()
        );
    }

}
