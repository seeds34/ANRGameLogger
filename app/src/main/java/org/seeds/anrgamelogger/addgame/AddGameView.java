package org.seeds.anrgamelogger.addgame;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import org.seeds.anrgamelogger.R;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by user on 09/12/2017.
 */

public class AddGameView extends FrameLayout {

    public AddGameView(Activity activity){
        super(activity);
        inflate(getContext(), R.layout.view_addgame_playerone, this);

    }

    private void setSpiinerAdaptor(ArrayList<String> identitiesList){

        ArrayAdapter<String> idListAdaptor = new ArrayAdapter<String>(this.getContext(), R.layout.view_addgame_playerone, identitiesList);
        Spinner spin = (Spinner) findViewById(R.id.idSpinner);

        spin.setAdapter(idListAdaptor);
    }

//    public class GetIdentityData extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            Log.v(LOG_TAG, "Started Proccessing Data");
//            Context context = getApplicationContext();
//
//            Log.v(LOG_TAG, "Finished Proccessing Data");
//
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//
//        }
//    }




}
