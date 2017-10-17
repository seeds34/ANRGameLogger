package org.seeds.anrgamelogger.gamelistview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.seeds.anrgamelogger.R;

import butterknife.ButterKnife;

/**
 * Created by Tomas Seymour-Turner on 19/03/2017.
 */

public class GameListViewHolder extends RecyclerView.ViewHolder {


   // @BindView(R.id.playerOneName)
    protected TextView playerOneName;
   // @BindView(R.id.playerTwoName)
    protected TextView playerTwoName;
    //@BindView(R.id.playeddate)
    protected TextView playedDate;
    //@BindView(R.id.location)
    protected TextView location;
    //@BindView(R.id.playerIneIDImage)
    protected ImageView playerOneIDImage;
    //@BindView(R.id.playerTwoIDImage)
    protected ImageView playerTwoIDImage;
    //@BindView(R.id.gameNumber)
    protected  TextView gameNo;

    public GameListViewHolder(View view) {
        super(view);

        ButterKnife.bind(this, view);

        playerOneName = (TextView) view.findViewById(R.id.playerOneName);
        playerTwoName = (TextView) view.findViewById(R.id.playerTwoName);
        playedDate = (TextView) view.findViewById(R.id.playeddate);
        location = (TextView) view.findViewById(R.id.location);
        playerOneIDImage = (ImageView) view.findViewById(R.id.playerIneIDImage);
        playerTwoIDImage = (ImageView) view.findViewById(R.id.playerTwoIDImage);
        gameNo = (TextView) view.findViewById(R.id.gameNumber);
    }

}
