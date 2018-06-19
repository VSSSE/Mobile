package org.dhbw.movietunes.http;

import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.dhbw.movietunes.exception.HttpException;

public class HttpCommunication {

  private static final Logger LOGGER = Logger.getLogger(HttpCommunication.class.getName());

  private HttpCommunication() {

  }

  public static String executeRequest(Request request) {
    LOGGER.log(Level.INFO, "Request: " + request.toString());

    String response;
    try {
      Response re =  new OkHttpClient().newCall(request).execute();
      response =    re.body().string();

    } catch (Exception e) {
      throw new HttpException("Http Request faild!", e);
    }

    LOGGER.log(Level.INFO, "Response: " + response);
    return response;
  }

}
