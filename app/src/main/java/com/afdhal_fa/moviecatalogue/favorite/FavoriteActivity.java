package com.afdhal_fa.moviecatalogue.favorite;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afdhal_fa.moviecatalogue.R;
import com.afdhal_fa.moviecatalogue.adapter.MovieAdapterFavorite;
import com.afdhal_fa.moviecatalogue.database.DatabaseContract;
import com.afdhal_fa.moviecatalogue.database.movieHelper;
import com.afdhal_fa.moviecatalogue.entity.MovieData;
import com.afdhal_fa.moviecatalogue.helper.MappingHelper;
import com.google.android.material.snackbar.Snackbar;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity implements LoadMoviesCallback {
    private static final String EXTRA_STATE = "EXTRA_STATE";

    private MovieAdapterFavorite adapter;
    private ProgressBar progressBar;
    RecyclerView mRecyclerView;
    private movieHelper movieHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        mRecyclerView = findViewById(R.id.rv_movies);
        progressBar = findViewById(R.id.progressBarMovie);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        adapter = new MovieAdapterFavorite(this);
        mRecyclerView.setAdapter(adapter);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.favorite_list);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        movieHelper = movieHelper.getInstance(getApplicationContext());
        movieHelper.open();

        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());

        DataObserver myObserver = new DataObserver(handler, this);
        getContentResolver().registerContentObserver(DatabaseContract.MovieColumns.CONTENT_URI, true, myObserver);

        if (savedInstanceState == null) {
            new LoadMovieAsync(this, this).execute();
        } else {
            ArrayList<MovieData> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null) {
                adapter.setListMovie(list);
            }
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_STATE, adapter.getListMovie());
    }

    @Override
    public void preExecute() {
         /*
        Callback yang akan dipanggil di onPreExecute Asyntask
        Memunculkan progressbar
        */
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void postExecute(ArrayList<MovieData> movies) {
         /*
        Callback yang akan dipanggil di onPostExture Asynctask
        Menyembunyikan progressbar, kemudian isi adapter dengan data yang ada
         */
        progressBar.setVisibility(View.INVISIBLE);
        if (movies.size() > 0) {
            adapter.setListMovie(movies);
        } else {
            adapter.setListMovie(new ArrayList<MovieData>());
            showSnackbarMessage(R.string.favorite_list_not_found);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void showSnackbarMessage(int message) {
        Snackbar snackbar = Snackbar.make(mRecyclerView, message, Snackbar.LENGTH_SHORT);
        View view = snackbar.getView();
        view.setBackgroundColor(Color.parseColor("#eb4d55"));
        TextView tv = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snackbar.show();
    }



    private static class LoadMovieAsync extends AsyncTask<Void, Void, ArrayList<MovieData>> {
        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadMoviesCallback> weakCallback;

        private LoadMovieAsync(Context context, LoadMoviesCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        private LoadMovieAsync(WeakReference<Context> weakContext, WeakReference<LoadMoviesCallback> weakCallback) {
            this.weakContext = weakContext;
            this.weakCallback = weakCallback;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<MovieData> doInBackground(Void... voids) {
            Context context = weakContext.get();
            Cursor dataCursor = context.getContentResolver().query(DatabaseContract.MovieColumns.CONTENT_URI, null, null, null, null);
            return MappingHelper.mapCursorToArrayList(dataCursor);
        }

        @Override
        protected void onPostExecute(ArrayList<MovieData> movies) {
            super.onPostExecute(movies);
            weakCallback.get().postExecute(movies);
        }
    }

    public static class DataObserver extends ContentObserver {
        final Context context;

        public DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new LoadMovieAsync(context, (LoadMoviesCallback) context).execute();
        }
    }
}

interface LoadMoviesCallback {
    void preExecute();
    void postExecute(ArrayList<MovieData> movies);
}