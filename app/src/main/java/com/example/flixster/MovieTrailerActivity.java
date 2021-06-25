package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.models.Movie;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;

import okhttp3.Headers;

public class MovieTrailerActivity extends AppCompatActivity {

        Movie movie;
        int movieId;
        String video_id;
        JSONArray results;
        String trailer_url;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_movie_trailer);

            //unwrap movie data
            movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
            movieId = movie.getId();

            trailer_url = "https://api.themoviedb.org/3/movie/"+ movieId + "/videos?api_key=3493a81ca7ef0536653b049b3aae20cd";

            // declaring variable for youtubeplayer view
            YouTubePlayerView youTubePlayerView = findViewById(R.id.videoPlayer);

            //allows user controls of video (pause, play, speed up)
            youTubePlayerView.getPlayerUiController();

            //adding listener for our youtube player view
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    //loading video into player
                    youTubePlayer.loadVideo(movie.getVideoId(), 0);
                }

                @Override
                public void onStateChange(@NonNull YouTubePlayer youTubePlayer,
                                          @NonNull PlayerConstants.PlayerState state) {
                    //called when video has ended
                    super.onStateChange(youTubePlayer, state);
                }
            });
    }
}