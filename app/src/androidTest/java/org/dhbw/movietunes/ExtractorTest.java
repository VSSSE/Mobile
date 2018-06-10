package org.dhbw.movietunes;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.dhbw.movietunes.exception.ExtractorException;
import org.dhbw.movietunes.extract.Extractor;
import org.dhbw.movietunes.model.PlaylistKey;
import org.dhbw.movietunes.model.Song;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

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

    PlaylistKey excpected = new PlaylistKey(
            "revinjan28",
            "6SNgCWsYUA9JSt9XWhU9JM",
            "Terminator Theme song",
            "https://open.spotify.com/user/revinjan28/playlist/6SNgCWsYUA9JSt9XWhU9JM"
    );

    assertTrue(excpected.equalsTo(playlist));
  }

  @Test
  public void testGetSongsFromPlaylist() {
    String responseString = readStringFromFile(R.raw.get_tracks_of_playlist);
    ArrayList<Song> result = new ArrayList<>(codeUnderTest.getSongsFromPlaylist(responseString));

    ArrayList<Song> excpected = new ArrayList<>();
    excpected.add(new Song(
            "24TPOx1PUEl8sJIVnMMs0m",
            "The Terminator Theme - Extended Version",
            "Brad Fiedel",
            "4:20",
            "https://open.spotify.com/track/24TPOx1PUEl8sJIVnMMs0m",
            "https://i.scdn.co/image/a7db5a11960241544bb92a3b7ebcb06aab540861"
    ));
    excpected.add(new Song(
            "7aZn38ep1wLgOHyiRQ212o",
            "Terminator 2 (\"From Terminator\")",
            "Movie Themes Studio",
            "1:57",
            "https://open.spotify.com/track/7aZn38ep1wLgOHyiRQ212o",
            "https://i.scdn.co/image/ca32b21b56dfd3da1961709b557983cc7648ed99"
    ));
    assertEquals(excpected.size(), result.size());
    for (int i = 0; i < excpected.size(); i++) {
      assertTrue(excpected.get(i).equalsTo(result.get(i)));
    }
  }

  @Test
  public void testGetRecommendedSongs() {
    String responseString = readStringFromFile(R.raw.get_recomations);
    ArrayList<Song> result = new ArrayList<>(codeUnderTest.getRecommendedSongs(responseString));

    Song excpected0 = new Song(
            "5HcDgTNMMjSG3VrpTiP940",
            "Will Exploring",
            "Christopher Lennertz",
            "5:4",
            "https://open.spotify.com/track/5HcDgTNMMjSG3VrpTiP940",
            "https://i.scdn.co/image/f91f46d0fd648c7f3e3c4e9c3b223a397e22b062"
    );
    Song excpected19 = new Song(
            "5MlY3mwE2eQlmaYHONLTaO",
            "Forth Eorlingas",
            "Howard Shore, Ben Del Maestro",
            "3:15",
            "https://open.spotify.com/track/5MlY3mwE2eQlmaYHONLTaO",
            "https://i.scdn.co/image/29fc60dc9d2bf7e29dbdc985141a4a45711d5456"
    );

    assertEquals(20, result.size());
    assertTrue(result.get(0).equalsTo(excpected0));
    assertTrue(result.get(19).equalsTo(excpected19));
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
