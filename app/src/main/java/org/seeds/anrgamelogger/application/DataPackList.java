package org.seeds.anrgamelogger.application;

import com.squareup.moshi.Json;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 29/03/2018.
 */

class DataPackList {


    @Json(name = "data")
    public List<DataPack> dataPacks;

    public List<DataPack> getDataPacks(){
        //return identities.stream().filter(c -> c.getSide_code().equals("identity")).collect(Collectors.toList(Collectors.toList()));

        Map<String, String> ret =  new HashMap<>();
        for(DataPack dp : dataPacks){
            ret.put(dp.getPack_code(), dp.getFfg_code());
        }
        return dataPacks;
    }

    public Map<String, String> getDataPackMap(){
        //return identities.stream().filter(c -> c.getSide_code().equals("identity")).collect(Collectors.toList(Collectors.toList()));

        Map<String, String> ret =  new HashMap<>();
        for(DataPack dp : dataPacks){
            ret.put(dp.getPack_code(), dp.getFfg_code());
        }
        return ret;
    }


}
