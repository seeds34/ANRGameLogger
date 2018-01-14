package org.seeds.anrgamelogger.model;

import java.util.List;

/**
 * Created by Tomas Seymour-Turner on 10/01/2018.
 */

public class Identities {

  //@Json(name = "data")
  public List<Identity> data;

  public List<Identity> getIDS(){
    return data;
  }

  public Identity getID(int pos){
    return data.get(pos);
  }

}
