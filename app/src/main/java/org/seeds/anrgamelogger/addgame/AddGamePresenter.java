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
import org.seeds.anrgamelogger.model.LoggedGameValidator;


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

        IdentityList idList = new IdentityList(model.getListOfIdentities());

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

        LoggedGameValidator v =  new LoggedGameValidator();

        if(v.validateGame(lgo,lgpr, lgpc)) {
            model.saveLoggedGame(lgo, lgpr, lgpc);
            view.displayGameLoggedMessagge(String.valueOf(GAMENO));
            model.finishActivity();
        }else {
            view.displayMessage(v.getValidationMessage());
        }

    }

    public void onDestroy(){
        compositeSubscription.dispose();
    }

}
