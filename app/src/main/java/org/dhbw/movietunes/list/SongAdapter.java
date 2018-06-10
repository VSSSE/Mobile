package org.dhbw.movietunes.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import java.util.ArrayList;
import org.dhbw.movietunes.R;
import org.dhbw.movietunes.ResultMovieSoundtracksActivity;
import org.dhbw.movietunes.ResultSimilarSongsActivity;
import org.dhbw.movietunes.SearchMovieTitlesActivity;
import org.dhbw.movietunes.http.ImageLoader;
import org.dhbw.movietunes.model.Song;
import org.dhbw.movietunes.player.SpotifyPlayer;
import org.dhbw.movietunes.player.YoutubePlayer;

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

    TextView title = vi.findViewById(R.id.title); // title
    TextView artist = vi.findViewById(R.id.artist); // artist name
    TextView duration = vi.findViewById(R.id.duration); // duration
    ImageView thumb_image = vi.findViewById(R.id.list_image); // thumb image

    final Song song = data.get(position);

    // Setting all values in listview
    title.setText(song.getSongTitle());
    artist.setText(song.getArtist());
    duration.setText(song.getDuration());
    imageLoader.DisplayImage(song.getImageUri(), thumb_image);


    if(activity.getClass() == ResultSimilarSongsActivity.class) {
      vi.setOnClickListener(new SimilarSongsListener(activity, song));
    } else if(activity.getClass() == ResultMovieSoundtracksActivity.class) {
      vi.setOnClickListener(new MovieSoundtracksListener(activity, song));
    }
    return vi;
  }


}