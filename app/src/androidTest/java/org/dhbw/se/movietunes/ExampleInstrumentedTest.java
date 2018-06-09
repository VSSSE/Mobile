package org.dhbw.se.movietunes;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.dhbw.se.movietunes.controller.SearchByTitleController;
import org.dhbw.se.movietunes.model.SoundtrackSearchResult;
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
  public void useAppContext()  {
    // Context of the app under test.
    Context appContext = InstrumentationRegistry.getTargetContext();

    assertEquals("com.example.myapplication", appContext.getPackageName());
  }

  @Test
  public void testSearchByTitle()  {
    // Context of the app under test.
    SearchByTitleController c = new SearchByTitleController();

    //searching for song "titanic"
    SoundtrackSearchResult songs = c.searchTracklist("titanic");

    assertTrue(!songs.getSongs().isEmpty());
    assertTrue(songs.getSongs().get(1) != null);

  }
}
