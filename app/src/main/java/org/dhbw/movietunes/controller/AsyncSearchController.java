package org.dhbw.movietunes.controller;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import java.util.List;
import org.dhbw.movietunes.R;
import org.dhbw.movietunes.exception.HttpException;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public abstract class AsyncSearchController extends AsyncTask<String, Integer, List<Object>> {
  protected Activity activity;

  public AsyncSearchController(Activity activity) {
    this.activity = activity;
  }

  @Override
  protected List<Object> doInBackground(String... params) {
    if (params.length != 1) {
      throw new HttpException("Expected 1 prameter, got " + params.length);
    }

    return search(params[0]);
  }

  protected abstract List<Object> search(String searchString);

  @Override
  protected void onPreExecute() {
    ProgressBar bar = activity.findViewById(R.id.progressSearch);
    bar.setVisibility(View.VISIBLE);
  }

  @Override
  protected void onProgressUpdate(Integer... values) {

  }

  @Override
  protected void onPostExecute(List<Object> result) {
    ProgressBar bar = activity.findViewById(R.id.progressSearch);
    bar.setVisibility(View.GONE);

    postResult(result);
  }

  protected abstract void postResult(List<Object> result);

}

