package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.addgame.AddGameIdentitesPageAdapter;

/**
 * Created by Tomas Seymour-Turner on 04/01/2018.
 */

public class AddGamePlayerView extends FrameLayout{

  private static final String LOG_TAG = AddGamePlayerView.class.getSimpleName();


  @BindView(R.id.identitiesSpinner)
  Spinner identitiesSpinner;

  @BindView(R.id.identitiesImageViewPager)
  ViewPager identitiesImageViewPager;

  private Activity activity;

  public AddGamePlayerView(Activity activity){
    super(activity);
    this.activity = activity;
    inflate(getContext(), R.layout.view_addgame_player, this);
    ButterKnife.setDebug(true);
    ButterKnife.bind(this);
  }

  public void onCreate(){
    Toast.makeText(this.getContext(), "HELLO!!" , Toast.LENGTH_LONG);
  }


  public void setIdentitiesImageViewPager(ArrayList<byte[]> imageListIn){
    identitiesImageViewPager.setAdapter(new AddGameIdentitesPageAdapter(getContext(), imageListIn));
  }

  public void setSpiinerAdaptor(ArrayList<String> identitiesList){
    ArrayAdapter<String> idListAdaptor = new ArrayAdapter<String>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, identitiesList);
    identitiesSpinner.setAdapter(idListAdaptor);
  }

}
