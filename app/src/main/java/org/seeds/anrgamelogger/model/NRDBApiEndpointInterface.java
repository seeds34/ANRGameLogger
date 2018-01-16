package org.seeds.anrgamelogger.model;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by Tomas Seymour-Turner on 09/01/2018.
 */

public interface NRDBApiEndpointInterface {

  @GET("cards")
  Single<IdentitiesData> getAllIdentities();




}
