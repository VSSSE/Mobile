package org.dhbw.se.movietunes;

import org.dhbw.se.movietunes.model.Song;

import java.util.List;

class TracklistSearchResult {
private String url;
private List<Song> songs;

    public TracklistSearchResult(String url, List<Song> songs) {
        this.url = url;
        this.songs = songs;
    }
}
