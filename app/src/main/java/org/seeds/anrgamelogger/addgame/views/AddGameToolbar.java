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

  public Observable<Object> save(){
    return temp;
  }

}
