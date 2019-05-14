package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.addgame.AddGamePagerAdapter;

/**
 * Created by user on 09/12/2017.
 */

public class AddGameView extends FrameLayout {

    private static final String LOG_TAG = AddGameView.class.getSimpleName();

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.addGameViewPager)
    public ViewPager addGameViewPager;

    @BindView(R.id.tabs)
    public TabLayout tabLayout;

  private final PublishSubject<Object> temp = PublishSubject.create();

    private AddGamePagerAdapter addGamePagerAdapter;

    public AddGameView(Activity activity){
        super(activity);
        inflate(getContext(), R.layout.view_addgame_base, this);

        ButterKnife.bind(this);

        addGamePagerAdapter =  new AddGamePagerAdapter();
        addGameViewPager.setOffscreenPageLimit(2);

      String title = getResources().getString(R.string.title_add_new_game);

      toolbar.setTitle(title);

        tabLayout.setupWithViewPager(addGameViewPager);
        ((AppCompatActivity)activity).setSupportActionBar(toolbar);
        ((AppCompatActivity)activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setOnMenuItemClickListener(
            new Toolbar.OnMenuItemClickListener() {
          @Override
          public boolean onMenuItemClick(MenuItem item) {


            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_save) {
              save();
              return true;
            }


            return false;
          }


      });

      toolbar.setNavigationOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

          new AlertDialog.Builder(activity)
              .setTitle("Discard Changes?")
              //.setMessage("All changes will be lost if you leave.")

              // Specifying a listener allows you to take an action before dismissing the dialog.
              // The dialog is automatically dismissed when a dialog button is clicked.
              .setPositiveButton("DISCARD", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                  activity.finish();
                }
              })

              // A null listener allows the button to dismiss the dialog and take no further action.
              .setNegativeButton("CANCEL", null)
              //.setIcon(android.R.drawable.ic_dialog_alert)
              .show();
        }
      });


    }

//    public void setTitle(String gameNo){
//        String title = getResources().getString(R.string.title_add_new_game);
//        toolbar.setTitle(title);
//    }




  public Observable<Object> save(){
    temp.onNext(1);
    return temp;
  }






    public void setUpPagerViews(AddGameSubView viewIn) {
        Log.d(LOG_TAG,"Adding View: " + viewIn.getSide());
        addGamePagerAdapter.addView(viewIn);
    }

    public void startPageViewer(){
        addGameViewPager.setAdapter(addGamePagerAdapter);
    }

    public void displayGameLoggedMessagge(String gameNumber){
        Toast.makeText(this.getContext(),"Game "+ gameNumber +" has been Logged",Toast.LENGTH_SHORT).show();
    }

  public void displayMessage(String message) {
      Toast.makeText(this.getContext(),message,Toast.LENGTH_SHORT).show();

  }
}
