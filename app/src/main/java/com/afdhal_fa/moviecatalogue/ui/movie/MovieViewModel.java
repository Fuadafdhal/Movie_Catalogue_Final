package com.afdhal_fa.moviecatalogue.ui.movie;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.afdhal_fa.moviecatalogue.BuildConfig;
import com.afdhal_fa.moviecatalogue.entity.MovieData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<ArrayList<MovieData>> listMovieData = new MutableLiveData<>();

    public void setMovie() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MovieData> listItems = new ArrayList<>();
        String url = BuildConfig.URL_MOVIE_API + BuildConfig.API_KEY + "&language=en-US";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray resultsArray = responseObject.getJSONArray("results");
                    for (int i = 0; i < resultsArray.length(); i++) {
                        JSONObject movies = resultsArray.getJSONObject(i);
                        MovieData movieData = new MovieData();
                        movieData.setIdMoview(movies.getInt("id"));
                        movieData.setTitleMovie(movies.getString("title"));
                        movieData.setPosterMovie(movies.getString("poster_path"));
                        movieData.setBackdropMovie(movies.getString("backdrop_path"));
                        movieData.setDescriptionMovie(movies.getString("overview"));
                        movieData.setVoteAverageMovie(movies.getDouble("vote_average"));
                        movieData.setOriginalLanguage(movies.getString("original_language"));
                        listItems.add(movieData);
                    }
                    listMovieData.postValue(listItems);
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

    void setMovieSerch(String search) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MovieData> listItems = new ArrayList<>();
        String url = BuildConfig.URL_SEARCH_MOVIE + BuildConfig.API_KEY + "&language=en-US&query=" + search;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray resultsArray = responseObject.getJSONArray("results");
                    for (int i = 0; i < resultsArray.length(); i++) {
                        JSONObject movies = resultsArray.getJSONObject(i);
                        MovieData movieData = new MovieData();
                        movieData.setIdMoview(movies.getInt("id"));
                        movieData.setTitleMovie(movies.getString("title"));
                        movieData.setPosterMovie(movies.getString("poster_path"));
                        movieData.setBackdropMovie(movies.getString("backdrop_path"));
                        movieData.setDescriptionMovie(movies.getString("overview"));
                        movieData.setVoteAverageMovie(movies.getDouble("vote_average"));
                        movieData.setOriginalLanguage(movies.getString("original_language"));
                        listItems.add(movieData);
                    }
                    listMovieData.postValue(listItems);
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

    public LiveData<ArrayList<MovieData>> getMovies() {
        return listMovieData;
    }
}
