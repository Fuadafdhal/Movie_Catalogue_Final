package com.afdhal_fa.moviecatalogue.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.afdhal_fa.moviecatalogue.database.movieHelper;

import static com.afdhal_fa.moviecatalogue.database.DatabaseContract.AUTHORITY;
import static com.afdhal_fa.moviecatalogue.database.DatabaseContract.MovieColumns.CONTENT_URI;
import static com.afdhal_fa.moviecatalogue.database.DatabaseContract.MovieColumns.TABLE_NAME;

public class CatalogueProvider extends ContentProvider {
    private static final int MOVIE = 1;
    private static final int MOVIE_ID = 2;
    private movieHelper movieHelper;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    /*
    Uri matcher untuk mempermudah identifier dengan menggunakan integer
    misal
    uri com.dicoding.picodiploma.mynotesapp dicocokan dengan integer 1
    uri com.dicoding.picodiploma.mynotesapp/# dicocokan dengan integer 2
     */
    static {
        // content://com.dicoding.picodiploma.mynotesapp/note
        sUriMatcher.addURI(AUTHORITY, TABLE_NAME, MOVIE);
        // content://com.dicoding.picodiploma.mynotesapp/note/id
        sUriMatcher.addURI(AUTHORITY,
                TABLE_NAME + "/#",
                MOVIE_ID);
    }
    public CatalogueProvider() {
    }

    @Override
    public boolean onCreate() {
        movieHelper = movieHelper.getInstance(getContext());
        movieHelper.open();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case MOVIE:
                cursor = movieHelper.queryAll();
                break;
            case MOVIE_ID:
                cursor = movieHelper.queryById(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long added;
        switch (sUriMatcher.match(uri)) {
            case MOVIE:
                added = movieHelper.insert(values);
                break;
            default:
                added = 0;
                break;
        }

        getContext().getContentResolver().notifyChange(CONTENT_URI, null);

        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deleted;
        switch (sUriMatcher.match(uri)) {
            case MOVIE_ID:
                deleted = movieHelper.deleteById(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }
        getContext().getContentResolver().notifyChange(CONTENT_URI, null);
        return deleted;
    }
}
