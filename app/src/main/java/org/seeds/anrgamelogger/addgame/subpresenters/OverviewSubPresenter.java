package org.seeds.anrgamelogger.addgame.subpresenters;

import android.app.Activity;

import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.addgame.views.AddGameOverviewView;
import org.seeds.anrgamelogger.model.IdentityList;

import io.reactivex.Observable;

public class OverviewSubPresenter extends SubPresenter{

    public OverviewSubPresenter(Activity activity, AddGameOverviewView view){
        super(activity, view, activity.getString(R.string.title_overview));
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
