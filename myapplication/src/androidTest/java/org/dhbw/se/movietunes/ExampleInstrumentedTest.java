package org.dhbw.se.movietunes;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.dhbw.se.movietunes.controller.SearchByTitleController;

import org.dhbw.se.movietunes.model.Song;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.myapplication", appContext.getPackageName());
    }

    @Test
    public void testSearchByTitle() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        SearchByTitleController c = new SearchByTitleController(appContext);

        SoundtrackSearchResult songs = c.searchTracklist("aaaa");

        assertTrue(songs.getSongs().size() > 0 );
        assertTrue (songs.getSongs().get(1) != null);

    }
}
