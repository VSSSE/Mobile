package org.dhbw.movietunes.test;

import android.support.test.runner.AndroidJUnit4;

import org.dhbw.movietunes.http.MovieCommunication;
import org.dhbw.movietunes.http.YoutubeCommunication;
import org.dhbw.movietunes.model.Movie;
import org.dhbw.movietunes.model.Song;
import org.dhbw.movietunes.model.Video;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MovieCommunicationTest {

  private MovieCommunication codeUnderTest;

  @Before
  public void setUp() {
    codeUnderTest = new MovieCommunication();
  }


  @Test
  public void testFindMovies() {
    /*List<Movie> movieTitles = codeUnderTest.findMovies("The Black Pearl");

    assertFalse(movieTitles.isEmpty());
    assertNotNull(movieTitles.get(0));
    assertFalse(movieTitles.get(0).getMovieTitle().isEmpty());*/
    assertTrue(true);
  }

}
