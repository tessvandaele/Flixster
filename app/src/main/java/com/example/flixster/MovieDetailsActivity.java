package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import okhttp3.Headers;
import okhttp3.Request;

public class MovieDetailsActivity extends AppCompatActivity {

    Context context = this;
    String trailer_url;
    JSONArray results;
    String video_id;

    //movie to display
    Movie movie;

    //view objects
    TextView tvTitleDetails;
    TextView tvOverviewDetails;
    TextView tvRating;
    RatingBar rbVoteAverage;
    Button btnBack;
    ImageButton btnTrailer;
    ImageView thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        tvTitleDetails = findViewById(R.id.tvTitleDetails);
        tvOverviewDetails = findViewById(R.id.tvOverviewDetails);
        tvRating = findViewById(R.id.tvRating);
        rbVoteAverage = findViewById(R.id.rbVoteAverage);
        btnBack = findViewById(R.id.btnBack);
        btnTrailer = findViewById(R.id.btnTrailer);
        thumbnail = findViewById(R.id.ivThumbnail);

        //unwrap the movie via intent (simple name is key)
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        //set text
        tvTitleDetails.setText(movie.getTitle());
        tvOverviewDetails.setText(movie.getOverview());
        tvOverviewDetails.setMovementMethod(new ScrollingMovementMethod());

        //set vote average
        float rating = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(rating / 2.0f);

        //set thumbnail image
        Glide.with(context)
                .load(movie.getBackdropPath())
                .into(thumbnail);

        tvRating.setText((rating / 2.0f) + " / 5.0");

        trailer_url = "https://api.themoviedb.org/3/movie/"+ movie.getId() + "/videos?api_key=3493a81ca7ef0536653b049b3aae20cd";

        //data retrieval from API
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(trailer_url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                JSONObject jsonObject = json.jsonObject;
                try {
                    //first retrieve jason array
                    results = jsonObject.getJSONArray("results");
                    //store only first element in json array (official trailer data)
                    JSONObject first_trailer_data = results.getJSONObject(0);
                    //set video_id to be key in data
                    video_id = first_trailer_data.getString("key");
                    movie.setVideoId(video_id);
                } catch (JSONException e) {
                    Log.d("MovieTrailerActivity", "JsonException");
                }

            }
            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d("MovieTrailerActivity", "Failed to load API data");
            }
        });

        //add back button functionality
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                //start the activity
                context.startActivity(intent);
            }
        });

        //add trailer button functionality
        btnTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MovieDetailsActivity.this, MovieTrailerActivity.class);
                i.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));
                //start the activity
                startActivity(i);
            }
        });
    }
}