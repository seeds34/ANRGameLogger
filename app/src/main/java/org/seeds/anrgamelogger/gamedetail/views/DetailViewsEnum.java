package org.seeds.anrgamelogger.gamedetail.views;

import org.seeds.anrgamelogger.R;

/**
 * Created by user on 04/12/2017.
 */

public enum DetailViewsEnum {

    GAMEOVERVIEW(R.string.gameoverview_title, R.layout.view_gamedetail_overview),
    GAMESTATS( R.string.gamestats_title, R.layout.view_gamedetail_notes);

    private int titleRId;
    private int layoutRId;

    DetailViewsEnum(int titleId, int layoutId){
        titleRId = titleId ;
        layoutRId = layoutId;
    }

    public int getTitleID(){
    return titleRId;
}

    public int getLayoutID(){
        return layoutRId;
    }
}
