package org.dhbw.movietunes.model;

import android.database.Cursor;
import android.provider.BaseColumns;
import java.nio.charset.Charset;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class Movie implements BaseColumns {
  public static final String _TabellenName = "Movie";
  public static final String _MovieUri = "movieUri";
  public static final String _MovieTitle = "movieTitle";
  private String movieUri;
  private String movieTitle;

  public Movie(String movieUri, String movieTitle) {
    this.movieUri = movieUri;
    this.movieTitle = movieTitle;
  }
  public Movie(Cursor cursor) {
    Charset UTF8_CHARSET = Charset.forName("UTF-8");
    this.movieUri = new String(cursor.getBlob(cursor.getColumnIndexOrThrow(_MovieUri)), UTF8_CHARSET);
    this.movieTitle = new String(cursor.getBlob(cursor.getColumnIndexOrThrow(_MovieTitle)), UTF8_CHARSET);
  }
  public String getMovieUri() {
    return movieUri;
  }

  public String getMovieTitle() {
    return movieTitle;
  }

}
