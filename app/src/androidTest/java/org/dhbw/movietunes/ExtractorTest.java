package org.dhbw.movietunes;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.dhbw.movietunes.exception.ExtractorException;
import org.dhbw.movietunes.extract.Extractor;
import org.dhbw.movietunes.model.PlaylistKey;
import org.dhbw.movietunes.model.Song;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ExtractorTest {

  private Extractor codeUnderTest;

  @Before
  public void setUp() {
    codeUnderTest = new Extractor();
  }


  @Test
  public void testGetFirstPlaylist() {
    String responseString = readStringFromFile(R.raw.search_playlists);
    PlaylistKey playlist = codeUnderTest.getFirstPlaylist(responseString);


    assertEquals("6lwDOP2ZW0h2jOccLB0342", playlist);
  }

  @Test
  public void testGetSongsFromPlaylist() {
    String responseString = readStringFromFile(R.raw.get_tracks_of_playlist);
    ArrayList<Song> result = new ArrayList<>(codeUnderTest.getSongsFromPlaylist(responseString));

    assertEquals("moyomba", result);
  }

  @Test
  public void testGetRecommendedSongs() {
    String responseString = readStringFromFile(R.raw.get_recomations);
    ArrayList<Song> result = new ArrayList<>(codeUnderTest.getRecommendedSongs(responseString));

    assertEquals("moyomba", result);
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
      throw new ExtractorException("Could not readd File!", e);
    }

  }

}
