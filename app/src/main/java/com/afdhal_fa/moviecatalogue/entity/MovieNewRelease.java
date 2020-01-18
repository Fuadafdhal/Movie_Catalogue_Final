package com.afdhal_fa.moviecatalogue.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieNewRelease implements Parcelable {
    private String Title;

    public MovieNewRelease(String title) {
        Title = title;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public static Creator<MovieNewRelease> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Title);
    }

    public MovieNewRelease() {
    }

    protected MovieNewRelease(Parcel in) {
        this.Title = in.readString();
    }

    public static final Creator<MovieNewRelease> CREATOR = new Creator<MovieNewRelease>() {
        @Override
        public MovieNewRelease createFromParcel(Parcel source) {
            return new MovieNewRelease(source);
        }

        @Override
        public MovieNewRelease[] newArray(int size) {
            return new MovieNewRelease[size];
        }
    };
}
