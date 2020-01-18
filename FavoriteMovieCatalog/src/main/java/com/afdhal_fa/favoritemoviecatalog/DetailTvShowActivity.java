package com.afdhal_fa.favoritemoviecatalog;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.afdhal_fa.favoritemoviecatalog.entity.TvShowData;
import com.bumptech.glide.Glide;

import java.util.Locale;

public class DetailTvShowActivity extends AppCompatActivity {

    public static final String EXTRA_TV_SHOW = "extra_tv_show";
    TvShowData movie;
//    private NoteHelper noteHelper;

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

//        noteHelper = NoteHelper.getInstance(getApplicationContext());
//        noteHelper.open();

        setData();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        noteHelper.close();
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
