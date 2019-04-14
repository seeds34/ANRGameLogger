package org.seeds.anrgamelogger.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import org.seeds.anrgamelogger.database.entities.Deck.DecksColumns;
import org.seeds.anrgamelogger.database.GameLoggerDatabase.Tables;
import org.seeds.anrgamelogger.database.entities.Identity.IdentitiesColumns;

@Entity(tableName = Tables.DECKS, foreignKeys = @ForeignKey(entity = Identity.class, parentColumns = IdentitiesColumns.IDENTITY_NAME, childColumns = DecksColumns.DECK_IDENTITY),
    indices = {@Index(value = {DecksColumns.DECK_NAME, DecksColumns.DECK_VERSION, DecksColumns.DECK_IDENTITY }, unique = true)})
public class Deck {

  public interface DecksColumns{
    String DECK_NAME= "name";
    String DECK_VERSION= "version";
    String DECK_ARCHETYPE= "archetype";
    String DECK_IDENTITY = "identity";
    String NRDB_LINK= "nrdblink";
    String ID = "id";
  }

  @NonNull
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = DecksColumns.ID)
  public int id;

  @NonNull
  @ColumnInfo(name = DecksColumns.DECK_NAME)
  public String name;

  @ColumnInfo(name = DecksColumns.DECK_VERSION)
  public String version;

  @ColumnInfo(name = DecksColumns.DECK_ARCHETYPE)
  public String archetype;

  @ColumnInfo(name = DecksColumns.NRDB_LINK)
  public String nrdbLink;

  @NonNull
  @ColumnInfo(name = DecksColumns.DECK_IDENTITY)
  public String identityName;

  public Deck(@NonNull int id, @NonNull String name, String version, String archetype,
      String nrdbLink, @NonNull String identityName) {
    this.id = id;
    this.name = name;
    this.version = version;
    this.archetype = archetype;
    this.nrdbLink = nrdbLink;
    this.identityName = identityName;
  }

  @NonNull
  public int getId() {
    return id;
  }

  public void setId(@NonNull int id) {
    this.id = id;
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

  @NonNull
  public String getIdentityName() {
    return identityName;
  }

  public void setIdentityName(@NonNull String identityName) {
    this.identityName = identityName;
  }
}
