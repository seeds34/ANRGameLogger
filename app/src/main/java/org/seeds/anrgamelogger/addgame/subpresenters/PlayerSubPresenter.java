package org.seeds.anrgamelogger.addgame.subpresenters;

import android.app.Activity;

import org.seeds.anrgamelogger.addgame.views.AddGameBaseView;
import org.seeds.anrgamelogger.addgame.views.AddGamePlayerView;
import org.seeds.anrgamelogger.model.IdentityList;

import java.util.ArrayList;

public class PlayerSubPresenter extends SubPresenter{

    //ToDo: Change to title id and convert??
    public PlayerSubPresenter(Activity activity, AddGamePlayerView view, String title, ArrayList<String> playerList){
        super(activity, view, title);
        view.setUpNameAutoComplete(playerList);
    }

    @Override
    public void onCreateView(){ }

    @Override
    public void setUpIdentitySpiner(IdentityList idList){
        view.setIdApadters(idList);
    }

}
