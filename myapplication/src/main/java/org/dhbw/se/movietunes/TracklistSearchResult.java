package org.dhbw.se.movietunes;

import java.util.List;
import org.dhbw.se.movietunes.model.Song;

class TracklistSearchResult {
  private String url;
  private List<Song> songs;

  public TracklistSearchResult(String url, List<Song> songs) {
    this.url = url;
    this.songs = songs;
  }

  public String getUrl() {
    return url;
  }

  public List<Song> getSongs() {
    return songs;
  }
}
