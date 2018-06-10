package org.dhbw.movietunes;

import android.support.test.runner.AndroidJUnit4;
import java.util.List;
import org.dhbw.movietunes.http.SpotifyCommunication;
import org.dhbw.movietunes.model.Song;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class SpotifyCommunicationTest {

  private SpotifyCommunication codeUnderTest;

  @Before
  public void setUp() {
    codeUnderTest = new SpotifyCommunication();
  }

  @Test
  public void testFindSoundtracks() {
    List<Song> soundtracks = codeUnderTest.findSoundtracks("Terminator");

    assertFalse(soundtracks.isEmpty());
    assertNotNull(soundtracks.get(0));
    assertFalse(soundtracks.get(0).getSongTitle().isEmpty());
  }

  @Test
  public void testGetRecommendations() {
    List<Song> recomendetSongs = codeUnderTest.getRecommendations("24TPOx1PUEl8sJIVnMMs0m");

    assertFalse(recomendetSongs.isEmpty());
    assertNotNull(recomendetSongs.get(0));
    assertFalse(recomendetSongs.get(0).getSongTitle().isEmpty());
  }

}
