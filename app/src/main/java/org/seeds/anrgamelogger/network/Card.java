package org.seeds.anrgamelogger.network;
import com.squareup.moshi.Json;

/**
 * Created by user on 07/01/2018.
 */

public class Card{

      @Json(name = "title")
      String name;

      @Json(name = "side_code")
      String side_code;

       @Json(name = "faction_code")
       String faction_code;

      @Json(name = "code")
      String code;

      @Json(name = "type_code")
      String type_code;

      @Json(name = "pack_code")
      String pack_code;

      @Json(name = "position")
      String pos;

      public Card(){}

  public Card(String name, String side_code, String faction_code, String code,
      String type_code, String pack_code, String pos) {
    this.name = name;
    this.side_code = side_code;
    this.faction_code = faction_code;
    this.code = code;
    this.type_code = type_code;
    this.pack_code = pack_code;
    this.pos = pos;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
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

  @Override
  public String toString() {
    return "Card{" +
        "name='" + name + '\'' +
        ", side_code='" + side_code + '\'' +
        ", faction_code='" + faction_code + '\'' +
        ", code='" + code + '\'' +
        ", type_code='" + type_code + '\'' +
        ", pack_code='" + pack_code + '\'' +
        ", pos='" + pos + '\'' +
        '}';
  }
}
