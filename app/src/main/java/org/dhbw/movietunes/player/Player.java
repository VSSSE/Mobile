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
    if (params.length != 2) {
      throw new HttpException("Expected 2 prameters, got " + params.length);
    }
    return createUri(params[0], params[1]);
  }

  protected abstract String createUri(String searchString, String searchString2);

  @Override
  protected void onPostExecute(String result) {
    if (result != null) {
      activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(result)));
    }
  }

}
