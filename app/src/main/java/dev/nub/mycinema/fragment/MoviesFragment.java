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
import android.widget.ProgressBar;

import java.util.ArrayList;

import dev.nub.mycinema.R;
import dev.nub.mycinema.adapter.MovieAdapter;
import dev.nub.mycinema.model.MainViewViewModel;
import dev.nub.mycinema.model.MovieModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {


    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ArrayList<MovieModel> listMovies = new ArrayList<>();

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.rvMovies);
        MainViewViewModel MVVM = ViewModelProviders.of(this).get(MainViewViewModel.class);
        MVVM.setListMovie();
        MVVM.getListMovie().observe(this,getMovie);
        setupRecyclerView();

        return view;
    }

    private void setupMovie(){

    }

    private Observer<ArrayList<MovieModel>> getMovie = new Observer<ArrayList<MovieModel>>() {
        @Override
        public void onChanged(ArrayList<MovieModel> movieModels) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            MovieAdapter movieAdapter = new MovieAdapter(getContext());
            movieAdapter.setListMovies(movieModels);
            recyclerView.setAdapter(movieAdapter);
        }
    };

    private void showLoading(){

    }

    public void setupRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MovieAdapter movieAdapter = new MovieAdapter(getContext());
        movieAdapter.setListMovies(listMovies);
        recyclerView.setAdapter(movieAdapter);
    }

}
