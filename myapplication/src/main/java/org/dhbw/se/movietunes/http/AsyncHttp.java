package org.dhbw.se.movietunes.http;

import android.os.AsyncTask;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class AsyncHttp extends AsyncTask<String, Integer, String> {
  private final static Logger LOGGER = Logger.getLogger(AsyncHttp.class.getName());

  protected String doInBackground(String... params) {
    try {
      OkHttpClient client = new OkHttpClient();
      Request request = new Request.Builder()
              .url("http://www.google.de")
              .build();
      Response response = client.newCall(request).execute();
      return response.body().string();
    } catch (IOException e) {
      LOGGER.log(Level.WARNING, "SearchByTitle", e);
      return "ERROR";
    }
  }
}