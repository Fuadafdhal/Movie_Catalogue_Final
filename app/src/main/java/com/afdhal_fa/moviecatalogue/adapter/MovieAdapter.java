package com.afdhal_fa.moviecatalogue.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afdhal_fa.moviecatalogue.BuildConfig;
import com.afdhal_fa.moviecatalogue.CustomOnItemClickListener;
import com.afdhal_fa.moviecatalogue.DetailMovieActivity;
import com.afdhal_fa.moviecatalogue.R;
import com.afdhal_fa.moviecatalogue.entity.MovieData;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<MovieData> mDataMovie = new ArrayList<>();

    public void setData(ArrayList<MovieData> items) {
        mDataMovie.clear();
        mDataMovie.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder movieViewHolder, int position) {
        movieViewHolder.bind(mDataMovie.get(position));

        movieViewHolder.itemView.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent moveWithDataIntent = new Intent(view.getContext(), DetailMovieActivity.class);
                moveWithDataIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, mDataMovie.get(position));
                view.getContext().startActivity(moveWithDataIntent);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return mDataMovie.size();
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
                    .load(BuildConfig.URL_LOAD_IMAGE+ movie.getPosterMovie())
                    .into(imgPhoto);
            tvJudul.setText(movie.getTitleMovie());
            tvDetail.setText(movie.getDescriptionMovie());
            tvRating.setText(String.format("%s", movie.getVoteAverageMovie()));
        }
    }
}
