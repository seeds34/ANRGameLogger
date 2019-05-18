package org.seeds.anrgamelogger.addgame.model;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;
import org.seeds.anrgamelogger.addgame.AddGameActivity;
import org.seeds.anrgamelogger.database.DatabaseModel;
import org.seeds.anrgamelogger.database.buisnessobjects.Deck;
import org.seeds.anrgamelogger.database.buisnessobjects.Identity;
import org.seeds.anrgamelogger.database.buisnessobjects.Location;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameFlat;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameOverview;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGamePlayer;
import org.seeds.anrgamelogger.database.buisnessobjects.Player;


/**
 * Created by user on 08/12/2017.
 */

public class AddGameModel {

    private static final String LOG_TAG = AddGameModel.class.getSimpleName();
    private Activity activity;
    private DatabaseModel databaseModel;

    public AddGameModel(Activity activityIn, DatabaseModel databaseModelIn){
        activity = activityIn;
        databaseModel = databaseModelIn;
    }

//    public String getSide(){
//        return activity.getIntent().getStringExtra(AddGameActivity.SIDE);
//    }

    public List<Identity> getListOfIdentities(){

        return databaseModel.getAllIdentities();
    }


    public ArrayList<String> getPlayerList() {
        List<Player> temp = databaseModel.getAllPlayers();
        ArrayList<String> ret = new ArrayList<>();
        for(Player p : temp){
            ret.add(p.getName());
        }
       return ret;
    }

    public int getNextGameNo() {
        return databaseModel.getNextGameNo();
    }

    public ArrayList<String> getDeckList() {
        List<Deck> temp = databaseModel.getAllDecks();
        ArrayList<String> ret = new ArrayList<>();
        for(Deck d : temp){
            ret.add(d.getName());
        }
        return ret;
    }

    public ArrayList<String> getLocationList() {
        List<Location> temp = databaseModel.getAllLocations();
        ArrayList<String> ret = new ArrayList<>();
        for(Location l : temp){
            ret.add(l.getName());
        }
        return ret;
    }

    public void saveLoggedGame(LoggedGameOverview lgo, LoggedGamePlayer lgpo, LoggedGamePlayer lgpt) {
        databaseModel.insertLoggedGame(lgo,lgpo, lgpt);
    }

    public void finishActivity(){ activity.finish();
    }


    public boolean gameNoPassed() {
        boolean ret = false;
        if(activity.getIntent().getStringExtra(AddGameActivity.GAMENO) != null){
            ret = true;
        }
        return ret;
    }

    public LoggedGameFlat getPassedInGame() {
       return databaseModel.getLoggedGame(Integer.valueOf(activity.getIntent().getStringExtra(AddGameActivity.GAMENO)));
    }
}
