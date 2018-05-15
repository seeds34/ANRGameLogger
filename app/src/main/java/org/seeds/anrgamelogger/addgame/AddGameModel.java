package org.seeds.anrgamelogger.addgame;

import android.app.Activity;
import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio3.contentresolver.operations.put.PutResult;

import java.util.ArrayList;
import java.util.List;
import org.seeds.anrgamelogger.application.DatabaseModel;
import org.seeds.anrgamelogger.buisnessobjects.Deck;
import org.seeds.anrgamelogger.buisnessobjects.Identity;
import org.seeds.anrgamelogger.buisnessobjects.Location;
import org.seeds.anrgamelogger.buisnessobjects.Player;


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

    //Fix Overview to work out winner (and which player it is) etc etc

    /*
    1: Get MAX Logged Game GAME_ID
    2: Create LoggedGame using PlayerOne Data
    3: Create LoggedGame using PlayerTwo Data
     */


}
