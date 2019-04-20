package org.seeds.anrgamelogger.addgame;

import android.util.Log;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import org.seeds.anrgamelogger.addgame.model.AddGameModel;
import org.seeds.anrgamelogger.addgame.views.AddGameCorpView;
import org.seeds.anrgamelogger.addgame.views.AddGameOverviewView;
import org.seeds.anrgamelogger.addgame.views.AddGameRunnerView;
import org.seeds.anrgamelogger.addgame.views.AddGameView;
import org.seeds.anrgamelogger.application.ANRLoggerApplication;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameOverview;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGamePlayer;
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

    private CustomDPDLister cdl;
    private final int GAMENO;

    public AddGamePresenter(AddGameView view, AddGameModel model, AddGameRunnerView runnerPlayerView, AddGameCorpView corpPlayerView, AddGameOverviewView overviewView){
        this.view = view;
        this.model = model;
        this.overviewView = overviewView;
        this.runnerPlayerView = runnerPlayerView;
        this.corpPlayerView = corpPlayerView;
        GAMENO = model.getNextGameNo();
    }

    public void onCreate() {

        cdl = new CustomDPDLister();
        view.setTitle(String.valueOf(GAMENO));

        ArrayList<String> playerList = model.getPlayerList();
        ArrayList<String> deckList = model.getDeckList();
        ArrayList<String> locationList = model.getLocationList();

        IdentityList idList = new IdentityList(model.getListOfIdenties());

        runnerPlayerView.setSide(ANRLoggerApplication.RUNNER_SIDE_IDENTIFIER);
        runnerPlayerView.setIdApadters(idList.getOneSidedList(ANRLoggerApplication.RUNNER_SIDE_IDENTIFIER));
        runnerPlayerView.setUpNameAutoComplete(playerList);
        runnerPlayerView.setUpDeckNameAutoComplete(deckList);

        corpPlayerView.setSide(ANRLoggerApplication.CORP_SIDE_IDENTIFIER);
        corpPlayerView.setIdApadters(idList.getOneSidedList(ANRLoggerApplication.CORP_SIDE_IDENTIFIER));
        corpPlayerView.setUpNameAutoComplete(playerList);
        corpPlayerView.setUpDeckNameAutoComplete(deckList);

        overviewView.setUpLocationAutoComplete(locationList);
        overviewView.setTitle("Overview");
        overviewView.setListener(cdl);

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
        return cdl.alertDateSelected()
                .subscribe(
                        a -> overviewView.setDate(a),
                        Throwable::printStackTrace,
                        ()->Log.d(LOG_TAG,"dateSelect Complete")
                );
    }

    private void addGame() {

        //TODO: Need to think how to track who won a game better
        String ws = overviewView.getWiningSide().toLowerCase();

        LoggedGamePlayer lgpr = new LoggedGamePlayer(
            GAMENO,
            runnerPlayerView.getPlayerName(),
                runnerPlayerView.getDeckName(),
                runnerPlayerView.getIdentityName(),
                runnerPlayerView.getSide(),
                (ws.equals(ANRLoggerApplication.RUNNER_SIDE_IDENTIFIER))?"Y":"N",
                runnerPlayerView.getScore(),
            runnerPlayerView.getDeckVer()

        );


        LoggedGamePlayer lgpc = new LoggedGamePlayer(
            GAMENO,
            corpPlayerView.getPlayerName(),
            corpPlayerView.getDeckName(),
            corpPlayerView.getIdentityName(),
            corpPlayerView.getSide(),
            (ws.equals(ANRLoggerApplication.CORP_SIDE_IDENTIFIER))?"Y":"N",
            corpPlayerView.getScore(),
            corpPlayerView.getDeckVer()
        );

        LoggedGameOverview lgo = new LoggedGameOverview(
                overviewView.getLocation(),
                overviewView.getPlayedDate(),
                overviewView.getWinType(),
                GAMENO,
                overviewView.getWiningSide()
        );





/*
        LoggedGamePlayer lgpr = new LoggedGamePlayer(
            "Runner 1",
            "Runner Deck 1",
            "The Masque: Cyber General",
            ANRLoggerApplication.RUNNER_SIDE_IDENTIFIER,
            "N",
            6,
            GAMENO,
            "1"
        );


        LoggedGamePlayer lgpc = new LoggedGamePlayer(
            "Corp 1",
            "Corp Deck 1",
            "The Shadow: Pulling the Strings",
            ANRLoggerApplication.CORP_SIDE_IDENTIFIER,
            "Y",
            7,
            GAMENO,
            "1"
        );

        LoggedGameOverview lgo = new LoggedGameOverview(
            "Home",
            "11/04/2019",
            "Score",
            GAMENO,
            ANRLoggerApplication.CORP_SIDE_IDENTIFIER
        );
*/

        model.saveLoggedGame(lgo, lgpr, lgpc);

        view.displayGameLoggedMessagge(String.valueOf(GAMENO));

        model.finishActivity();

        //LoggedGamePlayer pOneData =;
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
