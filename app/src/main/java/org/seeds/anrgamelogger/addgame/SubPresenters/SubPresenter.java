package org.seeds.anrgamelogger.addgame.SubPresenters;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class SubPresenter {

    protected View view;
    private Activity activity;

    public SubPresenter(Activity activity, View view) {
        this.activity = activity;
        this.view = view;
        onCreateView();
    }

    public View getView() {
        return view;
    }

    public abstract void onCreateView();

}
