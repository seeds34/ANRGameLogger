package org.seeds.anrgamelogger.addgame;

/**
 * Created by user on 08/12/2017.
 */

public class AddGamePresenter {

    private AddGameModel model;
    private AddGameView view;

    public AddGamePresenter(AddGameView view, AddGameModel model){
        this.view = view;
        this.model = model;
    }

    public void onCreate(){
      //  view.setSpiinerAdaptor(model.getListOfIdentites());
    }

    public void onDestroy(){}


}
