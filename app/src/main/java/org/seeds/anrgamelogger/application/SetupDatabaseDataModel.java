package org.seeds.anrgamelogger.application;


import android.util.Log;
import com.pushtorefresh.storio3.sqlite.operations.put.PutResult;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import okhttp3.Response;
import org.seeds.anrgamelogger.database.DatabaseModel;
import org.seeds.anrgamelogger.database.buisnessobjects.CardImage;
import org.seeds.anrgamelogger.database.buisnessobjects.Identity;
import org.seeds.anrgamelogger.model.CardList;
import org.seeds.anrgamelogger.network.Card;
import org.seeds.anrgamelogger.network.NetworkModel;

/**
 * Created by Tomas Seymour-Turner on 09/01/2018.
 */

public class SetupDatabaseDataModel {

    private static final String LOG_TAG = SetupDatabaseDataModel.class.getSimpleName();
    private DatabaseModel databaseModel;
    private NetworkModel networkModel;
    private final String CGDB_BASE_URL = "http://www.cardgamedb.com/forums/uploads/an/med_ADN";
    private final String NRDB_IMAGE_URL = "https://netrunnerdb.com/card_image/";
    private final String IMAGE_FILE_EXT = ".png";

    public SetupDatabaseDataModel(DatabaseModel databaseModelIn, NetworkModel networkModelIn){
        databaseModel = databaseModelIn;
        networkModel = networkModelIn;

        Log.i(LOG_TAG,"Database Model is: " + databaseModel);
    }

//
//    public boolean haveAllIdentites() {
//        final boolean ret = false;
//
//        int dbIdentityListSize = databaseModel.getAllIdentities().size();
//
//        networkModel.getNRDBCardList()
//                .
//
//        return ret;
//    }

    public void populateIdentitiesTable() {

        networkModel.getNRDBCardList()
        .subscribe(i -> insertIdentityData(i));
    }

    public void insertIdentityData(CardList identitiesIn) {

        List<Card> ids = identitiesIn.getCards();

        Log.d(LOG_TAG, "ID List length: " + ids.size());

        Log.d(LOG_TAG,"Inserting IDs");
        for (Card i : ids) {
            Log.d(LOG_TAG, "Type: " + i.getType_code());
            if (i.getType_code().equals("identity")) {
                Log.d(LOG_TAG, i.toString());
                Identity ident = new Identity(i);
                databaseModel.insertIdentity(ident);
            }
        }
    }

    public void insertIdentityImages() {

        Log.d(LOG_TAG,"(1) Add Images to IDs");
        List<Identity> cardImageList = databaseModel.getAllIdentities();
        for(Identity c : cardImageList){
            Log.d(LOG_TAG, "(2) " +c.toString());
            downloadImageFromNRDB(c.getPack_code(), c.getCode(), c.getPos());
        }

    }

    //TODO: Please Tidy, this is horrid and wrong. Feel this should be simipler and not needing a CardImage Object. Should just add and UPDATE Identity
    public void downloadImageFromNRDB(String nrdb_pack_code, String card_code, String pos) {
        CardImage cardImage = new CardImage(card_code);
        cardImage.setImageUrl(NRDB_IMAGE_URL + card_code + IMAGE_FILE_EXT);

        Log.d(LOG_TAG, "(3)URL Is: " + cardImage.getImageUrl());

        Observable<Response> data = networkModel.getData(cardImage.getImageUrl());

        Log.d(LOG_TAG,"(4)Observable is " + ((data == null)?"Null":"Not Null"));

        data.subscribeOn(Schedulers.single())
            //.observeOn(AndroidSchedulers.mainThread())
            .subscribe(r -> {
                InputStream is = r.body().byteStream();

                int index;
                byte[] byteChunk = new byte[1024];

                ByteArrayOutputStream imageByteArrayOutputStream = new ByteArrayOutputStream();
                if (is != null) {
                  try {
                    while ((index = is.read(byteChunk)) > 0) {
                      imageByteArrayOutputStream.write(byteChunk, 0, index);
                    }
                  } catch (IOException e) {
                    e.printStackTrace();
                  }
                }

                //TODO: This feels like a hack
                String startOfSteam =  imageByteArrayOutputStream.toString().substring(0,15);
                if(startOfSteam.matches("<!DOCTYPE html>")){
                  Log.d(LOG_TAG, "(4.1)Getting Image form CGDB");


                  networkModel.getNRDBPackList()
                      .subscribeOn(Schedulers.single())
                      //.observeOn(AndroidSchedulers.mainThread())
                      .subscribe(dpl -> {
                            String url = CGDB_BASE_URL
                                + dpl.getMapping(nrdb_pack_code)
                                + "_" + pos
                                + IMAGE_FILE_EXT;
                          cardImage.setImageUrl(url);

                        Log.d(LOG_TAG, "(4.2)URL Is: " + cardImage.getImageUrl());

                        networkModel.getData(cardImage.getImageUrl())
                            .subscribeOn(Schedulers.single())
                            //.observeOn(AndroidSchedulers.mainThread())
                            .subscribe(a -> {
                              InputStream isa = a.body().byteStream();
                              cardImage.setImageByteArray(isa);


                              PutResult pr = databaseModel.insertIdentitieImage(cardImage);
                              Log.d(LOG_TAG, "(5)Put Result for adding image for " + cardImage.getCode() + " is " + pr.wasUpdated() + ". Count of rows updated was " + pr.numberOfRowsUpdated());

                            },e -> Log.d(LOG_TAG,e.getMessage()));
                          },e -> Log.d(LOG_TAG,e.getMessage())
                      );





                }else{
                  cardImage.setImageByteArray(imageByteArrayOutputStream.toByteArray());

                  PutResult pr = databaseModel.insertIdentitieImage(cardImage);
                  Log.d(LOG_TAG, "(6)Put Result for adding image for " + cardImage.getCode() + " is " + pr.wasUpdated() + ". Count of rows updated was " + pr.numberOfRowsUpdated());
                }

            }, e -> Log.d(LOG_TAG,e.getMessage()));

        Log.d(LOG_TAG, "(7)Card Image is for: " + cardImage.getCode());
        Log.d(LOG_TAG, "(8)Image Array is: " + ((cardImage.getImageByteArray() == null)?"Null":"Not Null") );
    }

//    public CardImage getCGDBCardImage(String nrdb_pack_code, String card_code){
//        CardImage ret = new CardImage(card_code);
//        networkModel.getNRDBPackList()
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
//        networkModel.getData(ret.getImageUrl())
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










