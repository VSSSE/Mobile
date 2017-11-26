package com.example.myapplication.org.dhbw.se.movietunes.model;

import com.example.myapplication.*;

import java.util.List;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class Soundtrack {
    private Movie movie;
    private List<Song> songs;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}

