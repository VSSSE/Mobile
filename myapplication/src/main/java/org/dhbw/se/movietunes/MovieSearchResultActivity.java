package org.dhbw.se.movietunes;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.myapplication.R;

public class MovieSearchResultActivity extends MainActivity implements AdapterView.OnClickListener{
    TextView song;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();
        String songTitle = intent.getStringExtra(LookUpMoviesActivity.EXTRA_MESSAGE);
        setContentView(R.layout.movie_search_result);
        song = (TextView) findViewById(R.id.song);
        song.setText(songTitle);
    }

    @Override
    public void onClick(View v) {

    }
}
