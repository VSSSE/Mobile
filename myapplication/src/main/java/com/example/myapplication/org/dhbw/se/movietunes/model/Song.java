package com.example.myapplication.org.dhbw.se.movietunes.model;

import com.example.myapplication.*;
import com.example.myapplication.Movie;

import java.util.List;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class Song {
    private String songId;
    private String songTitle;
    private String composer;
    private String singer;
    private String duration;
    private List<Movie> usedInMovies;
    private List<Song> similarSongs;

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTime() {
        return duration;
    }

    public void setDuration(String time) {
        this.duration = time;
    }

    public List<Movie> getUsedInMovies() {
        return usedInMovies;
    }

    public void setUsedInMovies(List<Movie> usedInMovies) {
        this.usedInMovies = usedInMovies;
    }

    public List<Song> getSimilarSongs() {
        return similarSongs;
    }

    public void setSimilarSongs(List<Song> similarSongs) {
        this.similarSongs = similarSongs;
    }
}
