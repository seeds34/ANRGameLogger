package org.seeds.anrgamelogger.gamelistview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.seeds.anrgamelogger.R;

/**
 * Created by Tomas Seymour-Turner on 19/03/2017.
 */

public class GameListViewHolder extends RecyclerView.ViewHolder {


    protected TextView playerOneName;
    protected TextView playerTwoName;
    protected TextView playedDate;
    protected TextView location;
    protected ImageView playerOneIDImage;
    protected ImageView playerTwoIDImage;

    public GameListViewHolder(View view) {
        super(view);
        playerOneName = (TextView) view.findViewById(R.id.playerOneName);
        playerTwoName = (TextView) view.findViewById(R.id.playerTwoName);
        playedDate = (TextView) view.findViewById(R.id.playeddate);
        location = (TextView) view.findViewById(R.id.location);
        playerOneIDImage = (ImageView) view.findViewById(R.id.playerIneIDImage);
        playerTwoIDImage = (ImageView) view.findViewById(R.id.playerTwoIDImage);
    }

}
