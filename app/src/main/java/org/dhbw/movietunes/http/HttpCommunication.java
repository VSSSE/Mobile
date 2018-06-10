package org.dhbw.movietunes.http;

import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.dhbw.movietunes.exception.HttpException;

public class HttpCommunication {

  private static final Logger LOGGER = Logger.getLogger(HttpCommunication.class.getName());

  private HttpCommunication() {

  }

  public static String executeRequest(Request request) {
    LOGGER.log(Level.INFO, "Request: {0}", request.toString());

    String response;
    try {
      response = new OkHttpClient().newCall(request).execute().body().string();
    } catch (Exception e) {
      throw new HttpException("Http Request faild!", e);
    }

    LOGGER.log(Level.INFO, "Response: {0}", response);
    return response;
  }

}
