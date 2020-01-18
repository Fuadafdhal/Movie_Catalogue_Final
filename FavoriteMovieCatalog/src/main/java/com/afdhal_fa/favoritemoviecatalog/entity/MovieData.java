package com.afdhal_fa.favoritemoviecatalog.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieData implements Parcelable {
    private int idMoview;
    private String titleMovie;
    private String descriptionMovie;
    private String posterMovie;
    private String backdropMovie;
    private double voteAverageMovie;
    private String originalLanguage;
    private String favorite;
    private String release_date;

    public MovieData(int idMoview, String titleMovie, String descriptionMovie, String posterMovie, String backdropMovie, double voteAverageMovie, String originalLanguage, String favorite) {
        this.idMoview = idMoview;
        this.titleMovie = titleMovie;
        this.descriptionMovie = descriptionMovie;
        this.posterMovie = posterMovie;
        this.backdropMovie = backdropMovie;
        this.voteAverageMovie = voteAverageMovie;
        this.originalLanguage = originalLanguage;
        this.favorite = favorite;
    }

    public int getIdMoview() {
        return idMoview;
    }

    public void setIdMoview(int idMoview) {
        this.idMoview = idMoview;
    }

    public String getTitleMovie() {
        return titleMovie;
    }

    public void setTitleMovie(String titleMovie) {
        this.titleMovie = titleMovie;
    }

    public String getDescriptionMovie() {
        return descriptionMovie;
    }

    public void setDescriptionMovie(String descriptionMovie) {
        this.descriptionMovie = descriptionMovie;
    }

    public String getPosterMovie() {
        return posterMovie;
    }

    public void setPosterMovie(String posterMovie) {
        this.posterMovie = posterMovie;
    }

    public String getBackdropMovie() {
        return backdropMovie;
    }

    public void setBackdropMovie(String backdropMovie) {
        this.backdropMovie = backdropMovie;
    }

    public double getVoteAverageMovie() {
        return voteAverageMovie;
    }

    public void setVoteAverageMovie(double voteAverageMovie) {
        this.voteAverageMovie = voteAverageMovie;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public static Creator<MovieData> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idMoview);
        dest.writeString(this.titleMovie);
        dest.writeString(this.descriptionMovie);
        dest.writeString(this.posterMovie);
        dest.writeString(this.backdropMovie);
        dest.writeDouble(this.voteAverageMovie);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.favorite);
        dest.writeString(this.release_date);
    }

    public MovieData() {
    }

    protected MovieData(Parcel in) {
        this.idMoview = in.readInt();
        this.titleMovie = in.readString();
        this.descriptionMovie = in.readString();
        this.posterMovie = in.readString();
        this.backdropMovie = in.readString();
        this.voteAverageMovie = in.readDouble();
        this.originalLanguage = in.readString();
        this.favorite = in.readString();
        this.release_date = in.readString();
    }

    public static final Creator<MovieData> CREATOR = new Creator<MovieData>() {
        @Override
        public MovieData createFromParcel(Parcel source) {
            return new MovieData(source);
        }

        @Override
        public MovieData[] newArray(int size) {
            return new MovieData[size];
        }
    };
}
