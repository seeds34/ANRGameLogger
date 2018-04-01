package org.seeds.anrgamelogger.model;

import com.squareup.moshi.Json;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Tomas Seymour-Turner on 10/01/2018.
 */

public class CardList {

  @Json(name = "data")
  public List<Card> identities;

  public List<Card> getIdentities(){
    //return identities.stream().filter(c -> c.getSide_code().equals("identity")).collect(Collectors.toList(Collectors.toList()));
    return identities;
  }


}
