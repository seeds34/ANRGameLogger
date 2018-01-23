package org.seeds.anrgamelogger.model;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import org.seeds.anrgamelogger.database.contracts.DecksContract;
import org.seeds.anrgamelogger.database.GameLoggerDatabase;
import org.seeds.anrgamelogger.database.contracts.GameNotesContract;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;
import org.seeds.anrgamelogger.database.contracts.LocationsContract;
import org.seeds.anrgamelogger.database.contracts.LoggedGamesContract;
import org.seeds.anrgamelogger.database.contracts.LoggedGamesFlatViewContract;
import org.seeds.anrgamelogger.database.contracts.PlayersContract;

import static org.seeds.anrgamelogger.database.contracts.DecksContract.Deck.buildDecksUri;

/**
 * Created by Tomas Seymour-Turner on 16/05/2017.
 */

public class GameLogggerProvider extends ContentProvider {

    private static final String LOG_TAG = GameLogggerProvider.class.getSimpleName();

    private GameLoggerDatabase dbHelper;
    private static final UriMatcher URIMATCHER = buildUriMatcher();

    private static final int IDENTITIES = 10;
    private static final int IDENTITIES_ID = 11;

    private static final int DECK = 20;
    private static final int DECK_ID = 21;

    private static final int LOCATION = 30;
    private static final int LOCATION_ID = 31;

    private static final int LOGGEDGAME = 40;
    private static final int LOGGEDGAME_ID = 41;

    private static final int LOGGEDGAMEFLATVIEW = 45;
    private static final int LOGGEDGAMEFLATVIEW_ID = 46;

    private static final int PLAYER = 50;
    private static final int PLAYER_ID = 51;

    private static final int GAMENOTES = 60;
    private static final int GAMENOTES_ID = 61;

    //Todo: Read what this does
    private static UriMatcher buildUriMatcher(){
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        String authority = IdentitiesContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, "identities", IDENTITIES );
        matcher.addURI(authority, "identities/*", IDENTITIES_ID );

