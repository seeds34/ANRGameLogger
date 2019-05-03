package org.seeds.anrgamelogger.database.buisnessobjects;

import android.provider.BaseColumns;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteType;
import org.seeds.anrgamelogger.database.GameLoggerDatabase.Tables;
import org.seeds.anrgamelogger.network.Card;

/**
 * Created by user on 07/01/2018.
 */

@StorIOSQLiteType(table= Tables.IDENTITIES)
public class Identity {

      @StorIOSQLiteColumn(name = Identity.IdentitiesColumns.IDENTITY_NAME)
      public String name;

      @StorIOSQLiteColumn(name = Identity.IdentitiesColumns.IDENTITY_SIDE)
      public String side_code;

       @StorIOSQLiteColumn(name = Identity.IdentitiesColumns.IDENTITY_FACTION)
       public String faction_code;

       @StorIOSQLiteColumn(name = Identity.IdentitiesColumns.ROTATED_FLAG)
       public String roatated_flag = "N";

      @StorIOSQLiteColumn(name = Identity.IdentitiesColumns.NRDB_CODE)
      public String code;

     @StorIOSQLiteColumn(name = Identity.IdentitiesColumns.IMAGE_BIT_ARRAY)
     public byte[] imageByteArrayOutputStream = null;

      @StorIOSQLiteColumn(name = Identity.IdentitiesColumns.NRDB_PACK_CODE)
      public String pack_code;

      @StorIOSQLiteColumn(name = Identity.IdentitiesColumns.POSTION_IN_PACK)
      public String pos;

    @StorIOSQLiteColumn(name = BaseColumns._ID, key = true)
    public Integer rowid;

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

    public Integer getRowid() {
        return rowid;
    }

    public void setRowid(Integer rowid) {
        this.rowid = rowid;
    }

  public interface IdentitiesColumns {
      String IDENTITY_NAME = "name";
      String IDENTITY_FACTION = "faction";
      String IDENTITY_SIDE = "side";
      String ROTATED_FLAG = "rotated";
      String NRDB_CODE = "nrdbcode";
      String IMAGE_BIT_ARRAY = "imagedata";
      String POSTION_IN_PACK = "postioninpack";
      String NRDB_PACK_CODE = "nrdbpackcode";
  }
}
