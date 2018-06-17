package org.dhbw.movietunes.model;

import android.database.Cursor;
import android.provider.BaseColumns;
import java.nio.charset.Charset;

public class IsSimilarTo implements BaseColumns {

  public static final String _TabellenName = "IsSimilarTo";
  public static final String _IsName = "isName";
  public static final String _ToName = "toName";
  private String isName;    //this song is similar to toId
  private String toName;

  public IsSimilarTo(String isName, String toId) {
    this.isName = isName;
    this.toName = toId;
  }
  public IsSimilarTo(Cursor cursor) {
    this.isName = cursor.getString(cursor.getColumnIndexOrThrow(_IsName));
    this.toName = cursor.getString(cursor.getColumnIndexOrThrow(_ToName));
  }
  public String getIsId() {
    return isName;
  }

  public String getToId() {
    return toName;
  }
}
