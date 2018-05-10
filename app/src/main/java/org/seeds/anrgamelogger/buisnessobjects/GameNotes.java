package org.seeds.anrgamelogger.buisnessobjects;

import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverType;

import org.seeds.anrgamelogger.database.contracts.GameNotesContract;
import org.seeds.anrgamelogger.database.contracts.PlayersContract;

@StorIOContentResolverType(uri = "content://" + GameNotesContract.CONTENT_AUTHORITY + "/" + GameNotesContract.PATH_GAME_NOTES)
public class GameNotes {
}
