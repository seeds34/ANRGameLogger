package org.seeds.anrgamelogger.database.datacreater;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import com.pushtorefresh.storio3.contentresolver.ContentResolverTypeMapping;
import com.pushtorefresh.storio3.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio3.contentresolver.impl.DefaultStorIOContentResolver;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;
import org.seeds.anrgamelogger.model.Card;

/**
 * Created by Tomas Seymour-Turner on 20/05/2017.
 */

public class PopulateIdentitiesData {

    private static final String LOG_TAG = PopulateIdentitiesData.class.getSimpleName();

    private ContentResolver contentResolver;
    private final String NRDB_CARD_LIST_API_URL = "https://netrunnerdb.com/api/2.0/public/cards";
    private final String NRDB_IMAGE_URL = "https://netrunnerdb.com/card_image/";
    private final String IMAGE_FILE_EXT = ".png";
    private StorIOContentResolver storIOContentResolver;


//    public  PopulateIdentitiesData(Context contextIn){
//        contentResolver = contextIn.getContentResolver();
//        storIOContentResolver = DefaultStorIOContentResolver.builder()
//                .contentResolver(contentResolver)
//                .build();
//    }

//    public  PopulateIdentitiesData(Activity activityIn){
//        contentResolver = activityIn.getContentResolver();
//
//
//        storIOContentResolver = DefaultStorIOContentResolver.builder()
//                .contentResolver(contentResolver)
//            .addTypeMapping(Card.class,ContentResolverTypeMapping.<Card>builder()
//            .putResolver(new IdentityStorIOContentResolverPutResolver())
//                .getResolver(new IdentityStorIOContentResolverGetResolver())
//                .deleteResolver(new IdentityStorIOContentResolverDeleteResolver())
//                .build()
//            )
//                .build();
//
//
//    }
//
//    public boolean isIdentitiesTableEmpty(){
//        boolean ret = true;
//
//        Cursor queryResult = contentResolver.query(IdentitiesContract.URI_TABLE,null, null ,null,null);
//
//        if(queryResult != null && queryResult.getCount() > 0){
//            ret = false;
//        }
//
//        queryResult.close();
//
//        return  ret;
//    }
//
//
//
//
//    public void extractIdentitiesFromNRDB(){
//       // String data = GetRawData.GetRawDataFromSite(NRDB_CARD_LIST_API_URL);
//
//        try{
//            JSONObject jsonData = new JSONObject(data);
//            JSONArray itemArray = jsonData.getJSONArray("data");
//
//            ContentValues values;
//
//            for(int i = 1 ; i < itemArray.length() ; i++){
//                JSONObject card = itemArray.getJSONObject(i);
//
//                String type_code = card.getString("type_code");
//                if(type_code.equals("identity")){
//
//                    String code = card.getString("code");
//                    String side = card.getString("side_code");
//                    String faction = card.getString("faction_code");
//                    String name = card.getString("title");
//
//
//
//
//
//
//                    OkHttpClient client = new OkHttpClient();
//                    InputStream imageInputStream = null;
//
//                    try {
//                        Request request = new Request.Builder()
//                            .url(NRDB_IMAGE_URL + code + IMAGE_FILE_EXT)
//                            .build();
//
//                        Response response = client.newCall(request).execute();
//                        imageInputStream = response.body().byteStream();
//                    }catch (IOException e){
//                        Log.e(LOG_TAG, "Can Not Read File: " + e.toString());
//                    }
//
////                    HttpURLConnection urlConnection = null;
////
////                        URL url = new URL(NRDB_IMAGE_URL + code + IMAGE_FILE_EXT);
////                        urlConnection =(HttpURLConnection) url.openConnection();
////                        urlConnection.setRequestMethod("GET");
////                        urlConnection.connect();
//
//
//                        ByteArrayOutputStream imageByteArrayOutputStream = new ByteArrayOutputStream();
//                        int index;
//                        byte[] byteChunk = new byte[1024];
//
//                    if ( imageInputStream != null ) {
//
//
//                            while ( (index = imageInputStream.read(byteChunk)) > 0 ) {
//                                imageByteArrayOutputStream.write(byteChunk, 0, index);
//                            }
//
//                        imageInputStream.close();
//
//                        }
////                    values = new ContentValues();
////                    values.put(IdentitiesContract.IdentitiesColumns.IDENTITY_NAME, name);
////                    values.put(IdentitiesContract.IdentitiesColumns.IDENTITY_SIDE, side);
////                    values.put(IdentitiesContract.IdentitiesColumns.IDENTITY_FACTION, faction);
////                    values.put(IdentitiesContract.IdentitiesColumns.ROTATED_FLAG, "N");
////                    values.put(IdentitiesContract.IdentitiesColumns.NRDB_CODE, code);
////                    values.put(IdentitiesContract.IdentitiesColumns.IMAGE_BIT_ARRAY, imageByteArrayOutputStream.toByteArray());
//
//
//                    //contentResolver.insert(IdentitiesContract.URI_TABLE, values);
//
//                    Card iden = new Card(name,side,faction,"N", code, imageByteArrayOutputStream.toByteArray());
//
//                    storIOContentResolver.put()
//                        .object(iden)
//                        .prepare()
//                        .executeAsBlocking();
//
//
//                    Log.d(LOG_TAG, "Card: Name: " + name + " | Side: " + side +" | Faction: " + faction + " | Code: " + code);
////                    urlConnection.disconnect();
//                }
//
//            }
//        }catch(IOException e) {
//            e.printStackTrace();
//            Log.e(LOG_TAG,"IO Exception" + e.toString());
//        }
//        catch (JSONException e){
//            e.printStackTrace();
//            Log.e(LOG_TAG,"Error Proccessing JSON" + e.toString());
//        }
//
//    }

}
