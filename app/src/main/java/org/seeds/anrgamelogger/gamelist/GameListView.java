package org.seeds.anrgamelogger.gamelist;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.model.LocalLoggedGame;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;


public class GameListView extends FrameLayout{

    public static final String GAME_TRNASFER = "GAME_TRNASFER";
    private static final String LOG_TAG = GameListView.class.getSimpleName();
    private GameListRecyclerViewAdaptor gameListRecyclerViewAdaptor;

    @BindView(R.id.toolbar)
      Toolbar toolbar;

    @BindView(R.id.listofgmaesrecyclerview)
     RecyclerView gameRecyclerList;

    @BindView(R.id.addGameLog)
     FloatingActionButton fab;

    public GameListView(Activity activity) {
        super(activity);

        inflate(getContext(), R.layout.activity_main, this);

        ButterKnife.setDebug(true);
        ButterKnife.bind(this);

        gameRecyclerList = (RecyclerView) findViewById(R.id.listofgmaesrecyclerview);
        gameRecyclerList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        gameListRecyclerViewAdaptor = new GameListRecyclerViewAdaptor();
        gameRecyclerList.setAdapter(gameListRecyclerViewAdaptor);

        Log.d(LOG_TAG,"fab is " + ((fab == null)?"Null":"Not Null"));
    }

    public void setData(ArrayList<LocalLoggedGame> gameListIn) {
        gameListRecyclerViewAdaptor.loadNewData(gameListIn);
        gameRecyclerList.refreshDrawableState();
    }

    public Observable<Void> observeRV() {
        return RxView.clicks(fab);
       // return RxView.clicks(gameRecyclerList);

    }

    public void showMessage(String messageIn){
        Toast.makeText(this.getContext(), messageIn, Toast.LENGTH_LONG).show();
    }

    public void showLoading(boolean loading){
        if(loading){

        }else{

        }

    }



        //testData = new ArrayList<>();
        //setUpData.execute();

//        gameList = (RecyclerView) findViewById(R.id.listofgmaesview);
//        gameList.setLayoutManager(new LinearLayoutManager(this));

//        contentResolver = this.getContentResolver();
//
//        mGameListAdaptor = new GameListRecyclerViewAdaptor(new ArrayList<LocalLoggedGame>());
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
//                Intent intent = new Intent(view.getContext(), CreateLoggedGameActivity.class);
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
//                Intent intent = new Intent(view.getContext(), CreateLoggedGameActivity.class);
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