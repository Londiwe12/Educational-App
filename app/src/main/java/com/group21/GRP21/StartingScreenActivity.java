package com.group21.GRP21;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class StartingScreenActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_MODULE_ID = "extraModuleID";
    public static final String EXTRA_MODULE_NAME = "extraModuleName";
    public static final String EXTRA_DIFFICULTY = "extraDifficulty";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";
    private TextView textViewHighscore;
    private Spinner spinnerModule;
    private Spinner spinnerDifficulty;
    private int highscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);
        textViewHighscore = findViewById(R.id.text_view_highscore);
        spinnerModule = findViewById(R.id.spinner_module);
        spinnerDifficulty = findViewById(R.id.spinner_difficulty);
        loadModules();
        loadDifficultyLevels();
        loadHighscore();

        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.activity_menu_start,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_m:
                start_add_m();
                return true;
            case R.id.add_q:
                start_add_q();
                return true;
            case R.id.sign_out:
                start_sign_out();
                return true;
            case R.id.delete_q:
                start_del_q();
                return true;
            case R.id.delete_m:
                start_del();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startQuiz() {
        Module selectedModule = (Module) spinnerModule.getSelectedItem();
        int moduleID = selectedModule.getId();
        String moduleName = selectedModule.getName();
        String difficulty = spinnerDifficulty.getSelectedItem().toString();
        Intent intent = new Intent(StartingScreenActivity.this, MainActivity.class);
        intent.putExtra(EXTRA_MODULE_ID, moduleID);
        intent.putExtra(EXTRA_MODULE_NAME, moduleName);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }



    private void start_add_m() {
        Intent intent = new Intent(StartingScreenActivity.this, InsertModuleActivity.class);
        startActivity(intent);
    }

    private void start_add_q() {
        Intent intent = new Intent(StartingScreenActivity.this, InsertQuestionActivity.class);
        startActivity(intent);
    }

    private void start_sign_out() {
        Intent intent = new Intent(StartingScreenActivity.this, loginActivity.class);
        startActivity(intent);
    }

    private void start_del() {
        Intent intent = new Intent(StartingScreenActivity.this, DeleteModuleActivity.class);
        startActivity(intent);
    }
    private void start_del_q() {
        Intent intent = new Intent(StartingScreenActivity.this, DeleteQuestionActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(MainActivity.EXTRA_SCORE, 0);
                if (score > highscore) {
                    updateHighscore(score);
                }
            }
        }
    }
    private void loadModules() {
        DbCommands dbCommand = DbCommands.getInstance(this);
        List<Module> modules = dbCommand.getAllModules();
        ArrayAdapter<Module> adapterModules = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, modules);
        adapterModules.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModule.setAdapter(adapterModules);
    }
    private void loadDifficultyLevels() {
        String[] difficultyLevels = Question.getAllDifficultyLevels();
        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, difficultyLevels);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficulty);
    }
    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighscore.setText("Best score: " + highscore);
    }
    private void updateHighscore(int highscoreNew) {
        highscore = highscoreNew;
        textViewHighscore.setText("Best score: " + highscore);
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }
}