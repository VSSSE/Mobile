package org.dhbw.movietunes.controller;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.List;
import org.dhbw.movietunes.R;
import org.dhbw.movietunes.ResultSimilarSongsActivity;
import org.dhbw.movietunes.exception.HttpException;
import org.dhbw.movietunes.http.SpotifyCommunication;
import org.dhbw.movietunes.list.MovieSoundtracksAdapter;
import org.dhbw.movietunes.model.Song;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class SearchSimilarSongsController extends AsyncTask<String, Integer, List<Song>> {
  private ResultSimilarSongsActivity activity;

  public SearchSimilarSongsController(ResultSimilarSongsActivity activity) {
    this.activity = activity;
  }

  @Override
  protected List<Song> doInBackground(String... params) {
    if (params.length != 1) {
      throw new HttpException("Expected 1 prameter, got " + params.length);
    }
    SpotifyCommunication spotifyCommunication = new SpotifyCommunication();

    return spotifyCommunication.getRecommendations(params[0]);
  }

  @Override
  protected void onPreExecute() {
    ProgressBar bar = activity.findViewById(R.id.progressSearch);
    bar.setVisibility(View.VISIBLE);
  }

  @Override
  protected void onProgressUpdate(Integer... values) {

  }

  @Override
  protected void onPostExecute(List<Song> result) {
    ProgressBar bar = activity.findViewById(R.id.progressSearch);
    bar.setVisibility(View.GONE);

    MovieSoundtracksAdapter adapter = new MovieSoundtracksAdapter(activity, new ArrayList<>(result));
    ListView list = activity.findViewById(R.id.similarSongs_list_view);
    list.setAdapter(adapter);
  }

}

