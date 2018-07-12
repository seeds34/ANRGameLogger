package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import butterknife.OnPageChange;
import io.reactivex.Observable;
import java.util.ArrayList;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.addgame.AddGameIdentitesPageAdapter;
import org.seeds.anrgamelogger.model.IdentityList;

/**
 * Created by Tomas Seymour-Turner on 04/01/2018.
 */

public class AddGamePlayerView extends AddGameBaseView{

  private static final String LOG_TAG = AddGamePlayerView.class.getSimpleName();

  @BindView(R.id.identitiesSpinner)
  Spinner identitiesSpinner;

  @BindView(R.id.identitiesImageViewPager)
  ViewPager identitiesImageViewPager;

  @BindView(R.id.addGamepalyerName)
  AutoCompleteTextView playerName;

  @BindView(R.id.addGameDeckName)
  AutoCompleteTextView deckName;

  @BindView(R.id.addGameDeckVer)
  TextView deckVer;

  private AddGameIdentitesPageAdapter identityImageViewAdapter;
  private ArrayAdapter identityNameArrayAdapter;
  private Activity activity;
  private ArrayAdapter<String> nameListAdapter;
  private ArrayAdapter<String> deckListAdapter;

  public AddGamePlayerView(Activity activity){
    super(activity);
    this.activity = activity;
    inflate(getContext(), R.layout.view_addgame_player, this);
    ButterKnife.setDebug(true);
    ButterKnife.bind(this);
  }

  public void onCreate(){}

  @Override
  public void setUpNameAutoComplete(ArrayList<String> playerList){
    nameListAdapter = new ArrayAdapter<>(activity, R.layout.support_simple_spinner_dropdown_item, playerList);
    nameListAdapter.setNotifyOnChange(true);
    playerName.setAdapter(nameListAdapter);
  }

  @Override
  public void setUpDeckNameAutoComplete(ArrayList<String> deckList) {
    deckListAdapter = new ArrayAdapter<>(activity, R.layout.support_simple_spinner_dropdown_item, deckList);
    deckListAdapter.setNotifyOnChange(true);
    deckName.setAdapter(deckListAdapter);
  }

  @Override
  public void setIdApadters(IdentityList idList) {
    Log.d(LOG_TAG, "Setting up ID Apadters");
    identityImageViewAdapter = new AddGameIdentitesPageAdapter(getContext(), idList);
    identitiesImageViewPager.setAdapter(identityImageViewAdapter);

    identityNameArrayAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, idList.getListOfNames());
    identitiesSpinner.setAdapter(identityNameArrayAdapter);
  }



  //TODO: This entire section of getting the spinner and view pager to work togeher feels off. For a start the view pager os relying on
  // the list not the store name on the view
  @OnItemSelected(R.id.identitiesSpinner)
  public void identityNameSelected() {
    Log.d(LOG_TAG,"ID name spinner has changed to " + identitiesSpinner.getSelectedItem().toString());

    int selectedPos = identityImageViewAdapter.getNameAtPOS(identitiesSpinner.getSelectedItem().toString());
    identitiesImageViewPager.setCurrentItem(selectedPos);


    //    identitiesSpinner.getSelectedItem();
//    identitiesImageViewPager
  }

  @OnPageChange(R.id.identitiesImageViewPager)
  public void identityIamgeSinnerSelecterd(int i){
    Log.d(LOG_TAG,"ID image spinner has changed to " + i);
//TODO: This feels like more luck then jundment for working
    identitiesSpinner.setSelection(identityNameArrayAdapter.getPosition(identityImageViewAdapter.getNameAtPOS(i)));
    //identityImageViewAdapter.get
    //identitiesSpinner.setSelection(identityNameArrayAdapter.getPosition(i));
  }

  @Override
  public Observable<Object> save() {
    return null;
  }

  public String getIdentitiesName() {
    return identitiesSpinner.getSelectedItem().toString();
  }

  public String getPlayerName() {
    return playerName.getText().toString();
  }

  public String getDeckName() {
    return deckName.getText().toString();
  }

  public String getDeckVer() {
    return deckVer.getText().toString();
  }

  //TODO: Fix get score
  public String getScore(){
    return "7";
  }

  //  @Override
//  public void setIdentitiesImageViewPager(LinkedHashMap<String, byte[]> imageListIn){
//      identityImageViewAdapter = new AddGameIdentitesPageAdapter(getContext(), imageListIn);
//      identitiesImageViewPager.setAdapter(identityImageViewAdapter);
//  }
//
//  @Override
//  public void setSpiinerAdaptor(ArrayList<String> identitiesList){
//      identitiesSpinner.setAdapter(identityNameArrayAdapter);
//      identityNameArrayAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, identitiesList);
//  }

}