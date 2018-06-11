package org.dhbw.movietunes.model;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class Movie {
  private String movieUri;
  private String movieTitle;

  public Movie(String movieUri, String movieTitle) {
    this.movieUri = movieUri;
    this.movieTitle = movieTitle;
  }

  public String getMovieUri() {
    return movieUri;
  }

  public String getMovieTitle() {
    return movieTitle;
  }

}
