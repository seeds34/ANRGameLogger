package org.seeds.anrgamelogger.gamedetail;

import android.app.Activity;

import org.seeds.anrgamelogger.model.LocalLoggedGame;

/**
 * Created by Tomas Seymour-Turner on 19/11/2017.
 */

public class GameDetailModel {

    private Activity activity;
    
    public GameDetailModel(Activity activity){
        this.activity = activity;
    }

    public LocalLoggedGame getLoggedGame() {
        return activity.getIntent().getParcelableExtra(GameDetailActivity.GAME_TRNASFER);
    }

}
