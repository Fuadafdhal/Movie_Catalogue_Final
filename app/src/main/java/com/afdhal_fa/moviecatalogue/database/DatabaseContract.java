package com.afdhal_fa.moviecatalogue.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String AUTHORITY = "com.afdhal_fa.moviecatalogue";
    private static final String SCHEME = "content";

    public static final class MovieColumns implements BaseColumns {
        public static final String TABLE_NAME = "movie";
        //Movie title
        public static final String TITLE_MOVIE = "title";
        //Movie description
        public static final String DESCRIPTION_MOVIE = "description";
        //Movie poster
        public static final String POSTER_MOVIE = "poster";
        //Movie banner
        public static final String BANNER_MOVIE = "banner";
        //Movie original language
        public static final String ORIGINAL_LANGUAGE = "original_languange";
        //Movie rating
        public static final String VOTE_AVERAGE_MOVIE = "rating";
        //Movie type
        public static final String TYPE = "type";
        //Movie favorite
        public static final String FAVORITE = "favorite";


        // untuk membuat URI content://com.dicoding.picodiploma.mynotesapp/note
        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }
}
