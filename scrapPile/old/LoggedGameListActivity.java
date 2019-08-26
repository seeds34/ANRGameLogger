package org.seeds.anrgamelogger.activities.gamelist;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.seeds.anrgamelogger.LazyDataGen;
import org.seeds.anrgamelogger.LocalLoggedGame;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.application.database.datacreater.InportedLoggedGame;
import org.seeds.anrgamelogger.application.database.datacreater.PopulateIdentitiesData;
import org.seeds.anrgamelogger.activities.gamedetail.GameDetailActivity;

import java.util.ArrayList;

public class LoggedGameListActivity extends AppCompatActivity {

    public static final String GAME_TRNASFER = "GAME_TRNASFER";
    private RecyclerView gameList;
    private GameListAdaptor mGameListAdaptor;
    private ArrayList<LocalLoggedGame> testData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        testData = new ArrayList<>();

        SetUpData setUpData = new SetUpData();
        setUpData.execute();

        gameList = (RecyclerView) findViewById(R.id.listofgmaesview);
        gameList.setLayoutManager(new LinearLayoutManager(this));

        mGameListAdaptor = new GameListAdaptor(LoggedGameListActivity.this,testData );
        gameList.setAdapter(mGameListAdaptor);


        gameList.addOnItemTouchListener(new GameListItemClickListner(this, gameList, new GameListItemClickListner.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //NOTE:Changed MainActivity.this to view.getContext()
                Toast.makeText(view.getContext(), "Normal Tap", Toast.LENGTH_SHORT).show();
                //Toast.makeText(MainActivity.this, "Normal Tap", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Intent intent = new Intent(view.getContext(), GameDetailActivity.class);
                intent.putExtra(GAME_TRNASFER, mGameListAdaptor.getGame(position));
                startActivity(intent);
                //Toast.makeText(view.getContext(), "Long Tap", Toast.LENGTH_SHORT).show();
            }
        }));



    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        SetUpData setUpData = new SetUpData();
//        setUpData.execute();
//    }

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

    public class SetUpData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            Context context = getApplicationContext();
            PopulateIdentitiesData c = new PopulateIdentitiesData();
            c.extractIdentitiesFromNRDB(context);
            InportedLoggedGame e = new InportedLoggedGame(context);
            testData = new LazyDataGen(context).getAllGames();
            return null;
        }
    }

}
