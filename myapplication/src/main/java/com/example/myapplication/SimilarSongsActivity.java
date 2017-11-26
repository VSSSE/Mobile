package com.example.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;



/**
 * Created by anastasia.schwed on 11/21/2017.
 */

public class SimilarSongsActivity extends ListActivity{




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();

        setContentView(R.layout.similar_songs);
        View intro = findViewById(R.id.similar);

    }
}
