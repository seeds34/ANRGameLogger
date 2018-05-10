package org.seeds.anrgamelogger.buisnessobjects;

import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverType;

import org.seeds.anrgamelogger.database.contracts.LocationsContract;
import org.seeds.anrgamelogger.database.contracts.LoggedGamesContract;

@StorIOContentResolverType(uri = "content://" + LocationsContract.CONTENT_AUTHORITY + "/" + LocationsContract.PATH_LOCATIONS)
public class Location {
}
