package org.dhbw.movietunes.utils;

import android.app.Activity;
import android.content.Intent;
import java.io.InputStream;
import java.io.OutputStream;
import org.dhbw.movietunes.R;

public class Utils {
  public static void CopyStream(InputStream is, OutputStream os) {
    byte[] buffer = new byte[1024];
    int bufferLength = 0;

    try {
      while ((bufferLength = is.read(buffer)) > 0) {
        os.write(buffer, 0, bufferLength);
      }

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void ShareText(Activity activity, String text) {

    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
    sharingIntent.setType("text/plain");
    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Share Movie found on Movie tunes");
    sharingIntent.putExtra(Intent.EXTRA_TEXT, text);
    activity.startActivity(Intent.createChooser(sharingIntent, activity.getResources().getString(R.string.share_using)));
  }


}