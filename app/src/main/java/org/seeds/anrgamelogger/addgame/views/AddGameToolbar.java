package org.seeds.anrgamelogger.addgame.views;

import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import org.seeds.anrgamelogger.R;

public class AddGameToolbar {

  private final PublishSubject<Object> temp = PublishSubject.create();

  @BindView(R.id.toolbar)
  public Toolbar toolbar;

//  public void test() {
//    toolbar.setOnMenuItemClickListener(
//        new Toolbar.OnMenuItemClickListener() {
//          @Override
//          public boolean onMenuItemClick(MenuItem item) {
//
//            int id = item.getItemId();
//
//            //noinspection SimplifiableIfStatement
//            if (id == R.id.action_save) {
//              save();
//              return true;
//            }
//
//            return false;
//          }
//
//
//        });
//
//    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//
//        new AlertDialog.Builder(activity)
//            .setTitle("Discard Changes?")
//            //.setMessage("All changes will be lost if you leave.")
//
//            // Specifying a listener allows you to take an action before dismissing the dialog.
//            // The dialog is automatically dismissed when a dialog button is clicked.
//            .setPositiveButton("DISCARD", new DialogInterface.OnClickListener() {
//              public void onClick(DialogInterface dialog, int which) {
//                activity.finish();
//              }
//            })
//
//            // A null listener allows the button to dismiss the dialog and take no further action.
//            .setNegativeButton("CANCEL", null)
//            //.setIcon(android.R.drawable.ic_dialog_alert)
//            .show();
//      }
//    });
//
//
//  }

  public Observable<Object> save(){
    return temp;
  }

}
