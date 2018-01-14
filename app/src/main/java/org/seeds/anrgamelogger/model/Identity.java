package org.seeds.anrgamelogger.model;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverType;
import com.squareup.moshi.Json;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract.IdentitiesColumns;

/**
 * Created by user on 07/01/2018.
 */

@StorIOContentResolverType(uri = "content://" + IdentitiesContract.CONTENT_AUTHORITY + "/" + IdentitiesContract.PATH_IDENTITIES)
public class Identity {


      @Json(name = "title")
      @StorIOContentResolverColumn(name = IdentitiesColumns.IDENTITY_NAME, key = true)
      String title;

      @Json(name = "side_code")
      @StorIOContentResolverColumn(name = IdentitiesColumns.IDENTITY_SIDE)
      String side_code;

      @Json(name = "faction_code")
      @StorIOContentResolverColumn(name = IdentitiesColumns.IDENTITY_FACTION)
      String faction_code;

     // @StorIOContentResolverColumn(name = IdentitiesColumns.ROTATED_FLAG)
      transient String roatated_flag;

      @Json(name = "code")
      @StorIOContentResolverColumn(name = IdentitiesColumns.NRDB_CODE)
      String code;

     // @StorIOContentResolverColumn(name = IdentitiesColumns.IMAGE_BIT_ARRAY)
      transient byte[] imageByteArrayOutputStream;

      @Json(name = "type_code")
      String type_code;

      public Identity(){}

      public Identity(String name, String side, String faction, String roatated_flag, String nrdb_code, byte[] imageByteArrayOutputStream, String type_code){
            this.title = name;
            this.side_code = side;
            this.faction_code = faction;
            this.roatated_flag = roatated_flag;
            this.code = nrdb_code;
            this.imageByteArrayOutputStream = imageByteArrayOutputStream;
            this.type_code = type_code;
      }

      public String toString(){
            return "Title: " + title + " | Side Code: " + side_code + " | Faction Code: " + faction_code + " | Roatated Flag: " + roatated_flag + " | NRDB Code: " + code;
      }
}
