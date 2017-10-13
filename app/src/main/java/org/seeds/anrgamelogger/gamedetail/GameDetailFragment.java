package org.seeds.anrgamelogger.gamedetail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.seeds.anrgamelogger.model.LocalLoggedGame;
import org.seeds.anrgamelogger.R;

import java.io.ByteArrayInputStream;

import static org.seeds.anrgamelogger.gamelistview.GameListViewActivity.GAME_TRNASFER;

/**
 * Created by Tomas Seymour-Turner on 29/03/2017.
 */

public class GameDetailFragment extends Fragment {

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


    public GameDetailFragment() {
        super();
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(this.getClass().getName(),"Inflatting");
        //return super.onCreateView(inflater, container, savedInstanceState);
        LocalLoggedGame data;
        View v = inflater.inflate(R.layout.fragment_game_detail,container,false);
        Log.v(this.getClass().getName(),"Checking savedInstanceState");
        if(getArguments() != null){

            data = (LocalLoggedGame) getArguments().get(GAME_TRNASFER);

            location = (TextView) v.findViewById(R.id.playedLocation);
            playerOneName = (TextView) v.findViewById(R.id.playerOneName);
            playerTwoName = (TextView) v.findViewById(R.id.playerTwoName);
            playedDate = (TextView) v.findViewById(R.id.playedDate);
            playerOneIDImage = (ImageView) v.findViewById(R.id.playerOneIDImage);
            playerTwoIDImage = (ImageView) v.findViewById(R.id.playerTwoIDImage);
            playerOneScore = (TextView) v.findViewById(R.id.playerOneScore);
            playerTwoScore = (TextView) v.findViewById(R.id.playerTwoScore);
            playerOneDeckName = (TextView) v.findViewById(R.id.playerOneDeckName);
            playerTwoDeckName = (TextView) v.findViewById(R.id.playerTwoDeckName);
         //   winnerName = (TextView) v.findViewById(R.id.winnerName);
            gameNo = (TextView) v.findViewById(R.id.gameNo);

            location.setText("@" + data.getLocationName());
            playedDate.setText(data.getPlayedDate());
            playerOneName.setText(data.getPlayerOne().getName());
            playerTwoName.setText(data.getPlayerTwo().getName());
            playerOneScore.setText(String.valueOf(data.getPlayerOne().getScore()));
            playerTwoScore.setText(String.valueOf(data.getPlayerTwo().getScore()));
            playerOneDeckName.setText(data.getPlayerOne().getDeck());
            playerTwoDeckName.setText(data.getPlayerTwo().getDeck());
            gameNo.setText(data.getGameID());

            String winnerText = "Winner Name: " + data.getWinnerName();
           // winnerName.setText(winnerText);

            if(data.getWinnerName() == data.getPlayerOne().getName()){
                winnerLabel = (TextView) v.findViewById(R.id.playerOneWinnerLable);
                winnerLabel.setVisibility(View.VISIBLE);
            }else{
                winnerLabel = (TextView) v.findViewById(R.id.playerTwoWinnerLable);
                winnerLabel.setVisibility(View.VISIBLE);
            }



            byte[] imageByteArray = data.getPlayerOne().getImageByteArray();
            ByteArrayInputStream imageStream = new ByteArrayInputStream(imageByteArray);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);

            playerOneIDImage.setImageBitmap(theImage);

            imageByteArray = data.getPlayerTwo().getImageByteArray();
             imageStream = new ByteArrayInputStream(imageByteArray);
             theImage = BitmapFactory.decodeStream(imageStream);
            playerTwoIDImage.setImageBitmap(theImage);



        }




        return v;
    }
}
