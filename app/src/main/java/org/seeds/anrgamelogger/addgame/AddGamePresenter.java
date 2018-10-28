package org.seeds.anrgamelogger.addgame;

import android.util.Log;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import org.seeds.anrgamelogger.addgame.model.AddGameModel;
import org.seeds.anrgamelogger.addgame.views.AddGameView;
import org.seeds.anrgamelogger.application.ANRLoggerApplication;
import org.seeds.anrgamelogger.database.LoggedGameValidationList;
import org.seeds.anrgamelogger.database.buisnessobjects.*;
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

        ArrayList<String> viewTitleList = new ArrayList<>();

        if (model.getSide() == ANRLoggerApplication.CORP_SIDE_IDENTIFIER){
            viewTitleList.add(ANRLoggerApplication.CORP_SIDE_IDENTIFIER);
            viewTitleList.add(ANRLoggerApplication.RUNNER_SIDE_IDENTIFIER);
        }else {
            viewTitleList.add(ANRLoggerApplication.RUNNER_SIDE_IDENTIFIER);
            viewTitleList.add(ANRLoggerApplication.CORP_SIDE_IDENTIFIER);
        }

        ArrayList<String> playerList = model.getPlayerList();
        ArrayList<String> deckList = model.getDeckList();
        ArrayList<String> locationList = model.getLocationList();

        Log.d(LOG_TAG, "Player List is " + playerList.toString());

        view.setUpPagerViews(viewTitleList, playerList, deckList, locationList, model.getNextGameNo());
        IdentityList idList = new IdentityList(model.getListOfIdenties());
        view.setIDSelecters(idList);
        view.startPageViewer();

        compositeSubscription.add(observerSave());
    }

    public void setIdentityData(){
        IdentityList idList = new IdentityList(model.getListOfIdenties());
        view.setIDSelecters(idList);
        //TODO: Setup Name Spinner
    }

    public Disposable observerSave(){
        return view.save()
                .subscribe( a ->
                    addGame()
                );
    }

    private void addGame() {

        LoggedGamePlayer pOneData = view.getPlayerOne();
        LoggedGamePlayer pTwoData = view.getPlayerTwo();
        LoggedGameOverview ovData = view.getGameOverview();


        /*
        1: Sort Player 1 ID
        2: Sort Player 1 Deck
        3: Sort Player 2 ID
        4: Sort Player 3 Deck
        5: Sort Player 1
        6: Sort Player 2
        7: Sort Location
        8: Sort Player 1 Logged Game
        9: Sort Player 2 Logged Game

      ?Could Player/Deck?Identity ID be enterd by defult if player has selected from previuouse option?

      Varfiy fields for non null and anything else that can be checked without DB.
           Do all non DB validation before checking and inserting into DB
           Skip Locateal validatuon if realted ID has a number
      Ask model to get data or new id (insert into DB)

         */

    if(pOneData.getPlayer_name().matches("") || pTwoData.getPlayer_name().matches("")) {
        view.showMessage(
            "Player One is: " + pOneData.getPlayer_name() + " and Player Two is: " + pTwoData
                .getPlayer_name() + " Either Player name can be empty");
//TODO: Add check that win type is not score but ethier players scores are less then 7
    }else {

    }
    }

    public void setViewData(){
        setIdentityData();
        //setIdentityData(R.string.title_runner);
        //setIdentityData(R.string.title_corp);
    }

    public void setUpView(){

        ArrayList<String> viewTitleList = new ArrayList<>();

        if (model.getSide() == ANRLoggerApplication.CORP_SIDE_IDENTIFIER){
            viewTitleList.add(ANRLoggerApplication.CORP_SIDE_IDENTIFIER);
            viewTitleList.add(ANRLoggerApplication.RUNNER_SIDE_IDENTIFIER);
        }else {
            viewTitleList.add(ANRLoggerApplication.RUNNER_SIDE_IDENTIFIER);
            viewTitleList.add(ANRLoggerApplication.CORP_SIDE_IDENTIFIER);
        }
        viewTitleList.add("Overview");
//        view.setUpPagerViews(viewTitleList);
    }

    public void setUpDeafults(){ }

    public void onDestroy(){
        compositeSubscription.dispose();
    }

//    public Disposable observeSaveGame(){
//        return view.saveGame().subscribe(
//            a -> view.showMessage("Save Game")
//        );
//    }

}