        authority = DecksContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, "decks", DECK );
        matcher.addURI(authority, "decks/*", DECK_ID );

        authority = LocationsContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, "locations", LOCATION );
        matcher.addURI(authority, "locations/*", LOCATION_ID );

        authority = LoggedGamesContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, "loggedgames", LOGGEDGAME );
        matcher.addURI(authority, "loggedgames/*", LOGGEDGAME_ID );

        authority = LoggedGamesFlatViewContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, "loggedgamesflatview", LOGGEDGAMEFLATVIEW );
        matcher.addURI(authority, "loggedgamesflatview/*", LOGGEDGAMEFLATVIEW_ID );

        authority = PlayersContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, "players", PLAYER );
        matcher.addURI(authority, "players/*", PLAYER_ID );

        authority = GameNotesContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, "gamenotes", GAMENOTES );
        matcher.addURI(authority, "gamenotes/*", GAMENOTES_ID );

        return matcher;

    }

    @Override
    public boolean onCreate() {
        dbHelper = new GameLoggerDatabase(getContext());
        return true;
    }

    @Override
    public String getType( Uri uri) {
        final int MATCH = URIMATCHER.match(uri);
        String ret = null;
        switch (MATCH){
            case LOCATION:
                ret = LocationsContract.Location.CONTENT_TYPE;
                break;
            case LOCATION_ID:
                ret = LocationsContract.Location.CONTENT_ITEM_TYPE;
                break;
            case PLAYER:
                ret = PlayersContract.Player.CONTENT_TYPE;
                break;
            case PLAYER_ID:
                ret = PlayersContract.Player.CONTENT_ITEM_TYPE;
                break;
            case IDENTITIES:
                ret = IdentitiesContract.Identitity.CONTENT_TYPE;
                break;
            case IDENTITIES_ID:
                ret = IdentitiesContract.Identitity.CONTENT_ITEM_TYPE;
                break;
            case DECK:
                ret = DecksContract.Deck.CONTENT_TYPE;
                break;
            case DECK_ID:
                ret = DecksContract.Deck.CONTENT_ITEM_TYPE;
                break;
            case LOGGEDGAME:
                ret = LoggedGamesContract.LoggedGame.CONTENT_TYPE;
                break;
            case LOGGEDGAME_ID:
                ret = LoggedGamesContract.LoggedGame.CONTENT_ITEM_TYPE;
                break;
            case LOGGEDGAMEFLATVIEW:
                ret = LoggedGamesFlatViewContract.LoggedGameFlatView.CONTENT_TYPE;
                break;
            case LOGGEDGAMEFLATVIEW_ID:
                ret = LoggedGamesFlatViewContract.LoggedGameFlatView.CONTENT_ITEM_TYPE;
                break;
            default:break;
        }
        if(ret == null){
            throw new IllegalArgumentException("getType Opration: Unkown Uri " + uri);
        }else{
            return ret;
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public Cursor query( Uri uri,  String[] projection,  String selection,  String[] selectionArgs,  String sortOrder) {

        final SQLiteDatabase db = dbHelper.getReadableDatabase();
        final int MATCH = URIMATCHER.match(uri);
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        Cursor ret = null;
        switch (MATCH) {
            case DECK:
                queryBuilder.setTables(GameLoggerDatabase.Tables.DECKS);
                ret = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case DECK_ID:
                queryBuilder.setTables(GameLoggerDatabase.Tables.DECKS);
                String deck_id = DecksContract.Deck.getDeckId(uri);
                queryBuilder.appendWhere(BaseColumns._ID + " = " + deck_id);
                ret = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case IDENTITIES:
                queryBuilder.setTables(GameLoggerDatabase.Tables.IDENTITIES);
                ret = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case LOCATION:
                queryBuilder.setTables(GameLoggerDatabase.Tables.LOCATIONS);
                ret = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case LOCATION_ID:
                queryBuilder.setTables(GameLoggerDatabase.Tables.LOCATIONS);
                String loc_id = LocationsContract.Location.getLocationId(uri);
                queryBuilder.appendWhere(BaseColumns._ID + " = " + loc_id);
                ret = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PLAYER:
                queryBuilder.setTables(GameLoggerDatabase.Tables.PLAYERS);
                ret = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PLAYER_ID:
                queryBuilder.setTables(GameLoggerDatabase.Tables.PLAYERS);
                String player_id = PlayersContract.Player.getPlayerId(uri);
                queryBuilder.appendWhere(BaseColumns._ID + " = " + player_id);
                ret = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case LOGGEDGAME:
                queryBuilder.setTables(GameLoggerDatabase.Tables.LOGGED_GAMES);
                ret = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case LOGGEDGAMEFLATVIEW:
                queryBuilder.setTables(GameLoggerDatabase.Views.LOGGED_GAMES_FLAT_VIEW);
                ret = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default: break;
        }

       // Cursor ret = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
       // db.close();

        return ret;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {

        final int MATCH = URIMATCHER.match(uri);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        int ret = 0;

        switch (MATCH){
            case IDENTITIES:
                Log.d(LOG_TAG,"Match No: " + String.valueOf(MATCH));
                ret = db.update(GameLoggerDatabase.Tables.LOCATIONS, contentValues, selection, selectionArgs);
                break;
            default:break;

        }

        db.close();
        return ret;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        final int MATCH = URIMATCHER.match(uri);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        Uri ret = null;

        Log.d(LOG_TAG,"URI = " + uri.toString() + " : " + String.valueOf(MATCH));
        Log.d(LOG_TAG, "Values to insert: " + contentValues.toString());
        long recordID;
        switch (MATCH){
            case LOCATION:
                Log.d(LOG_TAG,"Match No: " + String.valueOf(MATCH));
                recordID = db.insertOrThrow(GameLoggerDatabase.Tables.LOCATIONS, null, contentValues);
                ret = LocationsContract.Location.buildLocationUri(String.valueOf(recordID));
                break;

            case PLAYER:
                Log.d(LOG_TAG,"Match No: " + String.valueOf(MATCH));
                recordID = db.insertOrThrow(GameLoggerDatabase.Tables.PLAYERS, null, contentValues);
                ret = PlayersContract.Player.buildPlayersUri(String.valueOf(recordID));
                break;
            case IDENTITIES:
                Log.d(LOG_TAG,"Match No: " + String.valueOf(MATCH));
                recordID = db.insertOrThrow(GameLoggerDatabase.Tables.IDENTITIES, null, contentValues);
                ret = IdentitiesContract.Identitity.buildIdentutyUri(String.valueOf(recordID));
                break;
            case DECK:
                Log.d(LOG_TAG,"Match No: " + String.valueOf(MATCH));
                recordID = db.insertOrThrow(GameLoggerDatabase.Tables.DECKS, null, contentValues);
                ret = buildDecksUri(String.valueOf(recordID));
                break;
            case LOGGEDGAME:
                Log.d(LOG_TAG,"Match No: " + String.valueOf(MATCH));
                recordID = db.insertOrThrow(GameLoggerDatabase.Tables.LOGGED_GAMES, null, contentValues);
                ret = LoggedGamesContract.LoggedGame.buildLoggedGameUri(String.valueOf(recordID));
            break;
            default:break;

        }

        db.close();
        if(ret == null){
            throw new IllegalArgumentException("insert Oprations: Unkown Uri " + uri);
        }else{
            return ret;
        }
    }
}
