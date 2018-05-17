package com.example.myapplication.http;

import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SpotifyCommunicationTest {

    SpotifyCommunication classUnderTest = new SpotifyCommunication();

    @Test
    public void testFetchToken() {
        String token = classUnderTest.fetchToken();
        Assert.assertTrue(token.length() > 20);
    }

    @Test
    public void testGetPlaylistId() {
        String playlistId = classUnderTest.getPlaylistId("terminator");
        Assert.assertEquals("6lwDOP2ZW0h2jOccLB0342", playlistId);
    }



}
