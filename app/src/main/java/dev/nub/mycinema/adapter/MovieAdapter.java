package dev.nub.mycinema.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dev.nub.mycinema.R;
import dev.nub.mycinema.model.MovieModel;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<MovieModel> listMovies = new ArrayList<>();
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<MovieModel> getListMovies() {
        return listMovies;
    }

    public void setListMovies(ArrayList<MovieModel> listMovies) {
        if (listMovies.size() > 0 ){
            this.listMovies.clear();
        }
        this.listMovies= listMovies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieModel movieModel = listMovies.get(position);

        holder.tvTitle.setText(movieModel.getTitle());
        Glide.with(holder.itemView.getContext())
                .load(movieModel.getBackdrop_path())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintLayout;
        private ImageView imageView;
        private TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            constraintLayout = itemView.findViewById(R.id.cl_items);
            imageView = itemView.findViewById(R.id.img_item);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
