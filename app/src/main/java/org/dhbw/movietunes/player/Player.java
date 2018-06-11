package org.dhbw.movietunes.player;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import org.dhbw.movietunes.exception.HttpException;

public abstract class Player extends AsyncTask<String, Integer, String> {
  protected Activity activity;

  public Player(Activity activity) {
    this.activity = activity;
  }

  @Override
  protected String doInBackground(String... params) {
    if (params.length != 1) {
      throw new HttpException("Expected 1 prameter, got " + params.length);
    }
    return createUri(params[0]);
  }

  protected abstract String createUri(String searchString);

  @Override
  protected void onPostExecute(String result) {
    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(result)));
  }

}
