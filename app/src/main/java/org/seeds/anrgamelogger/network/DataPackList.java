package org.seeds.anrgamelogger.network;

import com.squareup.moshi.Json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 29/03/2018.
 */

public class DataPackList {


    @Json(name = "data")
    public List<DataPack> dataPacks;

    private static final String LOG_TAG = DataPackList.class.getSimpleName();

    public List<DataPack> getDataPacks(){
        return dataPacks;
    }

    public Map<String, String> getDataPackMap(){
        Map<String, String> ret =  new HashMap<>();
        for(DataPack dp : dataPacks){
            ret.put(dp.getPack_code(), dp.getFfg_code());
        }
        return ret;
    }

    public String getMapping(String x){
        String ret = "null";
        for(DataPack d : dataPacks){
            if(d.pack_code.matches(x)){
                ret = d.getFfg_code();
            }
        }
        return ret;
    }
}
