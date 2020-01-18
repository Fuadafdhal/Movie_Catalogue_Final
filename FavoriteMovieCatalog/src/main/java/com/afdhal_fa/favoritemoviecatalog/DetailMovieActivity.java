package com.afdhal_fa.favoritemoviecatalog;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.afdhal_fa.favoritemoviecatalog.entity.MovieData;
import com.bumptech.glide.Glide;

import java.util.Locale;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    MovieData movie;

    private Uri uriWithId;

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

        setData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setData() {
        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        String language = movie.getOriginalLanguage();

        if (language.equalsIgnoreCase("cn")) {
            language = "zh";
        }
        Locale locale = new Locale(language);
        Glide.with(imgPoster)
                .load(BuildConfig.URL_LOAD_IMAGE + movie.getPosterMovie())
                .into(imgPoster);

        Glide.with(imgBanner)
                .load(BuildConfig.URL_LOAD_IMAGE + movie.getBackdropMovie())
                .into(imgBanner);

        tvtitle.setText(movie.getTitleMovie());
        tvDescription.setText(movie.getDescriptionMovie());
        tvRating.setText(String.format("%s", movie.getVoteAverageMovie()));
        tvlanguage.setText(locale.getDisplayLanguage(locale));
    }

}
