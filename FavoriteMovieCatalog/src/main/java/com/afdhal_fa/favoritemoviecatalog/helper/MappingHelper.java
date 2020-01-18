package com.afdhal_fa.favoritemoviecatalog.helper;

import android.database.Cursor;

import com.afdhal_fa.favoritemoviecatalog.database.DatabaseContract;
import com.afdhal_fa.favoritemoviecatalog.entity.MovieData;

import java.util.ArrayList;


public class MappingHelper {
    public static ArrayList<MovieData> mapCursorToArrayList(Cursor moviesCursor) {
        ArrayList<MovieData> moviesList = new ArrayList<>();
        while (moviesCursor.moveToNext()) {
            int id = moviesCursor.getInt(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns._ID));
            String title = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.TITLE_MOVIE));
            String description = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.DESCRIPTION_MOVIE));
            String poster = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.POSTER_MOVIE));
            String banner = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.BANNER_MOVIE));
            double rating = moviesCursor.getDouble(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.VOTE_AVERAGE_MOVIE));
            String language = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.ORIGINAL_LANGUAGE));
            String favorite = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.FAVORITE));
            moviesList.add(new MovieData(id, title, description, poster,banner,rating,language,favorite));
        }
        return moviesList;
    }

    public static MovieData mapCursorToObject(Cursor moviesCursor) {
        moviesCursor.moveToFirst();
        int id = moviesCursor.getInt(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns._ID));
        String title = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.TITLE_MOVIE));
        String description = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.DESCRIPTION_MOVIE));
        String poster = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.POSTER_MOVIE));
        String banner = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.BANNER_MOVIE));
        double rating = moviesCursor.getDouble(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.VOTE_AVERAGE_MOVIE));
        String language = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.ORIGINAL_LANGUAGE));
        String favorite = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.FAVORITE));
        return new MovieData(id, title, description, poster, banner, rating, language, favorite);
    }
}
