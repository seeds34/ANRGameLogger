package org.seeds.anrgamelogger.database.datacreater;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Tomas Seymour-Turner on 15/05/2017.
 */

public class GetRawData {

    private static final String LOG_TAG = GetRawData.class.getSimpleName();







    public static String GetRawDataFromFile(Context contextIn, int fileRef){

        String ret = "";

        try{

            InputStream inputStream = contextIn.getResources().openRawResource(fileRef);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e(LOG_TAG, "File Not Found Exception: " + e.toString());
        } catch (IOException e) {
            Log.e(LOG_TAG, "Can Not Read File: " + e.toString());
        }

        return ret;
        }

//    public static String GetRawDataFromSite(String urlIn){
//
//       // urlIn = "https://netrunnerdb.com/api/2.0/public/cards";
//
////        HttpURLConnection urlConnection;
////        BufferedReader reader;
////
////        String ret = "";
////
////        OkHttpClient client = new OkHttpClient();
////
////
////
////        final Gson gson = new Gson();
////
////        Request request = new Request.Builder()
////            .url(urlIn)
////            .build();
////
////
////        client.newCall(request).enqueue(new Callback() {
////
////                     @Override public void onFailure(Call call, IOException e) {
////                    e.printStackTrace();
////                }
////                                            // Parse response using gson deserializer
////                                            @Override
////                                            public void onResponse(Call call, final Response response) throws IOException {
////                                                // Process the data on the worker thread
////                                                Identity ident = gson.fromJson(response.body().charStream(), Identity.class);
////                                                // Access deserialized user object here
////                                            }
////            });
//
////
////        try {
////            Request request = new Request.Builder()
////                .url(urlIn)
////                .build();
////
////            client.newCall(request).enqueue(new Callback() {
////                @Override public void onFailure(Call call, IOException e) {
////                    e.printStackTrace();
////                }
////
////                @Override public void onResponse(Call call, Response response) throws IOException {
////
////                    ret = response.body().string();
////
////
////
////
////                });
//
//
//
//
//
//
//
//
////        }catch (IOException e){
////            Log.e(LOG_TAG, "Can Not Read File: " + e.toString());
////        }
//
//
//
////
////        try{
////
////            URL url = new URL(urlIn);
////            urlConnection =(HttpURLConnection) url.openConnection();
////            urlConnection.setRequestMethod("GET");
////            urlConnection.connect();
////
////            InputStream inputStream = urlConnection.getInputStream();
////
////            if ( inputStream != null ) {
////                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
////                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
////                String receiveString = "";
////                StringBuilder stringBuilder = new StringBuilder();
////
////                while ( (receiveString = bufferedReader.readLine()) != null ) {
////                    stringBuilder.append(receiveString);
////                }
////
////                inputStream.close();
////                ret = stringBuilder.toString();
////            }
////            urlConnection.disconnect();
////        }
////        catch (FileNotFoundException e) {
////            Log.e(LOG_TAG, "File Not Found Exception: " + e.toString());
////        } catch (IOException e) {
////            Log.e(LOG_TAG, "Can Not Read File: " + e.toString());
////        }
//
//
//        return ret;
//    }

}
