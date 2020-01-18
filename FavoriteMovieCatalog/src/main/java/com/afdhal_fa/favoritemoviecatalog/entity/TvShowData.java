package com.afdhal_fa.favoritemoviecatalog.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShowData implements Parcelable {
    private int idTvShow;
    private String titleTvShow;
    private String descriptionTvShow;
    private String posterTvShow;
    private String backdropTvShow;
    private String originalLanguageTvShow;
    private String voteAverageTvShow;
    private String favorite;


    public TvShowData(int idTvShow, String titleTvShow, String descriptionTvShow, String posterTvShow, String backdropTvShow, String originalLanguageTvShow, String voteAverageTvShow, String favorite) {
        this.idTvShow = idTvShow;
        this.titleTvShow = titleTvShow;
        this.descriptionTvShow = descriptionTvShow;
        this.posterTvShow = posterTvShow;
        this.backdropTvShow = backdropTvShow;
        this.originalLanguageTvShow = originalLanguageTvShow;
        this.voteAverageTvShow = voteAverageTvShow;
        this.favorite = favorite;
    }

    public int getIdTvShow() {
        return idTvShow;
    }

    public void setIdTvShow(int idTvShow) {
        this.idTvShow = idTvShow;
    }

    public String getTitleTvShow() {
        return titleTvShow;
    }

    public void setTitleTvShow(String titleTvShow) {
        this.titleTvShow = titleTvShow;
    }

    public String getDescriptionTvShow() {
        return descriptionTvShow;
    }

    public void setDescriptionTvShow(String descriptionTvShow) {
        this.descriptionTvShow = descriptionTvShow;
    }

    public String getPosterTvShow() {
        return posterTvShow;
    }

    public void setPosterTvShow(String posterTvShow) {
        this.posterTvShow = posterTvShow;
    }

    public String getBackdropTvShow() {
        return backdropTvShow;
    }

    public void setBackdropTvShow(String backdropTvShow) {
        this.backdropTvShow = backdropTvShow;
    }


    public String getOriginalLanguageTvShow() {
        return originalLanguageTvShow;
    }

    public void setOriginalLanguageTvShow(String originalLanguageTvShow) {
        this.originalLanguageTvShow = originalLanguageTvShow;
    }

    public String getVoteAverageTvShow() {
        return voteAverageTvShow;
    }

    public void setVoteAverageTvShow(String voteAverageTvShow) {
        this.voteAverageTvShow = voteAverageTvShow;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public static Creator<TvShowData> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idTvShow);
        dest.writeString(this.titleTvShow);
        dest.writeString(this.descriptionTvShow);
        dest.writeString(this.posterTvShow);
        dest.writeString(this.backdropTvShow);
        dest.writeString(this.originalLanguageTvShow);
        dest.writeString(this.voteAverageTvShow);
        dest.writeString(this.favorite);
    }

    public TvShowData() {
    }

    protected TvShowData(Parcel in) {
        this.idTvShow = in.readInt();
        this.titleTvShow = in.readString();
        this.descriptionTvShow = in.readString();
        this.posterTvShow = in.readString();
        this.backdropTvShow = in.readString();
        this.originalLanguageTvShow = in.readString();
        this.voteAverageTvShow = in.readString();
        this.favorite = in.readString();
    }

    public static final Creator<TvShowData> CREATOR = new Creator<TvShowData>() {
        @Override
        public TvShowData createFromParcel(Parcel source) {
            return new TvShowData(source);
        }

        @Override
        public TvShowData[] newArray(int size) {
            return new TvShowData[size];
        }
    };
}
