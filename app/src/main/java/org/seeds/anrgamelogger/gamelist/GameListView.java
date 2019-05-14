package org.seeds.anrgamelogger.gamelist;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.Observable;
import java.util.ArrayList;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameFlat;


public class GameListView extends FrameLayout{

    private static final String LOG_TAG = GameListView.class.getSimpleName();
    private GameListRecyclerViewAdaptor gameListRecyclerViewAdaptor;

    @BindView(R.id.toolbar)
      Toolbar toolbar;

    @BindView(R.id.listofgmaesrecyclerview)
     RecyclerView gameRecyclerList;

    @BindView(R.id.addGameLog)
     FloatingActionButton fab;

    @BindView(R.id.add_corp_game_fab)
    FloatingActionButton corp_fab;

    @BindView(R.id.add_runner_game_fab)
    FloatingActionButton runner_fab;

    @BindView(R.id.loadImages)
    Button loadImageButton;

    public GameListView(Activity activity) {
        super(activity);

        inflate(getContext(), R.layout.view_gamelist_base, this);

        ButterKnife.setDebug(true);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.app_name);

        gameRecyclerList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        gameListRecyclerViewAdaptor = new GameListRecyclerViewAdaptor();
        gameRecyclerList.setAdapter(gameListRecyclerViewAdaptor);

        Log.d(LOG_TAG,"fab is " + ((fab == null)?"Null":"Not Null"));
    }


    public void setData(ArrayList<LoggedGameFlat> gameListIn) {
        gameListRecyclerViewAdaptor.loadNewData(gameListIn);
        gameRecyclerList.refreshDrawableState();
    }

    public Observable<String> observeRecyclerView() {
        return gameListRecyclerViewAdaptor.getViewClickedObservable();
       // return RxView.clicks(gameRecyclerList);
    }

    public Observable<Object> observeLoadImageClick(){
        Log.d(LOG_TAG, "Load Image Buttin pressed");
        return RxView.clicks(loadImageButton);
    }


    public void showSubFABs(){
      FrameLayout.LayoutParams layoutParams;


      layoutParams = (FrameLayout.LayoutParams) corp_fab.getLayoutParams();
      layoutParams.rightMargin += (int) (corp_fab.getWidth() * 1.7);
      layoutParams.bottomMargin += (int) (corp_fab.getHeight() * 0.25);
      corp_fab.setLayoutParams(layoutParams);
      //fab.startAnimation(show_fab_1);
      corp_fab.setVisibility(View.VISIBLE);
      corp_fab.setClickable(true);

      layoutParams = (FrameLayout.LayoutParams) runner_fab.getLayoutParams();
      layoutParams.rightMargin += (int) (runner_fab.getWidth() * 0.25);
      layoutParams.bottomMargin += (int) (runner_fab.getHeight() * 1.7);
      runner_fab.setLayoutParams(layoutParams);
      //fab.startAnimation(show_fab_1);
      runner_fab.setVisibility(View.VISIBLE);
      runner_fab.setClickable(true);
    }

  public void hideSubFABs(){
    FrameLayout.LayoutParams layoutParams;
    layoutParams = (FrameLayout.LayoutParams) corp_fab.getLayoutParams();
    layoutParams.rightMargin -= (int) (corp_fab.getWidth() * 1.7);
    layoutParams.bottomMargin -= (int) (corp_fab.getHeight() * 0.25);
    corp_fab.setLayoutParams(layoutParams);
    //fab.startAnimation(show_fab_1);
    corp_fab.setVisibility(View.INVISIBLE);
    corp_fab.setClickable(false);

    layoutParams = (FrameLayout.LayoutParams) runner_fab.getLayoutParams();
    layoutParams.rightMargin -= (int) (runner_fab.getWidth() * 0.25);
    layoutParams.bottomMargin -= (int) (runner_fab.getHeight() * 1.7);
    runner_fab.setLayoutParams(layoutParams);
    //fab.startAnimation(show_fab_1);
    runner_fab.setVisibility(View.INVISIBLE);
    runner_fab.setClickable(false);
  }

  @OnClick(R.id.addGameLog)
    public void fabClick(){
      if(corp_fab.getVisibility() == View.INVISIBLE & runner_fab.getVisibility() == View.INVISIBLE  ) {
        showSubFABs();
        }else{
        hideSubFABs();
        }
    }

    public Observable<Object> observeCorpFab(){
        return RxView.clicks(corp_fab);
    }

  public Observable<Object> observeRunnerFab(){
    return RxView.clicks(runner_fab);
  }


//    @OnClick(R.id.listofgmaesrecyclerview)
//    private void childSelect(){
//        View childView = gameRecyclerList.findChildViewUnder(e.getX(),e.getY());
//        if(childView != null && mListener != null){
//            mListener.onItemLongClick(childView, gameRecyclerList.getChildAdapterPosition(childView));
//        }
//    }

    public void showMessage(String messageIn){
        Toast.makeText(this.getContext(), messageIn, Toast.LENGTH_LONG).show();
    }

    public void showLoading(boolean loading){
        if(loading){
            showMessage("Loading Data");
        }else{
            showMessage("Data Loaded");
        }

    }

//
//    public Observable<RecyclerViewChildAttachStateChangeEvent> downloadImage(){
//        //return RxView.clicks(gameRecyclerList);
//        RxRecyclerViewAdapter.
//        RxRecyclerVie
//        return RxRecyclerView.childAttachStateChangeEvents(gameRecyclerList);
//    }

        //testData = new ArrayList<>();
        //setUpData.execute();

//        gameList = (RecyclerView) findViewById(R.id.listofgmaesview);
//        gameList.setLayoutManager(new LinearLayoutManager(this));

//        contentResolver = this.getContentResolver();
//
//        mGameListAdaptor = new GameListRecyclerViewAdaptor(new ArrayList<LoggedGameOverview>());
//        gameList.setAdapter(mGameListAdaptor);
//
//
//        gameList.addOnItemTouchListener(new GameListItemClickListner(this, gameList, new GameListItemClickListner.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                //NOTE:Changed MainActivity.this to view.getContext()
//                //Toast.makeText(view.getContext(), "Normal Tap", Toast.LENGTH_SHORT).show();
//                //Toast.makeText(MainActivity.this, "Normal Tap", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(view.getContext(), GameDetailActivity.class);
//                intent.putExtra(GAME_TRNASFER, mGameListAdaptor.getGame(position));
//                startActivity(intent);
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//                Intent intent = new Intent(view.getContext(), GameDetailActivity.class);
//                intent.putExtra(GAME_TRNASFER, mGameListAdaptor.getGame(position));
//                startActivity(intent);
//                //Toast.makeText(view.getContext(), "Long Tap", Toast.LENGTH_SHORT).show();
//            }
//        }));


//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Intent intent = new Intent(view.getContext(), AddGameActivity.class);
//                startActivity(intent);
//            }
//        });

//    @Override
//    protected void onResume() {
//        super.onResume();
//    }

//    @OnClick(R.id.addGameLog)
//    public void onClickFAB(View view) {
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Intent intent = new Intent(view.getContext(), AddGameActivity.class);
//                startActivity(intent);
//            }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }



}