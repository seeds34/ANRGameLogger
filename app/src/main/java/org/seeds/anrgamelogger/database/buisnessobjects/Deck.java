package org.seeds.anrgamelogger.database.buisnessobjects;

import android.provider.BaseColumns;

import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverType;
import org.seeds.anrgamelogger.database.contracts.DecksContract;
import org.seeds.anrgamelogger.database.contracts.DecksContract.DecksColumns;

@StorIOContentResolverType(uri = "content://" + DecksContract.CONTENT_AUTHORITY + "/" + DecksContract.PATH_DECKS)
public class Deck {

  @StorIOContentResolverColumn(name = DecksColumns.DECK_NAME, key = true)
  public String name;

  @StorIOContentResolverColumn(name = DecksColumns.DECK_VERSION)
  public String version;

  @StorIOContentResolverColumn(name = DecksColumns.DECK_ARCHETYPE)
  public String archetype;

  @StorIOContentResolverColumn(name = DecksColumns.NRDB_LINK)
  public String nrdbLink;

  @StorIOContentResolverColumn(name = BaseColumns._ID)
  public int rowid;

  @StorIOContentResolverColumn(name = DecksColumns.DECK_IDENTITY)
  public int identity_rowno;

  public Deck(){}

  public Deck(String name) {
    this.name = name;
  }

  public Deck(String name, String version) {
    this.name = name;
    this.version = version;
  }

  public Deck(String name, String version, int identity_rowno) {
    this.name = name;
    this.version = version;
    this.identity_rowno = identity_rowno;
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

  public int getRowid() {
    return rowid;
  }

  public void setRowid(int rowid) {
    this.rowid = rowid;
  }
}
