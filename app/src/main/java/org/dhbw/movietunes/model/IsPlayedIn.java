package org.dhbw.movietunes.model;

import android.database.Cursor;
import android.provider.BaseColumns;
import java.nio.charset.Charset;

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

  public IsPlayedIn(Cursor cursor) {
    this.songId = cursor.getString(cursor.getColumnIndexOrThrow(_SongId));
    this.movieName = cursor.getString(cursor.getColumnIndexOrThrow(_MovieName));
  }

  public String getSongId() {
    return songId;
  }

  public String getMovieName() {
    return movieName;
  }

}
