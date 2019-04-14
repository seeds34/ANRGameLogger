package org.seeds.anrgamelogger.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import org.seeds.anrgamelogger.room.GameLoggerDatabase.Tables;

/**
 * Created by user on 07/01/2018.
 */

@Entity(tableName = Tables.IDENTITIES)
public class Identity {

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

    @PrimaryKey
    @NonNull
      @ColumnInfo(name = IdentitiesColumns.IDENTITY_NAME)
      public String name;

      @ColumnInfo(name = IdentitiesColumns.IDENTITY_SIDE)
      public String side_code;

       @ColumnInfo(name = IdentitiesColumns.IDENTITY_FACTION)
       public String faction_code;

       @ColumnInfo(name = IdentitiesColumns.ROTATED_FLAG)
       public String roatated_flag;

      @ColumnInfo(name = IdentitiesColumns.NRDB_CODE)
      public String code;

     @ColumnInfo(name = IdentitiesColumns.IMAGE_BIT_ARRAY)
     public byte[] imageByteArrayOutputStream;

      @ColumnInfo(name = IdentitiesColumns.NRDB_PACK_CODE)
      public String pack_code;

      @ColumnInfo(name = IdentitiesColumns.POSTION_IN_PACK)
      public String pos;

  @ColumnInfo(name = IdentitiesColumns.IMAGE_BIT_ARRAY)
  byte[] imageByteArray;

  public Identity(@NonNull String name, String side_code, String faction_code,
      String roatated_flag, String code, byte[] imageByteArrayOutputStream,
      String pack_code, String pos, byte[] imageByteArray) {
    this.name = name;
    this.side_code = side_code;
    this.faction_code = faction_code;
    this.roatated_flag = roatated_flag;
    this.code = code;
    this.imageByteArrayOutputStream = imageByteArrayOutputStream;
    this.pack_code = pack_code;
    this.pos = pos;
    this.imageByteArray = imageByteArray;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
    this.name = name;
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

  public String getRoatated_flag() {
    return roatated_flag;
  }

  public void setRoatated_flag(String roatated_flag) {
    this.roatated_flag = roatated_flag;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public byte[] getImageByteArrayOutputStream() {
    return imageByteArrayOutputStream;
  }

  public void setImageByteArrayOutputStream(byte[] imageByteArrayOutputStream) {
    this.imageByteArrayOutputStream = imageByteArrayOutputStream;
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

  public byte[] getImageByteArray() {
    return imageByteArray;
  }

  public void setImageByteArray(byte[] imageByteArray) {
    this.imageByteArray = imageByteArray;
  }
}
