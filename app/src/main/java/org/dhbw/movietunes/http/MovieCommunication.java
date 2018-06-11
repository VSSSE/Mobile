package org.dhbw.movietunes.http;

import java.util.List;
import okhttp3.Request;
import org.dhbw.movietunes.exception.HttpException;
import org.dhbw.movietunes.extract.Extractor;
import org.dhbw.movietunes.model.Movie;

public class MovieCommunication {

  private Extractor extractor;

  public MovieCommunication() {
    extractor = new Extractor();
  }

  private String getHtmlForSearchMovie(String searchString) {
    Request request = new Request.Builder()
            .url("http://www.soundtrackcollector.com/catalog/search.php?searchon=track&searchtext=" + searchString)
            .get()
            .build();

    String responseBody = HttpCommunication.executeRequest(request);
    if (responseBody == null || responseBody.isEmpty()) {
      throw new HttpException("Response body for search is empty!");
    }
    return responseBody;
  }

  public List<Movie> findMovies(String searchString) {
    return extractor.getMovies(getHtmlForSearchMovie(searchString));
  }

}

