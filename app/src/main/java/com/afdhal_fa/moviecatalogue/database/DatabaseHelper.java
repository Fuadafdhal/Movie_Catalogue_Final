package com.afdhal_fa.moviecatalogue.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.afdhal_fa.moviecatalogue.database.DatabaseContract.MovieColumns;
import static com.afdhal_fa.moviecatalogue.database.DatabaseContract.MovieColumns.TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbmoviecatalogue";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TABLE_NAME,
            MovieColumns._ID,
            MovieColumns.TITLE_MOVIE,
            MovieColumns.DESCRIPTION_MOVIE,
            MovieColumns.POSTER_MOVIE,
            MovieColumns.BANNER_MOVIE,
            MovieColumns.VOTE_AVERAGE_MOVIE,
            MovieColumns.ORIGINAL_LANGUAGE,
            MovieColumns.FAVORITE,
            MovieColumns.TYPE


    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
