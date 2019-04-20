package org.seeds.anrgamelogger.gamelist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.io.ByteArrayInputStream;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameFlat;

/**
 * Created by Tomas Seymour-Turner on 19/03/2017.
 */

public class GameOverviewViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.playerOneName)
    protected TextView playerOneName;
    @BindView(R.id.playerTwoName)
    protected TextView playerTwoName;
    @BindView(R.id.playeddate)
    protected TextView playedDate;
    @BindView(R.id.location)
    protected TextView location;
    @BindView(R.id.playerIneIDImage)
    protected ImageView playerOneIDImage;
    @BindView(R.id.playerTwoIDImage)
    protected ImageView playerTwoIDImage;
    @BindView(R.id.gameNumber)
    protected  TextView gameNo;

    public GameOverviewViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void setUpData(LoggedGameFlat loggedGame){
        playerOneName.setText(loggedGame.getpO_Name());
        playerTwoName.setText(loggedGame.getpT_Name());
        playedDate.setText(loggedGame.getPlayedDate());
        location.setText(loggedGame.getLocationName());

        String gn = loggedGame.getGameID().toString();
        gameNo.setText(gn);

        byte[] imageByteArray = loggedGame.getpO_IdentityImage();

        ByteArrayInputStream imageStream;
        Bitmap theImage;

        if(imageByteArray != null ) {
            imageStream= new ByteArrayInputStream(imageByteArray);
            theImage = BitmapFactory.decodeStream(imageStream);
            playerOneIDImage.setImageBitmap(theImage);
        }

        imageByteArray = loggedGame.getpT_IdentityImage();
        if(imageByteArray != null ){
            imageStream = new ByteArrayInputStream(imageByteArray);
            theImage = BitmapFactory.decodeStream(imageStream);
            playerTwoIDImage.setImageBitmap(theImage);
        }
    }


    public String getGameNo(){
        return (String)gameNo.getText();
    }


}
