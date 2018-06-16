package org.dhbw.movietunes.model;

import android.provider.BaseColumns;

public class IsSimilarTo implements BaseColumns {

  public static final String _TabellenName = "IsSimilarTo";
  public static final String _IsId = "isId";
  public static final String _ToId = "toId";
  private String isId;    //this song is similar to toId
  private String toId;

  public IsSimilarTo(String isId, String toId) {
    this.isId = isId;
    this.toId = toId;
  }

  public String getIsId() {
    return isId;
  }

  public String getToId() {
    return toId;
  }
}
