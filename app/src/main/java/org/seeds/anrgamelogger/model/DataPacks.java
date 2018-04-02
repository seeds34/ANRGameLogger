package org.seeds.anrgamelogger.model;

import com.squareup.moshi.Json;
import java.util.Map;

/**
 * Created by Tomas Seymour-Turner on 02/04/2018.
 */

public class DataPacks {


  Map<String, String> packs;

  @Json(name = "pack_code")
  String pack_code;

  @Json(name = "ffg_code")
  String ffg_code;

  public DataPacks(){}

  public DataPacks(String pack_codeIn, String ffg_codeIn){
    pack_code = pack_codeIn;
    ffg_code = ffg_codeIn;
  }

  public String getFfg_code(){
    return ffg_code;
  }

  public String getPack_code(){
    return pack_code;
  }

}
