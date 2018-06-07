package org.dhbw.se.movietunes.http;

import android.os.AsyncTask;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpAsyncTask extends AsyncTask<Request, Integer, Response> {

  @Override
  protected Response doInBackground(Request... params) {
    try {
      if (params.length != 1) {
        throw new HttpException("Only 1 prameter expected");
      }

      Request req = params[0];

      OkHttpClient client = new OkHttpClient();
      return client.newCall(req).execute();

    } catch (IOException e) {
      throw new HttpException("HTTP request failed", e);
    }
  }
}