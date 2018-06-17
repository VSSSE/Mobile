package org.dhbw.movietunes.model;

import android.database.Cursor;
import android.provider.BaseColumns;

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
    this.movieUri = cursor.getString(cursor.getColumnIndexOrThrow(_MovieUri));
    this.movieTitle = cursor.getString(cursor.getColumnIndexOrThrow(_MovieTitle));
  }

  public String getMovieUri() {
    return movieUri;
  }

  public String getMovieTitle() {
    return movieTitle;
  }

}
