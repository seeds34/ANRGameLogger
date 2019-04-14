package org.seeds.anrgamelogger.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {}, version = 1)
public class GameLoggerDatabase extends RoomDatabase {

  public interface Tables{
    String LOGGED_GAME_OVERVIEWS = "loggedgameoverviews";
    String DECKS = "decks";
    String IDENTITIES = "identities";
    String LOCATIONS = "locations";
    String GAME_NOTES ="gamenotes";
    String PLAYERS = "players";
    String LOGGED_GAME_PLAYERS ="loggedgameplayers";
  }



}
