package org.seeds.anrgamelogger.gamedetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.seeds.anrgamelogger.R;

/**
 * Created by Tomas Seymour-Turner on 29/03/2017.
 */

public class GameDetailNoteFragment extends Fragment {

    public GameDetailNoteFragment() {
     //   super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(this.getClass().getName(),"Inflatting");
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_game_detail_notes,container,false);
    }
}
