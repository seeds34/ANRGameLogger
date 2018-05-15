package org.seeds.anrgamelogger.addgame;

import android.util.Log;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import org.seeds.anrgamelogger.application.ANRLoggerApplication;
import org.seeds.anrgamelogger.buisnessobjects.Deck;
import org.seeds.anrgamelogger.buisnessobjects.Identity;
import org.seeds.anrgamelogger.buisnessobjects.Location;
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
        OverviewViewData ovData = view.getGameOverview();

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
         */

        Identity playerOneid = model.getIdentity(pOneData.getIdentityName());
        Identity playerTwoid = model.getIdentity(pTwoData.getIdentityName());

//Get Deck based on Name, ID and Version. If Name is Null user Player+ID+Version. Although Current Deck name can't be Null
        Deck pOneDeck = model.getDeck(pOneData.getDeckName(), pOneData.getDeckVersion(), playerOneid.getRowid());
        if(pOneDeck == null){
            model.insertNewDeck(new Deck(pOneData.getDeckName(), pOneData.getDeckVersion(), playerOneid.getRowid()));
            pOneDeck = model.getDeck(pOneData.getDeckName(), pOneData.getDeckVersion(), playerOneid.getRowid());
        }


        Deck pTwoDeck = model.getDeck(pTwoData.getDeckName(), pTwoData.getDeckVersion(), playerTwoid.getRowid());
        if(pTwoDeck == null){
            model.insertNewDeck(new Deck(pTwoData.getDeckName(), pTwoData.getDeckVersion(), playerTwoid.getRowid()));
            pTwoDeck = model.getDeck(pTwoData.getDeckName(), pTwoData.getDeckVersion(), playerTwoid.getRowid());
        }


        Player playerOne = model.getPlayer(pOneData.getPlayerNames());
        if(playerOne == null){
            model.insertPlayer(new Player(pOneData.getPlayerNames()));
            playerOne = model.getPlayer(pOneData.getPlayerNames());
        }

        Player playerTwo = model.getPlayer(pTwoData.getPlayerNames());
        if(playerTwo == null){
            model.insertPlayer(new Player(pTwoData.getPlayerNames()));
            playerTwo = model.getPlayer(pTwoData.getPlayerNames());
        }

        Location loc = model.getLocation(ovData.getLocation());
        if(loc == null){
            model.insertNewLocation(new Location(ovData.getLocation()));
            loc = model.getLocation(ovData.getLocation());
        }

        //Caused by: android.database.sqlite.SQLiteConstraintException: UNIQUE constraint failed: locations._id (code 1555)

        //How to sort null values
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
