package org.seeds.anrgamelogger.activities.gamehandler.views;

import android.app.Activity;

import org.seeds.anrgamelogger.application.ANRLoggerApplication;

public class GameHandlerCorpView extends GameHandlerHandlerPlayerView {

    private final String TITLE = ANRLoggerApplication.CORP_SIDE_IDENTIFIER;

    public GameHandlerCorpView(Activity activity) {
        super(activity);
    }

    @Override
    public String getSide(){
        return TITLE;
    }
}
