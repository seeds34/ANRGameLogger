package org.seeds.anrgamelogger.gamelistview;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.addgame.CreateLoggedGameActivity;
import org.seeds.anrgamelogger.gamedetail.GameDetailActivity;
import org.seeds.anrgamelogger.model.LocalLoggedGame;

import java.util.ArrayList;

public class GameListViewActivity extends MvpActivity<GameListView, GameListPresenter> implements GameListView {

    public static final String GAME_TRNASFER = "GAME_TRNASFER";
    private static final String LOG_TAG = GameListViewActivity.class.getSimpleName();
    public RecyclerView gameList;
    private GameListAdaptor mGameListAdaptor;
    private ArrayList<LocalLoggedGame> testData;
    private ContentResolver contentResolver;

//    @BindView(R.id.addGameLog)
//    FloatingActionButton fab;


    @Override // Called internally by Mosby
    public GameListPresenter createPresenter() {
        return new GameListPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //testData = new ArrayList<>();
        //GameListPresenter.SetUpData setUpData = new SetUpData();
        //setUpData.execute();

        gameList = (RecyclerView) findViewById(R.id.listofgmaesview);
        gameList.setLayoutManager(new LinearLayoutManager(this));

        contentResolver = this.getContentResolver();

        mGameListAdaptor = new GameListAdaptor(new ArrayList<LocalLoggedGame>());
        gameList.setAdapter(mGameListAdaptor);


        gameList.addOnItemTouchListener(new GameListItemClickListner(this, gameList, new GameListItemClickListner.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //NOTE:Changed MainActivity.this to view.getContext()
                //Toast.makeText(view.getContext(), "Normal Tap", Toast.LENGTH_SHORT).show();
                //Toast.makeText(MainActivity.this, "Normal Tap", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), GameDetailActivity.class);
                intent.putExtra(GAME_TRNASFER, mGameListAdaptor.getGame(position));
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Intent intent = new Intent(view.getContext(), GameDetailActivity.class);
                intent.putExtra(GAME_TRNASFER, mGameListAdaptor.getGame(position));
                startActivity(intent);
                //Toast.makeText(view.getContext(), "Long Tap", Toast.LENGTH_SHORT).show();
            }
        }));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addGameLog);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(view.getContext(), CreateLoggedGameActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

//    @OnClick(R.id.addGameLog)
//    public void onClickFAB(View view) {
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Intent intent = new Intent(view.getContext(), CreateLoggedGameActivity.class);
//                startActivity(intent);
//            }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}