package org.seeds.anrgamelogger.addgame;

import android.util.Log;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import org.seeds.anrgamelogger.application.ANRLoggerApplication;
import org.seeds.anrgamelogger.buisnessobjects.Deck;
import org.seeds.anrgamelogger.buisnessobjects.Player;
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

        Log.d(LOG_TAG, "Player List is " + playerList.toString());

        view.setUpPagerViews(viewTitleList, playerList);
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


        PlayerViewData pOneData = view.getPlayerOne();
        PlayerViewData pTwoData = view.getPlayerTwo();

        Player playerOne = model.getPlayer(pOneData.getPlayerNames());
        if(playerOne == null){
            playerOne = new Player(pOneData.getPlayerNames());
        }



        Deck pOneDeck = new Deck(pOneData.getDeckName());
        Deck pTwoDeck = new Deck(pTwoData.getDeckName());

        Player playerTwo = new Player(pTwoData.getPlayerNames());

        view.getGameOverview();


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
