package com.afdhal_fa.moviecatalogue.ui.movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afdhal_fa.moviecatalogue.R;
import com.afdhal_fa.moviecatalogue.SettingsActivity;
import com.afdhal_fa.moviecatalogue.adapter.MovieAdapter;
import com.afdhal_fa.moviecatalogue.entity.MovieData;
import com.afdhal_fa.moviecatalogue.favorite.FavoriteActivity;

import java.util.ArrayList;

public class MovieFragment extends Fragment {

    private MovieAdapter adapter;
    private MovieViewModel mainViewModel;
    private ProgressBar progressBar;
    RecyclerView mRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.rv_movies);
        progressBar = view.findViewById(R.id.progressBarMovie);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new MovieAdapter();
        adapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(adapter);

        showLoading(true);

        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);
        mainViewModel.getMovies().observe(getActivity(), new Observer<ArrayList<MovieData>>() {
            @Override
            public void onChanged(ArrayList<MovieData> movieData) {
                if (movieData != null) {
                    adapter.setData(movieData);
                    showLoading(false);
                }
            }
        });

        mainViewModel.setMovie();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.menu_main, menu);

        final MenuItem search = menu.findItem(R.id.menu_search);

        final SearchView searchView = (SearchView) search.getActionView();
        searchView.setQuery("", false);
        searchView.clearFocus();
        searchView.setIconified(false);
        searchView.setQueryHint(getResources().getString(R.string.search_hint));


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(getActivity(), query, Toast.LENGTH_SHORT).show();
                RecyclerView vrRecyclerView;
                searchView.clearFocus();
                mainViewModel = new ViewModelProvider(MovieFragment.this, new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);
                mainViewModel.setMovieSerch(query);
                adapter = new MovieAdapter();

                adapter.notifyDataSetChanged();
                vrRecyclerView=mRecyclerView;
                vrRecyclerView.setAdapter(adapter);

                showLoading(true);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        search.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(getActivity(), SettingsActivity.class);
            startActivity(mIntent);
        } else if (item.getItemId() == R.id.favorite_list) {
            Intent intent = new Intent(getActivity(), FavoriteActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showLoading(boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }

    }
}