package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import org.parceler.Parcel;

@Parcel
public class Movie {

    String posterPath;
    String backdropPath;
    String title;
    String overview;
    Double voteAverage;
    Integer id;
    String video_id;

    //empty constructor required for parceler
    public Movie() {}

    //initializes member variables of the movie from the jsonObject
    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        backdropPath = jsonObject.getString("backdrop_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        voteAverage = jsonObject.getDouble("vote_average");
        id = jsonObject.getInt("id");
        video_id = "";
    }

    //extracts and returns a list of movie objects from the JSONArray passed in
    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < movieJsonArray.length(); i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    //returns poster path
    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    //returns backdrop path
    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    //returns movie title
    public String getTitle() {
        return title;
    }

    //returns movie overview
    public String getOverview() {
        return overview;
    }

    //returns vote average
    public Double getVoteAverage() {
        return voteAverage;
    }

    //returns id
    public int getId() { return id; }

    //returns video id
    public String getVideoId() { return video_id; }

    //sets video id
    public void setVideoId(String s) { video_id = s; }
}
