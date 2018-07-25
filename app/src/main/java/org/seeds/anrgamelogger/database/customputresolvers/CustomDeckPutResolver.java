package org.seeds.anrgamelogger.database.customputresolvers;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import com.pushtorefresh.storio3.contentresolver.operations.put.DefaultPutResolver;
import com.pushtorefresh.storio3.contentresolver.queries.InsertQuery;
import com.pushtorefresh.storio3.contentresolver.queries.UpdateQuery;

import org.seeds.anrgamelogger.database.buisnessobjects.Deck;

public class CustomDeckPutResolver extends DefaultPutResolver<Deck> {
/**
 * {@inheritDoc}
 */
@Override
@NonNull
public InsertQuery mapToInsertQuery(@NonNull Deck object) {
    return InsertQuery.builder()
    .uri("content://org.seeds.anrgl.procider/decks")
    .build();}

/**
 * {@inheritDoc}
 */
@Override
@NonNull
public UpdateQuery mapToUpdateQuery(@NonNull Deck object) {
    return UpdateQuery.builder()
    .uri("content://org.seeds.anrgl.procider/decks")
    .where("name = ?")
    .whereArgs(object.name)
    .build();}

/**
 * {@inheritDoc}
 */
@Override
@NonNull
public ContentValues mapToContentValues(@NonNull Deck object) {
    ContentValues contentValues = new ContentValues(6);

    contentValues.put("name", object.name);
    contentValues.put("version", object.version);
    contentValues.put("archetype", object.archetype);
    contentValues.put("nrdblink", object.nrdbLink);
    //ontentValues.put("_id", object.rowid);
    contentValues.put("identity", object.identity_rowno);

    return contentValues;
    }
    }

