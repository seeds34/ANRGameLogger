package org.seeds.anrgamelogger.gamedetail.views;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.seeds.anrgamelogger.R;

/**
 * Created by Tomas Seymour-Turner on 29/03/2017.
 */

public class GameDetailNoteView extends FrameLayout {

    public GameDetailNoteView(Activity activity) {
        super(activity);
    }

    public void onCreate(Bundle savedInstanceState) {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(this.getClass().getName(),"Inflatting");
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.view_gamedetail_notes,container,false);
    }
}
