package org.seeds.anrgamelogger.buisnessobjects;

import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverType;

import org.seeds.anrgamelogger.database.contracts.LoggedGamesContract;
import org.seeds.anrgamelogger.database.contracts.PlayersContract;

@StorIOContentResolverType(uri = "content://" + LoggedGamesContract.CONTENT_AUTHORITY + "/" + LoggedGamesContract.PATH_LOGGED_GAMES)
public class LoggedGame {
}
