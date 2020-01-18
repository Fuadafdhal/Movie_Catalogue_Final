package com.afdhal_fa.favoritemoviecatalog.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afdhal_fa.favoritemoviecatalog.BuildConfig;
import com.afdhal_fa.favoritemoviecatalog.CustomOnItemClickListener;
import com.afdhal_fa.favoritemoviecatalog.DetailMovieActivity;
import com.afdhal_fa.favoritemoviecatalog.R;
import com.afdhal_fa.favoritemoviecatalog.entity.MovieData;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapterFavorite extends RecyclerView.Adapter<MovieAdapterFavorite.MovieViewHolder> {
    private final Activity activity;

    private final ArrayList<MovieData> mMovieData = new ArrayList<>();

    public MovieAdapterFavorite(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<MovieData> getListMovies() {
        return mMovieData;
    }

    public void setListMovies(ArrayList<MovieData> listMovies) {

        if (listMovies.size() > 0) {
            this.mMovieData.clear();
        }
        this.mMovieData.addAll(listMovies);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieAdapterFavorite.MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(mMovieData.get(position));
        holder.itemView.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent moveWithDataIntent = new Intent(view.getContext(), DetailMovieActivity.class);
                moveWithDataIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, mMovieData.get(position));
                view.getContext().startActivity(moveWithDataIntent);
            }
        }));

    }

    @Override
    public int getItemCount() {
        return mMovieData.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvJudul;
        TextView tvDetail;
        TextView tvRating;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo_movie);
            tvJudul = itemView.findViewById(R.id.txt_title_movie);
            tvDetail = itemView.findViewById(R.id.txt_description_movie);
            tvRating = itemView.findViewById(R.id.tv_rating);
        }

        public void bind(MovieData movie) {
            Glide.with(imgPhoto)
                    .load(BuildConfig.URL_LOAD_IMAGE + movie.getPosterMovie())
                    .into(imgPhoto);
            tvJudul.setText(movie.getTitleMovie());
            tvDetail.setText(movie.getDescriptionMovie());
            tvRating.setText(String.format("%s", movie.getVoteAverageMovie()));

        }
    }
}
