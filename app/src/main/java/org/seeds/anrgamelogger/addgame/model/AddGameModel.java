package org.seeds.anrgamelogger.addgame.model;

import android.app.Activity;
import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;

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
import org.seeds.anrgamelogger.database.buisnessobjects.Identity;


/**
 * Created by user on 08/12/2017.
 */

public class AddGameModel {

    private static final String LOG_TAG = AddGameModel.class.getSimpleName();
    private Activity activity;
    private StorIOContentResolver storIOContentResolver;
    private DatabaseModel databaseModel;

    public AddGameModel(Activity activityIn, StorIOContentResolver storIOContentResolverIn, DatabaseModel databaseModelIn){
        activity = activityIn;
        storIOContentResolver = storIOContentResolverIn;
        databaseModel = databaseModelIn;
    }

//public ArrayList<String> getListOfIdentitesNames(int side){
//
//    ArrayList<String> ret = new ArrayList<String>();
//
//    String sideName = activity.getString(side);
//
//    Cursor queryResult = storIOContentResolver
//        .get()
//        .cursor()
//        .withQuery(Query.builder()
//            .uri(IdentitiesContract.URI_TABLE)
//            .where(IdentitiesContract.IdentitiesColumns.IDENTITY_SIDE + " = '" + sideName + "'")
//                .sortOrder(IdentitiesColumns.IDENTITY_FACTION + " asc")
//            .build())
//        .prepare()
//        .executeAsBlocking();
//
//    if (queryResult != null) {
//        if (queryResult.moveToFirst()) {
//            do {
//                ret.add(queryResult.getString(queryResult.getColumnIndex(IdentitiesColumns.IDENTITY_NAME)));
//            }while (queryResult.moveToNext());
//            queryResult.close();
//        }
//    }
//    return  ret;
//}
//
//    public ArrayList<byte[]> getListOfIdentitesImages(int side) {
//        ArrayList<byte[]> ret = new ArrayList<>();
//
//        String sideName = activity.getString(side);
//
//        Cursor queryResult = storIOContentResolver
//                .get()
//                .cursor()
//                .withQuery(Query.builder()
//                        .uri(IdentitiesContract.URI_TABLE)
//                        .where(IdentitiesContract.IdentitiesColumns.IDENTITY_SIDE + " = '" + sideName + "'")
//                        .sortOrder(IdentitiesColumns.IDENTITY_FACTION + " asc")
//                        .build())
//                .prepare()
//                .executeAsBlocking();
//
//        if (queryResult != null) {
//            if (queryResult.moveToFirst()) {
//                do {
//                    ret.add(queryResult.getBlob(queryResult.getColumnIndex(IdentitiesColumns.IMAGE_BIT_ARRAY)));
//                }while (queryResult.moveToNext());
//                queryResult.close();
//            }
//        }
//        return  ret;
//
//    }

    public String getSide(){
        return activity.getIntent().getStringExtra(AddGameActivity.SIDE);
    }


    public List<Identity> getListOfIdenties(){

        return databaseModel.getAllIdentities();
//        List<Card> ret;
//        String sideName = activity.getString(side);
//
//        ret = storIOContentResolver
//            .get()
//            .listOfObjects(Card.class)
//            .withQuery(Query.builder()
//                .uri(IdentitiesContract.URI_TABLE)
//                .where(IdentitiesContract.IdentitiesColumns.IDENTITY_SIDE + " = '" + sideName + "'")
//                .sortOrder(IdentitiesColumns.IDENTITY_FACTION + " asc")
//                .build())
//            .prepare()
//            .executeAsBlocking();
//
//        return ret;
    }

    public Identity getIdentity(String identityName){
        return databaseModel.getIdentity(identityName);
    }

    public ArrayList<String> getPlayerList() {
        List<Player> temp = databaseModel.getAllPlayers();
        ArrayList<String> ret = new ArrayList<>();
        for(Player p : temp){
            ret.add(p.getName());
        }
       return ret;
    }

    public Player getPlayer(String playerName){
        return databaseModel.getPlayer(playerName);
    }

    public Location getLocation(String locationName){
        return databaseModel.getLocation(locationName);
    }

    public Deck getDeck(String deckName, String deckVersion, int identiyNo){
        return databaseModel.getDeck(deckName, deckVersion, identiyNo);
    }

    public void insertNewLocation(Location loc){
        databaseModel.insertLocation(loc);
    }

    public void insertNewDeck(Deck deck) {
        databaseModel.insertDeck(deck);
    }

    public void insertPlayer(Player player) {
        databaseModel.insertPlayer(player);
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
        databaseModel.insertLoggedGameN(lgo,lgpo, lgpt);
    }

    //Fix Overview to work out winner (and which player it is) etc etc

    /*
    1: Get MAX Logged Game GAME_ID
    2: Create LoggedGameOverview using PlayerOne Data
    3: Create LoggedGameOverview using PlayerTwo Data
     */


}
