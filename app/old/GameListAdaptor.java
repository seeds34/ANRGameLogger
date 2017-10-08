package org.seeds.anrgamelogger.gamelist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.seeds.anrgamelogger.IDImageSelector;
import org.seeds.anrgamelogger.LocalLoggedGame;
import org.seeds.anrgamelogger.R;

import java.util.ArrayList;

/**
 * Created by Tomas Seymour-Turner on 19/03/2017.
 */

public class GameListAdaptor extends android.support.v7.widget.RecyclerView.Adapter<GameListViewHolder> {

    private String LOG_TAG = GameListAdaptor.class.getSimpleName();
    private ArrayList<LocalLoggedGame> dataSet;

    public GameListAdaptor(Context contextin, ArrayList<LocalLoggedGame> dataSet){
        this.dataSet = dataSet;

    }

    @Override
    public GameListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_overview_view, parent, false);
        GameListViewHolder gameListImageViewHolder = new GameListViewHolder(view);
        return gameListImageViewHolder;
    }

    @Override
    public void onBindViewHolder(GameListViewHolder holder, int position) {
        Log.d(LOG_TAG,"Match: " +dataSet.get(position).getPlayerOneName() + " VS " + dataSet.get(position).getPlayerTwoName());

        holder.playerOneName.setText(dataSet.get(position).getPlayerOneName());
        holder.playerTwoName.setText(dataSet.get(position).getPlayerTwoName());
        holder.playedDate.setText(dataSet.get(position).getPlayedDate());
        holder.location.setText(dataSet.get(position).getLocationName());

        holder.playerOneIDImage.setImageResource(IDImageSelector.imageChooser(dataSet.get(position).getPlayerOneDeck()));

        holder.playerTwoIDImage.setImageResource(IDImageSelector.imageChooser(dataSet.get(position).getPlayerTwoDeck()));

        //This does not suppoty Draws
//        if(dataSet.get(position).getWinner() == dataSet.get(position).getPlayerOneName()){
//            //Player one won so player 2 image greyed
//            holder.playerTwoIDImage.setImageTintList(new ColorStateList());
//            holder.playerTwoIDImage.setImageTintList();
//
//        }else{
//
//        }


    }

    @Override
    public int getItemCount() {
        return (null != dataSet ? dataSet.size() : 0);
    }

    public LocalLoggedGame getGame(int position){
        return (null != dataSet ? dataSet.get(position):null);
    }
}
