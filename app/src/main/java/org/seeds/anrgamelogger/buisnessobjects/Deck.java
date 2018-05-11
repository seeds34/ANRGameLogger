package org.seeds.anrgamelogger.buisnessobjects;

import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverType;
import org.seeds.anrgamelogger.database.contracts.DecksContract;
import org.seeds.anrgamelogger.database.contracts.DecksContract.DecksColumns;

@StorIOContentResolverType(uri = "content://" + DecksContract.CONTENT_AUTHORITY + "/" + DecksContract.PATH_DECKS)
public class Deck {

  @StorIOContentResolverColumn(name = DecksColumns.DECK_NAME, key = true)
  String name;

  @StorIOContentResolverColumn(name = DecksColumns.DECK_VERSION)
  String version;

  @StorIOContentResolverColumn(name = DecksColumns.DECK_ARCHETYPE)
  String archetype;

  @StorIOContentResolverColumn(name = DecksColumns.NRDB_LINK)
  String nrdbLink;
  //String identity;

  public Deck(){}

  public Deck(String name) {
    this.name = name;
  }

  public Deck(String name, String version) {
    this.name = name;
    this.version = version;
  }

  public Deck(String name, String version, String archetype, String nrdbLink) {
    this.name = name;
    this.version = version;
    this.archetype = archetype;
    this.nrdbLink = nrdbLink;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getArchetype() {
    return archetype;
  }

  public void setArchetype(String archetype) {
    this.archetype = archetype;
  }

  public String getNrdbLink() {
    return nrdbLink;
  }

  public void setNrdbLink(String nrdbLink) {
    this.nrdbLink = nrdbLink;
  }
}
