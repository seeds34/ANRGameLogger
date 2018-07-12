package org.seeds.anrgamelogger.database.buisnessobjects;

import android.provider.BaseColumns;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverType;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract.IdentitiesColumns;
import org.seeds.anrgamelogger.network.Card;

/**
 * Created by user on 07/01/2018.
 */

@StorIOContentResolverType(uri = "content://" + IdentitiesContract.CONTENT_AUTHORITY + "/" + IdentitiesContract.PATH_IDENTITIES)
public class Identity {

      @StorIOContentResolverColumn(name = IdentitiesColumns.IDENTITY_NAME, key = true)
      String name;

      @StorIOContentResolverColumn(name = IdentitiesColumns.IDENTITY_SIDE)
      String side_code;

       @StorIOContentResolverColumn(name = IdentitiesColumns.IDENTITY_FACTION)
       String faction_code;

       @StorIOContentResolverColumn(name = IdentitiesColumns.ROTATED_FLAG)
       String roatated_flag = "N";

      @StorIOContentResolverColumn(name = IdentitiesColumns.NRDB_CODE)
      String code;

     @StorIOContentResolverColumn(name = IdentitiesColumns.IMAGE_BIT_ARRAY)
     byte[] imageByteArrayOutputStream = null;

      @StorIOContentResolverColumn(name = IdentitiesColumns.NRDB_PACK_CODE)
      String pack_code;

      @StorIOContentResolverColumn(name = IdentitiesColumns.POSTION_IN_PACK)
      String pos;

    @StorIOContentResolverColumn(name = BaseColumns._ID)
    int rowid;

      public Identity(){}

      public Identity(String name, String side, String faction, String roatated_flag, String nrdb_code, byte[] imageByteArrayOutputStream, String pack_code, String posIn){
            this.name = name;
            this.side_code = side;
            this.faction_code = faction;
            this.roatated_flag = roatated_flag;
            this.code = nrdb_code;
            this.imageByteArrayOutputStream = imageByteArrayOutputStream;
            this.pack_code = pack_code;
            this.pos = posIn;
      }

      public Identity(Card c){
        this.name = c.getName();
        this.side_code = c.getSide_code();
        this.faction_code = c.getFaction_code();
        this.code = c.getCode();
        this.pack_code = c.getPack_code();
        this.pos = c.getPos();
      }

      public Identity(CardImage ci){
        this.code = ci.getCode();
        this.imageByteArrayOutputStream = ci.getImageByteArray();
      }

      public String toString(){
            return  "Title: " + name +
                " | Side Code: " + side_code +
                " | Faction Code: " + faction_code +
                " | Roatated Flag: " + roatated_flag +
                " | NRDB Code: " + code +
                " | Pack Code " + pack_code +
                " | POS " + pos;
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
        return name;
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

      public String getPos() {
    return pos;
  }

      public void setPos(String pos) {
    this.pos = pos;
  }

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }
}
