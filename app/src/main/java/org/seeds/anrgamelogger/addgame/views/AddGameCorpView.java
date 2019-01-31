package org.seeds.anrgamelogger.addgame.views;

import android.app.Activity;

import org.seeds.anrgamelogger.application.ANRLoggerApplication;

public class AddGameCorpView extends AddGamePlayerView{

    private final String TITLE = ANRLoggerApplication.CORP_SIDE_IDENTIFIER;

    public AddGameCorpView(Activity activity) {
        super(activity);
    }

    @Override
    public String getTitle(){
        return TITLE;
    }
}
