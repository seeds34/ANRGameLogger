package org.seeds.anrgamelogger.gamelist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.seeds.anrgamelogger.model.LocalLoggedGame;
import org.seeds.anrgamelogger.R;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by Tomas Seymour-Turner on 19/03/2017.
 */

//public class GameListAdaptor extends CursorRecyclerViewAdapter<GameListViewHolder>{
public class GameListAdaptor extends android.support.v7.widget.RecyclerView.Adapter<GameListViewHolder> {

    private String LOG_TAG = GameListAdaptor.class.getSimpleName();
    private ArrayList<LocalLoggedGame> dataSet;
    private final String GAME_NO_TEXT = "No. ";



    public GameListAdaptor(ArrayList<LocalLoggedGame> dataSet) {
        this.dataSet = dataSet;
    }

//    public GameListAdaptor(Context context, Cursor cursor){
//        super(context,cursor);
//    }

    @Override
    public GameListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_overview_view, parent, false);
        GameListViewHolder gameListImageViewHolder = new GameListViewHolder(view);
        return gameListImageViewHolder;
    }

    @Override
    public void onBindViewHolder(GameListViewHolder holder, int position) {

        holder.playerOneName.setText(dataSet.get(position).getPlayerOne().getName());
        holder.playerTwoName.setText(dataSet.get(position).getPlayerTwo().getName());
        holder.playedDate.setText(dataSet.get(position).getPlayedDate());
        holder.location.setText(dataSet.get(position).getLocationName());
        holder.gameNo.setText(GAME_NO_TEXT + dataSet.get(position).getGameID());

        byte[] imageByteArray = dataSet.get(position).getPlayerOne().getImageByteArray();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(imageByteArray);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);

        holder.playerOneIDImage.setImageBitmap(theImage);

        imageByteArray = dataSet.get(position).getPlayerTwo().getImageByteArray();
        imageStream = new ByteArrayInputStream(imageByteArray);
        theImage = BitmapFactory.decodeStream(imageStream);

        holder.playerTwoIDImage.setImageBitmap(theImage);
//

    }


    @Override
    public int getItemCount() {
        return (null != dataSet ? dataSet.size() : 0);
    }

    public LocalLoggedGame getGame(int position){
        return (null != dataSet ? dataSet.get(position):null);
    }

    public void loadNewData(ArrayList<LocalLoggedGame> dataIn){
        dataSet = dataIn;
        notifyDataSetChanged();
    }

//    public void onBindViewHolder(GameListViewHolder holder, Cursor cursor) {
//        //Create Game Card
//
//        Log.v(LOG_TAG, "TEST #0001");
//
//        holder.playerOneName.setText(cursor.getString(cursor.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_NAME)));
//        holder.playerTwoName.setText(cursor.getString(cursor.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_NAME)));
//        holder.playedDate.setText(cursor.getString(cursor.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYED_DATE)));
//        holder.location.setText(cursor.getString(cursor.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.LOCATION_NAME)));
//
//        holder.gameNo.setText(GAME_NO_TEXT + cursor.getString(cursor.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.GAME_ID)));
//
//        byte[] imageByteArray = cursor.getBlob(cursor.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_ID_IMAGE));
//        ByteArrayInputStream imageStream = new ByteArrayInputStream(imageByteArray);
//        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
//
//        holder.playerOneIDImage.setImageBitmap(theImage);
//
//        imageByteArray = cursor.getBlob(cursor.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_ID_IMAGE));
//        imageStream = new ByteArrayInputStream(imageByteArray);
//        theImage = BitmapFactory.decodeStream(imageStream);
//
//        holder.playerTwoIDImage.setImageBitmap(theImage);
//
//
////        cursor.close();
//    }


}
