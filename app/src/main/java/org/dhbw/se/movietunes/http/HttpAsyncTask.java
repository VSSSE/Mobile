package org.dhbw.se.movietunes.http;

import android.os.AsyncTask;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpAsyncTask extends AsyncTask<Request, Integer, Response> {

  private static final Logger LOGGER = Logger.getLogger(HttpAsyncTask.class.getName());

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
      LOGGER.log(Level.WARNING, "Failed to send Request: " + params[0].toString());
      throw new HttpException("Failed to send Request", e);
    }
  }
}