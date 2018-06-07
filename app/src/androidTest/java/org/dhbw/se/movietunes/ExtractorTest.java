package org.dhbw.se.movietunes;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.example.myapplication.R;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.dhbw.se.movietunes.extract.Extractor;
import org.dhbw.se.movietunes.model.Song;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ExtractorTest {

  Extractor codeUnderTest;

  @Before
  public void setUp() {
    codeUnderTest = new Extractor();
  }

  @Test
  public void testExtractPlaylistIdFromSearchResult() {
    String responseString = readStringFromFile(R.raw.test_result_search_track);

    String result = codeUnderTest.extractPlaylistIdFromSearchResult(responseString);

    assertEquals("6lwDOP2ZW0h2jOccLB0342", result);
  }

  @Test
  public void testExtractUserIdFromSearchResult() {
    String responseString = readStringFromFile(R.raw.test_result_search_track);
    String result = codeUnderTest.extractUserIdFromSearchResult(responseString);
    assertEquals("moyomba", result);
  }

    /*@Test
    public void testExtractArtistName(){
    fail();
    }*/

  @Test
  public void testExtractSongs() {

    List<Song> songs = codeUnderTest.extractSongsFromTracklistDetails(readStringFromFile(R.raw.test_tracklist_details_uri));
    assertNotNull(songs);
    assertTrue(songs.size() > 0);
    Song song = songs.get(0);

    assertEquals("The Terminator (Main Title)", song.getSongTitle());
    assertEquals("2:40", song.getDuration());
    assertEquals("Brad Fiedel", song.getSinger());
    assertEquals("6vIZTOdX8TPTRBqcloIsUz", song.getTrackId());
    assertEquals("spotify:track:6vIZTOdX8TPTRBqcloIsUz", song.getUri());
  }

  @Test
  public void testExtractSongsFromRecommendationsResponse() {

    List<Song> songs = codeUnderTest.extractSongsFromRecommendationsResponse(readStringFromFile(R.raw.test_recommendations_response));
    assertNotNull(songs);
    assertTrue(songs.size() > 0);
    Song song = songs.get(0);

    assertEquals("Tightrope", song.getSongTitle());
  }

  private String readStringFromFile(int resourceId) {
    try {
      Context appContext = InstrumentationRegistry.getTargetContext();
      InputStream in = appContext.getResources().openRawResource(resourceId);
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      StringBuilder result = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        result.append(line);
        result.append("\n");
      }
      reader.close();
      return result.toString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

}
