package org.seeds.anrgamelogger.application.model;

import com.squareup.moshi.Json;

import org.seeds.anrgamelogger.application.network.Card;

import java.util.List;

/**
 * Created by Tomas Seymour-Turner on 10/01/2018.
 */

public class CardList {

  @Json(name = "data")
  public List<Card> cards;

  public List<Card> getCards(){
    //return cards.stream().filter(c -> c.getSide_code().equals("identity")).collect(Collectors.toList(Collectors.toList()));
    return cards;
  }


}
