package org.dhbw.movietunes;

import android.support.test.runner.AndroidJUnit4;
import java.util.List;
import org.dhbw.movietunes.http.SpotifyCommunication;
import org.dhbw.movietunes.model.PlaylistKey;
import org.dhbw.movietunes.model.Song;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SpotifyCommunicationTest {

  private SpotifyCommunication classUnderTest = new SpotifyCommunication();

  @Test
  public void testFetchToken() {
    String token = classUnderTest.fetchToken();
    assertTrue(token.length() > 20);
  }

  @Test
  public void testFindPlaylist() {
    PlaylistKey playlistKey = classUnderTest.findPlaylist("terminator");
    assertFalse(playlistKey.getPlaylistId().isEmpty());
    assertFalse(playlistKey.getUserId().isEmpty());
  }

  @Test
  public void testGetSongsFromPlaylist() {
    PlaylistKey key = new PlaylistKey("moyomba", "6lwDOP2ZW0h2jOccLB0342", "");

    List<Song> songsFromPlaylist = classUnderTest.getSongsFromPlaylist(key);

    assertNotNull(songsFromPlaylist);
    assertFalse(songsFromPlaylist.isEmpty());
    assertNotNull(songsFromPlaylist.get(0));
  }

  @Test
  public void testGetRecommendations() {

    List<Song> songsFromPlaylist = classUnderTest.getRecommendations("0c6xIDDpzE81m2q797ordA");

    assertNotNull(songsFromPlaylist);
    assertFalse(songsFromPlaylist.isEmpty());
    assertNotNull(songsFromPlaylist.get(0));
  }

}
