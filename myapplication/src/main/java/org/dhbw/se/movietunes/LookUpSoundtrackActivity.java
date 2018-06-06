package org.dhbw.se.movietunes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.myapplication.R;

public class LookUpSoundtrackActivity extends AppCompatActivity {

  public static final String EXTRA_MESSAGE = "org.dhbw.se.movietunes.LookUpSoundtrack";

  EditText textField;
  Button searchButton;
  TextView welcomeText;
  TextView introText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.look_up_soundtrack_activity);

    welcomeText = findViewById(R.id.welcome);
    searchButton = findViewById(R.id.search_button);
    introText = findViewById(R.id.intro_text);
    textField = findViewById(R.id.text_input);
  }

  public void onClickLookUpSoundtracks(View v) {
    String movieTitle = textField.getText().toString();

    Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
    intent.putExtra(EXTRA_MESSAGE, movieTitle);
    startActivity(intent);
  }
}









