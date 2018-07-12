package org.seeds.anrgamelogger.database.buisnessobjects.customputresolvers;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.contentresolver.operations.put.DefaultPutResolver;
import com.pushtorefresh.storio3.contentresolver.queries.InsertQuery;
import com.pushtorefresh.storio3.contentresolver.queries.UpdateQuery;

import org.seeds.anrgamelogger.database.buisnessobjects.Identity;

public class CustomIdentityPutResolver extends DefaultPutResolver<Identity> {

    @Override
    @NonNull
    public InsertQuery mapToInsertQuery(@NonNull Identity object) {
        return InsertQuery.builder()
                .uri("content://org.seeds.anrgl.procider/identities")
                .build();}

    @Override
    @NonNull
    public UpdateQuery mapToUpdateQuery(@NonNull Identity object) {
        return UpdateQuery.builder()
                .uri("content://org.seeds.anrgl.procider/identities")
                .where("name = ?")
                .whereArgs(object.name)
                .build();}

    @Override
    @NonNull
    public ContentValues mapToContentValues(@NonNull Identity object) {
        ContentValues contentValues = new ContentValues(9);

        contentValues.put("name", object.name);
        contentValues.put("side", object.side_code);
        contentValues.put("faction", object.faction_code);
        contentValues.put("rotated", object.roatated_flag);
        contentValues.put("nrdbcode", object.code);
        contentValues.put("imagedata", object.imageByteArrayOutputStream);
        contentValues.put("nrdbpackcode", object.pack_code);
        contentValues.put("postioninpack", object.pos);

        return contentValues;
    }
}

