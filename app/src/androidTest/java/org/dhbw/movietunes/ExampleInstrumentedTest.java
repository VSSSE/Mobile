package org.dhbw.movietunes;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import java.util.List;
import org.dhbw.movietunes.http.SpotifyCommunication;
import org.dhbw.movietunes.list.SoundtrackSearchResult;
import org.dhbw.movietunes.model.PlaylistKey;
import org.dhbw.movietunes.model.Song;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
  @Test
  public void useAppContext() {
    // Context of the app under test.
    Context appContext = InstrumentationRegistry.getTargetContext();

    assertEquals("org.dhbw.movietunes", appContext.getPackageName());
  }

  @Test
  public void testSearchByTitle() {
    // Context of the app under test.
    SpotifyCommunication c = new SpotifyCommunication();

    //searching for song "titanic"
    PlaylistKey playlistKey = c.findPlaylist("titanic");

    String url = playlistKey.getSpotifyUrl();
    List<Song> songList = c.getSongsFromPlaylist(playlistKey);

    SoundtrackSearchResult songs = new SoundtrackSearchResult(url, songList);

    assertTrue(!songs.getSongs().isEmpty());
    assertTrue(songs.getSongs().get(1) != null);

  }
}
