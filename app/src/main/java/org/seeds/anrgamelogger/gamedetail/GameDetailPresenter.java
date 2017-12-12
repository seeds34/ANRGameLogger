package org.seeds.anrgamelogger.gamedetail;

/**
 * Created by Tomas Seymour-Turner on 19/11/2017.
 */

public class GameDetailPresenter {

    private  GameDetailModel model;
    private GameDetailView view;

    public GameDetailPresenter(GameDetailView view, GameDetailModel model){
        this.view = view;
        this.model = model;
    }

    public void onCreate(){

        view.setData(model.getLoggedGame());
        view.setUpPages();
        view.setTitle();
       // view.setLLGData(model.getLoggedGame());
    }

    public void onDestroy(){

    }
}
