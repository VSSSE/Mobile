package org.dhbw.movietunes.controller;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.List;
import org.dhbw.movietunes.R;
import org.dhbw.movietunes.ResultMovieTitleActivity;
import org.dhbw.movietunes.ResultSimilarSongsActivity;
import org.dhbw.movietunes.exception.HttpException;
import org.dhbw.movietunes.http.MovieCommunication;
import org.dhbw.movietunes.http.SpotifyCommunication;
import org.dhbw.movietunes.list.SongAdapter;
import org.dhbw.movietunes.model.Song;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class SearchMovieTitleController extends AsyncSearchController {

  public SearchMovieTitleController(ResultMovieTitleActivity activity) {
    super(activity);
  }

  @Override
  protected List<Object> search(String searchString) {
    MovieCommunication movieCommunication = new MovieCommunication();

    return new ArrayList<Object>(movieCommunication.findMovies(searchString));
  }

  @Override
  protected void postResult(List<Object> result) {

    /*MovieAdapter adapter = new MovieAdapter(activity, (ArrayList<Movie>)(List)(result));
    ListView list = activity.findViewById(R.id.movie_list_view);
    list.setAdapter(adapter);*/
  }

}

