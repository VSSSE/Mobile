package com.example.myapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.myapplication.controller.SearchByTitleController;

import com.example.myapplication.org.dhbw.se.movietunes.model.Song;

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

        List<Song > songs = c.lookupSoundtrack("aaaa");

        assertTrue(songs.size() == 5);

        assertTrue (songs.get(2) != null);

    }
}
