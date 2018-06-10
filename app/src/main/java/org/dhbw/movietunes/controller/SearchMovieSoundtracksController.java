package org.dhbw.movietunes.controller;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.List;
import org.dhbw.movietunes.R;
import org.dhbw.movietunes.ResultMovieSoundtracksActivity;
import org.dhbw.movietunes.exception.HttpException;
import org.dhbw.movietunes.http.SpotifyCommunication;
import org.dhbw.movietunes.model.Song;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class SearchMovieSoundtracksController extends AsyncTask<String, Integer, List<Song>> {

  private ResultMovieSoundtracksActivity activity;

  public SearchMovieSoundtracksController(ResultMovieSoundtracksActivity activity) {
    this.activity = activity;
  }

  @Override
  protected List<Song>  doInBackground(String... params) {
    if (params.length != 1) {
      throw new HttpException("Expected 1 prameter, got " + params.length);
    }
    SpotifyCommunication spotifyCommunication = new SpotifyCommunication();

    return spotifyCommunication.findSoundtracks(params[0]);
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
    ListView list = activity.findViewById(R.id.soundtrack_list_view);
    list.setAdapter(adapter);
  }

}
