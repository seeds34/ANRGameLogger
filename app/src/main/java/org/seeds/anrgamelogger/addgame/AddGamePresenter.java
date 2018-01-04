package org.seeds.anrgamelogger.addgame;

import java.util.ArrayList;
import org.seeds.anrgamelogger.addgame.views.AddGameEnum;

/**
 * Created by user on 08/12/2017.
 */

public class AddGamePresenter {

    private static final String LOG_TAG = AddGamePresenter.class.getSimpleName();

    private AddGameModel model;
    private AddGameView view;

    public AddGamePresenter(AddGameView view, AddGameModel model){
        this.view = view;
        this.model = model;
    }

    public void onCreate() {
        setViewData();
        setUpDeafults();;
        setUpView();
    }


    public void setViewData(){

//        view.setSpiinerAdaptor(model.getListOfIdentitesNames("Runner"));
//        view.setIdentitiesImageViewPager(model.getListOfIdentitesImages("Runner"));
    }

    public void setUpView(){

        ArrayList<Integer> viewList = new ArrayList<>();

        if (model.getSide() == AddGameEnum.ADDGAMECORP.getLayoutID()){
            viewList.add(AddGameEnum.ADDGAMECORP.getTitleID());
            viewList.add(AddGameEnum.ADDGAMERUNNER.getTitleID());
        }else {
            viewList.add(AddGameEnum.ADDGAMERUNNER.getTitleID());
            viewList.add(AddGameEnum.ADDGAMECORP.getTitleID());
        }

        viewList.add(AddGameEnum.ADDGAMEOVERVIEW.getTitleID());

        view.setUpPages(viewList);

    }

    public void setUpDeafults(){

    }



    public void onDestroy(){}


}
