package org.seeds.anrgamelogger.addgame.subpresenters;

import android.app.Activity;

import org.seeds.anrgamelogger.addgame.views.AddGameBaseView;
import org.seeds.anrgamelogger.model.IdentityList;

public class PlayerSubPresenter extends SubPresenter{

    //ToDo: Change to title id and convert??
    public PlayerSubPresenter(Activity activity, AddGameBaseView view, String title){
        super(activity, view, title);
    }

    @Override
    public void onCreateView(){ }

    @Override
    public void setUpIdentitySpiner(IdentityList idList){
        view.setIdApadters(idList);
    }

}
