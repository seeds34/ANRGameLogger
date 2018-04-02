package org.seeds.anrgamelogger.application;

import com.squareup.moshi.Json;

/**
 * Created by user on 01/04/2018.
 */

class DataPack {

    @Json(name = "pack_code")
    String pack_code;

    @Json(name = "ffg_code")
    String ffg_code;

    public DataPack(){}

    public DataPack(String pack_codeIn, String ffg_codeIn){
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
