package dev.nub.mycinema.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dev.nub.mycinema.R;
import dev.nub.mycinema.model.MovieModel;

public class UpcomingMovieAdapter extends RecyclerView.Adapter<UpcomingMovieAdapter.UpcomingMovieViewHolder> {
    private ArrayList<MovieModel> listUpcomingMovie = new ArrayList<>();
    private Context context;

    public ArrayList<MovieModel> getListUpcomingMovie() {
        return listUpcomingMovie;
    }

    public void setListUpcomingMovie(ArrayList<MovieModel> listUpcomingMovie) {
        if (listUpcomingMovie.size()>0){
            this.listUpcomingMovie.clear();
        }
        this.listUpcomingMovie = listUpcomingMovie;
    }

    public UpcomingMovieAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public UpcomingMovieAdapter.UpcomingMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_upcoming, parent, false);

        return new UpcomingMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingMovieAdapter.UpcomingMovieViewHolder holder, int position) {
        MovieModel movieModel = listUpcomingMovie.get(position);

        Glide.with(holder.itemView.getContext())
                .load(movieModel.getPoster_path())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return this.listUpcomingMovie.size();
    }

    public class UpcomingMovieViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintLayout;
        private ImageView imageView;

        public UpcomingMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            imageView = itemView.findViewById(R.id.img_item_upcoming);
        }
    }
}
