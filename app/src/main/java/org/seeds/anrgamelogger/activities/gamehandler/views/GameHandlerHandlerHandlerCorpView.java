package org.seeds.anrgamelogger.activities.gamehandler.views;

import android.app.Activity;

import org.seeds.anrgamelogger.application.ANRLoggerApplication;

public class GameHandlerHandlerHandlerCorpView extends GameHandlerHandlerPlayerView {

    private final String TITLE = ANRLoggerApplication.CORP_SIDE_IDENTIFIER;

    public GameHandlerHandlerHandlerCorpView(Activity activity) {
        super(activity);
    }

    @Override
    public String getSide(){
        return TITLE;
    }
}
