package org.dhbw.movietunes.model;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class Movie {
  private String movieId;
  private String movieTitle;

  public Movie(String movieId, String movieTitle) {
    this.movieId = movieId;
    this.movieTitle = movieTitle;
  }

  public String getMovieId() {
    return movieId;
  }

  public String getMovieTitle() {
    return movieTitle;
  }

}
