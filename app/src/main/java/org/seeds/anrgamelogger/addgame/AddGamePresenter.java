package org.seeds.anrgamelogger.addgame;

import android.util.Log;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import org.seeds.anrgamelogger.addgame.model.AddGameModel;
import org.seeds.anrgamelogger.addgame.views.AddGameCorpView;
import org.seeds.anrgamelogger.addgame.views.AddGameOverviewView;
import org.seeds.anrgamelogger.addgame.views.AddGamePlayerView;
import org.seeds.anrgamelogger.addgame.views.AddGameRunnerView;
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

    private AddGameCorpView corpPlayerView;
    private AddGameRunnerView runnerPlayerView;
    private AddGameOverviewView overviewView;

    private final CompositeDisposable compositeSubscription = new CompositeDisposable();

    public AddGamePresenter(AddGameView view, AddGameModel model, AddGameRunnerView runnerPlayerView, AddGameCorpView corpPlayerView, AddGameOverviewView overviewView){
        this.view = view;
        this.model = model;
        this.overviewView = overviewView;
        this.runnerPlayerView = runnerPlayerView;
        this.corpPlayerView = corpPlayerView;
    }

    public void onCreate() {

        ArrayList<String> playerList = model.getPlayerList();
        ArrayList<String> deckList = model.getDeckList();
        ArrayList<String> locationList = model.getLocationList();

        IdentityList idList = new IdentityList(model.getListOfIdenties());

        runnerPlayerView.setTitle(ANRLoggerApplication.RUNNER_SIDE_IDENTIFIER);
        runnerPlayerView.setIdApadters(idList.getOneSidedList(ANRLoggerApplication.RUNNER_SIDE_IDENTIFIER));
        runnerPlayerView.setUpNameAutoComplete(playerList);
        runnerPlayerView.setUpDeckNameAutoComplete(deckList);

        corpPlayerView.setTitle(ANRLoggerApplication.CORP_SIDE_IDENTIFIER);
        corpPlayerView.setIdApadters(idList.getOneSidedList(ANRLoggerApplication.CORP_SIDE_IDENTIFIER));
        corpPlayerView.setUpNameAutoComplete(playerList);
        corpPlayerView.setUpDeckNameAutoComplete(deckList);

        overviewView.setUpLocationAutoComplete(locationList);
        overviewView.setTitle("Overview");

        if (model.getSide() == ANRLoggerApplication.CORP_SIDE_IDENTIFIER){
          view.setUpPagerViews(corpPlayerView);
          view.setUpPagerViews(runnerPlayerView);
        }else {
          view.setUpPagerViews(runnerPlayerView);
          view.setUpPagerViews(corpPlayerView);
      }
        
        view.setUpPagerViews(overviewView);
        view.startPageViewer();
        compositeSubscription.add(observerSave());
        compositeSubscription.add(dateSelected());
    }

    public Disposable observerSave(){
        return overviewView.save()
                .subscribe( a ->
                    addGame()
                );
    }

    public Disposable dateSelected(){
        Log.d(LOG_TAG,"Date has been selected. Telling to change Date");
        return overviewView.obvsAlertDateSelected()
                .subscribe(a -> overviewView.setDate());
    }

    private void addGame() {

//        LoggedGamePlayer pOneData = view.getPlayerOne();
//        LoggedGamePlayer pTwoData = view.getPlayerTwo();
//        LoggedGameOverview ovData = view.getGameOverview();


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

//    if(pOneData.getPlayer_name().matches("") || pTwoData.getPlayer_name().matches("")) {
//        view.showMessage(
//            "Player One is: " + pOneData.getPlayer_name() + " and Player Two is: " + pTwoData
//                .getPlayer_name() + " Either Player name can be empty");
////TODO: Add check that win type is not score but ethier players scores are less then 7
//    }else {
//
//    }
    }

    public void onDestroy(){
        compositeSubscription.dispose();
    }

}
