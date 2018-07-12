package org.seeds.anrgamelogger.gamedetail.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.ByteArrayInputStream;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.buisnessobjects.LoggedGameFlat;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameFlat;

/**
 * Created by user on 21/11/2017.
 */

@SuppressLint("ViewConstructor")
public class GameDetailOverview extends FrameLayout {

//    private final Activity activity;
    private LoggedGameFlat data;

    protected TextView playerOneName;
    protected TextView playerTwoName;
    protected TextView playedDate;
    protected TextView location;
    protected ImageView playerOneIDImage;
    protected ImageView playerTwoIDImage;
    protected TextView playerOneScore;
    protected TextView playerTwoScore;
    protected TextView playerOneDeckName;
    protected TextView playerTwoDeckName;
    protected TextView winnerName;
    protected  TextView gameNo;
    protected TextView winnerLabel;

    private Activity activity;

    public GameDetailOverview(Activity activity, LoggedGameFlat gameIn){
        super(activity);
        inflate(getContext(), R.layout.view_gamedetail_overview, this);
        data = gameIn;
        Log.d("OVERVIEW","Data = " + data);
        onCreate();
    }

    public void onCreate(){

//        View v = activity.getLayoutInflater().inflate(R.layout.view_gamedetail_overview,container,false);

        Log.v(this.getClass().getName(),"Checking savedInstanceState");
        //if(getArguments() != null){
        //data = (LoggedGameOverview) getArguments().get(GAME_TRNASFER);

        location = (TextView) this.findViewById(R.id.playedLocation);
        playerOneName = (TextView) this.findViewById(R.id.playerOneName);
        playerTwoName = (TextView) this.findViewById(R.id.playerTwoName);
        playedDate = (TextView) this.findViewById(R.id.playedDate);
        playerOneIDImage = (ImageView) this.findViewById(R.id.playerOneIDImage);
        playerTwoIDImage = (ImageView) this.findViewById(R.id.playerTwoIDImage);
        playerOneScore = (TextView) this.findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) this.findViewById(R.id.playerTwoScore);
        playerOneDeckName = (TextView) this.findViewById(R.id.playerOneDeckName);
        playerTwoDeckName = (TextView) this.findViewById(R.id.playerTwoDeckName);
        //   winnerName = (TextView) v.findViewById(R.id.winnerName);
        gameNo = (TextView) this.findViewById(R.id.gameNo);

        location.setText("@" + data.getLocationName());
        playedDate.setText(data.getPlayedDate());
        playerOneName.setText(data.getpO_Name());
        playerTwoName.setText(data.getpT_Name());
        playerOneScore.setText(String.valueOf(data.getpO_Score()));
        playerTwoScore.setText(String.valueOf(data.getpT_Score()));
        playerOneDeckName.setText(data.getpO_DeckName());
        playerTwoDeckName.setText(data.getpT_DeckName());
        gameNo.setText(data.getGameID());

        String winnerText = "Winner Name: " + data.getWinnerName();
        // winnerName.setText(winnerText);

        //TODO: Fix Win logic
//        if(data.getWinnerName() == data.getPlayerOne().getName()){
            winnerLabel = (TextView) this.findViewById(R.id.playerOneWinnerLable);
            winnerLabel.setVisibility(View.VISIBLE);
//        }else{
//            winnerLabel = (TextView) this.findViewById(R.id.playerTwoWinnerLable);
//            winnerLabel.setVisibility(View.VISIBLE);
//        }

        byte[] imageByteArray = data.getpO_IdentityImage();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(imageByteArray);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);

        playerOneIDImage.setImageBitmap(theImage);

        imageByteArray = data.getpT_IdentityImage();
        imageStream = new ByteArrayInputStream(imageByteArray);
        theImage = BitmapFactory.decodeStream(imageStream);
        playerTwoIDImage.setImageBitmap(theImage);
    }




}
