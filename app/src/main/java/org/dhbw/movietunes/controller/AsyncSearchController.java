package org.dhbw.movietunes.controller;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import java.nio.charset.Charset;
import java.util.List;
import java.util.logging.Logger;
import org.dhbw.movietunes.R;
import org.dhbw.movietunes.exception.HttpException;
import org.dhbw.movietunes.http.HttpCommunication;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public abstract class AsyncSearchController extends AsyncTask<String, Integer, Boolean> {
  protected Activity activity;

  protected static final Logger LOGGER = Logger.getLogger(AsyncSearchController.class.getName());
  protected static final Charset UTF8_CHARSET = Charset.forName("UTF-8");

  public AsyncSearchController(Activity activity) {
    this.activity = activity;
  }

  @Override
  protected Boolean doInBackground(String... params) {
    if (params.length != 1) {
      throw new HttpException("Expected 1 prameter, got " + params.length);
    }

    return search(params[0]);
  }

  protected abstract Boolean search(String searchString);

  @Override
  protected void onPreExecute() {
    ProgressBar bar = activity.findViewById(R.id.progressSearch);
    bar.setVisibility(View.VISIBLE);
  }

  @Override
  protected void onProgressUpdate(Integer... values) {

  }

  @Override
  protected void onPostExecute(Boolean result) {
    ProgressBar bar = activity.findViewById(R.id.progressSearch);
    bar.setVisibility(View.GONE);

    postResult(result);
  }

  protected abstract void postResult(Boolean result);

}

