package org.seeds.anrgamelogger.activities.gamehandler.views;

import android.app.Activity;

import org.seeds.anrgamelogger.application.ANRLoggerApplication;

public class GameHandlerRunnerView extends GameHandlerHandlerPlayerView {
    private final String SIDE = ANRLoggerApplication.RUNNER_SIDE_IDENTIFIER;

    public GameHandlerRunnerView(Activity activity) {
        super(activity);
    }

    @Override
    public String getSide(){
        return SIDE;
    }
}
