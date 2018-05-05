package org.seeds.anrgamelogger.addgame.subpresenters;

import android.app.Activity;
import android.view.View;

import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.addgame.views.AddGameOverviewView;
import org.seeds.anrgamelogger.model.IdentityList;

import io.reactivex.Observable;

public class OverviewSubPresenter extends SubPresenter{

    private AddGameOverviewView view;

    public OverviewSubPresenter(Activity activity, AddGameOverviewView view){
        super(activity, activity.getString(R.string.title_overview));
        this.view = view;
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void setUpIdentitySpiner(IdentityList oneSidedList) {}

    @Override
    public void onCreateView(){

    }

    public Observable<Object> observeSave(){
        return view.save();
    }
}
