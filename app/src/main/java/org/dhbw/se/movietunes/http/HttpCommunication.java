package org.dhbw.se.movietunes.http;

import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.Request;
import okhttp3.Response;

public class HttpCommunication {

  private static final Logger LOGGER = Logger.getLogger(HttpCommunication.class.getName());

  public static Response executeRequest(Request request) {
    HttpAsyncTask httpTask = new HttpAsyncTask();
    LOGGER.log(Level.INFO, "Request: " + request.toString());
    httpTask.execute(request);
    Response response;

    try {
      response = httpTask.get();

      LOGGER.log(Level.INFO, "Response: " + response.body().string());
    } catch (Exception e) {
      LOGGER.log(Level.INFO, "Got no response from AsyncTask!");
      throw new HttpException("\"Got no response from AsyncTask!", e);
    }

    return response;
  }

}
