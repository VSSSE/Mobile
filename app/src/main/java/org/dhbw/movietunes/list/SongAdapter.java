package org.dhbw.movietunes.list;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import org.dhbw.movietunes.R;
import org.dhbw.movietunes.http.ImageLoader;
import org.dhbw.movietunes.model.Song;

public class SongAdapter extends BaseAdapter {

  private static LayoutInflater inflater = null;
  public ImageLoader imageLoader;
  private Activity activity;
  private ArrayList<Song> data;

  public SongAdapter(Activity a, ArrayList<Song> d) {
    activity = a;
    data = d;
    inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    imageLoader = new ImageLoader(activity.getApplicationContext());
  }

  public int getCount() {
    return data.size();
  }

  public Object getItem(int position) {
    return position;
  }

  public long getItemId(int position) {
    return position;
  }

  public View getView(int position, View convertView, ViewGroup parent) {
    View vi = convertView;
    if (convertView == null)
      vi = inflater.inflate(R.layout.list_row, null);

    TextView title = (TextView) vi.findViewById(R.id.title); // title
    TextView artist = (TextView) vi.findViewById(R.id.artist); // artist name
    TextView duration = (TextView) vi.findViewById(R.id.duration); // duration
    ImageView thumb_image = (ImageView) vi.findViewById(R.id.list_image); // thumb image

    Song song = data.get(position);

    // Setting all values in listview
    title.setText(song.getSongTitle());
    artist.setText(song.getSinger());
    duration.setText(song.getDuration());
    imageLoader.DisplayImage(song.getImageUri(), thumb_image);
    return vi;
  }
}