package org.dhbw.se.movietunes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.myapplication.R;

public class LookUpMoviesActivity extends AppCompatActivity {

  public static final String EXTRA_MESSAGE = "org.dhbw.se.movietunes.LookUpMovie";

  EditText textField;
  TextView introText;
  TextView welcomeText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.look_up_movies_activity);

    welcomeText = findViewById(R.id.welcome);
    introText = findViewById(R.id.intro_text);
    textField = findViewById(R.id.text_input);
  }

  public void onClickLookUpMovies(View v) {
    String songTitle = textField.getText().toString();

    Intent intent = new Intent(getApplicationContext(), MovieSearchResultActivity.class);
    intent.putExtra(EXTRA_MESSAGE, songTitle);
    startActivity(intent);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return true;
  }

}









