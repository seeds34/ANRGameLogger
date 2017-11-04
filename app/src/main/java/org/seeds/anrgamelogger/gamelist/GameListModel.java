package org.seeds.anrgamelogger.gamelist;

import android.content.ContentResolver;

import org.seeds.anrgamelogger.model.LocalLoggedGame;
import org.seeds.anrgamelogger.model.LoggedGameList;

import java.util.ArrayList;

/**
 * Created by Tomas Seymour-Turner on 04/11/2017.
 */

public class GameListModel {

    private ContentResolver contentResolver;

    public GameListModel(ContentResolver CR){
        contentResolver = CR;
    }

    public ArrayList<LocalLoggedGame> createList(){
        ArrayList<LocalLoggedGame> ret = null;

        LoggedGameList lgl = new LoggedGameList(contentResolver);
        ret = lgl.getLoggedGameList(25);

        return ret;
    }
}
