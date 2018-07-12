package org.seeds.anrgamelogger.network;
import com.squareup.moshi.Json;

/**
 * Created by user on 07/01/2018.
 */

//@StorIOContentResolverType(uri = "content://" + IdentitiesContract.CONTENT_AUTHORITY + "/" + IdentitiesContract.PATH_IDENTITIES)
public class Card{

      @Json(name = "title")
      //@StorIOContentResolverColumn(name = IdentitiesColumns.IDENTITY_NAME, key = true)
      String name;

      @Json(name = "side_code")
      //@StorIOContentResolverColumn(name = IdentitiesColumns.IDENTITY_SIDE)
      String side_code;

       @Json(name = "faction_code")
      // @StorIOContentResolverColumn(name = IdentitiesColumns.IDENTITY_FACTION)
       String faction_code;

      @Json(name = "code")
      //@StorIOContentResolverColumn(name = IdentitiesColumns.NRDB_CODE)
      String code;

      @Json(name = "type_code")
      String type_code;

      //@StorIOContentResolverColumn(name = IdentitiesColumns.NRDB_PACK_CODE)
      @Json(name = "pack_code")
      String pack_code;

     // @StorIOContentResolverColumn(name = IdentitiesColumns.POSTION_IN_PACK)
      @Json(name = "position")
      String pos;

      public Card(){}

      public Card(String name, String side, String faction, String roatated_flag, String nrdb_code, byte[] imageByteArrayOutputStream, String type_code, String pack_code, String posIn){
            this.name = name;
            this.side_code = side;
            this.faction_code = faction;
            //this.roatated_flag = roatated_flag;
            this.code = nrdb_code;
            //this.imageByteArrayOutputStream = imageByteArrayOutputStream;
            this.type_code = type_code;
            this.pack_code = pack_code;
            this.pos = posIn;
      }

      public String toString(){
            return  "Title: " + name +
                " | Type Code: " + type_code +
                " | Side Code: " + side_code +
                " | Faction Code: " + faction_code +
                //" | Roatated Flag: " + roatated_flag +
                " | NRDB Code: " + code +
                " | Pack Code " + pack_code +
                " | POS " + pos;
      }

      public String getName() {
        return name;
      }

      public void setName(String title) {
        this.name = title;
      }

      public String getSide_code() {
        return side_code;
      }

      public void setSide_code(String side_code) {
        this.side_code = side_code;
      }

      public String getFaction_code() {
        return faction_code;
      }

      public void setFaction_code(String faction_code) {
        this.faction_code = faction_code;
      }

      public String getCode() {
        return code;
      }

      public void setCode(String code) {
        this.code = code;
      }

      public String getType_code() {
        return type_code;
      }

      public void setType_code(String type_code) {
        this.type_code = type_code;
      }

      public String getPack_code() {
        return pack_code;
      }

      public void setPack_code(String pack_code) {
        this.pack_code = pack_code;
      }

      public String getPos() {
        return pos;
      }

      public void setPos(String pos) {
        this.pos = pos;
      }
}
