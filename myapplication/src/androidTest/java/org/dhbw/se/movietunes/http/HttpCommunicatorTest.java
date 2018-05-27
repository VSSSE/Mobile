package org.dhbw.se.movietunes.http;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;


@RunWith(AndroidJUnit4.class)
public class HttpCommunicatorTest {

    @Test
    public void testExecuteRequest() throws IOException {
        Request request = new Request.Builder()
                .url("http://www.google.de")
                .build();
        Response response = HttpCommunication.executeRequest(request);

        String bodyString = response.body().string();

        Assert.assertTrue(bodyString.contains("http://www.google.de/"));

    }

}
