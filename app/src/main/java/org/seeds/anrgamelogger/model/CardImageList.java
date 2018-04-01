package org.seeds.anrgamelogger.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by user on 31/03/2018.
 */

public class CardImageList {

    public List<CardImage> cardImages;

    public List<CardImage> getCardImages(){
        //return identities.stream().filter(c -> c.getSide_code().equals("identity")).collect(Collectors.toList(Collectors.toList()));
        return cardImages;
    }

}
