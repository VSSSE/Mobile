package com.example.myapplication.http;

import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SpotifyCommunicationTest {

    @Test
    public void testFetchToken() {
        String token = SpotifyCommunication.fetchToken();
        Assert.assertTrue(token.length() > 20);
    }


}
