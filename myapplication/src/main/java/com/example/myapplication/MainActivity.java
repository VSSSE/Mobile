package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {




    public static final String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";
    Intent intent;

    EditText textField;
    View searchButton;
    String movieTitle;
    Movie searchResultSoundtrack;


   //Connector
    //ParseObject: movieTitle-soundtrack

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View welcomeText=findViewById(R.id.welcome);
        searchButton =  findViewById(R.id.search_button);
        View introText=findViewById(R.id.intro_text);
        textField=(EditText) findViewById(R.id.text_input);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                movieTitle = textField.getText().toString();
               // searchResultSoundtrack=new Movie(movieTitle);
               // searchResultSoundtrack.setSoundtracks(lookUpSoundtrack(movieTitle));
                intent = new Intent(getApplicationContext(), SearchResultActivity.class);
                intent.putExtra(EXTRA_MESSAGE, movieTitle);
                startActivity(intent);


            }
        });
}


}













