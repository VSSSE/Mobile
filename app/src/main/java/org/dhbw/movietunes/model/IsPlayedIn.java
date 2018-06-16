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
    Charset UTF8_CHARSET = Charset.forName("UTF-8");
    this.songId = new String(cursor.getBlob(cursor.getColumnIndexOrThrow(_SongId)), UTF8_CHARSET);
    this.movieName = new String(cursor.getBlob(cursor.getColumnIndexOrThrow(_MovieName)), UTF8_CHARSET);
  }

  public String getSongId() {
    return songId;
  }

  public String getMovieName() {
    return movieName;
  }

}
