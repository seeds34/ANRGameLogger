package org.seeds.anrgamelogger.application;

import org.seeds.anrgamelogger.model.CardList;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by Tomas Seymour-Turner on 09/01/2018.
 */

public interface NRDBApiEndpointInterface {

  @GET("cards")
  Single<CardList> getAllIdentities();

  @GET("packs")
  Single<DataPackList> getAllDataPacks();


}
