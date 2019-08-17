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
import org.seeds.anrgamelogger.application.PredefinedGame;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameFlat;
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
    private  int GAMENO;
    private final PredefinedGame passedGameValue;

    public AddGamePresenter(AddGameView view, AddGameModel model, AddGameRunnerView runnerPlayerView, AddGameCorpView corpPlayerView, AddGameOverviewView overviewView){
        this.view = view;
        this.model = model;
        this.overviewView = overviewView;
        this.runnerPlayerView = runnerPlayerView;
        this.corpPlayerView = corpPlayerView;
        passedGameValue = model.getPredefineGameValue();
        //GAMENO = model.getNextGameNo();
    }

    public void onCreate() {

        cdl = new CustomDPDLister();
        //view.setTitle(String.valueOf(GAMENO));

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

//        if (model.getSide() == ANRLoggerApplication.CORP_SIDE_IDENTIFIER){
//          view.setUpPagerViews(corpPlayerView);
//          view.setUpPagerViews(runnerPlayerView);
//        }else {
//
//      }

        view.setUpPagerViews(runnerPlayerView);
        view.setUpPagerViews(corpPlayerView);

        view.setUpPagerViews(overviewView);
        view.startPageViewer();
        compositeSubscription.add(observerSave());
        compositeSubscription.add(dateSelected());

        //PredefinedGame passedGameValue = model.getPredefineGameValue();

        if(passedGameValue == PredefinedGame.NEW){
            setUpNewGame();
        }
        else if(passedGameValue == PredefinedGame.PARTIAL){
            setUpPartailGame();
        }else if(passedGameValue == PredefinedGame.EDIT){
            setUpFullGame();
        }

    }

    private void setUpNewGame() {
        GAMENO = model.getNextGameNo();
    }

    private void setUpPartailGame(){

        LoggedGameFlat lgf = model.getPassedInGame();
        if(lgf != null){

            Log.d(LOG_TAG, ".setUpFullGame() : Passing Log: " + lgf.toString());

            //GAMENO = lgf.getGameID();
            GAMENO = model.getNextGameNo();

            runnerPlayerView.setPlayerName(lgf.getpO_Name());
            runnerPlayerView.setDeckName(lgf.getpO_DeckName());
            runnerPlayerView.setIdentity(lgf.getpO_Identity());

            corpPlayerView.setPlayerName(lgf.getpT_Name());
            corpPlayerView.setDeckName(lgf.getpT_DeckName());
            corpPlayerView.setIdentity(lgf.getpT_Identity());

            overviewView.setPlayedDate(lgf.getPlayedDate());
            overviewView.setLocation(lgf.getLocationName());
        }
    }

    //NOTE: For Edit Game
    private void setUpFullGame(){

        LoggedGameFlat lgf = model.getPassedInGame();
        if(lgf != null){

            Log.d(LOG_TAG, ".setUpFullGame() : Passing Log: " + lgf.toString());

            GAMENO = lgf.getGameID();

//TODO: Still need to fix version and date
            runnerPlayerView.setPlayerName(lgf.getpO_Name());
            runnerPlayerView.setDeckName(lgf.getpO_DeckName());
            runnerPlayerView.setScore(lgf.getpO_Score());
            runnerPlayerView.setDeckVersion(lgf.getpO_DeckVer());
            runnerPlayerView.setIdentity(lgf.getpO_Identity());

            corpPlayerView.setPlayerName(lgf.getpT_Name());
            corpPlayerView.setDeckName(lgf.getpT_DeckName());
            corpPlayerView.setScore(lgf.getpT_Score());
            runnerPlayerView.setDeckVersion(lgf.getpO_DeckVer());
            corpPlayerView.setIdentity(lgf.getpT_Identity());

            overviewView.setPlayedDate(lgf.getPlayedDate());
            overviewView.setLocation(lgf.getLocationName());
            overviewView.setWinningSide(lgf.getWinningSide());
            overviewView.setWinType(lgf.getWinType());
        }
    }

    public Disposable observerSave(){

        //TODO: need to figure out if its an update or new. Feel its the presenters job
        Disposable ret;

        if(passedGameValue == PredefinedGame.NEW){
            ret = view.save()
                    .subscribe( a ->
                            createGame()
                    );
        }else{
            ret = view.save()
                    .subscribe( a ->
                            editGame()
                    );
        }

        return ret;
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

    private void createGame() {

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

        LoggedGameValidator v =  new LoggedGameValidator();

        if(v.validateGame(lgo,lgpr, lgpc)) {
            model.saveNewGame(lgo, lgpr, lgpc);
            view.displayGameLoggedMessagge(String.valueOf(GAMENO));
            model.finishActivity();
        }else {
            view.displayMessage(v.getValidationMessage());
        }

    }

    private void editGame() {


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

        LoggedGameValidator v =  new LoggedGameValidator();

        if(v.validateGame(lgo,lgpr, lgpc)) {
            model.saveEdittedGame(lgo, lgpr, lgpc);
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
