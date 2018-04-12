package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import java.util.ArrayList;
import java.util.Map;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.addgame.AddGameIdentitesPageAdapter;

/**
 * Created by Tomas Seymour-Turner on 04/01/2018.
 */

public class AddGamePlayerView extends AddGameBaseView{

  private static final String LOG_TAG = AddGamePlayerView.class.getSimpleName();

  private final int TITLE;

  @BindView(R.id.identitiesSpinner)
  Spinner identitiesSpinner;

  @BindView(R.id.identitiesImageViewPager)
  ViewPager identitiesImageViewPager;

  private AddGameIdentitesPageAdapter identityImageViewAdapter;
  private ArrayAdapter identityNameArrayAdapter;

  private Activity activity;

  public AddGamePlayerView(Activity activity, int title){
    super(activity);
    this.activity = activity;
    inflate(getContext(), R.layout.view_addgame_player, this);
    ButterKnife.setDebug(true);
    ButterKnife.bind(this);
    this.TITLE = title;
  }

  public void onCreate(){}

  public int getTitle(){
    return TITLE;
  }

  @Override
  public void setIdentitiesImageViewPager(Map<String, byte[]> imageListIn){
    identityImageViewAdapter = new AddGameIdentitesPageAdapter(getContext(), imageListIn);
    identitiesImageViewPager.setAdapter(identityImageViewAdapter);
  }

  @Override
  public void setSpiinerAdaptor(ArrayList<String> identitiesList){

    identityNameArrayAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, identitiesList);
    identitiesSpinner.setAdapter(identityNameArrayAdapter);
  }

  @OnItemSelected(R.id.identitiesSpinner)
  public void identityNameSelected() {

      identitiesSpinner.getSelectedItem();


  }
}
