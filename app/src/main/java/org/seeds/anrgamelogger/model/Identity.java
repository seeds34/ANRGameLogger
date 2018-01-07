package org.seeds.anrgamelogger.model;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverType;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract.IdentitiesColumns;

/**
 * Created by user on 07/01/2018.
 */

@StorIOContentResolverType(uri = "content://" + IdentitiesContract.CONTENT_AUTHORITY + "/" + IdentitiesContract.PATH_IDENTITIES)
public class Identity {


      @StorIOContentResolverColumn(name = IdentitiesColumns.IDENTITY_NAME, key = true)
      String name;
      @StorIOContentResolverColumn(name = IdentitiesColumns.IDENTITY_SIDE)
      String side;
      @StorIOContentResolverColumn(name = IdentitiesColumns.IDENTITY_FACTION)
      String faction;
      @StorIOContentResolverColumn(name = IdentitiesColumns.ROTATED_FLAG)
      String roatated_flag;
      @StorIOContentResolverColumn(name = IdentitiesColumns.NRDB_CODE)
      String nrdb_code;
      @StorIOContentResolverColumn(name = IdentitiesColumns.IMAGE_BIT_ARRAY)
      byte[] imageByteArrayOutputStream;

      public Identity(){}

      public Identity(String name, String side, String faction, String roatated_flag, String nrdb_code, byte[] imageByteArrayOutputStream){
            this.name = name;
            this.side = side;
            this.faction = faction;
            this.roatated_flag = roatated_flag;
            this.nrdb_code = nrdb_code;
            this.imageByteArrayOutputStream = imageByteArrayOutputStream;
      }
}
