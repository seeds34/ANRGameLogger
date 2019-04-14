package org.seeds.anrgamelogger.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import org.seeds.anrgamelogger.room.GameLoggerDatabase.Tables;

@Entity(tableName = Tables.DECKS)
public class Deck {

  public interface DecksColumns{
    String DECK_NAME= "name";
    String DECK_VERSION= "version";
    String DECK_ARCHETYPE= "archetype";
    String DECK_IDENTITY = "identity";
    String NRDB_LINK= "nrdblink";
  }

  @PrimaryKey
  @NonNull
  @ColumnInfo(name = DecksColumns.DECK_NAME)
  public String name;

  @PrimaryKey
  @ColumnInfo(name = DecksColumns.DECK_VERSION)
  public String version;

  @ColumnInfo(name = DecksColumns.DECK_ARCHETYPE)
  public String archetype;

  @ColumnInfo(name = DecksColumns.NRDB_LINK)
  public String nrdbLink;

  @PrimaryKey
  @ColumnInfo(name = DecksColumns.DECK_IDENTITY)
  public String identityName;

  public Deck(@NonNull String name, String version, String archetype, String nrdbLink,
      String identity_rowno) {
    this.name = name;
    this.version = version;
    this.archetype = archetype;
    this.nrdbLink = nrdbLink;
    this.identityName = identity_rowno;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
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

  public String getIdentityName() {
    return identityName;
  }

  public void setIdentityName(String identityName) {
    this.identityName = identityName;
  }
}
