package org.seeds.anrgamelogger.addgame;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.seeds.anrgamelogger.R;

public class CreateLoggedGameActivity extends AppCompatActivity {

    private static final String LOG_TAG = CreateLoggedGameActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_logged_game_player_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




    }


    private void fillSpinners(){
        //get data
        //add to adaptors
        //add adaptors to to spinners. Need to?


    }


    public class GetIdentityData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            Log.v(LOG_TAG, "Started Proccessing Data");
            Context context = getApplicationContext();

            Log.v(LOG_TAG, "Finished Proccessing Data");


            return null;
        }

        @Override
        protected void onPostExecute(String s) {

        }
    }




}
