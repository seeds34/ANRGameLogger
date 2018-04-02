package org.seeds.anrgamelogger.application;

import com.squareup.moshi.Json;

import org.seeds.anrgamelogger.model.Card;

import java.util.List;

/**
 * Created by user on 29/03/2018.
 */

class DataPackList {


    @Json(name = "data")
    public List<DataPack> dataPacks;

    public List<DataPack> getDataPacks(){
        //return identities.stream().filter(c -> c.getSide_code().equals("identity")).collect(Collectors.toList(Collectors.toList()));
        return dataPacks;
    }

}
