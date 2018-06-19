package org.dhbw.movietunes.test;

import android.support.test.runner.AndroidJUnit4;
import org.dhbw.movietunes.http.YoutubeCommunication;
import org.dhbw.movietunes.model.Video;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class YoutubeCommunicationTest {

  private YoutubeCommunication codeUnderTest;

  @Before
  public void setUp() {
    codeUnderTest = new YoutubeCommunication();
  }

  @Test
  public void testFindSoundtracks() {
    Video thatVideo = codeUnderTest.findFirstVideo("Terminator");

    assertNotNull(thatVideo);
    assertFalse(thatVideo.getVideoID().isEmpty());
    assertFalse(thatVideo.getTitle().isEmpty());
  }

}
