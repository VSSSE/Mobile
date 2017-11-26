package com.example.myapplication.org.dhbw.se.movietunes.model;

import com.example.myapplication.*;

import java.util.List;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class Movie {
   private Soundtrack soundtrack;
    private String movieId;
    private String movieTitle;

    public Soundtrack getSoundtrack() {
        return soundtrack;
    }

    public void setSoundtrack(Soundtrack soundtrack) {
        this.soundtrack = soundtrack;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
}
