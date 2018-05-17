package com.example.myapplication.http;

import android.os.AsyncTask;


import org.dhbw.se.movietunes.model.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


class HttpAsyncTask extends AsyncTask<Request, Integer, Response> {
    protected Response doInBackground(Request... params) {
        try {
            if (params.length != 1) {
                throw new RuntimeException("only 1 request expected");
            }
            Request req = params[0];

            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(req).execute();
            return response;
        } catch (IOException e) {
            throw new RuntimeException("HTTP request failed", e);
        }
    }
}

public class HttpCommunication {
    public static Response executeRequest(Request request) {
        HttpAsyncTask httpTask = new HttpAsyncTask();
        httpTask.execute(request);
        Response response = null;
        try {
            response = httpTask.get();
        } catch (Exception e) {
            throw new RuntimeException("Sync request failed", e);
        }

        return response;
    }

}
