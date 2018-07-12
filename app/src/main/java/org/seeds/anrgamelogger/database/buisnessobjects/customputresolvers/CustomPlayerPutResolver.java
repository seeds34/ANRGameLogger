package org.seeds.anrgamelogger.database.buisnessobjects.customputresolvers;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import com.pushtorefresh.storio3.contentresolver.operations.put.DefaultPutResolver;
import com.pushtorefresh.storio3.contentresolver.queries.InsertQuery;
import com.pushtorefresh.storio3.contentresolver.queries.UpdateQuery;

import org.seeds.anrgamelogger.buisnessobjects.Player;

public class CustomPlayerPutResolver extends DefaultPutResolver<Player> {
  /**
   * {@inheritDoc}
   */
  @Override
  @NonNull
  public InsertQuery mapToInsertQuery(@NonNull Player object) {
    return InsertQuery.builder()
        .uri("content://org.seeds.anrgl.procider/players")
        .build();}

  /**
   * {@inheritDoc}
   */
  @Override
  @NonNull
  public UpdateQuery mapToUpdateQuery(@NonNull Player object) {
    return UpdateQuery.builder()
        .uri("content://org.seeds.anrgl.procider/players")
        .where("playername = ?")
        .whereArgs(object.name)
        .build();}

  /**
   * {@inheritDoc}
   */
  @Override
  @NonNull
  public ContentValues mapToContentValues(@NonNull Player object) {
    ContentValues contentValues = new ContentValues(4);

    contentValues.put("playername", object.name);
    contentValues.put("jnetid", object.jnetName);
    contentValues.put("nickname", object.nickName);
    //contentValues.put("_id", object.rowid);

    return contentValues;
  }
}
