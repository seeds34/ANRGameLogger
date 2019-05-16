package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import butterknife.OnPageChange;
import java.util.ArrayList;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.addgame.AddGameIdentitesPageAdapter;
import org.seeds.anrgamelogger.model.IdentityList;

/**
 * Created by Tomas Seymour-Turner on 04/01/2018.
 */

public class AddGamePlayerView extends FrameLayout implements AddGameSubView {

  private static final String LOG_TAG = AddGamePlayerView.class.getSimpleName();

  @BindView(R.id.identitiesSpinner)
  Spinner identitiesSpinner;

  @BindView(R.id.identitiesImageViewPager)
  ViewPager identitiesImageViewPager;

  @BindView(R.id.addGamePlayerName)
  AutoCompleteTextView playerName;

  @BindView(R.id.addGameDeckName)
  AutoCompleteTextView deckName;

  @BindView(R.id.addGameDeckVer)
  TextView deckVer;

  @BindView(R.id.scoreSpinner)
  Spinner score;

  private AddGameIdentitesPageAdapter identityImageViewAdapter;
  private ArrayAdapter identityNameArrayAdapter;
  private Activity activity;
  private ArrayAdapter<String> nameListAdapter;
  private ArrayAdapter<String> deckListAdapter;
  private String side;

  public AddGamePlayerView(Activity activity){
    super(activity);
    this.activity = activity;
    inflate(getContext(), R.layout.view_addgame_player, this);
    ButterKnife.setDebug(true);
    ButterKnife.bind(this);

    setUpScoreSpinner(15);
  }

  public void setSide(String side){
    this.side = side;
  }

  public String getSide(){
    return side;
  }

  public View getView(){
    return this;
  }

  public void setUpNameAutoComplete(ArrayList<String> playerList){
    nameListAdapter = new ArrayAdapter<>(activity, R.layout.support_simple_spinner_dropdown_item, playerList);
    nameListAdapter.setNotifyOnChange(true);
    playerName.setAdapter(nameListAdapter);
  }

  public void setUpDeckNameAutoComplete(ArrayList<String> deckList) {
    deckListAdapter = new ArrayAdapter<>(activity, R.layout.support_simple_spinner_dropdown_item, deckList);
    deckListAdapter.setNotifyOnChange(true);
    deckName.setAdapter(deckListAdapter);
  }

  public void setIdApadters(IdentityList idList) {
    Log.d(LOG_TAG, "Setting up ID Apadters");
    identityImageViewAdapter = new AddGameIdentitesPageAdapter(getContext(), idList);
    identitiesImageViewPager.setAdapter(identityImageViewAdapter);

    identityNameArrayAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, idList.getListOfNames());
    identitiesSpinner.setAdapter(identityNameArrayAdapter);
  }

  private void setUpScoreSpinner(int maxPoint){

    Integer[] scoreList = new Integer[maxPoint];

    for(int i = 0 ; i < scoreList.length ; i++){
      scoreList[i] = i;
    }

    ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(activity, R.layout.support_simple_spinner_dropdown_item, scoreList);
    score.setAdapter(adapter);
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

  public String getIdentityName() {
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

  public Integer getScore(){
    return (Integer)score.getSelectedItem();
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
