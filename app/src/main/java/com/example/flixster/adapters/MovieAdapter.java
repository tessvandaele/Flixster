package com.example.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.flixster.MovieDetailsActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    //Inflating a layout from XML file and returning the holder (creates the space in which a movie box will be displayed)
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    @Override
    //Populates data into the holder (fills space with correct data)
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Retrieve movie in this position
        Movie movie = movies.get(position);

        //Bind movie data into view holder
        holder.bind(movie);
    }

    @Override
    //returns number of items in list
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);

            //itemView's onClickListener
            itemView.setOnClickListener(this);
        }

        //extracts data from movie object to the views
        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            tvOverview.setMovementMethod(new ScrollingMovementMethod());
            String imageUrl;
            int placeholderUrl = 0;

            //determining landscape vs portrait image url
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageUrl = movie.getBackdropPath();
                placeholderUrl = R.drawable.flicks_backdrop_placeholder;
            } else {
                imageUrl = movie.getPosterPath();
                placeholderUrl = R.drawable.flicks_movie_placeholder;
            }

            Glide.with(context)
                    .load(imageUrl)
                    .circleCrop()
                    .transform(new RoundedCornersTransformation(30, 0))
                    .placeholder(placeholderUrl)
                    .into(ivPoster);
        }

        @Override
        public void onClick(View v) {
            //get item position
            int position = getAdapterPosition();

            //check that position is valid
            if (position != RecyclerView.NO_POSITION) {
                //retrieve movie at position
                Movie movie = movies.get(position);
                //create intent for new activity
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                //serialize the movie
                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));
                //start the activity
                context.startActivity(intent);
            }

        }
    }
}
