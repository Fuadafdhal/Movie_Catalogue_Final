package com.afdhal_fa.moviecatalogue;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.afdhal_fa.moviecatalogue.database.movieHelper;
import com.afdhal_fa.moviecatalogue.entity.TvShowData;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkEventListener;

import java.util.Locale;

import static android.provider.BaseColumns._ID;
import static com.afdhal_fa.moviecatalogue.database.DatabaseContract.MovieColumns.BANNER_MOVIE;
import static com.afdhal_fa.moviecatalogue.database.DatabaseContract.MovieColumns.DESCRIPTION_MOVIE;
import static com.afdhal_fa.moviecatalogue.database.DatabaseContract.MovieColumns.FAVORITE;
import static com.afdhal_fa.moviecatalogue.database.DatabaseContract.MovieColumns.ORIGINAL_LANGUAGE;
import static com.afdhal_fa.moviecatalogue.database.DatabaseContract.MovieColumns.POSTER_MOVIE;
import static com.afdhal_fa.moviecatalogue.database.DatabaseContract.MovieColumns.TITLE_MOVIE;
import static com.afdhal_fa.moviecatalogue.database.DatabaseContract.MovieColumns.TYPE;
import static com.afdhal_fa.moviecatalogue.database.DatabaseContract.MovieColumns.VOTE_AVERAGE_MOVIE;

public class DetailTvShowActivity extends AppCompatActivity {

    public static final String EXTRA_TV_SHOW = "extra_tv_show";
    TvShowData movie;
    private movieHelper movieHelper;

    ImageView imgPoster;
    ImageView imgBanner;
    TextView tvtitle;
    TextView tvDescription;
    TextView tvlanguage;
    TextView tvRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        tvtitle = findViewById(R.id.tv_title);
        tvDescription = findViewById(R.id.tv_overview);
        tvRating = findViewById(R.id.tv_rating);
        tvlanguage = findViewById(R.id.tv_original_language_movie);
        imgPoster = findViewById(R.id.img_poster);
        imgBanner = findViewById(R.id.img_banner);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        movieHelper = movieHelper.getInstance(getApplicationContext());
        movieHelper.open();

        setData();

        final SparkButton sparkButton = findViewById(R.id.spark_button);

        Cursor cursor = movieHelper.queryByFavorite();
        movie = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
        String data = "";
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(TITLE_MOVIE));
            if (title.equals(movie.getTitleTvShow())) {
                data = title;
            }
        }
        if (data.equals(movie.getTitleTvShow())) {
            sparkButton.setChecked(true);
            if (sparkButton.isChecked()) {
                sparkButton.setActiveImage(R.drawable.ic_favorite_on);
            }
        }

        sparkButton.setEventListener(new SparkEventListener() {

            @Override
            public void onEvent(ImageView button, boolean buttonState) {
                if (buttonState) {
                    // Button is active
                    movie = getIntent().getParcelableExtra(EXTRA_TV_SHOW);

                    int id = movie.getIdTvShow();
                    String title = movie.getTitleTvShow();
                    String description = movie.getDescriptionTvShow();
                    String rating = movie.getVoteAverageTvShow() + " ";
                    String language = movie.getOriginalLanguageTvShow();
                    String poster = movie.getPosterTvShow();
                    String banner = movie.getBackdropTvShow();

                    // Gunakan contentvalues untuk menampung data
                    ContentValues values = new ContentValues();

                    values.put(_ID, id);
                    values.put(TITLE_MOVIE, title);
                    values.put(DESCRIPTION_MOVIE, description);
                    values.put(ORIGINAL_LANGUAGE, language);
                    values.put(VOTE_AVERAGE_MOVIE, rating);
                    values.put(POSTER_MOVIE, poster);
                    values.put(BANNER_MOVIE, banner);
                    values.put(FAVORITE, "favorite");
                    values.put(TYPE, "movie");

                    long result = movieHelper.insert(values);
                    if (result > 0) {

                        Snackbar snack = Snackbar.make(button, R.string.added_favorite, Snackbar.LENGTH_LONG);
                        View view = snack.getView();
                        view.setBackgroundColor(Color.parseColor("#50d890"));
                        TextView tv = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
                        tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done, 0, 0, 0);
                        tv.setTextColor(Color.WHITE);
                        snack.show();
                    } else {
                        Toast.makeText(DetailTvShowActivity.this, R.string.failed_to_add_favorite, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    movie = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
                    long result = movieHelper.deleteById(String.valueOf(movie.getIdTvShow()));
                    if (result > 0) {
                        // Button is inactive
                        Snackbar snack = Snackbar.make(button, R.string.removed_favorite, Snackbar.LENGTH_LONG);
                        View view = snack.getView();
                        view.setBackgroundColor(Color.parseColor("#eb4d55"));
                        TextView tv = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
                        tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_delete, 0, 0, 0);
                        tv.setTextColor(Color.WHITE);
                        snack.show();
                    } else {
                        Toast.makeText(DetailTvShowActivity.this, R.string.failed_to_removed_favorite, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onEventAnimationEnd(ImageView button, boolean buttonState) {

            }

            @Override
            public void onEventAnimationStart(ImageView button, boolean buttonState) {

            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieHelper.close();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private void setData() {
        movie = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
        assert movie != null;
        String language = movie.getOriginalLanguageTvShow();

        if (language.equalsIgnoreCase("cn")) {
            language = "zh";
        }
        Locale locale = new Locale(language);
        Glide.with(imgPoster)
                .load("https://image.tmdb.org/t/p/w780" + movie.getPosterTvShow())
                .into(imgPoster);

        Glide.with(imgBanner)
                .load("https://image.tmdb.org/t/p/w780" + movie.getBackdropTvShow())
                .into(imgBanner);

        tvtitle.setText(movie.getTitleTvShow());
        tvDescription.setText(movie.getDescriptionTvShow());
        tvRating.setText(movie.getVoteAverageTvShow() + "");
        tvlanguage.setText(locale.getDisplayLanguage(locale));
    }

}
