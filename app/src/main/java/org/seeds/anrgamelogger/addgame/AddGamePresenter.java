package org.seeds.anrgamelogger.addgame;

import java.util.ArrayList;
import org.seeds.anrgamelogger.R;

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
        setUpView();
        setViewData();
        view.startPA();
        //setUpDeafults();

    }


    public void setViewData(){

        view.setImageSpinner(R.string.title_corp, model.getListOfIdentitesImages(R.string.title_corp));
        view.setImageSpinner(R.string.title_corp, model.getListOfIdentitesImages(R.string.title_runner));

    }

    public void setUpView(){

        ArrayList<Integer> viewList = new ArrayList<>();

        if (model.getSide() == R.string.title_corp){
            viewList.add(R.string.title_corp);
            viewList.add(R.string.title_runner);
        }else {
            viewList.add(R.string.title_runner);
            viewList.add(R.string.title_corp);
        }

        viewList.add(R.string.title_overview);

        view.setUpPages(viewList);

    }

    public void setUpDeafults(){

    }



    public void onDestroy(){}


}
