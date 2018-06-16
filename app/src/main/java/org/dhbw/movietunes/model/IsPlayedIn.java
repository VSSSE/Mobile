package org.dhbw.movietunes.model;

import android.provider.BaseColumns;

public class IsPlayedIn implements BaseColumns {

  public static final String _TabellenName = "IsPlayedIn";
  public static final String _SongId = "songId";
  public static final String _MovieName = "movieName";
  private String songId;
  private String movieName;

  public IsPlayedIn(String songId, String movieName) {
    this.songId = songId;
    this.movieName = movieName;
  }

  public String getSongId() {
    return songId;
  }

  public String getMovieName() {
    return movieName;
  }
}
