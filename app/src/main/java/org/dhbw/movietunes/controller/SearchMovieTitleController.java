package org.dhbw.movietunes.controller;

import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import org.dhbw.movietunes.R;
import org.dhbw.movietunes.ResultMovieTitleActivity;
import org.dhbw.movietunes.ResultSimilarSongsActivity;
import org.dhbw.movietunes.http.MovieCommunication;
import org.dhbw.movietunes.list.MovieAdapter;
import org.dhbw.movietunes.model.Movie;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class SearchMovieTitleController extends AsyncSearchController {

  public SearchMovieTitleController(ResultMovieTitleActivity activity) {
    super(activity);
  }

  @Override
  protected Boolean search(String searchString) {
    MovieCommunication movieCommunication = new MovieCommunication();

    return new ArrayList<Object>(movieCommunication.findMovies(searchString));
  }

  @Override
  protected void postResult(Boolean result) {
    if(result) {
      ((ResultMovieTitleActivity)activity).updateList();
    }
  }

}

