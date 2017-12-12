package org.seeds.anrgamelogger.addgame;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import org.seeds.anrgamelogger.R;

import java.util.ArrayList;
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 09/12/2017.
 */

public class AddGameView extends FrameLayout {

    @BindView(R.id.identitiesSpinner)
    Spinner identitiesSpinner;

    @BindView(R.id.identitiesImageViewPager)
    ViewPager identitiesImageViewPager;

    private TabLayout tabLayout;

    public AddGameView(Activity activity){
        super(activity);
        inflate(getContext(), R.layout.view_addgame_playerone_v2, this);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Corp"));
        tabLayout.addTab(tabLayout.newTab().setText("Runner"));
        tabLayout.addTab(tabLayout.newTab().setText("Overview#"));
    }

    public void setIdentitiesImageViewPager(ArrayList<byte[]> imageListIn){
        identitiesImageViewPager.setAdapter(new AddGameIdentitesPageAdapter(getContext(), imageListIn));
    }

    public void setSpiinerAdaptor(ArrayList<String> identitiesList){
        ArrayAdapter<String> idListAdaptor = new ArrayAdapter<String>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, identitiesList);
        identitiesSpinner.setAdapter(idListAdaptor);
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
