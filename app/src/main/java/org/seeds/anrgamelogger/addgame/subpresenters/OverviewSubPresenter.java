package org.seeds.anrgamelogger.addgame.subpresenters;

import android.app.Activity;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;

import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.addgame.views.AddGameOverviewView;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGamePlayer;
import org.seeds.anrgamelogger.model.IdentityList;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameOverview;

import io.reactivex.Observable;

public class OverviewSubPresenter extends SubPresenter{

    private AddGameOverviewView view;
    private int gameNumber;

    public OverviewSubPresenter(Activity activity, AddGameOverviewView view, ArrayList<String> locationList, int gameNumber){
        super(activity, activity.getString(R.string.title_overview));
        this.view = view;
        this.view.setUpLocationAutoComplete(locationList);
        this.gameNumber = gameNumber;
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

    @Override
    public Observable<Object> observeSave(){
        return view.save();
    }

    @Override
    public LoggedGameOverview getGameOverview(){

        return new LoggedGameOverview(
            view.getLocation(),
            view.getPlayedDate(),
            view.getWinType(),
                gameNumber,
                "corp"
        );
    }

    @Override
    public LoggedGamePlayer getPlayerData() {
        return null;
    }

    public void onDateSet(int year, int month, int dayOfMonth){
        month = month + 1;
        String date = dayOfMonth + "/" + month + "/" + year;
        view.setDateText(date);
    }

//    public void onDateClicked (){
//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        view.displayDatePickerDialog(year, month, day);
//    }

}
