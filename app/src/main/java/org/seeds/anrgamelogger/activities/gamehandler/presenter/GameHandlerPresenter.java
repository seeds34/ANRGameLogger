package org.seeds.anrgamelogger.activities.gamehandler.presenter;

import android.util.Log;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import org.seeds.anrgamelogger.activities.gamehandler.model.GameHandlerModel;
import org.seeds.anrgamelogger.activities.gamehandler.views.GameHandlerCorpView;
import org.seeds.anrgamelogger.activities.gamehandler.views.GameHandlerOverviewView;
import org.seeds.anrgamelogger.activities.gamehandler.views.GameHandlerRunnerView;
import org.seeds.anrgamelogger.activities.gamehandler.views.GameHandlerView;
import org.seeds.anrgamelogger.application.ANRLoggerApplication;
import org.seeds.anrgamelogger.application.PredefinedGame;
import org.seeds.anrgamelogger.application.database.buisnessobjects.LoggedGameFlat;
import org.seeds.anrgamelogger.application.database.buisnessobjects.LoggedGameOverview;
import org.seeds.anrgamelogger.application.database.buisnessobjects.LoggedGamePlayer;
import org.seeds.anrgamelogger.application.model.IdentityList;
import org.seeds.anrgamelogger.application.model.LoggedGameValidator;


/**
 * Created by user on 08/12/2017.
 */

public class GameHandlerPresenter {

    private static final String LOG_TAG = GameHandlerPresenter.class.getSimpleName();

    private GameHandlerModel model;
    private GameHandlerView view;

    private GameHandlerCorpView corpPlayerView;
    private GameHandlerRunnerView runnerPlayerView;
    private GameHandlerOverviewView overviewView;

    private final CompositeDisposable compositeSubscription = new CompositeDisposable();

    private CustomDPDLister cdl;
    private int GAMENO;
    private final PredefinedGame passedGameValue;

    public GameHandlerPresenter(GameHandlerView view, GameHandlerModel model, GameHandlerRunnerView runnerPlayerView, GameHandlerCorpView corpPlayerView, GameHandlerOverviewView overviewView){
        this.view = view;
        this.model = model;
        this.overviewView = overviewView;
        this.runnerPlayerView = runnerPlayerView;
        this.corpPlayerView = corpPlayerView;
        passedGameValue = model.getPredefineGameValue();
    }

    public void onCreate() {

        cdl = new CustomDPDLister();

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

        view.setUpPagerViews(runnerPlayerView);
        view.setUpPagerViews(corpPlayerView);

        view.setUpPagerViews(overviewView);
        view.startPageViewer();
        compositeSubscription.add(observerSave());
        compositeSubscription.add(observeDateSelected());

        if(passedGameValue == PredefinedGame.NEW){
            GAMENO = model.getNextGameNo();
        }else if(passedGameValue == PredefinedGame.PARTIAL){
            GAMENO = model.getNextGameNo();
            setUpPartailGame();
        }else if(passedGameValue == PredefinedGame.EDIT){
            setUpFullGame();
        }

    }

    private void setUpPartailGame(){

        LoggedGameFlat lgf = model.getPassedInGame();
        if(lgf != null){

            Log.d(LOG_TAG, ".setUpFullGame() : Passing Log: " + lgf.toString());

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

    private void setUpFullGame(){
        setUpPartailGame();
        LoggedGameFlat lgf = model.getPassedInGame();
        if(lgf != null){
            Log.d(LOG_TAG, ".setUpFullGame() : Passing Log: " + lgf.toString());
            GAMENO = lgf.getGameID();

//TODO: Still need to fix version and date
            runnerPlayerView.setScore(lgf.getpO_Score());
            runnerPlayerView.setDeckVersion(lgf.getpO_DeckVer());

            corpPlayerView.setScore(lgf.getpT_Score());
            runnerPlayerView.setDeckVersion(lgf.getpO_DeckVer());

            overviewView.setWinningSide(lgf.getWinningSide());
            overviewView.setWinType(lgf.getWinType());
        }
    }

    private void saveGame(){

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

         LoggedGameValidator val =  new LoggedGameValidator();
         if(val.validateGame(lgo,lgpr, lgpc)) {
             if(passedGameValue == PredefinedGame.NEW || passedGameValue == PredefinedGame.PARTIAL){
                 model.saveNewGame(lgo,lgpr,lgpc);
             }else if(passedGameValue == PredefinedGame.EDIT){
                 model.saveEdittedGame(lgo, lgpr, lgpc);
             }
             view.displayGameLoggedMessagge(String.valueOf(GAMENO));
             model.finishActivity();
         }else {
             view.displayMessage(val.getValidationMessage());
         }

     }

    public Disposable observerSave(){
        return view.save()
                .subscribe( a ->
                    saveGame()
                );
    }

    public Disposable observeDateSelected(){
        Log.d(LOG_TAG,"Date has been selected. Telling to change Date");
        return cdl.alertDateSelected()
            .subscribe(
                a -> overviewView.setDate(a),
                Throwable::printStackTrace,
                ()->Log.d(LOG_TAG,"dateSelect Complete")
            );
    }

    public void onDestroy(){
        compositeSubscription.dispose();
    }

}
