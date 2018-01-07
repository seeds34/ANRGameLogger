package org.seeds.anrgamelogger.model;

import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;

/**
 * Created by user on 07/01/2018.
 */

@StorIOSQLiteType(table = "Identites")
public class Identites {


      @StorIOSQLiteColumn(name = "name")
              String name;

      String side;
      String faction;
      String roatated_flag;
      String nrdb_code;
      byte[] imageByteArrayOutputStream;

              values.put(IdentitiesContract.IdentitiesColumns.IDENTITY_NAME, name);
                    values.put(IdentitiesContract.IdentitiesColumns.IDENTITY_SIDE, side);
                    values.put(IdentitiesContract.IdentitiesColumns.IDENTITY_FACTION, faction);
                    values.put(IdentitiesContract.IdentitiesColumns.ROTATED_FLAG, "N");
                    values.put(IdentitiesContract.IdentitiesColumns.NRDB_CODE, code);
                    values.put(IdentitiesContract.IdentitiesColumns.IMAGE_BIT_ARRAY, imageByteArrayOutputStream.toByteArray());


}
