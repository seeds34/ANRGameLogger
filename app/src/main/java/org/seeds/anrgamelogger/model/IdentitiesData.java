package org.seeds.anrgamelogger.model;

import com.squareup.moshi.Json;
import java.util.List;

/**
 * Created by Tomas Seymour-Turner on 10/01/2018.
 */

public class IdentitiesData {

  @Json(name = "data")
  public List<Identity> identities;


}
