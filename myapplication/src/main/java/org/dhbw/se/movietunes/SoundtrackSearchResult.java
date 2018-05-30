package org.dhbw.se.movietunes;

import org.dhbw.se.movietunes.model.Song;

import java.util.List;

public class SoundtrackSearchResult {
    private String url;
    private List<Song> songs;

    public String getUrl() {
        return url;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public SoundtrackSearchResult(String url, List<Song> songs) {
        this.url = url;
        this.songs = songs;
    }
}
