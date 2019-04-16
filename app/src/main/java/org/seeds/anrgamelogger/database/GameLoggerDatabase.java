package org.seeds.anrgamelogger.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import org.seeds.anrgamelogger.database.dao.DeckDao;
import org.seeds.anrgamelogger.database.dao.IdentityDao;
import org.seeds.anrgamelogger.database.dao.LocationDao;
import org.seeds.anrgamelogger.database.dao.LoggedGameOverviewDao;
import org.seeds.anrgamelogger.database.dao.LoggedGamePlayersDao;
import org.seeds.anrgamelogger.database.dao.PlayerDao;
import org.seeds.anrgamelogger.database.entities.Deck;
import org.seeds.anrgamelogger.database.entities.Identity;
import org.seeds.anrgamelogger.database.entities.Location;
import org.seeds.anrgamelogger.database.entities.LoggedGameOverview;
import org.seeds.anrgamelogger.database.entities.LoggedGamePlayer;
import org.seeds.anrgamelogger.database.entities.Player;

@Database(entities = {Deck.class, Identity.class, Location.class, LoggedGameOverview.class, LoggedGamePlayer.class, Player.class}, version = 1)
public abstract class GameLoggerDatabase extends RoomDatabase {

  public interface Tables{
    String LOGGED_GAME_OVERVIEWS = "loggedgameoverviews";
    String DECKS = "decks";
    String IDENTITIES = "identities";
    String LOCATIONS = "locations";
    String PLAYERS = "players";
    String LOGGED_GAME_PLAYERS ="loggedgameplayers";
  }

    public abstract DeckDao deckDao();
    public abstract IdentityDao identityDao();
    public abstract LocationDao locationDao();
    public abstract LoggedGameOverviewDao loggedGameOverviewDao();
    public abstract LoggedGamePlayersDao loggedGamePlayersDao();
    public abstract PlayerDao playerDao();






}
