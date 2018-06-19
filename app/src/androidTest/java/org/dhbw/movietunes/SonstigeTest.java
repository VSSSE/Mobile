package org.dhbw.movietunes;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.test.mock.MockContext;
import android.widget.ImageView;

import java.util.ConcurrentModificationException;
import java.util.List;

import org.dhbw.movietunes.controller.SearchMovieSoundtracksController;
import org.dhbw.movietunes.exception.ExtractorException;
import org.dhbw.movietunes.exception.HttpException;
import org.dhbw.movietunes.http.ImageLoader;
import org.dhbw.movietunes.http.SpotifyCommunication;
import org.dhbw.movietunes.model.IsPlayedIn;
import org.dhbw.movietunes.model.IsSimilarTo;
import org.dhbw.movietunes.model.Song;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class SonstigeTest extends AndroidTestCase {


    @Before
    public void setUp() {

    }

    @Test
    public void testIsSimilarTo() {
        IsSimilarTo isTo = new IsSimilarTo("Is","To");
        assertTrue(isTo.getIsId() == "Is");
        assertTrue(isTo.getToId() == "To");

    }

    @Test
    public void testIsPlayedIn() {

        IsPlayedIn isIn = new IsPlayedIn("songtitle","movietitle");
        assertTrue(isIn.getMovieName() == "movietitle");
        assertTrue(isIn.getSongName() == "songtitle");
    }


    @Test
    public void testException() {
        ExtractorException ex = new ExtractorException("Test");
        HttpException ex2 = new HttpException("Test");
        assertTrue(ex.getMessage() == "Test");
        assertTrue(ex2.getMessage() == "Test");

    }


}
