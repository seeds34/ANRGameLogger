package org.seeds.anrgamelogger.addgame;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.model.Card;

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

    public void setIdentityData(int side){
        List<Card> idList = model.getListOfIdenties(side);
        ArrayList<String> idNameList = new ArrayList<>();
        Map<String, byte[]> idImageList = new LinkedHashMap<>();

        for (Card i : idList) {
            idNameList.add(i.getName());
            idImageList.put(i.getCode(), i.getImageByteArrayOutputStream());
        }

        view.setImageSpinner(side,idImageList);
        view.setIDNameSpinner(side, idNameList);
        //TODO: Setup Name Spinner
    }


    public void setViewData(){
        setIdentityData(R.string.title_runner);
        setIdentityData(R.string.title_corp);
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
