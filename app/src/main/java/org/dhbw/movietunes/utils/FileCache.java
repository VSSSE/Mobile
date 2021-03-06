package org.dhbw.movietunes.utils;

import android.content.Context;
import java.io.File;

public class FileCache {

  private File cacheDir;

  public FileCache(Context context) {
    //Find the dir to save cached images
    if (android.os.Environment.getExternalStorageState()
            .equals(android.os.Environment.MEDIA_MOUNTED)) {
      cacheDir = new File(context.getCacheDir(), "Movie_Tunes"); //android.os.Environment.getExternalStorageDirectory()
    } else {
      cacheDir = context.getCacheDir();
    }

    if (!cacheDir.exists()) {
      cacheDir.mkdirs();
    }
  }

  public File getFile(String url) {
    String filename = String.valueOf(url.hashCode()); //maybe URLEncoder.encode(url)
    return new File(cacheDir, filename);

  }

  public void clear() {
    File[] files = cacheDir.listFiles();
    if (files == null) {
      return;
    }
    for (File f : files) {
      f.delete();
    }
  }

}