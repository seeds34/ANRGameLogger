package org.seeds.anrgamelogger.database.buisnessobjects;

import android.provider.BaseColumns;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteType;
import org.seeds.anrgamelogger.database.GameLoggerDatabase.Tables;

@StorIOSQLiteType(table= Tables.DECKS)
public class Deck {

  @StorIOSQLiteColumn(name = DecksColumns.DECK_NAME)
  public String name;

  @StorIOSQLiteColumn(name = DecksColumns.DECK_VERSION)
  public String version;

  @StorIOSQLiteColumn(name = DecksColumns.DECK_ARCHETYPE)
  public String archetype;

  @StorIOSQLiteColumn(name = DecksColumns.NRDB_LINK)
  public String nrdbLink;

  @StorIOSQLiteColumn(name = BaseColumns._ID, key = true)
  public Integer rowid;

  @StorIOSQLiteColumn(name = DecksColumns.DECK_IDENTITY)
  public Integer identity_rowno;

  public Deck(){}

  public Deck(String name) {
    this.name = name;
  }

  public Deck(String name, String version) {
    this.name = name;
    this.version = version;
  }

  public Deck(String name, String version, Integer identity_rowno) {
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

  public Integer getRowid() {
    return rowid;
  }

  public void setRowid(Integer rowid) {
    this.rowid = rowid;
  }

  public Integer getIdentity_rowno() {
    return identity_rowno;
  }

  public void setIdentity_rowno(Integer identity_rowno) {
    this.identity_rowno = identity_rowno;
  }

  public String toString(){
    return
        "Name = " + name + "\n" +
            "Version = " + version + "\n" +
            "Archtype = " +  archetype + "\n" +
            "Identity ID = " + identity_rowno + "\n" +
            "NRDB Link = " + nrdbLink + "\n" +
            "Rowid = " + rowid;
  }


  public interface DecksColumns{
      String DECK_NAME= "name";
      String DECK_VERSION= "version";
      String DECK_ARCHETYPE= "archetype";
      String DECK_IDENTITY = "identity";
      String NRDB_LINK= "nrdblink";
  }
}
