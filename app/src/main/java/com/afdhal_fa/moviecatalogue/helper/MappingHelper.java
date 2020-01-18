package com.afdhal_fa.moviecatalogue.helper;

import android.database.Cursor;

import com.afdhal_fa.moviecatalogue.database.DatabaseContract;
import com.afdhal_fa.moviecatalogue.entity.MovieData;

import java.util.ArrayList;


public class MappingHelper {
    public static ArrayList<MovieData> mapCursorToArrayList(Cursor movieCursor) {
        ArrayList<MovieData> moviesList = new ArrayList<>();
        while (movieCursor.moveToNext()) {
            int id = movieCursor.getInt(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns._ID));
            String title = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.TITLE_MOVIE));
            String description = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.DESCRIPTION_MOVIE));
            String poster = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.POSTER_MOVIE));
            String banner = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.BANNER_MOVIE));
            double rating = movieCursor.getDouble(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.VOTE_AVERAGE_MOVIE));
            String language = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.ORIGINAL_LANGUAGE));
            String favorite = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.FAVORITE));
            moviesList.add(new MovieData(id, title, description, poster,banner,rating,language,favorite));
        }
        return moviesList;
    }

    public static MovieData mapCursorToObject(Cursor movieCursor) {
        movieCursor.moveToFirst();
        int id = movieCursor.getInt(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns._ID));
        String title = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.TITLE_MOVIE));
        String description = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.DESCRIPTION_MOVIE));
        String poster = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.POSTER_MOVIE));
        String banner = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.BANNER_MOVIE));
        double rating = movieCursor.getDouble(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.VOTE_AVERAGE_MOVIE));
        String language = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.ORIGINAL_LANGUAGE));
        String favorite = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.FAVORITE));
        return new MovieData(id, title, description, poster, banner, rating, language, favorite);
    }
}
