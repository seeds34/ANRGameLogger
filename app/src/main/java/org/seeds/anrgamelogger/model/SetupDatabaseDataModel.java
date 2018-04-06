package org.seeds.anrgamelogger.model;


import android.util.Log;
import com.pushtorefresh.storio3.contentresolver.operations.put.PutResult;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.io.InputStream;
import java.util.List;
import okhttp3.Response;
import org.seeds.anrgamelogger.application.DatabaseModel;
import org.seeds.anrgamelogger.application.NetworkModel;


/**
 * Created by Tomas Seymour-Turner on 09/01/2018.
 */

public class SetupDatabaseDataModel {

    private static final String LOG_TAG = SetupDatabaseDataModel.class.getSimpleName();
    private DatabaseModel databaseModel;
    private NetworkModel networkModel;
    private final String CGDB_BASE_URL = "http://www.cardgamedb.com/forums/uploads/an/ffg_adn";
    private final String NRDB_IMAGE_URL = "https://netrunnerdb.com/card_image/";
    private final String IMAGE_FILE_EXT = ".png";

    public SetupDatabaseDataModel(DatabaseModel databaseModelIn, NetworkModel networkModelIn){
        databaseModel = databaseModelIn;
        networkModel = networkModelIn;

        Log.i(LOG_TAG,"Database Model is: " + databaseModel);
    }


    public void populateIdentitiesTable() {

        networkModel.getNRDBCardList()
        .subscribe(i -> insertIdentityData(i));
    }


    public void insertIdentityData(CardList identitiesIn) {

        List<Card> ids = identitiesIn.getIdentities();

        Log.d(LOG_TAG,"Inserting IDs");
        for (Card i : ids) {
            Log.d(LOG_TAG, "Type: " + i.type_code);
            if (i.type_code.equals("identity")) {
                Log.d(LOG_TAG, i.toString());
                databaseModel.insertIdentity(i);
            }
        }
    }

    public void insertIdentityImages() {

        Log.d(LOG_TAG,"Add Images to IDs");
        List<Card> cardImageList = databaseModel.getIdentities();
        for(Card c : cardImageList){
            downloadImageFromNRDB(c.getCode());
        }

    }

    public void downloadImageFromNRDB(String card_code) {
        CardImage cardImage = new CardImage(card_code);
        cardImage.setImageUrl(NRDB_IMAGE_URL + card_code + IMAGE_FILE_EXT);

        //Observable.just(1,2,3,4,5).subscribe(n -> Log.d(LOG_TAG,"A Nummeber " + n));

        Log.d(LOG_TAG, "(1)URL Is: " + cardImage.getImageUrl());

        Observable<Response> data = networkModel.getData(cardImage.getImageUrl());

        Log.d(LOG_TAG,"(2)Observable is " + ((data == null)?"Null":"Not Null"));

        data
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(r -> {
                InputStream is = r.body().byteStream();
                cardImage.setImageByteArray(is);
                PutResult pr = databaseModel.insertIdentitieImage(cardImage);
                Log.d(LOG_TAG, "(3)Put Result for adding image for "
                    + cardImage.getCode()
                    + " is "
                    + pr.wasUpdated()
                    + ". Count of rows updated was "
                    + pr.numberOfRowsUpdated());
            }, e -> Log.d(LOG_TAG,e.getMessage()));

        Log.d(LOG_TAG, "(4)Card Image is for: " + cardImage.getCode());
        Log.d(LOG_TAG, "(5)Image Array is: " + ((cardImage.getImageByteArray() == null)?"Null":"Not Null") );
    }

//    public CardImage getCGDBCardImage(String nrdb_pack_code, String card_code){
//        CardImage ret = new CardImage(card_code);
//        getNRDBPackList()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(dpl -> {
//                    String url = CGDB_BASE_URL
//                        + dpl.getDataPackMap().get(nrdb_pack_code)
//                        + "_" +card_code
//                        + IMAGE_FILE_EXT;
//                    ret.setImageUrl(url);
//                },e -> Log.d(LOG_TAG,e.getMessage())
//            );
//
//        getData(ret.getImageUrl())
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(r -> {
//                InputStream is = r.body().byteStream();
//                ret.setImageByteArray(is);
//            },e -> Log.d(LOG_TAG,e.getMessage()));
//        Log.d(LOG_TAG, "Card Image is for: " + ret.getCode());
//        return ret;
//    }







}










