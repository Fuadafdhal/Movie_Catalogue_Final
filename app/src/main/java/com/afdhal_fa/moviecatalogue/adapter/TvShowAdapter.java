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
import com.afdhal_fa.moviecatalogue.DetailTvShowActivity;
import com.afdhal_fa.moviecatalogue.R;
import com.afdhal_fa.moviecatalogue.entity.TvShowData;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.tvShowViewHolder> {
    private ArrayList<TvShowData> mDataTvShow = new ArrayList<>();

    public void setData(ArrayList<TvShowData> items) {
        mDataTvShow.clear();
        mDataTvShow.addAll(items);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public tvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new tvShowViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final tvShowViewHolder tvShowViewHolder, int position) {

        tvShowViewHolder.bind(mDataTvShow.get(position));

        tvShowViewHolder.itemView.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent moveWithDataIntent = new Intent(view.getContext(), DetailTvShowActivity.class);
                moveWithDataIntent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, mDataTvShow.get(position));
                view.getContext().startActivity(moveWithDataIntent);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return mDataTvShow.size();
    }


    public class tvShowViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvJudul;
        TextView tvDetail;
        TextView tvRating;

        public tvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo_movie);
            tvJudul = itemView.findViewById(R.id.txt_title_movie);
            tvDetail = itemView.findViewById(R.id.txt_description_movie);
            tvRating = itemView.findViewById(R.id.tv_rating);
        }

        public void bind(TvShowData tvShowData) {
            Glide.with(imgPhoto)
                    .load(BuildConfig.URL_LOAD_IMAGE + tvShowData.getPosterTvShow())
                    .into(imgPhoto);
            tvJudul.setText(tvShowData.getTitleTvShow());
            tvDetail.setText(tvShowData.getDescriptionTvShow());
            tvRating.setText(String.format("%s", tvShowData.getVoteAverageTvShow()));
        }
    }
}
