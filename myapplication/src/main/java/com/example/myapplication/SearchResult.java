package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.myapplication.R.id.intro_text;
import static com.example.myapplication.R.id.search_result;

public class SearchResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        TextView intro = (TextView) findViewById(intro_text);


            Intent intent = getIntent();
            String message = intent.getDataString();

            // Capture the layout's TextView and set the string as its text
            TextView searchResult = (TextView) findViewById(search_result);
            searchResult.setText(message);
        }
    }

