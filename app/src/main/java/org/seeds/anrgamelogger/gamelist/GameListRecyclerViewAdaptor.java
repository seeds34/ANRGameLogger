package org.seeds.anrgamelogger.gamelist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.model.LoggedGame;

/**
 * Created by Tomas Seymour-Turner on 19/03/2017.
 */

public class GameListRecyclerViewAdaptor extends android.support.v7.widget.RecyclerView.Adapter<GameOverviewViewHolder> {

    private String LOG_TAG = GameListRecyclerViewAdaptor.class.getSimpleName();
    private ArrayList<LoggedGame> gameList = new ArrayList<>();
    private final String GAME_NO_TEXT = "No. ";

    private PublishSubject<String> mViewClickSubject = PublishSubject.create();

    public Observable<String> getViewClickedObservable() {
        return mViewClickSubject;
    }

    @Override
    public GameOverviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_gamelist_item, parent, false);
        GameOverviewViewHolder gameListImageViewHolder = new GameOverviewViewHolder(view);

        RxView.clicks(view)
                .takeUntil(RxView.detaches(parent))
                .map(aVoid -> gameListImageViewHolder.getGameNo())
                .subscribe(mViewClickSubject);

        return gameListImageViewHolder;
    }

    @Override
    public void onBindViewHolder(GameOverviewViewHolder holder, int position) {
        holder.setUpData(gameList.get(position));
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return (null != gameList ? gameList.size() : 0);
    }

    public void loadNewData(ArrayList<LoggedGame> gameListIn){
        gameList.clear();
        if (gameListIn != null && !gameListIn.isEmpty()) {
            this.gameList.addAll(gameListIn);
        }
        notifyDataSetChanged();
    }

//    public void onBindViewHolder(GameOverviewViewHolder holder, Cursor cursor) {
//        //Create Game Card
//
//        Log.v(LOG_TAG, "TEST #0001");
//
//        holder.playerOneName.setText(cursor.getString(cursor.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_ONE_NAME)));
//        holder.playerTwoName.setText(cursor.getString(cursor.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYER_TWO_NAME)));
//        holder.playedDate.setText(cursor.getString(cursor.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.PLAYED_DATE)));
//        holder.Location.setText(cursor.getString(cursor.getColumnIndex(LoggedGamesFlatViewContract.LoggedGamesFlatViewContractColumns.LOCATION_NAME)));
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
