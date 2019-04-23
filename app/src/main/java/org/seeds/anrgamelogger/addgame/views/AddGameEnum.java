package org.seeds.anrgamelogger.addgame.views;

import org.seeds.anrgamelogger.R;

/**
 * Created by user on 04/12/2017.
 */

public enum AddGameEnum {

    ADDGAMEPLAYERONE(R.string.corp, R.layout.view_addgame_player, AddGamePlayerView.class.getClass()),
    ADDGAMEPLAYERTWO(R.string.runner, R.layout.view_addgame_player, AddGamePlayerView.class.getClass()),
    ADDGAMEOVERVIEW(R.string.title_overview, R.layout.view_addgame_overview, AddGameOverviewView.class.getClass());

    private int titleRId;
    private int layoutRId;
    private Class classID;

    AddGameEnum(int titleId, int layoutId, Class a ){
        titleRId = titleId ;
        layoutRId = layoutId;
        classID = a;
    }

    public int getTitleID(){
    return titleRId;
}

    public int getLayoutID(){
        return layoutRId;
    }

    public Class getClassID(){
        return classID;
    }
}
