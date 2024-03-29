package dev.nub.mycinema.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;

import dev.nub.mycinema.R;
import dev.nub.mycinema.adapter.MovieAdapter;
import dev.nub.mycinema.adapter.UpcomingMovieAdapter;
import dev.nub.mycinema.model.MainViewViewModel;
import dev.nub.mycinema.model.MovieModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView recyclerViewUpcoming;
    private ProgressBar progressBar;
    private View viewUpcoming;
    private View viewNews;
    private ArrayList<MovieModel> listMovies = new ArrayList<>();

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        viewUpcoming = view.findViewById(R.id.view);
        viewNews = view.findViewById(R.id.view2);
        recyclerView = view.findViewById(R.id.rvMovies);
        recyclerViewUpcoming = view.findViewById(R.id.rv_upcoming);

        MainViewViewModel MVVM = ViewModelProviders.of(this).get(MainViewViewModel.class);
        MVVM.setListUpcomingMovie();
        MVVM.setListMovie();
        MVVM.getListMovie().observe(this,getMovie);
        MVVM.getListUpcomingMovie().observe(this, getUpcomingMovie);

        return view;
    }
    
    private Observer<ArrayList<MovieModel>> getMovie = new Observer<ArrayList<MovieModel>>() {
        @Override
        public void onChanged(ArrayList<MovieModel> movieModels) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            MovieAdapter movieAdapter = new MovieAdapter(getContext());
            movieAdapter.setListMovies(movieModels);
            showLoading(false);
            recyclerView.setAdapter(movieAdapter);
        }
    };

    private Observer<ArrayList<MovieModel>> getUpcomingMovie = new Observer<ArrayList<MovieModel>>() {
        @Override
        public void onChanged(ArrayList<MovieModel> movieModels) {
            recyclerViewUpcoming.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            UpcomingMovieAdapter movieAdapter = new UpcomingMovieAdapter(getContext());
            movieAdapter.setListUpcomingMovie(movieModels);
            showLoading(false);
            recyclerViewUpcoming.setAdapter(movieAdapter);
        }
    };

    private void showLoading(boolean condition){
        if (condition){
            progressBar.setVisibility(View.VISIBLE);
            viewNews.setVisibility(View.GONE);
            viewUpcoming.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            viewNews.setVisibility(View.VISIBLE);
            viewUpcoming.setVisibility(View.VISIBLE);
        }
    }

    public void setupRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MovieAdapter movieAdapter = new MovieAdapter(getContext());
        movieAdapter.setListMovies(listMovies);
        recyclerView.setAdapter(movieAdapter);
    }

}
