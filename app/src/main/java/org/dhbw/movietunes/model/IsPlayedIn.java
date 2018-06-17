package org.dhbw.movietunes.model;

import android.database.Cursor;
import android.provider.BaseColumns;
import java.nio.charset.Charset;

public class IsPlayedIn implements BaseColumns {

  public static final String _TabellenName = "IsPlayedIn";
  public static final String _SongName = "songName";
  public static final String _MovieName = "movieName";
  private String songName;
  private String movieName;

  public IsPlayedIn(String songName, String movieName) {
    this.songName = songName;
    this.movieName = movieName;
  }

  public IsPlayedIn(Cursor cursor) {
    this.songName = cursor.getString(cursor.getColumnIndexOrThrow(_SongName));
    this.movieName = cursor.getString(cursor.getColumnIndexOrThrow(_MovieName));
  }


  public String getMovieName() {
    return movieName;
  }

}
