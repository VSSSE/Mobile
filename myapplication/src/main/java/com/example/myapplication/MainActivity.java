package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    final View introText=findViewById(R.id.intro_text);
    final EditText textField=(EditText) findViewById(R.id.text_input);
    final View searchButton = findViewById(R.id.search_button);
    public String movieTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                movieTitle = textField.getText().toString();
                Intent intent = new Intent(String.valueOf(this));

                intent.putExtra("Song 1, Song 2, Song 3", movieTitle);
                startActivity(intent);

            }
        });
}}













