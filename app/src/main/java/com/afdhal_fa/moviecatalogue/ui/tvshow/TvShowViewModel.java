package com.afdhal_fa.moviecatalogue.ui.tvshow;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.afdhal_fa.moviecatalogue.BuildConfig;
import com.afdhal_fa.moviecatalogue.entity.TvShowData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TvShowViewModel extends ViewModel {
//    private static final String API_KEY = "141b551a8db0c0937d97c52c8c29a5b3";
    private MutableLiveData<ArrayList<TvShowData>> listTvShowData = new MutableLiveData<>();

    void setTvShow() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvShowData> listItems = new ArrayList<>();
        String url = BuildConfig.URL_TV_SHOW_API + BuildConfig.API_KEY + "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray resultsArray = responseObject.getJSONArray("results");
                    for (int i = 0; i < resultsArray.length(); i++) {
                        JSONObject tvShows = resultsArray.getJSONObject(i);

                        TvShowData tvShowData = new TvShowData();
                        tvShowData.setIdTvShow(tvShows.getInt("id"));
                        tvShowData.setTitleTvShow(tvShows.getString("name"));
                        tvShowData.setPosterTvShow(tvShows.getString("poster_path"));
                        tvShowData.setBackdropTvShow(tvShows.getString("backdrop_path"));
                        tvShowData.setDescriptionTvShow(tvShows.getString("overview"));
                        tvShowData.setVoteAverageTvShow(tvShows.getString("vote_average"));
                        tvShowData.setOriginalLanguageTvShow(tvShows.getString("original_language"));
                        listItems.add(tvShowData);
                    }
                    listTvShowData.postValue(listItems);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }
    void setSerchTvShow(String search) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvShowData> listItems = new ArrayList<>();
        String url = BuildConfig.URL_SEARCH_TV_SHOW + BuildConfig.API_KEY + "&language=en-US&query=" + search;

        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray resultsArray = responseObject.getJSONArray("results");
                    for (int i = 0; i < resultsArray.length(); i++) {
                        JSONObject tvShows = resultsArray.getJSONObject(i);

                        TvShowData tvShowData = new TvShowData();
                        tvShowData.setIdTvShow(tvShows.getInt("id"));
                        tvShowData.setTitleTvShow(tvShows.getString("name"));
                        tvShowData.setPosterTvShow(tvShows.getString("poster_path"));
                        tvShowData.setBackdropTvShow(tvShows.getString("backdrop_path"));
                        tvShowData.setDescriptionTvShow(tvShows.getString("overview"));
                        tvShowData.setVoteAverageTvShow(tvShows.getString("vote_average"));
                        tvShowData.setOriginalLanguageTvShow(tvShows.getString("original_language"));
                        listItems.add(tvShowData);
                    }
                    listTvShowData.postValue(listItems);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }
    LiveData<ArrayList<TvShowData>> getTvShow() {
        return listTvShowData;
    }
}
