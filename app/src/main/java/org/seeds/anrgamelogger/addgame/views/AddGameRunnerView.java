package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;

import org.seeds.anrgamelogger.application.ANRLoggerApplication;

public class AddGameRunnerView extends AddGamePlayerView{
    private final String TITLE = ANRLoggerApplication.RUNNER_SIDE_IDENTIFIER;

    public AddGameRunnerView(Activity activity) {
        super(activity);
    }

    @Override
    public String getTitle(){
        return TITLE;
    }
}