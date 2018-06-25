package org.seeds.anrgamelogger.buisnessobjects;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import com.pushtorefresh.storio3.contentresolver.operations.put.DefaultPutResolver;
import com.pushtorefresh.storio3.contentresolver.queries.InsertQuery;
import com.pushtorefresh.storio3.contentresolver.queries.UpdateQuery;

public class CustomLocationPutResolver extends DefaultPutResolver<Location> {
  /**
   * {@inheritDoc}
   */
  @Override
  @NonNull
  public InsertQuery mapToInsertQuery(@NonNull Location object) {
    return InsertQuery.builder()
        .uri("content://org.seeds.anrgl.procider/locations")
        .build();}

  /**
   * {@inheritDoc}
   */
  @Override
  @NonNull
  public UpdateQuery mapToUpdateQuery(@NonNull Location object) {
    return UpdateQuery.builder()
        .uri("content://org.seeds.anrgl.procider/locations")
        .where("locationname = ?")
        .whereArgs(object.name)
        .build();}

  /**
   * {@inheritDoc}
   */
  @Override
  @NonNull
  public ContentValues mapToContentValues(@NonNull Location object) {
    ContentValues contentValues = new ContentValues(2);

    contentValues.put("locationname", object.name);
    //contentValues.put("_id", object.rowid);

    return contentValues;
  }
}
