package org.seeds.anrgamelogger.model;

import android.util.Log;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGameOverview;
import org.seeds.anrgamelogger.database.buisnessobjects.LoggedGamePlayer;


public class LoggedGameValidator {

  private static final String LOG_TAG = LoggedGameValidator.class.getSimpleName();

  private String validationMessage;

  public boolean validateGame(LoggedGameOverview overview, LoggedGamePlayer runner, LoggedGamePlayer corp){

    validationMessage = "No Issue";
    boolean valid = false;

        if(nameNotNull(runner)){
          if(nameNotNull(corp)){
            if(isWinValid(overview,runner,corp)){
              valid = true;
            }else{
              validationMessage = "Invalid win. Side and Win Type do not match or Score is too low";
            }
          }else{
            validationMessage = "Corp Player has no name";
          }
        }else{
          validationMessage = "Runner Player has no name";
        }

    return valid;
  }

  private boolean isWinValid(LoggedGameOverview overview, LoggedGamePlayer runner, LoggedGamePlayer corp){
    //TODO: How to check win type. This is why the calidation needs to be done for the entire game
    //TODO: Breaks when Medtech played as check of Score nad less the n 7 will fail
    //TODO: Need better place to hold WinTypes
    boolean ret = false;

    Log.d(LOG_TAG,".isWinValid(LoggedGameOverview, LoggedGamePlayer, LoggedGamePlayer)\n Win Type: " + overview.getWin_type() + "\n Winning Side: " + overview.getWinning_side() + "\n Runner Score: " + runner.getScore() + "\n Corp Score: " + corp.getScore());

    if("Score".equals(overview.getWin_type())) {
      if ((overview.getWinning_side().equals(runner.getSide()) && runner.getScore() >= 7) || (
          overview.getWinning_side().equals(corp.getSide()) && corp.getScore() >= 7)) {
        ret = true;
      }
      } else if ("Kill".equals(overview.win_type) && "Y".equals(corp.getWin_flag()) && corp.getScore() < 7
          && runner.getScore() < 7) {
      ret = true;
      } else if ("Mill".equals(overview.win_type) && "Y".equals(runner.getWin_flag()) && corp.getScore() < 7
          && runner.getScore() < 7) {
      ret = true;
      }

      return ret;
  }

  private boolean nameNotNull(LoggedGamePlayer player){
    Log.d(LOG_TAG,".nameNotNull(LoggedGamePlayer): Player Name is: " + player.getPlayer_name());

    boolean ret = false;
    if(player.getPlayer_name() != null && !"".equals(player.getPlayer_name()) ){
      ret = true;
    }
    return ret;
  }

  private boolean isNullScore(LoggedGamePlayer player){
    return player.getScore() != null;
  }

  public String getValidationMessage(){
    return validationMessage;
  }






}
