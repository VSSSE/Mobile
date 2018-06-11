package org.dhbw.movietunes.http;

import okhttp3.Request;
import org.dhbw.movietunes.exception.HttpException;
import org.dhbw.movietunes.extract.Extractor;
import org.dhbw.movietunes.model.Video;

public class YoutubeCommunication {

  private static final String TOKEN = "AIzaSyCWI8woBcBxJBxakviA2c3FWlBz_IyIlko";

  private Extractor extractor;

  public YoutubeCommunication() {
    extractor = new Extractor();
  }

  private String getJsonForSearchVideo(String searchString) {
    Request request = new Request.Builder()
            .url("https://www.googleapis.com/youtube/v3/search?part=snippet&type=video&key=" + TOKEN + "&q=" + searchString)
            .get()
            .build();

    String responseBody = HttpCommunication.executeRequest(request);
    if (responseBody == null || responseBody.isEmpty()) {
      throw new HttpException("Response body for search is empty!");
    }
    return responseBody;
  }

  public Video findFirstVideo(String searchString) {
    return extractor.getFirstVideo(getJsonForSearchVideo(searchString));
  }

}

