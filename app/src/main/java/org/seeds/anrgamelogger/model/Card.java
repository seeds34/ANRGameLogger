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
public class Card{

      @Json(name = "title")
      @StorIOContentResolverColumn(name = IdentitiesColumns.IDENTITY_NAME, key = true)
      String title;

      @Json(name = "side_code")
      @StorIOContentResolverColumn(name = IdentitiesColumns.IDENTITY_SIDE)
      String side_code;

      @Json(name = "faction_code")
      @StorIOContentResolverColumn(name = IdentitiesColumns.IDENTITY_FACTION)
      String faction_code;

     @StorIOContentResolverColumn(name = IdentitiesColumns.ROTATED_FLAG)
     String roatated_flag = "N";

      @Json(name = "code")
      @StorIOContentResolverColumn(name = IdentitiesColumns.NRDB_CODE)
      String code;

     @StorIOContentResolverColumn(name = IdentitiesColumns.IMAGE_BIT_ARRAY)
     byte[] imageByteArrayOutputStream = null;

      @Json(name = "type_code")
      String type_code;

      @Json(name = "pack_code")
      String pack_code;

      public Card(){}

      public Card(String name, String side, String faction, String roatated_flag, String nrdb_code, byte[] imageByteArrayOutputStream, String type_code, String packCode){
            this.title = name;
            this.side_code = side;
            this.faction_code = faction;
            this.roatated_flag = roatated_flag;
            this.code = nrdb_code;
            this.imageByteArrayOutputStream = imageByteArrayOutputStream;
            this.type_code = type_code;
            this.pack_code = pack_code;
      }

      public String toString(){
            return "Title: " + title + " | Type Code: " + type_code + " | Side Code: " + side_code + " | Faction Code: " + faction_code + " | Roatated Flag: " + roatated_flag + " | NRDB Code: " + code;
      }

      public void setImageByteArray(byte[] in){
         imageByteArrayOutputStream = in;
      }

      public String getCode(){
          return code;
      }

      public byte[] getImageByteArrayOutputStream(){
          return imageByteArrayOutputStream;
      }

      public String getName(){
        return title;
      }

      public String getSide_code(){
        return side_code;
      }

      public String getPack_code(){
          return pack_code;
      }

      public void setRotted(String rot){
          roatated_flag = rot;
      }
}
