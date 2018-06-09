package org.dhbw.movietunes.list;

import java.util.List;
import org.dhbw.movietunes.model.Song;

public class SoundtrackSearchResult {
  private String url;
  private List<Song> songs;

  public SoundtrackSearchResult(String url, List<Song> songs) {
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
