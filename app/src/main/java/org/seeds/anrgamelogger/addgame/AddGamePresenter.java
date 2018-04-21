package org.seeds.anrgamelogger.addgame;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import org.seeds.anrgamelogger.application.ANRLoggerApplication;
import org.seeds.anrgamelogger.model.IdentityList;

/**
 * Created by user on 08/12/2017.
 */

public class AddGamePresenter {

    private static final String LOG_TAG = AddGamePresenter.class.getSimpleName();

    private AddGameModel model;
    private AddGameView view;

    private final CompositeDisposable compositeSubscription = new CompositeDisposable();


    public AddGamePresenter(AddGameView view, AddGameModel model){
        this.view = view;
        this.model = model;
    }

    public void onCreate() {
        setUpView();
        setViewData();
        view.startPA();

        compositeSubscription.add(observeSaveGame());
        //setUpDeafults();

    }

    public void setIdentityData(){

        IdentityList idList = new IdentityList(model.getListOfIdenties());

        view.setIDSelecters(idList);
        //TODO: Setup Name Spinner
    }

    public void setViewData(){
        setIdentityData();
        //setIdentityData(R.string.title_runner);
        //setIdentityData(R.string.title_corp);
    }

    public void setUpView(){

        ArrayList<String> viewList = new ArrayList<>();

        if (model.getSide() == ANRLoggerApplication.CORP_SIDE_IDENTIFIER){
            viewList.add(ANRLoggerApplication.CORP_SIDE_IDENTIFIER);
            viewList.add(ANRLoggerApplication.RUNNER_SIDE_IDENTIFIER);
        }else {
            viewList.add(ANRLoggerApplication.CORP_SIDE_IDENTIFIER);
            viewList.add(ANRLoggerApplication.RUNNER_SIDE_IDENTIFIER);
        }
        viewList.add("Overview");
        view.setUpPages(viewList);
    }

    public void setUpDeafults(){

    }

    public Disposable observeSaveGame(){
        return view.saveGame().subscribe(
            a -> view.showMessage("Save Game")
        );
    }


    public void onDestroy(){
        compositeSubscription.dispose();
    }


}
