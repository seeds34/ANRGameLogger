package org.seeds.anrgamelogger.activities.gamehandler.views;

import android.app.Activity;

import org.seeds.anrgamelogger.application.ANRLoggerApplication;

public class GameHandlerHandlerRunnerView extends GameHandlerHandlerPlayerView {
    private final String SIDE = ANRLoggerApplication.RUNNER_SIDE_IDENTIFIER;

    public GameHandlerHandlerRunnerView(Activity activity) {
        super(activity);
    }

    @Override
    public String getSide(){
        return SIDE;
    }
}
